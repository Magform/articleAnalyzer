package com.ArticleAnalyzer.DataManagement;

import java.io.File;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Scanner CSV = new Scanner(file);

        //Scan frist line to make index
        String[] index;
        int numberOfIndex = 0;

        if (CSV.hasNextLine()) {
            String firstLine = CSV.nextLine();
            index = firstLine.split(","); // initialize the index array with the first line
            index[0] = index[0].replace("ï»¿", "");
            numberOfIndex = index.length;

            for(int i=0; i< index.length; i++){
                System.out.println(index[i]);  
            }
            while (CSV.hasNextLine()) {
                Article toLibrary = new Article(); // create a new Article object for each line
                String line = CSV.nextLine();
                String[] values = line.split(",");
        
            // set the properties of the Article object with the values from the CSV line
                for (int i = 0; i < numberOfIndex && i < values.length; i++) {
                    if(index[i].equalsIgnoreCase("body")){
                        toLibrary.fullSetter(values[i], "bodyText"); 
                    }else if(index[i].equalsIgnoreCase("Identifier")){
                        toLibrary.fullSetter(values[i], "id"); 
                    }else if(index[i].equalsIgnoreCase("URL")){
                        toLibrary.fullSetter(values[i], "webUrl"); 
                    }else if(index[i].equalsIgnoreCase("Date")){
                        LocalDateTime dateTime = LocalDateTime.parse(values[i], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
                        toLibrary.setWebPublicationDate(dateTime); 
                    }else{

                        toLibrary.fullSetter(values[i], index[i]);
                    }
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
    
        String index = null;
        String value = null;
        Article toLibrary = null;
    
        while(JSON.hasNextLine()) {
            String line = JSON.nextLine();
            
            indexing:
            while (line.indexOf("\"") != -1) {
                index = line.substring(line.indexOf("\"")+1, line.length());
                System.out.println(index);
                Boolean indexNotFounded = true;
                while (indexNotFounded) {
                    if (index.indexOf("\"") != -1) {
                        line = index.substring(index.indexOf("\"")+1, index.length());
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
                if(line.indexOf("\"") != -1){
                    value = line.substring(line.indexOf("\"")+1, line.length());
                    Boolean valueNotFounded = true;
                    System.out.println("index: "+index);
                    System.out.println("value: "+value);
                    System.out.println(" ");
                    while (valueNotFounded) {
                        if (value.indexOf("\"") != -1) {
                            value = value.substring(0, value.indexOf("\""));
                            valueNotFounded = false;
                            System.out.println("index: "+index);
                            System.out.println("value: "+value);
                            System.out.println(" ");
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
                }
                System.out.println("index: "+index);
                System.out.println("value: "+value);
                System.out.println(" ");
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
