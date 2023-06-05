package com.ArticleAnalyzer.DataManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
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
  private int articlesPerPage;
  private int initialPage;
  private int totalPageNumber;

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
    articlesPerPage = -1;
    initialPage = -1;
    totalPageNumber = -1;
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
    articlesPerPage = -1;
    initialPage = 1;
    totalPageNumber = 1;

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
      else if (key.equalsIgnoreCase("JSONOutput")) {
        JSONOutput = value;
      }
      else if (key.equalsIgnoreCase("articlesPerPage")) {
        try {
          articlesPerPage = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
          configurationFileScanner.close();
          throw new IllegalArgumentException("articlesPerPage must be an int value");
        }
      }
      else if (key.equalsIgnoreCase("initialPage")) {
        try {
          initialPage = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
          configurationFileScanner.close();
          throw new IllegalArgumentException("initialPage must be an int value");
        }
      }
      else if (key.equalsIgnoreCase("totalPageNumber")) {
        try {
          totalPageNumber = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
          configurationFileScanner.close();
          throw new IllegalArgumentException("totalPageNumber must be an int value");
        }
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
  * Start the download process basing on a specified endpoint
  * @throws IllegalArgumentException if the specified endpoint is invalid
  * @throws IOException if there are errors during the download process
*/
  private void download() throws IllegalArgumentException, IOException {
    if (endpoint.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("Endpoint must be specified");
    }
    if (endpoint.equalsIgnoreCase("TheGuardian")) {
      downloadFromTheGuardian();
    }
    else {
      throw new IllegalArgumentException(endpoint + " is an invalid endpoint");
    }
  }

/**
  * Downloads data from TheGuardian endpoint and saves the response data in a file.
  * It downloads a specified number of pages basing on the given page size and merge all the responses from each downloaded page
  * @throws IllegalArgumentException if different contents in the configuration file are invalid
  * @throws IOException if there are errors during the download process
*/
  private void downloadFromTheGuardian() throws IllegalArgumentException, IOException {
    String urlString = "";
    //checks if the necessary configuration is set correctly
    if (link.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("Link must be specified");
    }
    if (APIkey.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("APIkey must be specified");
    }
    if (articlesPerPage > 200 || articlesPerPage < -1) {
      throw new IllegalArgumentException("articlesPerPage value is invalid (it must be an int between 0 and 200 included)");
    }
    try {
      String mergedResults = "";
      Outputter toJSON = new Outputter(false, true, JSONOutput);
      for (int i = 0; i < totalPageNumber; i++) {
        //Create the web url with the specified parameters for each page formed of the page-size given
        urlString = link + "&show-fields=all";
        urlString = urlString + "&page=" + initialPage;
        initialPage++;
        if (articlesPerPage != -1) {
          urlString = urlString + "&page-size=" + articlesPerPage;
        }
        urlString = urlString + "&api-key=" + APIkey;
        if (!query.equals("")) {
          urlString = urlString + "&q=" + query.replace(" ", "+");
        }

        //Establishes a connection, retrieves and elaborates the response
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        Scanner scanner = new Scanner(connection.getInputStream());
        String result = "";
        while (scanner.hasNextLine()) {
          result = result + scanner.nextLine() + "\n";
        }

        //Adds the response retrieved by the current download connection in the response merged before
        mergedResults = mergeTheGuardianResponses(mergedResults, result);

        //Disconnects the connection
        connection.disconnect();
      }
      toJSON.print(mergedResults);
    }
    catch (MalformedURLException e) {
      throw new IOException(urlString + " is an invalid link");
    }
    catch (IOException e) {
      throw new IOException("Error with connection to the API with URL: " + urlString);
    }
  }

/**
  * Returns all the responses given by The Guardian merged in a unique JSON response with all the modified settings
  * @param merged the responses already merged
  * @param toMerge the response to merge together with the others
  * @return all the responses given by The Guardian merged in a unique JSON response
*/
  private String mergeTheGuardianResponses(String merged, String toMerge) {
    if (merged == "") {
      return toMerge;
    }
    if (toMerge == "") {
      return merged;
    }
    JSONObject results = new JSONObject();
    JSONObject content = new JSONObject();
    //Retrives the response already merged
    JSONObject added = (JSONObject)new JSONObject(merged).get("response");
    //Retrives the response to merge
    JSONObject toAdd = (JSONObject)new JSONObject(toMerge).get("response");

    //Create the new JSON response with all the merged results specifying the correct settings for the response
    content.put("status", added.getString("status"));
    content.put("userTier", added.getString("userTier"));
    content.put("total", added.getInt("total") + toAdd.getInt("total"));
    content.put("pageSize", added.getInt("pageSize"));
    content.put("pages", added.getInt("pages") + toAdd.getInt("pages"));
    content.put("orderBy", added.getString("orderBy"));

    JSONArray articles = new JSONArray();
    //Retrives all the articles contained in the response already merged
    JSONArray articlesAdded = (JSONArray)added.get("results");
    //Retrieves all the articles contained in the response to merge
    JSONArray articlesToAdd = (JSONArray)toAdd.get("results");

    articles = articlesAdded;

    //Merge each article of the response to merge to the already merge articles
    for (int i = 0; i < articlesToAdd.length(); i++) {
      articles.put(articlesToAdd.getJSONObject(i));
    }

    //Adds the completely merged articles
    content.put("results", articles);

    //Adds the complete response created
    results.put("response", content);
    return results.toString();
  }
}
