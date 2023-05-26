package com.ArticleAnalyzer;

import com.ArticleAnalyzer.Types.Library;
import com.ArticleAnalyzer.DataManagement.ArticleLoader;
import com.ArticleAnalyzer.DataManagement.Outputter;
import com.ArticleAnalyzer.DataProcesser.Elaborator;
import com.ArticleAnalyzer.Helper.Argparser;

public class Main {
  public static void main(String[] args) {
    try {
      Argparser argparser = new Argparser(args);
      Outputter outputter = argparser.getOutputter();
      ArticleLoader articleLoader = argparser.getArticleLoader();
      Library toAnalyze = articleLoader.getLibrary();
      Elaborator elaborator = new Elaborator(toAnalyze);
      if (argparser.getToExclude() == null) {
        outputter.print(elaborator.getWords(argparser.getToShow()));
      }
      else {
        outputter.print(elaborator.getWords(argparser.getToExclude(), argparser.getToShow()));
      }
    }
    catch(Exception e) {
      System.err.println("Errore: " + e);
      System.exit(1);
    }
  }
}
