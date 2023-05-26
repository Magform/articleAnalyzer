package com.ArticleAnalyzer.DataManagement;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
  * The ArticleLoader class is responsible for loading articles from different file formats, such as CSV and JSON, into an Library object.
  * It permits to load the articles and retrieve the loaded Library object.
*/
public class ArticleLoader {
  private File file;
  private Library library;

/**
  * Create the Library object and the file path with the given path
  * @param fn the file path
  * @throws FileNotFoundException if the file does not exist
*/
  public ArticleLoader(String fileName) throws FileNotFoundException {
    file = new File(fileName);
    library = new Library();
    if (!file.exists()) {
      throw new FileNotFoundException("File non trovato");
    }
  }

/**
  * Load, according to the file extension, the articles to the Library object
  * @see loadCSV()
  * @see loadJSON()
  * @throws IOException if the file extension is not specified or there are errors during the reading of the file
  * @throws CsvValidationException if there are errors during the reading of CSV file
  * @throws ParseException if there are errors during the reading of the JSON file
*/
  private void loadLibrary() throws IOException, CsvValidationException, ParseException {
    String extension = file.getName().substring(file.getName().lastIndexOf("."));
    if (extension.equalsIgnoreCase(".csv")) {
      loadCSV();
    }
    else if (extension.equalsIgnoreCase(".json")) {
      loadJSON();
    }
    else {
      throw new IOException("Estensione del file non specificata");
    }
  }

/**
  * Load to the Library object the articles contained in the CSV file
  * @throws IOException if the file extension is not specified or there are errors during the reading of the file
  * @throws CsvValidationException if there are errors during the reading of CSV file
*/
  private void loadCSV() throws IOException, CsvValidationException {
    FileReader fileReader = new FileReader(file);
    CSVReader reader = new CSVReader(fileReader);
    String[] index = reader.readNext();
    index[0] = index[0].replace("ï»¿", "");
    String[] line;
    while ((line = reader.readNext()) != null) {
      Article articleToAdd = new Article();
      for (int i = 0; i < index.length; i++) {
        if (index[i].equalsIgnoreCase("Identifier")) {
          articleToAdd.setId(line[i]);
        }
        else if (index[i].equalsIgnoreCase("URL")) {
          articleToAdd.setUrl(line[i]);
        }
        else if (index[i].equalsIgnoreCase("Source Set")) {
          articleToAdd.setSource(line[i]);
        }
        else if (index[i].equalsIgnoreCase("Date")) {
          articleToAdd.setDate(line[i]);
        }
        else if (index[i].equalsIgnoreCase("Title")) {
          articleToAdd.setTitle(line[i]);
        }
        else if (index[i].equalsIgnoreCase("Body")) {
          articleToAdd.setBody(line[i]);
        }
      }
      library.addArticle(articleToAdd);
    }
    reader.close();
    fileReader.close();
  }

/**
  * Load to the Library object the articles contained in the JSON file
  * @throws IOException if the file extension is not specified or there are errors during the reading of the file
  * @throws ParseException if there are errors during the reading of the JSON file
*/
  private void loadJSON() throws IOException, ParseException {
    FileReader fileReader = new FileReader(file);
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(fileReader);
    JSONObject response = (JSONObject)obj;
    JSONObject responseFields = (JSONObject)response.get("response");
    JSONArray results = (JSONArray)responseFields.get("results");
    Iterator<JSONObject> iteratorArticles = results.iterator();
    while (iteratorArticles.hasNext()) {
      JSONObject article = iteratorArticles.next();
      String articleId = (String)article.get("id");
      String articleUrl = (String)article.get("webUrl");
      JSONObject articleFields = (JSONObject)article.get("fields");
      String articleSource = (String)articleFields.get("publication");
      String articleDate = (String)articleFields.get("firstPublicationDate");
      String articleTitle = (String)articleFields.get("headline") + " " + (String)articleFields.get("trailText"); //it also contains the trail text
      String articleBody = (String)articleFields.get("bodyText");
      Article articleToAdd = new Article(articleId, articleUrl, articleSource, articleDate, articleTitle, articleBody);
      library.addArticle(articleToAdd);
    }
    fileReader.close();
  }

/**
  * Returns the Library object after it has been loaded with the articles contained in the file
  * @see loadLibrary()
  * @throws IOException if the file extension is not specified or there are errors during the reading of the file
  * @throws CsvValidationException if there are errors during the reading of CSV file
  * @throws ParseException if there are errors during the reading of the JSON file
  * @return the Library object after it has been loaded with the articles contained in the file
*/
  public Library getLibrary() throws IOException, CsvValidationException, ParseException {
    loadLibrary();
    return library;
  }
}
