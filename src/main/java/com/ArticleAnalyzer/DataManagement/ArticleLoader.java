package com.ArticleAnalyzer.DataManagement;

import java.io.BufferedReader;
import java.io.File;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.Iterator;
import java.util.Map;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private void loadLibrary() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException{
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

    private void loadCSV() throws FileNotFoundException, IOException{
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        CSVParser csvParser = new CSVParser(br, csvFormat);
        for (CSVRecord record : csvParser) {
            Article toLibrary = new Article();
            for (String fieldName : record.getParser().getHeaderMap().keySet()) {
                String fieldValue = record.get(fieldName);
                toLibrary.fullSetter(fieldValue, fieldName);
            }
            loadedLibrary.addArticle(toLibrary);
        }
        csvParser.close();
        br.close();

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

    public Library getLoadedLibrary() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException{
        loadLibrary();
        return loadedLibrary;
    }

}
