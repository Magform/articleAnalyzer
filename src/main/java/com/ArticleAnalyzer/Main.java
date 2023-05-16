package com.ArticleAnalyzer;

import java.util.LinkedHashMap;

import com.ArticleAnalyzer.DataManagement.ArticleLoader;
import com.ArticleAnalyzer.DataManagement.Outputter;
import com.ArticleAnalyzer.DataProcesser.Elaborator;
import com.ArticleAnalyzer.Helper.Argparser;
import com.ArticleAnalyzer.Types.Library;

public class Main {
  public static void main(String[] args) {

    Outputter outputter = null;
    ArticleLoader articleLoader = null;
    Elaborator elaborator = null;

    try{
      Argparser argparser = new Argparser(args);
      outputter = argparser.getOutputter();
      articleLoader = argparser.getArticleLoader();
      Library toAnalyze = articleLoader.getLoadedLibrary();
      elaborator = new Elaborator(toAnalyze);
      if(argparser.getToExclude() == null){
        outputter.print(elaborator.getWords(argparser.getToShow()));
      }else{
        outputter.print(elaborator.getWords(argparser.getToExclude(), argparser.getToShow()));
      }
    }catch(Exception e){
      System.err.println("Error: "+e);
      System.exit(1);
    }



  }
}