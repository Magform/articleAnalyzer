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

import org.json.JSONArray;
import org.json.JSONObject;

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
    private int initialPage;
    private int totalPageNumber;

    /**
     * Constructs a Downloader object with all variables set to null, making it inactive.
     */
    public Downloader(){
        endpoint = null;
        configurationFile = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = null;
        articleNumber = null;
        initialPage = -1;
        totalPageNumber = -1;
    }

    /**
     * Constructs a Downloader object with the specified configuration file.
     * The configuration file specifies the necessary parameters for the download process.
     *
     * @param configurationFile The path to the configuration file.
     * @throws FileNotFoundException If the configuration file is not found.
     * @throws IllegalArgumentException If the configuration file has invalid contents.
     * @throws IOException If an I/O error occurs during the download process.
     */
    public Downloader(String configurationFile) throws FileNotFoundException, IllegalArgumentException, IOException{
        this.configurationFile = new File(configurationFile);
        endpoint = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = "downloaded.json";
        articleNumber = null;
        initialPage = 1;
        totalPageNumber = 1;
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
            }else if (key.equalsIgnoreCase("initialPage")) {
                try{
                    initialPage = Integer.parseInt(value);
                }catch(NumberFormatException e){
                    configurationFileScanner.close();
                    throw new IllegalArgumentException("initialPage contain a non-int value");
                }
            }else if (key.equalsIgnoreCase("totalPageNumber")) {
                try{
                    totalPageNumber = Integer.parseInt(value);
                }catch(NumberFormatException e){
                    configurationFileScanner.close();
                    throw new IllegalArgumentException("totalPageNumber contain a non-int value");
                }
            }else{
                configurationFileScanner.close();
                throw new IllegalArgumentException(key +" is an invalid key but it is in the configuration file");
            }
        }
        configurationFileScanner.close();
        download();  
    }

    /**
     * Returns the file path of the saved response data.
     *
     * @return The file path of the saved response data.
     */
    public String getJSONoutput(){
        return JSONoutput;
    }

    /**
     * Initiates the download process based on the specified endpoint.
     * If the endpoint is "TheGuardian", it calls the downloadFromTheGuardian() method.
     * Otherwise, it throws an IllegalArgumentException indicating an invalid endpoint.
     *
     * @throws IOException If an I/O error occurs during the download process.
     */
    private void download() throws IOException{
        if(endpoint == null){
            throw new IllegalArgumentException("Endpoint need to be defined");
        }
        if(endpoint.equalsIgnoreCase("TheGuardian")){
            downloadFromTheGuardian();
        }else{
            throw new IllegalArgumentException(endpoint+" is an invalid endpoint");
        }
    }

    /**
     * Downloads data from TheGuardian endpoint and saves the response data in a file.
     * It checks if the necessary configuration is set correctly and constructs a URL
     * with the specified parameters. It then establishes a connection, retrieves the
     * response data, and saves it to the specified output file. Finally, it disconnects
     * the connection.
     *
     * @throws IOException If an I/O error occurs during the download process.
     */
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
            String mergedResults = "";
            Outputter toJSON = new Outputter(false, true, JSONoutput);
            for(int i=0; i<totalPageNumber; i++){
                //Merge all variable to the URL
                urlString = link + "&show-fields=all";
                urlString = urlString + "&page="+initialPage;
                initialPage++;
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
                    result = result + scanner.nextLine()+"\n";
                }

                mergedResults = theGuardianMerge(mergedResults, result);

                connection.disconnect();
            }

            toJSON.print(mergedResults);

        }catch(MalformedURLException e){
            throw new IOException(urlString+" is an invalid link");
        }catch(IOException e){
            throw new IOException("Error with connection to the API with URL: "+ urlString);
        }
    }

    private String theGuardianMerge(String fristJSON, String secondJSON){
        if(fristJSON==""){
            return secondJSON;
        }
        if(secondJSON==""){
            return fristJSON;
        }
        JSONObject results = new JSONObject();
        JSONObject content = new JSONObject();
        JSONObject  frist = (JSONObject) new JSONObject(fristJSON).get("response");
        JSONObject  second = (JSONObject) new JSONObject(secondJSON).get("response");
        
        content.put("status", frist.getString("status"));
        content.put("userTier", frist.getString("userTier"));
        content.put("total", frist.getInt("total")+second.getInt("total"));
        content.put("pageSize", frist.getInt("pageSize"));
        content.put("pages", frist.getInt("pages")+second.getInt("pages"));
        content.put("orderBy", frist.getString("orderBy"));

        JSONArray articles = new JSONArray();
        JSONArray fristArticles = (JSONArray) frist.get("results");
        JSONArray secondArticles = (JSONArray) second.get("results");

        articles = fristArticles;
        
        for (int i = 0; i < secondArticles.length(); i++) {
            articles.put(secondArticles.getJSONObject(i));
        }

        content.put("results", articles);

        results.put("response", content);
        return results.toString();
    }

}