package com.ArticleAnalyzer;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;
import java.util.ArrayList;

public class ReadFromOnlineNewspapers {
  private WriteXML xmlWriter;

  public ReadFromOnlineNewspapers(ArrayList<String> filesReaderNames, String xmlWriterName) {
    try {
      WriteXML xmlWriter = new WriteXML(xmlWriterName);
      for (int i = 0; i < filesReaderNames.size(); i++) {
        String fileName = filesReaderNames.get(i);
        FileReader fileReader = new FileReader(fileName);
        if (fileName.substring(fileName.indexOf(".") + 1).equals("csv")) {
          Scanner sc = new Scanner(fileReader);
          String line = sc.nextLine();
          while (sc.hasNextLine()) {
            line = sc.nextLine();
            Article a = createArticleFromCSV(line);
            xmlWriter.addArticle(a);
          }
          sc.close();
        }
        else if (fileName.substring(fileName.indexOf(".") + 1).equals("json")) {
          JSONParser parser = new JSONParser();
          Object obj = parser.parse(fileReader);
          JSONObject response = (JSONObject)obj;
          JSONObject responseFields = (JSONObject)response.get("response");
          JSONArray results = (JSONArray)responseFields.get("results");
          Iterator iteratorArticles = results.iterator();
          while (iteratorArticles.hasNext()) {
            JSONObject article = (JSONObject)iteratorArticles.next();
            Article a = createArticleFromJSON(article);
            xmlWriter.addArticle(a);
          }
        }
        /*else if (...) {
          si possono fare altri else if in futuro in caso vengano aggiunte altre testate
          Bisogna capire come fare se due testate restituiscono la stessa estensione ma i file hanno diversi formati
        }*/
        fileReader.close();
      }
      xmlWriter.writeXMLFile();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Article createArticleFromCSV(String articleLine) {
    String[] articlesFields = articleLine.split(",");
    String articleTitle = articlesFields[2];
    boolean quotationFound = false;
    if (articleTitle.charAt(0) == '"') { //se nel titolo è presente almeno una virgola
      for (int j = 3; j < articlesFields.length; j++) {
        articleTitle = articleTitle + ",";
        for (int i = 0; i < articlesFields[j].length(); i++) {
          if (articlesFields[j].charAt(i) != '"') {
            articleTitle = articleTitle + articlesFields[j].charAt(i);
          }
          else {
            articleTitle = articleTitle + "\"";
            quotationFound = true;
            break;
          }
        }
        if (quotationFound) {
          break;
        }
      }
    }
    int commasInTitle = 0;
    for (int i = 0; i < articleTitle.length(); i++) {
      if (articleTitle.charAt(i) == ',') {
        commasInTitle++;
      }
    }
    int startBodyIndex = 3 + commasInTitle;
    String allWords =  articlesFields[startBodyIndex];
    for (int i = startBodyIndex + 1; i < articlesFields.length - 3; i++) {
      allWords = allWords + "," + articlesFields[i];
    }
    String articleBody = allWords;
    return new Article(articlesFields[articlesFields.length - 2], articleTitle, articleBody);
  }

  private Article createArticleFromJSON(JSONObject art) {
    JSONObject articleFields = (JSONObject)art.get("fields");
    String source = (String)articleFields.get("publication");
    String title = (String)articleFields.get("headline") + " " + (String)articleFields.get("trailText"); //comprende anche il sottotitolo (da valutare se tenere o meno)
    String body = (String)articleFields.get("bodyText");
    return new Article(source, title, body);
  }
}
