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
    private String JSONoutput;
    private int articlesPerPage;
    private int initialPage;
    private int totalPageNumber;

    /**
     * Initializes all attributes of the class to their default values.
     */
    public Downloader() {
        endpoint = null;
        configurationFile = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = null;
        articlesPerPage = -1;
        initialPage = -1;
        totalPageNumber = -1;
    }

    /**
     * Initializes the Downloader object with the specified settings in the configuration file and downloads the articles.
     *
     * @param cf the path to the configuration file
     * @throws FileNotFoundException     if the configuration file does not exist
     * @throws IllegalArgumentException  if the configuration file contains invalid contents
     * @throws IOException               if there are errors during the download process
     */
    public Downloader(String cf) throws FileNotFoundException, IllegalArgumentException, IOException {
        configurationFile = new File(cf);
        endpoint = null;
        link = null;
        query = null;
        APIkey = null;
        JSONoutput = "downloaded.json";
        articlesPerPage = -1;
        initialPage = 1;
        totalPageNumber = 1;
        Scanner configurationFileScanner = new Scanner(this.configurationFile);
        // Read the configuration file and set all the variables accordingly
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
            } catch (IndexOutOfBoundsException e) {
                configurationFileScanner.close();
                throw new IllegalArgumentException("Invalid argument in configuration file");
            }

            if (key.equalsIgnoreCase("link")) {
                link = value;
            } else if (key.equalsIgnoreCase("endpoint")) {
                endpoint = value;
            } else if (key.equalsIgnoreCase("query")) {
                query = value;
            } else if (key.equalsIgnoreCase("APIkey")) {
                APIkey = value;
            } else if (key.equalsIgnoreCase("JSONoutput")) {
                JSONoutput = value;
            } else if (key.equalsIgnoreCase("articlesPerPage")) {
                try {
                    articlesPerPage = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    configurationFileScanner.close();
                    throw new IllegalArgumentException("articlesPerPage contains a non-int value");
                }
            } else if (key.equalsIgnoreCase("initialPage")) {
                try {
                    initialPage = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    configurationFileScanner.close();
                    throw new IllegalArgumentException("initialPage contains a non-int value");
                }
            } else if (key.equalsIgnoreCase("totalPageNumber")) {
                try {
                    totalPageNumber = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    configurationFileScanner.close();
                    throw new IllegalArgumentException("totalPageNumber contains a non-int value");
                }
            } else {
                configurationFileScanner.close();
                throw new IllegalArgumentException(key + " is an invalid key but it is in the configuration file");
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
    public String getJSONoutput() {
        return JSONoutput;
    }

    /**
     * Starts the download process based on a specified endpoint.
     *
     * @throws IllegalArgumentException if the specified endpoint is invalid
     * @throws IOException              if there are errors during the download process
     */
    private void download() throws IOException, IllegalArgumentException {
        if (endpoint == null) {
            throw new IllegalArgumentException("Endpoint needs to be defined");
        }
        if (endpoint.equalsIgnoreCase("TheGuardian")) {
            downloadFromTheGuardian();
        } else {
            throw new IllegalArgumentException(endpoint + " is an invalid endpoint");
        }
    }

    /**
     * Downloads data from TheGuardian endpoint and saves the response data in a file.
     * It downloads a specified number of pages based on the given page size and merges all the responses from each downloaded page.
     *
     * @throws IllegalArgumentException if different contents in the configuration file are invalid
     * @throws IOException if there are errors during the download process
     */
    private void downloadFromTheGuardian() throws IOException {
        String urlString = "";
        // Check if anything is configured incorrectly
        if (link == null || link.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("link needs to be configured");
        }
        if (endpoint == null || endpoint.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("endpoint needs to be configured");
        }
        if (APIkey == null || APIkey.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("APIkey needs to be configured");
        }
        if (articlesPerPage > 200 || articlesPerPage < -1) {
            throw new IllegalArgumentException("articlesPerPage value is invalid (it must be an int between 0 and 200 inclusive)");
        }

        try {
            String mergedResults = "";
            Outputter toJSON = new Outputter(false, true, JSONoutput);
            for (int i = 0; i < totalPageNumber; i++) {
                // Merge all variables into the URL
                urlString = link + "&show-fields=all";
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

                // Start URL connection and process the response
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Read the response and append it to the mergedResults
                Scanner scanner = new Scanner(connection.getInputStream());
                StringBuilder result = new StringBuilder();
                while (scanner.hasNextLine()) {
                    result.append(scanner.nextLine()).append("\n");
                }

                mergedResults = theGuardianMerge(mergedResults, result.toString());

                connection.disconnect();
            }

            toJSON.print(mergedResults);

        } catch (MalformedURLException e) {
            throw new IOException(urlString + " is an invalid link");
        } catch (IOException e) {
            throw new IOException("Error with connection to the API with URL: " + urlString);
        }
    }

    /**
     * Returns all responses provided by The Guardian merged into a single JSON response with all the settings modified.
     *
     * @param firstJSON  the responses that have already been merged
     * @param secondJSON the response that needs to be merged with the others
     * @return all the responses provided by The Guardian merged into a single JSON response
     */
    private String theGuardianMerge(String firstJSON, String secondJSON) {
        if (firstJSON.isEmpty()) {
            return secondJSON;
        }
        if (secondJSON.isEmpty()) {
            return firstJSON;
        }
        JSONObject results = new JSONObject();
        JSONObject content = new JSONObject();
        JSONObject first = new JSONObject(firstJSON).getJSONObject("response");
        JSONObject second = new JSONObject(secondJSON).getJSONObject("response");

        content.put("status", first.getString("status"));
        content.put("userTier", first.getString("userTier"));
        content.put("total", first.getInt("total") + second.getInt("total"));
        content.put("pageSize", first.getInt("pageSize"));
        content.put("pages", first.getInt("pages") + second.getInt("pages"));
        content.put("orderBy", first.getString("orderBy"));

        JSONArray articles = new JSONArray();
        JSONArray firstArticles = first.getJSONArray("results");
        JSONArray secondArticles = second.getJSONArray("results");

        articles = firstArticles;

        for (int i = 0; i < secondArticles.length(); i++) {
            articles.put(secondArticles.getJSONObject(i));
        }

        content.put("results", articles);

        results.put("response", content);
        return results.toString();
    }
}
