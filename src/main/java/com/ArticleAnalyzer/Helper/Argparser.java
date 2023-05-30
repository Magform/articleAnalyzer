package com.ArticleAnalyzer.Helper;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.*;

import com.ArticleAnalyzer.DataManagement.ArticleLoader;
import com.ArticleAnalyzer.DataManagement.Downloader;
import com.ArticleAnalyzer.DataManagement.Outputter;

public class Argparser {

    private Outputter outputter = null;
    private ArticleLoader articleLoader = null;

    private Boolean dataFromFile = null;
    private Downloader downloader = null;
    private String[] toExclude = null;
    private int toShow = -1;

    public Outputter getOutputter(){
        return outputter;
    }

    public String[] getToExclude(){
        return toExclude;
    }

    public int getToShow(){
        return toShow;
    }

    public ArticleLoader getArticleLoader(){
        return articleLoader;
    }

    public Downloader getDownloader(){
        return downloader;
    }

    public Boolean getDataFromFile(){
        return dataFromFile;
    }

    public Argparser(String[] args) throws FileNotFoundException, IllegalArgumentException, IOException{

        Options options = new Options();
        options.addOption("h", "help", false, "Print option list");
        options.addOption("d", "data", true, "Choose method of obtaining data (file or download)");
        options.addOption("c", "configurationFile", true, "Select the file that contain the configuration for the downloader");
        options.addOption("om", "outputMethod", true, "Choose output method (C -> console, F -> file, CF -> console and file)");
        options.addOption("i", "inputFile", true, "Input file path");
        options.addOption("o", "outputFile", true, "Output file path");
        options.addOption("e", "toExclude", true, "Choose some string to exclude from the output (Ex. \"str1, str2, str3\")");
        options.addOption("s", "show", true, "Choose the number of results to show");

        // parse the command line arguments
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            // access the options
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("", options);
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
                    throw new IllegalArgumentException("Invalid argument for data use \"file\" or \"download\"");
                } 
            }


            if(!dataFromFile && !cmd.hasOption("c")){
                throw new IllegalArgumentException("Argument configurationFile is required to download file");
            }else if(dataFromFile){

            }else{
                downloader = new Downloader(cmd.getOptionValue("c"));
            }


            if(!cmd.hasOption("om")){
                throw new IllegalArgumentException("Argument outputMethod is required");
            }else{
                if(cmd.getOptionValue("om").equalsIgnoreCase("C")){
                outputter = new Outputter(true, false);
                }else if(cmd.getOptionValue("om").equalsIgnoreCase("F")){
                outputter = new Outputter(false, true);
                }else if(cmd.getOptionValue("om").equalsIgnoreCase("CF")){
                outputter = new Outputter(true, true);
                }else{
                    throw new IllegalArgumentException("Invalid argument for data use \"C\" or \"F\" or \"CF\"");
                } 
            }


            if(dataFromFile && !cmd.hasOption("i")){
                throw new IllegalArgumentException("Argument inputFile is required if you are taking data from file");
            }else if(!dataFromFile && !cmd.hasOption("i")){
                articleLoader = new ArticleLoader(downloader.getJSONoutput());
            }else{
                try{
                    articleLoader = new ArticleLoader(cmd.getOptionValue("i"));
                }catch(FileNotFoundException e){
                    throw new IllegalArgumentException("Invalid data input file");
                }
            }


            if(outputter.getToFile() && !cmd.hasOption("o")){
                throw new IllegalArgumentException("Argument outputFile is required if you are outputting data to a file");
            }else{
                outputter.setFile(cmd.getOptionValue("o"));
            }

            if(cmd.hasOption("s")){
                try{
                    toShow = Integer.parseInt(cmd.getOptionValue("s"));
                }catch(NumberFormatException e){
                    throw new IllegalArgumentException("Argument show need an int");
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
