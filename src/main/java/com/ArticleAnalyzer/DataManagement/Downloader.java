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

    public Downloader(){
        configurationFile = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = null;
    }

    public Downloader(String configurationFile) throws FileNotFoundException, IllegalArgumentException, IOException{
        this.configurationFile = new File(configurationFile);
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = null;

        Scanner configurationFileScanner = new Scanner(this.configurationFile);
        while(configurationFileScanner.hasNextLine()){
            String line = configurationFileScanner.nextLine();
            String[] parts = line.split(":");
            String key = parts[0];
            String value = parts[1].replace("\"", "");
            if (key.equalsIgnoreCase("link")) {
                link = value;
            } else if (key.equalsIgnoreCase("query")) {
                query = value;
            } else if (key.equalsIgnoreCase("APIkey")) {
                APIkey = value;
            } else if (key.equalsIgnoreCase("JSONoutput")) {
                JSONoutput = value;
            }else{
                configurationFileScanner.close();
                throw new IllegalArgumentException("Invalid key in configuration file");
            }
        }
        configurationFileScanner.close();
        download();
 
    }

    public String getJSONoutput(){
        return JSONoutput;
    }

    private void download() throws IOException{
        try {
            String urlString = link+"?";
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
            throw new IOException("Invalid link");
        }catch(IOException e){
            throw new IOException("Error with connection to the API");
        }
    }

}