package com.ArticleAnalyzer;

import com.ArticleAnalyzer.DataManagement.ArticleLoader;
import com.ArticleAnalyzer.DataManagement.Outputter;
import com.ArticleAnalyzer.DataProcesser.Elaborator;
import com.ArticleAnalyzer.Helper.Argparser;
import com.ArticleAnalyzer.Types.Library;

public class Main {
  public static void main(String[] args) {
    // Initialize variables
    Outputter outputter = null;
    ArticleLoader articleLoader = null;
    Elaborator elaborator = null;

    try {
      // Parse command-line arguments
      Argparser argparser = new Argparser(args);

      // Retrieve necessary components from the argparser
      outputter = argparser.getOutputter();
      articleLoader = argparser.getArticleLoader();

      // Load the articles into a Library
      Library toAnalyze = articleLoader.getLibrary();

      // Create an Elaborator instance with the loaded library
      elaborator = new Elaborator(toAnalyze);

      // Check if any exclusion words were specified
      if (argparser.getToExclude() == null) {
        // If no exclusion words, print the word frequencies for the specified words
        outputter.print(elaborator.getWords(argparser.getToShow()));
      } else {
        // If exclusion words are provided, print the word frequencies for the specified words excluding the exclusion words
        outputter.print(elaborator.getWords(argparser.getToExclude(), argparser.getToShow()));
      }
    } catch (Exception e) {
      // Catch any exceptions that occur during the execution and print it
      System.err.println("Error: " + e);
      System.exit(1); // Terminate the program with an error status
    }
  }
}
