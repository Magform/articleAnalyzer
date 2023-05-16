package com.ArticleAnalyzer.DataManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private File configurationFile;
    private String link;
    private String APIkey;
    private String query;
    private String JSONoutput;
    private String articleNumber;

    public Downloader(){
        configurationFile = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = null;
        articleNumber = null;
    }

    public Downloader(String configurationFile) throws FileNotFoundException, IllegalArgumentException, IOException{
        this.configurationFile = new File(configurationFile);
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = null;
        articleNumber = null;

        Scanner configurationFileScanner = new Scanner(this.configurationFile);
        while(configurationFileScanner.hasNextLine()){
            String line = configurationFileScanner.nextLine();
            int splitter = line.indexOf(":");
            if(splitter == -1){
                configurationFileScanner.close();
                throw new IllegalArgumentException("Invalid configuration file");
            }
            String key = line.substring(0, splitter);
            String value = line.substring(splitter+1, line.length());
            try{
                value = value.substring(value.indexOf("\"")+1, value.length());
                value = value.substring(0, value.indexOf("\""));
            }catch(IndexOutOfBoundsException e){
                configurationFileScanner.close();
                throw new IllegalArgumentException("Invalid argument in configuration file");
            }
            if (key.equalsIgnoreCase("link")) {
                link = value;
            } else if (key.equalsIgnoreCase("query")) {
                query = value;
            } else if (key.equalsIgnoreCase("APIkey")) {
                APIkey = value;
            } else if (key.equalsIgnoreCase("JSONoutput")) {
                JSONoutput = value;
            }else if (key.equalsIgnoreCase("articleNumber")) {
                articleNumber = value;
            }else{
                configurationFileScanner.close();
                throw new IllegalArgumentException(key +" is an invalid key but it is in the configuration file");
            }
        }
        configurationFileScanner.close();
        if(link == null || link.equalsIgnoreCase("")){
            throw new IllegalArgumentException("link need to be configured");
        }
        download();
 
    }

    public String getJSONoutput(){
        return JSONoutput;
    }

    private void download() throws IOException{
        String urlString = "";
        try {
            urlString = link + "?show-fields=all";
            if(articleNumber != null){
                urlString = urlString + "&page-size="+articleNumber;
            }
            if(APIkey != null){
                urlString = urlString + "&api-key="+APIkey;
            }
            if(query != null){
                urlString = urlString+"&q="+query.replace(" ", "+");
            }

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        
            //String builder is more efficent than this, but I dont like it ;)
            Scanner scanner = new Scanner(connection.getInputStream());
            String result = "";
            while (scanner.hasNextLine()) {
                result = result +scanner.nextLine()+"\n";
            }

            // print response
            Outputter toJSON = new Outputter(false, true, JSONoutput);
            toJSON.print(result);
            
            connection.disconnect();
        }catch(MalformedURLException e){
            throw new IOException(urlString+" is an invalid link");
        }catch(IOException e){
            throw new IOException("Error with connection to the API with URL: "+ urlString);
        }
    }

}