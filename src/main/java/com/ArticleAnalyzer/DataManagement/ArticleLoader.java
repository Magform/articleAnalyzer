package com.ArticleAnalyzer.DataManagement;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The ArticleLoader class is responsible for loading articles from different file formats, such as CSV and JSON, into a Library object.
 * It provides methods to load the articles and retrieve the loaded Library object.
*/
public class ArticleLoader {
    private File file;
    private Library library;

    /**
     * Creates the Library object and the file path with the given path.
     * @param fileName the file path
     * @throws FileNotFoundException if the file is not found
    */
    public ArticleLoader(String fileName) throws FileNotFoundException {
        library = new Library();
        file = new File(fileName);
        if (!file.exists()) {
          throw new FileNotFoundException("File not found");
        }
    }

    /**
     * Loads the article library from the specified file.
     * @see loadCSV()
     * @see loadJSON()
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ParseException if an error occurs while parsing a JSON file
     * @throws CsvValidationException if an error occurs while validating a CSV file
     * @throws IllegalArgumentException if the file extension is not specified or is not one of the managed ones
    */
    private void loadLibrary() throws IOException, CsvValidationException, ParseException {
        String extension = "";
        try {
            extension = file.getName().substring(file.getName().lastIndexOf("."));
        }
        catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The file has no extension");
        }
        if (extension.equalsIgnoreCase(".csv")) {
            loadCSV();
        }
        else if (extension.equalsIgnoreCase(".json")) {
            loadJSON();
        }
        else {
            throw new IllegalArgumentException("The extension " + extension + " is not one of the managed ones");
        }
    }

    /**
     * Loads articles from a CSV file into the article library.
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if an I/O error occurs while reading the file
     * @throws CsvValidationException if an error occurs while validating the CSV file
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
                try {
                    if (index[i].equalsIgnoreCase("Identifier")) {
                        articleToAdd.fullSetter(line[i], "identifier");
                    }
                    else if (index[i].equalsIgnoreCase("URL")) {
                        articleToAdd.fullSetter(line[i], "url");
                    }
                    else if (index[i].equalsIgnoreCase("Date")) {
                        articleToAdd.fullSetter(line[i], "publicationDate");
                    }
                    else if (index[i].equalsIgnoreCase("Source Set")) {
                        articleToAdd.fullSetter(line[i], "source");
                    }
                    else if (index[i].equalsIgnoreCase("Title")) {
                        articleToAdd.fullSetter(line[i], "title");
                    }
                    else if (index[i].equalsIgnoreCase("Body")) {
                        articleToAdd.fullSetter(line[i], "body");
                    }
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
            }
            library.addArticle(articleToAdd);
        }
        reader.close();
    }

    /**
     * Loads articles from a JSON file into the article library.
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ParseException if an error occurs while parsing the JSON file
    */
    private void loadJSON() throws IOException, ParseException {
        FileReader fileReader = new FileReader(file);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        JSONObject response = (JSONObject) obj;
        JSONArray results = null;
        if (response.containsKey("response")) {
            JSONObject responseFields = (JSONObject) response.get("response");
            results = (JSONArray) responseFields.get("results");
            Iterator<JSONObject> iteratorArticles = results.iterator();
            while (iteratorArticles.hasNext()) {
                JSONObject article = iteratorArticles.next();
                Article articleToAdd = new Article();
                try {
                    articleToAdd.fullSetter((String)article.get("id"), "identifier");
                    articleToAdd.fullSetter((String)article.get("sectionId"), "section");
                    articleToAdd.fullSetter((String)article.get("webUrl"), "url");
                    JSONObject articleFields = (JSONObject)article.get("fields");
                    if (articleFields != null) {
                        articleToAdd.fullSetter((String)articleFields.get("publication"), "source");
                        articleToAdd.fullSetter((String)articleFields.get("firstPublicationDate"), "publicationDate");
                        articleToAdd.fullSetter((String)articleFields.get("lang"), "language");
                        articleToAdd.fullSetter((String)articleFields.get("headline"), "title");
                        articleToAdd.fullSetter((String)articleFields.get("trailText"), "subtitle");
                        articleToAdd.fullSetter((String)articleFields.get("bodyText"), "body");
                        articleToAdd.fullSetter((String)articleFields.get("newspaperPageNumber"), "newspaperPage");
                        articleToAdd.fullSetter((String)articleFields.get("wordcount"), "words");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
                library.addArticle(articleToAdd);
            }
        } else {
            results = (JSONArray) response.get("article");
            Iterator<JSONObject> iteratorArticles = results.iterator();
            while (iteratorArticles.hasNext()) {
                Article articleToAdd = new Article();
                JSONObject article = iteratorArticles.next();
                Iterator<?> articleIterator = article.entrySet().iterator();
                while (articleIterator.hasNext()) {
                    Map.Entry<?, ?> pair = (Map.Entry<?, ?>) articleIterator.next();
                    String key = pair.getKey().toString();
                    String value = pair.getValue().toString();
                    try {
                        if(key.equalsIgnoreCase("id")){
                            key = "identifier";
                        }
                        articleToAdd.fullSetter(value, key);
                    } catch (IllegalArgumentException e) {
                    }
                }
                library.addArticle(articleToAdd);
            }
        }
        fileReader.close();
    }

    /**
     * Returns the Library object after it has been loaded with the articles contained in the file.
     * @see loadLibrary()
     * @return the loaded Library object
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ParseException if an error occurs while parsing a JSON file
     * @throws CsvValidationException if an error occurs while validating a CSV file
    */
    public Library getLibrary() throws IOException, CsvValidationException, ParseException {
        loadLibrary();
        return library;
    }
}
