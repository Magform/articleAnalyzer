package com.ArticleAnalyzer.DataManagement;

 /**
 * The Downloader class is responsible for downloading data from a specified endpoint based on a configuration file.
 * It supports TheGuardian as the current implemented endpoint.
 * 
 * The class provides two constructors: a default constructor that initializes all variables to null, making it inactive,
 * and a parameterized constructor that takes a configuration file path. The configuration file specifies the necessary
 * parameters for the download process (refer to the documentation for more details on the configuration file format).
 * 
 * Once the configuration is set, the class initiates the download process by sending a request to the specified endpoint
 * and saving the response data in a file. The file is formatted in a JSON-like structure, with the output file path
 * specified in the configuration file.
 * 
 * The class also offers a getJSONOutput() method, which returns the file path of the saved response data.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private File configurationFile;
    private String endpoint;
    private String link;
    private String APIkey;
    private String query;
    private String JSONoutput;
    private String articleNumber;

    public Downloader(){
        endpoint = null;
        configurationFile = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = null;
        articleNumber = null;
    }

    public Downloader(String configurationFile) throws FileNotFoundException, IllegalArgumentException, IOException{
        this.configurationFile = new File(configurationFile);
        endpoint = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = "downloaded.json";
        articleNumber = null;

        Scanner configurationFileScanner = new Scanner(this.configurationFile);
        //Read the configuration file and set all the variable accordingly
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
            } else if (key.equalsIgnoreCase("endpoint")) {
                endpoint = value;
            }else if (key.equalsIgnoreCase("query")) {
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
        download();
 
    }

    public String getJSONoutput(){
        return JSONoutput;
    }

    private void download() throws IOException{
        if(endpoint.equalsIgnoreCase("TheGuardian")){
            downloadFromTheGuardian();
        }else{
            throw new IllegalArgumentException(endpoint+" is an invalid endpoint");
        }
    }

    private void downloadFromTheGuardian() throws IOException{
        String urlString = "";

        //Check if anything is configured incorrectly
        if(link == null || link.equalsIgnoreCase("")){
            throw new IllegalArgumentException("link need to be configured");
        }
        if(endpoint == null || endpoint.equalsIgnoreCase("")){
            throw new IllegalArgumentException("endpoint need to be configured");
        }
        if(APIkey == null || APIkey.equalsIgnoreCase("")){
            throw new IllegalArgumentException("APIkey need to be configured");
        }

        try {
            //Merge all variable to the URL
            urlString = link + "&show-fields=all";
            if(articleNumber != null){
                urlString = urlString + "&page-size="+articleNumber;
            }
            if(APIkey != null){
                urlString = urlString + "&api-key="+APIkey;
            }
            if(query != null){
                urlString = urlString+"&q="+query.replace(" ", "+");
            }

            //Start URL connection and elaborate response
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
        
            // We could have used a stringBuilder here but I'm not very confident with that
            Scanner scanner = new Scanner(connection.getInputStream());
            String result = "";
            while (scanner.hasNextLine()) {
                result = result +scanner.nextLine()+"\n";
            }

            // print response to JSONOutput
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