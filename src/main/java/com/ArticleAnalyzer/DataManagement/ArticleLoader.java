package com.ArticleAnalyzer.DataManagement;

/**
 * The ArticleLoader class is responsible for loading articles from different file formats, such as CSV and JSON, into an Article Library. 
 * It provides methods to load the articles and retrieve the loaded library.
 * If the file has an unrecognized extension or no extension at all, it assumes the file is formatted as JSON.
 * 
 * Public Constructors:
 * - ArticleLoader(String file): Constructs an ArticleLoader object with the specified file path. It throws a FileNotFoundException if the file is not found.
 *
 * Public Methods:
 * - getLoadedLibrary(): Loads the articles from the file into the library and returns the loaded Library object.
 *                      It throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException and CsvValidationException 
 *                      if any errors occur during the loading process.
 * 
 * Private Methods:
 * - loadLibrary(): Determines the file format based on the file extension and calls the corresponding load method (loadCSV or loadJSON) to load the articles into the library.
 * - loadCSV(): Loads articles from a CSV file into the library. It uses the opencsv library to parse the CSV file.
 *               It throws FileNotFoundException, IOException, and CsvValidationException if any errors occur during the loading process.
 * - loadJSON(): Loads articles from a JSON file into the library. It uses the json-simple library to parse the JSON file. 
 *              It throws FileNotFoundException, IOException, ParseException, and org.json.simple.parser.ParseException if any errors occur during the loading process.
 *
 * Note: The ArticleLoader assumes that the CSV file has a header row with column names and each subsequent row contains the corresponding values for each column.
 *       The JSON file should have a "response" object containing a "results" array, where each element in the array represents an article with its fields.
*/

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
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ArticleLoader {

    private File file;
    private Library loadedLibrary;

     /**
     * Constructs an ArticleLoader object with the specified file.
     *
     * @param file the file path of the article data
     * @throws FileNotFoundException if the file is not found
     */
    public ArticleLoader(String file) throws FileNotFoundException {
        loadedLibrary = new Library();
        this.file = new File(file);
        if (!this.file.exists()) {
          throw new FileNotFoundException("File not found");
        }
    }

    /**
     * Loads the article library from the specified file.
     *
     * @throws FileNotFoundException            if the file is not found
     * @throws IOException                      if an I/O error occurs while reading the file
     * @throws ParseException                   if an error occurs while parsing the file
     * @throws org.json.simple.parser.ParseException if an error occurs while parsing a JSON file
     * @throws CsvValidationException            if an error occurs while validating a CSV file
     */
    private void loadLibrary() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException, CsvValidationException{
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        if(extension.equalsIgnoreCase(".csv")){
            loadCSV();
        }else if(extension.equalsIgnoreCase(".json")){
            loadJSON();
        }else{
            //I've decided that if there is no extension or extension is not csv or JSON we suppose that the file was build with Outputter (and it is formatted as JSON)
            loadJSON();
            //throw new IOException("Extension of file not setted");
        }
    }

    /**
     * Loads articles from a CSV file into the article library.
     *
     * @throws FileNotFoundException    if the file is not found
     * @throws IOException              if an I/O error occurs while reading the file
     * @throws CsvValidationException   if an error occurs while validating the CSV file
     */
    private void loadCSV() throws FileNotFoundException, IOException, CsvValidationException{
        FileReader fileReader = new FileReader(file);

        CSVReader reader = new CSVReader(fileReader);
        String[] index = reader.readNext();
        index[0] = index[0].replace("ï»¿", "");
        String[] line;
        while ((line = reader.readNext()) != null) {
            Article toLibrary = new Article();
            for (int i = 0; i<index.length; i++) {
                try{
                    if(index[i].equalsIgnoreCase("Identifier")){
                        toLibrary.fullSetter(line[i], "id");
                    }else if(index[i].equalsIgnoreCase("URL")){
                        toLibrary.fullSetter(line[i], "webURL");
                    }else if(index[i].equalsIgnoreCase("Date")){
                        //Skip this since date is not an important date and it is in a different format
                        //toLibrary.fullSetter(line[i], "webPublicationDate");
                    }else if(index[i].equalsIgnoreCase("Source Set")){
                        toLibrary.fullSetter(line[i], "SourceSet");
                    }else if(index[i].equalsIgnoreCase("Fulltext")){
                        toLibrary.fullSetter(line[i], "BodyText");
                        toLibrary.fullSetter(line[i], index[i]);
                    }else{
                        toLibrary.fullSetter(line[i], index[i]);
                    }
                }catch(IOException e){
                    System.out.println(e);
                }
            }
            loadedLibrary.addArticle(toLibrary);
        }
        reader.close();
    }  

    /**
     * Loads articles from a JSON file into the article library.
     *
     * @throws IOException              if an I/O error occurs while reading the file
     * @throws ParseException           if an error occurs while parsing the file
     * @throws org.json.simple.parser.ParseException if an error occurs while parsing the JSON file
     */
    private void loadJSON() throws IOException, ParseException, org.json.simple.parser.ParseException{
        FileReader fileReader = new FileReader(file);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        JSONObject response = (JSONObject) obj;
        JSONArray results = null;
        if(response.containsKey("response")){
            JSONObject responseFields = (JSONObject) response.get("response");
            results = (JSONArray) responseFields.get("results"); 
        }else{
            results = (JSONArray) response.get("article");
        }

        Iterator<JSONObject> iteratorArticles = results.iterator();

        while (iteratorArticles.hasNext()) {
            Article toLibrary = new Article();
            JSONObject article = iteratorArticles.next();
            Iterator<?> articleIterator = article.entrySet().iterator();
            while (articleIterator.hasNext()) {
                Map.Entry<?, ?> pair = (Map.Entry<?, ?>) articleIterator.next();
                String key = pair.getKey().toString();
                String value = pair.getValue().toString();
                try{
                    toLibrary.fullSetter(value, key);
                }catch(IOException e){}

                //Iterate inside fields
                if(key.equalsIgnoreCase("fields")){
                    JSONObject articleFields = (JSONObject) article.get("fields");
                    Iterator<?> articleFieldsIterator = articleFields.entrySet().iterator();
                    while (articleFieldsIterator.hasNext()) {
                        Map.Entry<?, ?> pairInside = (Map.Entry<?, ?>) articleFieldsIterator.next();
                        String keyInside = pairInside.getKey().toString();
                        String valueInside = pairInside.getValue().toString();
                        try{
                            toLibrary.fullSetter(valueInside, keyInside);
                        }catch(IOException e){}
                    }
                }
            }
            loadedLibrary.addArticle(toLibrary);
        }
    
    }

    /**
     * Gets the loaded library of articles.
     *
     * @return the loaded Library object
     * @throws FileNotFoundException            if the file is not found
     * @throws IOException                      if an I/O error occurs while reading the file
     * @throws ParseException                   if an error occurs while parsing the file
     * @throws org.json.simple.parser.ParseException if an error occurs while parsing a JSON file
     * @throws CsvValidationException            if an error occurs while validating a CSV file
     */
    public Library getLoadedLibrary() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException, CsvValidationException{
        loadLibrary();
        return loadedLibrary;
    }

}
