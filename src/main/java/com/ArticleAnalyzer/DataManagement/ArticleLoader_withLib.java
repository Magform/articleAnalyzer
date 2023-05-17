package com.ArticleAnalyzer.DataManagement;

import java.io.File;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import com.opencsv.CSVReader;

public class ArticleLoader_withLib {
    private File file;
    private Library loadedLibrary;

    public ArticleLoader_withLib(String file) throws FileNotFoundException {
        loadedLibrary = new Library();
        this.file = new File(file);
        if (!this.file.exists()) {
          throw new FileNotFoundException("File not found");
        }
    }

    private void loadLibrary() throws FileNotFoundException, IOException{
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        System.out.println(extension);
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
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
        
            String[] header = reader.readNext();
            header[0] = header[0].replace("ï»¿", "");

            String[] line = reader.readNext(); 
        
            Article toLibrary = new Article();

            while (line != null) {
                for (int i = 0; i < header.length; i++) {
                    String headerValue = header[i];
                    String dataValue = line[i];
                    System.out.println(headerValue);
                    System.out.println(dataValue);
                    if(headerValue.equalsIgnoreCase("body")){
                        toLibrary.fullSetter(dataValue, "bodyText"); 
                    }else if(headerValue.equalsIgnoreCase("Identifier")){
                        toLibrary.fullSetter(dataValue, "id"); 
                    }else if(headerValue.equalsIgnoreCase("URL")){
                        toLibrary.fullSetter(dataValue, "webUrl"); 
                    }else if(headerValue.equalsIgnoreCase("Date")){
                        toLibrary.fullSetter(dataValue, "webPublicationDate"); 
                    }else{
                        toLibrary.fullSetter(dataValue, headerValue);
                    }
                }
                loadedLibrary.addArticle(toLibrary);
                line = reader.readNext();
            }
        
            reader.close();
        } catch (Exception e) {
            throw new IOException("Error with the CSV");
        }
    }

      

    //Another implementation harder to read and to understand
    private void loadJSON() throws FileNotFoundException, IOException {
        
    }

    public Library getLoadedLibrary() throws FileNotFoundException, IOException{
        loadLibrary();
        return loadedLibrary;
    }

}

