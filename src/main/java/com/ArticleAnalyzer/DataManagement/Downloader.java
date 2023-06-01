package com.ArticleAnalyzer.DataManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
  * The Downloader class is responsible for downloading data from a specified endpoint based on a configuration file.
  * It supports TheGuardian as the current implemented endpoint.
*/

public class Downloader {
  private File configurationFile;
  private String endpoint;
  private String link;
  private String APIkey;
  private String query;
  private String JSONOutput;
  private String articleNumber;

/**
  * Initializes all attributes of the class to their default values
*/
  public Downloader() {
    configurationFile = null;
    endpoint = "";
    link = "";
    query = "";
    APIkey = "";
    JSONOutput = "";
    articleNumber = "";
  }

/**
  * Initializes the file path of the configuration file with given path, create a Download object with the specified settings in the configuration file and download the articles
  * @param cf the path to the configuration file
  * @throws FileNotFoundException if the configuration file does not exist
  * @throws IllegalArgumentException if the configuration file has invalid contents
  * @throws IOException if there are errors during the download process
*/
  public Downloader(String cf) throws FileNotFoundException, IllegalArgumentException, IOException {
    configurationFile = new File(cf);
    endpoint = "";
    link = "";
    query = "";
    APIkey = "";
    JSONOutput = "downloaded.json";
    articleNumber = "";

    Scanner configurationFileScanner = new Scanner(configurationFile);
    while (configurationFileScanner.hasNextLine()) {
      String line = configurationFileScanner.nextLine();
      int splitter = line.indexOf(":");
      if (splitter == -1) {
        configurationFileScanner.close();
        throw new IllegalArgumentException("Configuration file invalid");
      }
      String key = line.substring(0, splitter);
      String value = line.substring(splitter + 1, line.length());
      try {
        value = value.substring(value.indexOf("\"") + 1, value.length());
        value = value.substring(0, value.indexOf("\""));
      }
      catch (IndexOutOfBoundsException e) {
        configurationFileScanner.close();
        throw new IllegalArgumentException("Invalid argument in the configuration file");
      }
      if (key.equalsIgnoreCase("link")) {
        link = value;
      }
      else if (key.equalsIgnoreCase("endpoint")) {
        endpoint = value;
      }
      else if (key.equalsIgnoreCase("query")) {
        query = value;
      }
      else if (key.equalsIgnoreCase("APIkey")) {
        APIkey = value;
      }
      else if (key.equalsIgnoreCase("JSONoutput")) {
        JSONOutput = value;
      }
      else if (key.equalsIgnoreCase("articleNumber")) {
        articleNumber = value;
      }
      else {
        configurationFileScanner.close();
        throw new IllegalArgumentException(key + " is an invalid key in the configuration file");
      }
    }
    configurationFileScanner.close();
    download();
  }

/**
  * Returns the file path of the saved response data
  * @return the file path of the saved response data
*/
  public String getJSONOutput() {
    return JSONOutput;
  }

/**
  * Start the download process based on a specified endpoint
  * @throws IllegalArgumentException if the specified endpoint is invalid
  * @throws IOException if there are errors during the download process
*/
  private void download() throws IllegalArgumentException, IOException {
    if (endpoint.equalsIgnoreCase("TheGuardian")) {
      downloadFromTheGuardian();
    }
    else {
      throw new IllegalArgumentException(endpoint + " is an invalid endpoint");
    }
  }

/**
  * Downloads data from TheGuardian endpoint and saves the response data in a file.
  * @throws IllegalArgumentException if url is invalid
  * @throws IOException if there are errors during the download process
*/
  private void downloadFromTheGuardian() throws IllegalArgumentException, IOException {
    String urlString = "";
    //checks if the necessary configuration is set correctly
    if (link.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("Link must be specified");
    }
    if (endpoint.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("Endpoint must be specified");
    }
    if (APIkey.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("APIkey must be specified");
    }
    try {
      //Create the url with the specified parameters
      urlString = link + "?api-key="+ APIkey + "&show-fields=all";
      if (!articleNumber.equalsIgnoreCase("")) {
        urlString = urlString + "&page-size=" + articleNumber;
      }
      if (!query.equalsIgnoreCase("")) {
        urlString = urlString + "&q=" + query.replace(" ", "+");
      }
      //Establishes a connection and retrieves the response data
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.connect();
      Scanner scanner = new Scanner(connection.getInputStream());
      String result = "";
      while (scanner.hasNextLine()) {
        result = result + scanner.nextLine() + "\n";
      }
      //Saves the response data to the specified output file
      Outputter toJSON = new Outputter(false, true, JSONOutput);
      toJSON.print(result);
      connection.disconnect(); //disconnect the connection
    }
    catch (MalformedURLException e) {
      throw new IOException(urlString + " is an invalid url");
    }
    catch (IOException e) {
      throw new IOException("Error during the connection with The Guardian API: " + urlString);
    }
  }
}
