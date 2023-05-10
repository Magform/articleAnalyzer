package com.ArticleAnalyzer.DataManagement;

import java.io.File;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class ArticleLoader {
    private File file;
    private Library loadedLibrary;

    ArticleLoader(String file) throws FileNotFoundException {
        loadedLibrary = new Library();
        this.file = new File(file);
        if (!this.file.exists()) {
          throw new FileNotFoundException("File not found");
        }
    }

    private void loadLibrary() throws FileNotFoundException, IOException{
        String extension = file.getName().substring(file.getName().lastIndexOf("."));
        if(extension.equalsIgnoreCase("csv")){
            loadCSV();
        }else if(extension.equalsIgnoreCase("json")){
            loadJSON();
        }else{
            //I've decided that if there is no extension or extension is not csv or JSON we suppose that the file was build with Outputter (and it is formatted as JSON)
            loadJSON();
            //throw new IOException("Extension of file not setted");
        }
    }

    private void loadCSV() throws FileNotFoundException, IOException{
        Scanner CSV = new Scanner(file);

        //Scan frist line to make index
        String[] index;
        int numberOfIndex = 0;

        if (CSV.hasNextLine()) {
            String firstLine = CSV.nextLine();
            index = firstLine.split(","); // initialize the index array with the first line
            numberOfIndex = index.length;

            while (CSV.hasNextLine()) {
                Article toLibrary = new Article(); // create a new Article object for each line
                String line = CSV.nextLine();
                String[] values = line.split(",");
        
            // set the properties of the Article object with the values from the CSV line
                for (int i = 0; i < numberOfIndex && i < values.length; i++) {
                    toLibrary.fullSetter(values[i], index[i]);
                }
            loadedLibrary.addArticle(toLibrary);
            }
        } else {
            CSV.close();
            throw new IOException("CSV not valid");
        }
        CSV.close();
    }  

    //Another implementation harder to read and to understand
    private void loadJSON() throws FileNotFoundException, IOException {
        Scanner JSON = new Scanner(file);
    
        String index;
        String value;
        Article toLibrary = null;
    
        if (JSON.hasNextLine()) {
            String line = JSON.nextLine();
            
            indexing:
            while (line.indexOf("\"") != -1) {
                index = line.substring(line.indexOf("\""), line.length());
                Boolean indexNotFounded = true;
                while (indexNotFounded) {
                    if (index.indexOf("\"") != -1) {
                        line = index.substring(index.indexOf("\""), index.length());
                        index = index.substring(0, index.indexOf("\""));
                        indexNotFounded = false;
                    } else {
                        index += JSON.nextLine();
                    }
                }
                if (index.equalsIgnoreCase("id")) {
                    if (toLibrary != null && !toLibrary.getId().equalsIgnoreCase("")) {
                        loadedLibrary.addArticle(toLibrary);
                    }
                    toLibrary = new Article();
                }
    
                value = line.substring(line.indexOf("\""), line.length());
                Boolean valueNotFounded = true;
                while (valueNotFounded) {
                    if (value.indexOf("\"") != -1) {
                        line = value.substring(value.indexOf("\""), value.length());
                        value = value.substring(0, value.indexOf("\""));
                        valueNotFounded = false;
                    } else if (value.indexOf(",") != -1) {
                        line = value.substring(value.indexOf(",") + 1, value.length());
                        continue indexing;
                    } else if (value.indexOf("{") != -1) {
                        line = value.substring(value.indexOf("{"), value.length());
                        continue indexing;
                    } else if (value.indexOf("[") != -1) {
                        line = value.substring(value.indexOf("["), value.length());
                        continue indexing;
                    } else {
                        value += JSON.nextLine();
                    }
                }
                if (toLibrary != null) {
                    try{
                        toLibrary.fullSetter(value, index);
                    }catch(IOException e){}
                }
            }
            if (toLibrary != null && !toLibrary.getId().equalsIgnoreCase("")) {
                loadedLibrary.addArticle(toLibrary);
            }
        }
    
        JSON.close();
    }

    public Library getLoadedLibrary() throws FileNotFoundException, IOException{
        loadLibrary();
        return loadedLibrary;
    }

}
