package com.ArticleAnalyzer.Helper;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.*;

import com.ArticleAnalyzer.DataManagement.ArticleLoader;
import com.ArticleAnalyzer.DataManagement.Downloader;
import com.ArticleAnalyzer.DataManagement.Outputter;

/**
 * The Argparser class provides command line options for executing the ArticleAnalyzer project.
 */
public class Argparser {

    private Outputter outputter = null;
    private ArticleLoader articleLoader = null;
    private Boolean dataFromFile = null;
    private Downloader downloader = null;
    private String[] toExclude = null;
    private int toShow = -1;

    /**
     * Returns the Outputter object used for printing the output.
     *
     * @return the Outputter object
     */
    public Outputter getOutputter(){
        return outputter;
    }

    /**
     * Returns the array of words to exclude from the analysis.
     *
     * @return the array of words to exclude
     */
    public String[] getToExclude(){
        return toExclude;
    }

    /**
     * Returns the number of results to show.
     *
     * @return the number of results to show
     */
    public int getToShow(){
        return toShow;
    }

    /**
     * Returns the ArticleLoader object used for loading articles.
     *
     * @return the ArticleLoader object
     */
    public ArticleLoader getArticleLoader(){
        return articleLoader;
    }

    /**
     * Returns the Downloader object used for downloading articles.
     *
     * @return the Downloader object
     */
    public Downloader getDownloader(){
        return downloader;
    }

    /**
     * Returns if data are retrived from files
     * 
     * @return if data are retrived from files
     */
    public Boolean getDataFromFile(){
        return dataFromFile;
    }

    /**
     * Creates an Argparser object that provides various command line options and checks the validity of the given arguments.
     *
     * @param args the command line arguments
     * @throws FileNotFoundException if the required files are not specified
     * @throws IllegalArgumentException if the given arguments are invalid for different reasons
     * @throws IOException if there are errors when parsing the arguments
     */
    public Argparser(String[] args) throws FileNotFoundException, IllegalArgumentException, IOException{

        Options options = new Options();
        options.addOption("h", "help", false, "Possible actions");
        options.addOption("d", "data", true, "Method of obtaining articles (values admitted: file, download)");
        options.addOption("i", "inputFile", true, "File path which contains articles");
        options.addOption("c", "configurationFile", true, "File path which contains the download configuration");
        options.addOption("om", "outputMethod", true, "Method of printing the output (values admitted: C (console), F (file), CF (console e file))");
        options.addOption("o", "outputFile", true, "Output file path");
        options.addOption("e", "toExclude", true, "Words to exclude (example: \"an, have, the\")");
        options.addOption("s", "show", true, "Number of results to show");

        // parse the command line arguments
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            // access the options
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.setOptionComparator(null); //options in the order they were declared
                formatter.printHelp(150, "Article Analyzer", "", options, "");
                System.exit(0);
            }

            if(!cmd.hasOption("d")){
                throw new IllegalArgumentException("Argument data is required");
            }else{
                if(cmd.getOptionValue("d").equalsIgnoreCase("file")){
                dataFromFile = true;
                }else if(cmd.getOptionValue("d").equalsIgnoreCase("download")){
                dataFromFile = false;
                }else{
                    throw new IllegalArgumentException("Data argument invalid. Values admitted: file, download");
                } 
            }


            if(!dataFromFile && !cmd.hasOption("c")){
                throw new IllegalArgumentException("ConfigurationFile argument is required in order to download articles");
            }else if(dataFromFile){

            }else{
                downloader = new Downloader(cmd.getOptionValue("c"));
            }


            if(!cmd.hasOption("om")){
                throw new IllegalArgumentException("Output argument is required");
            }else{
                if(cmd.getOptionValue("om").equalsIgnoreCase("C")){
                    outputter = new Outputter(true, false);
                }else if(cmd.getOptionValue("om").equalsIgnoreCase("F")){
                    outputter = new Outputter(false, true);
                }else if(cmd.getOptionValue("om").equalsIgnoreCase("CF")){
                    outputter = new Outputter(true, true);
                }else{
                    throw new IllegalArgumentException("Output argument invalid. Values admitted: C, F, CF");
                } 
            }


            if(dataFromFile && !cmd.hasOption("i")){
                throw new IllegalArgumentException("InputFile argument is required if you want to work with articles saved in a file");
            }else if(!dataFromFile && !cmd.hasOption("i")){
                articleLoader = new ArticleLoader(downloader.getJSONoutput());
            }else{
                try{
                    articleLoader = new ArticleLoader(cmd.getOptionValue("i"));
                }catch(FileNotFoundException e){
                    throw new IllegalArgumentException("InputFile argument invalid. File not found");
                }
            }


            if(outputter.getToFile() && !cmd.hasOption("o")){
                throw new IllegalArgumentException("OutputFile is required if you want to print the output to a file");
            }else{
                outputter.setFile(cmd.getOptionValue("o"));
            }

            if(cmd.hasOption("s")){
                try{
                    toShow = Integer.parseInt(cmd.getOptionValue("s"));
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Show argument requires and integer number");
                }
            }

            if(cmd.hasOption("e")){
                String toToExclude = cmd.getOptionValue("e").replace("\"", "");
                toToExclude = toToExclude.replace(" ", "");
                toExclude = toToExclude.split(",");
            }

        }catch(ParseException e){
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("", options);
            System.exit(1);
        }
    }

}
