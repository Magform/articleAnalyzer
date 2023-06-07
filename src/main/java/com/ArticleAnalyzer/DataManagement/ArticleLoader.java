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
    private Library loadedLibrary;

   /**
    * Constructs an ArticleLoader object with the specified file.
    * @param fileName the file path of the article data
    * @throws FileNotFoundException if the file is not found
    */
    public ArticleLoader(String fileName) throws FileNotFoundException {
        loadedLibrary = new Library();
        file = new File(fileName);
        if (!file.exists()) {
          throw new FileNotFoundException("File not found");
        }
    }

    /**
     * Loads the article library from the specified file.
     *
     * @see #loadCSV()
     * @see #loadJSON()
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ParseException if an error occurs while parsing the file
     * @throws org.json.simple.parser.ParseException if an error occurs while parsing a JSON file
     * @throws CsvValidationException if an error occurs while validating a CSV file
     * @throws IllegalArgumentException if the file extension is not specified or is not one of the managed ones
     */
    private void loadLibrary() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException, CsvValidationException{
        String extension;
        try{
            extension = file.getName().substring(file.getName().lastIndexOf("."));
        }catch(StringIndexOutOfBoundsException e){
            throw new IllegalArgumentException("The file has no extension");
        }
        if(extension.equalsIgnoreCase(".csv")){
            loadCSV();
        }else if(extension.equalsIgnoreCase(".json")){
            loadJSON();
        }else{
            throw new IllegalArgumentException("The extension "+extension+" is not one of the managed ones");
        }
    }

    /**
     * Loads articles from a CSV file into the article library.
     *
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if an I/O error occurs while reading the file
     * @throws CsvValidationException if an error occurs while validating the CSV file
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
                }catch(IllegalArgumentException e){
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
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ParseException if an error occurs while parsing the file
     * @throws org.json.simple.parser.ParseException if an error occurs while parsing the JSON file
     * @throws ClassCastException if an error occure while casting something
     */
    private void loadJSON() throws IOException, ParseException {
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
                }catch(IllegalArgumentException e){}

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
                        }catch(IllegalArgumentException e){}
                    }
                }
            }
            loadedLibrary.addArticle(toLibrary);
        }
    
    }

    /**
     * Returns the Library object after it has been loaded with the articles contained in the file.
     *
     * @see #loadLibrary()
     * @return the loaded Library object
     * @throws FileNotFoundException if the file is not found
     * @throws IOException if an I/O error occurs while reading the file
     * @throws ParseException if an error occurs while parsing the file
     * @throws org.json.simple.parser.ParseException if an error occurs while parsing a JSON file
     * @throws CsvValidationException if an error occurs while validating a CSV file
     * @throws IllegalArgumentException if the file extension is not specified or is not one of the managed ones 
     */
    public Library getLoadedLibrary() throws FileNotFoundException, IOException, ParseException, CsvValidationException, IllegalArgumentException{
        loadLibrary();
        return loadedLibrary;
    }

}
