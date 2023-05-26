package com.ArticleAnalyzer.Helper;

import com.ArticleAnalyzer.DataManagement.ArticleLoader;
import com.ArticleAnalyzer.DataManagement.Downloader;
import com.ArticleAnalyzer.DataManagement.Outputter;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

/**
  * The Argparser class provides options in order to execute the ArticleAnalyzer project.
*/
public class Argparser {
  private Outputter outputter;
  private ArticleLoader articleLoader;
  private boolean dataFromFile;
  private Downloader downloader;
  private String[] toExclude;
  private int toShow;

/**
  * Returns the Outputter object
  * @return the Outputter object
*/
  public Outputter getOutputter() {
    return outputter;
  }

/**
  * Returns the array of words to exclude
  * @return the array of words to exclude
*/
  public String[] getToExclude() {
    return toExclude;
  }

/**
  * Returns the number of results to show
  * @return the number of results to show
*/
  public int getToShow() {
    return toShow;
  }

/**
  * Returns the ArticleLoader object
  * @return the ArticleLoader object
*/
  public ArticleLoader getArticleLoader() {
    return articleLoader;
  }

/**
  * Returns the Downloader object
  * @return the Downloader object
*/
  public Downloader getDownloader() {
    return downloader;
  }

/**
  * Returns if data are retrived from files
  * @return if data are retrived from files
*/
  public boolean getDataFromFile() {
    return dataFromFile;
  }

/**
  * Create an Argparser object providing various command line options and checking if the given arguments are valid or not
  * @param args the command line arguments
  * @throws FileNotFoundException if the files required are not specified
  * @throws IllegalArgumentException if the given arguments are not valid for different reasons
  * @throws IOException if there are errors during the parsing of the arguments
*/
  public Argparser(String[] args) throws FileNotFoundException, IllegalArgumentException, IOException {
    Options options = new Options();
    options.addOption("h", "help", false, "Azioni possibili");
    options.addOption("d", "data", true, "Metodo di ottenimento degli articoli (valori ammessi: file, download)");
    options.addOption("c", "configurationFile", true, "File che contiene la configurazione del download");
    options.addOption("om", "outputMethod", true, "Metodo di stampa dell'output (valori ammessi: C (console), F (file), CF (console e file)");
    options.addOption("i", "inputFile", true, "Path del file di input");
    options.addOption("o", "outputFile", true, "Path del file di output");
    options.addOption("e", "toExclude", true, "Parole da escludere (esempio: \"an, have, the\")");
    options.addOption("s", "show", true, "Numero di risultati da mostrare");

    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine cmd = parser.parse(options, args);
      if (cmd.hasOption("h")) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null); //options in the order they were declared
        formatter.printHelp(120, "Article Analyzer", "", options, "");
        System.exit(0);
      }
      if (!cmd.hasOption("d")) {
        throw new IllegalArgumentException("L'argomento data è richiesto");
      }
      else {
        if (cmd.getOptionValue("d").equalsIgnoreCase("file")) {
          dataFromFile = true;
        }
        else if (cmd.getOptionValue("d").equalsIgnoreCase("download")) {
          dataFromFile = false;
        }
        else {
          throw new IllegalArgumentException("Argomento data invalido. Valori ammessi: file, download");
        }
      }

      if (!dataFromFile && !cmd.hasOption("c")) {
        throw new IllegalArgumentException("L'argomento configurationFile è richiesto per il download del file");
      }
      else if (!dataFromFile) {
        downloader = new Downloader(cmd.getOptionValue("c"));
      }


      if (!cmd.hasOption("om")) {
        throw new IllegalArgumentException("L'argomento outputMethod è richiesto");
      }
      else {
        if (cmd.getOptionValue("om").equalsIgnoreCase("C")) {
          outputter = new Outputter(true, false);
        }
        else if (cmd.getOptionValue("om").equalsIgnoreCase("F")) {
          outputter = new Outputter(false, true);
        }
        else if (cmd.getOptionValue("om").equalsIgnoreCase("CF")) {
          outputter = new Outputter(true, true);
        }
        else {
          throw new IllegalArgumentException("Argomento outputMethod invalido. Valori ammessi: C, F, CF");
        }
      }


      if (dataFromFile && !cmd.hasOption("i")) {
        throw new IllegalArgumentException("L'argomento inputFile è richiesto required se si vogliono prendere gli articoli da file presenti");
      }
      else if (!dataFromFile && !cmd.hasOption("i")) {
        articleLoader = new ArticleLoader(downloader.getJSONoutput());
      }
      else {
        try {
          articleLoader = new ArticleLoader(cmd.getOptionValue("i"));
        }
        catch (FileNotFoundException e) {
          throw new IllegalArgumentException("Argomento inputFile invalido, file non trovato");
        }
      }

      if (outputter.getToFile() && !cmd.hasOption("o")) {
        throw new IllegalArgumentException("L'argomento outputFile è richiesto se vuoi stampare l'output su file");
      }
      else {
        outputter.setFileName(cmd.getOptionValue("o"));
      }

      if (cmd.hasOption("s")) {
        try {
          toShow = Integer.parseInt(cmd.getOptionValue("s"));
        }
        catch (NumberFormatException e) {
          throw new IllegalArgumentException("L'argomento show necessita di un numero intero");
        }
      }

      if (cmd.hasOption("e")) {
        String toToExclude = cmd.getOptionValue("e").replace("\"", "");
        toToExclude = toToExclude.replace(" ", "");
        toExclude = toToExclude.split(",");
      }

    }
    catch (ParseException e) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.setOptionComparator(null); //options in the order they were declared
      formatter.printHelp(120, "Article Analyzer", "", options, "");
      System.exit(1);
    }
  }
}
