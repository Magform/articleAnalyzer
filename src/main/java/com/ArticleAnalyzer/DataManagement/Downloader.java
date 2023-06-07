package com.ArticleAnalyzer.DataManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Downloader class is responsible for downloading data from a specified endpoint based on a configuration file.
 * It currently supports TheGuardian as the implemented endpoint.
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
     * Initializes all attributes of the class to their default values.
    */
    public Downloader() {
        configurationFile = null;
        endpoint = "";
        link = "";
        APIkey = "";
        query = "";
        JSONOutput = "";
        articlesPerPage = -1;
        initialPage = -1;
        totalPageNumber = -1;
    }

    /**
     * Initializes the Downloader object with the specified settings in the configuration file and downloads the articles.
     * @param cf the configuration file path
     * @throws FileNotFoundException if the configuration file does not exist
     * @throws IllegalArgumentException if the configuration file contains invalid contents
     * @throws IOException if there are errors during the download process
    */
    public Downloader(String cf) throws FileNotFoundException, IllegalArgumentException, IOException {
        configurationFile = new File(cf);
        endpoint = "";
        link = "";
        APIkey = "";
        query = "";
        JSONOutput = "downloaded.json";
        articlesPerPage = -1;
        initialPage = 1;
        totalPageNumber = 1;
        Scanner configurationFileScanner = new Scanner(this.configurationFile);
        //Reads the configuration file and sets all the variables accordingly
        while (configurationFileScanner.hasNextLine()) {
            String line = configurationFileScanner.nextLine();
            int splitter = line.indexOf(":");
            if (splitter == -1) {
                configurationFileScanner.close();
                throw new IllegalArgumentException("Invalid configuration file");
            }
            String key = line.substring(0, splitter);
            String value = line.substring(splitter + 1, line.length());
            try {
                value = value.substring(value.indexOf("\"") + 1, value.length());
                value = value.substring(0, value.indexOf("\""));
            }
            catch (IndexOutOfBoundsException e) {
                configurationFileScanner.close();
                throw new IllegalArgumentException("Invalid argument in configuration file");
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
                    throw new IllegalArgumentException("initialPage  must be an int value");
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
     * Returns the file path of the saved response data.
     * @return The file path of the saved response data.
    */
    public String getJSONOutput() {
        return JSONOutput;
    }

    /**
     * Starts the download process basing on a specified endpoint.
     * @throws IllegalArgumentException if the specified endpoint is invalid
     * @throws IOException if there are errors during the download process
    */
    private void download() throws IOException, IllegalArgumentException {
        if (endpoint.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("Endpoint must be defined");
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
     * It downloads a specified number of pages based on the given page size and merges all the responses from each downloaded page.
     * @throws IllegalArgumentException if different contents in the configuration file are invalid
     * @throws IOException if there are errors during the download process
    */
    private void downloadFromTheGuardian() throws IOException {
        String urlString = "";
        //Checks if anything is configured incorrectly
        if (link.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("link needs to be configured");
        }
        if (endpoint.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("endpoint needs to be configured");
        }
        if (APIkey.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("APIkey needs to be configured");
        }
        if (articlesPerPage > 200 || articlesPerPage < -1) {
            throw new IllegalArgumentException("articlesPerPage value is invalid (it must be an int between 0 and 200 inclusive)");
        }
        try {
            String mergedResults = "";
            Outputter toJSON = new Outputter(false, true, JSONOutput);
            for (int i = 0; i < totalPageNumber; i++) {
                //Create the web url with the specified parameters for each page formed of the given page-size
                urlString = link + "show-fields=all";
                urlString = urlString + "&page=" + initialPage;
                initialPage++;
                if (articlesPerPage != -1) {
                    urlString = urlString + "&page-size=" + articlesPerPage;
                }
                if (APIkey != null) {
                    urlString = urlString + "&api-key=" + APIkey;
                }
                if (query != null) {
                    urlString = urlString + "&q=" + query.replace(" ", "+");
                }

                //Establishes an URL connection, processes and retrieves the response
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                Scanner scanner = new Scanner(connection.getInputStream());
                String result = "";
                while (scanner.hasNextLine()) {
                  result = result + scanner.nextLine() + "\n";
                }

                //Adds the response retrieved from the current download connection into the previously merged response
                mergedResults = mergeTheGuardianResponses(mergedResults, result);

                //Terminate the established connection
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
     * Returns all responses provided by The Guardian merged into a unique JSON response with all the settings modified
     * @param merged the responses that have already been merged
     * @param toMerge the response that needs to be merged with the others
     * @return all the responses provided by The Guardian merged into a unique JSON response
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
        //Retrieves the already merged response
        JSONObject added = (JSONObject)new JSONObject(merged).get("response");
        //Retrieves the response that needs to be merged
        JSONObject toAdd = (JSONObject)new JSONObject(toMerge).get("response");

        //Create the new JSON response with all the merged results and specifying the correct settings
        content.put("status", added.getString("status"));
        content.put("userTier", added.getString("userTier"));
        content.put("total", added.getInt("total") + toAdd.getInt("total"));
        content.put("pageSize", added.getInt("pageSize"));
        content.put("pages", added.getInt("pages") + toAdd.getInt("pages"));
        content.put("orderBy", added.getString("orderBy"));

        JSONArray articles = new JSONArray();
        //Retrieves all the articles contained in the response that have already been merged
        JSONArray articlesAdded = (JSONArray)added.get("results");
        //Retrieves all the articles contained in the response that need to be merged
        JSONArray articlesToAdd = (JSONArray)toAdd.get("results");

        articles = articlesAdded;

        //Merges each article of the response to merge to the already merge articles
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
