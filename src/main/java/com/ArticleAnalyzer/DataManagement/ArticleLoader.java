package com.ArticleAnalyzer.DataManagement;

import java.io.File;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Iterator;
import java.util.Map;
import java.io.IOException;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ArticleLoader {
    private File file;
    private Library loadedLibrary;

    public ArticleLoader(String file) throws FileNotFoundException {
        loadedLibrary = new Library();
        this.file = new File(file);
        if (!this.file.exists()) {
          throw new FileNotFoundException("File not found");
        }
    }

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
                        //Skip this since date is not a lot important and it is in a different format(EASY TO FIX)
                        //toLibrary.fullSetter(line[i], "webPublicationDate");
                    }else if(index[i].equalsIgnoreCase("Source Set")){
                        toLibrary.fullSetter(line[i], "SourceSet");
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

    private void loadJSON() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
        FileReader fileReader = new FileReader(file);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        JSONObject response = (JSONObject) obj;
        JSONObject responseFields = (JSONObject) response.get("response");
        JSONArray results = (JSONArray) responseFields.get("results");
    
        Iterator<JSONObject> iteratorArticles = results.iterator();
    
        while (iteratorArticles.hasNext()) {
            Article toLibrary = new Article();
            JSONObject article = iteratorArticles.next();
            JSONObject articleFields = (JSONObject) article.get("fields");
            Iterator<?> articleFieldsIterator = articleFields.entrySet().iterator();
            while (articleFieldsIterator.hasNext()) {
                Map.Entry pair = (Map.Entry) articleFieldsIterator.next();
                String key = (String) pair.getKey();
                String value = (String) pair.getValue();
                try{
                    toLibrary.fullSetter(value, key);
                }catch(IOException e){}
            }
            loadedLibrary.addArticle(toLibrary);
        }
    }

    public Library getLoadedLibrary() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException, CsvValidationException{
        loadLibrary();
        return loadedLibrary;
    }

}
