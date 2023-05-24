package com.ArticleAnalyzer.DataManagement;

/**
 *
 * The Outputter class provides functionality for printing Article objects, Library objects, and LinkedHashMap objects.
 * It offers options to print the output to the console or to a file. The class supports various print methods for different types of data.
 * 
 * Public Constructors:
 * - Outputter(): Constructs an Outputter object with default settings.
 * - Outputter(boolean toConsole, boolean toFile): Constructs an Outputter object with specified console and file output options.
 * - Outputter(boolean toConsole, boolean toFile, String file): Constructs an Outputter object with specified console and file output options, and sets the file path for printing to a file.
 * 
 * Public Methods:
 * - getFile(): Returns the file path for outputting to a file.
 * - getToConsole(): Returns a boolean indicating if outputting to the console is enabled.
 * - setFile(String toSet): Sets the file path for outputting to a file.
 * - getToFile(): Returns a boolean indicating if outputting to a file is enabled.
 * - print(String toPrint): Prints a string to the console or file, based on the output options.
 * - print(Article toPrint): Prints the details of an Article object to the console or file, based on the output options.
 * - print(Library toPrint): Prints the details of all Article objects in a Library object to the console or file, based on the output options.
 * - print(LinkedHashMap<String, Integer> toPrint): Prints the contents of a LinkedHashMap object to the console or file, based on the output options.
 * 
 * Private Methods:
 * - printToConsole(String toPrint): Prints a string to the console.
 * - printToFile(String toPrint): Prints a string to a file specified by the file path.
 * - check(): Checks the validity of the file path for outputting to a file.
*/

import java.io.FileWriter;
import java.io.File;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

public class Outputter {
    private boolean toConsole;
    private boolean toFile;
    private String file;

    /**
     * Constructs an Outputter object with default settings.
     * By default, printing to console and file is disabled.
     */
    public Outputter() {
        this.toConsole = false;
        this.toFile = false;
        this.file = "";
    }

    /**
     * Constructs an Outputter object with the specified settings.
     *
     * @param toConsole flag indicating whether to print to console
     * @param toFile    flag indicating whether to print to a file
     * @throws IOException if an I/O error occurs
     */
    public Outputter(boolean toConsole, boolean toFile) throws IOException {
        this.toConsole = toConsole;
        this.toFile = toFile;
        this.file = "";
    }

    /**
     * Constructs an Outputter object with the specified settings and file path.
     *
     * @param toConsole flag indicating whether to print to console
     * @param toFile    flag indicating whether to print to a file
     * @param file      the path of the file to print to
     * @throws IOException if an I/O error occurs
     */
    public Outputter(boolean toConsole, boolean toFile, String file) throws IOException {
        this(toConsole, toFile);
        this.file = file;
        check();
    }

    /**
     * Returns the file path for printing.
     *
     * @return the file path
     */
    public String getFile(){
        return this.file;
    }

    /**
     * Returns whether printing to console is enabled.
     *
     * @return true if printing to console is enabled, false otherwise
     */
    public boolean getToConsole(){
        return this.toConsole;       
    }

    /**
     * Sets the file path for printing.
     *
     * @param toSet the file path to set
     * @throws IOException if an I/O error occurs
     */
    public void setFile(String toSet) throws IOException{
        this.file = toSet;   
        check();    
    }

    /**
     * Returns whether printing to a file is enabled.
     *
     * @return true if printing to a file is enabled, false otherwise
     */
    public boolean getToFile(){
        return this.toFile;
    }

    
    /**
     * Prints a string to the console or file, based on the configured settings.
     *
     * @param toPrint the string to print
     * @throws IOException if an I/O error occurs
     */
    public void print(String toPrint) throws IOException{
        if(this.toConsole){
            this.printToConsole(toPrint);
        }
        if(this.toFile){
            this.printToFile(toPrint);
        }
    }

    /**
     * Prints the given string to the console.
     *
     * @param toPrint the string to be printed
     */
    private void printToConsole(String toPrint){
        System.out.println(toPrint);
    }

    /**
     * Prints the given string to a file.
     *
     * @param toPrint the string to be printed
     * @throws IOException if an error occurs while writing to the file
     */
    private void printToFile(String toPrint) throws IOException{
        try {
            File file = new File(this.file);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(this.file, true);
            fileWriter.write(toPrint);
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + this.file, e);
        }
    }

    /**
     * Prints the properties of an Article object.
     *
     * @param toPrint the Article object to be printed
     * @throws IOException if an error occurs while printing
     */
    public void print(Article toPrint) throws IOException{
        print("{");
        if(!toPrint.getId().equals("")){
            print("\"id\" : \"" + toPrint.getId() + "\",");
        }
        if(!toPrint.getType().equals("")){
            print("\"type\" : \"" + toPrint.getType() + "\",");
        }
        if (!toPrint.getSectionID().equals("")) {
            print("\"sectionID\" : \"" + toPrint.getSectionID() + "\",");
        }
        if (!toPrint.getWebUrl().equals("")) {
            print("\"webUrl\" : \"" + toPrint.getWebUrl() + "\",");
        }
        if (!toPrint.getApiUrl().equals("")) {
            print("\"apiUrl\" : \"" + toPrint.getApiUrl() + "\",");
        }
        if (!toPrint.getTrailText().equals("")) {
            print("\"trailText\" : \"" + toPrint.getTrailText() + "\",");
        }
        if (!toPrint.getHeadline().equals("")) {
            print("\"headline\" : \"" + toPrint.getHeadline() + "\",");
        }
        if (!toPrint.getByline().equals("")) {
            print("\"byline\" : \"" + toPrint.getByline() + "\",");
        }
        if (!toPrint.getPublication().equals("")) {
            print("\"publication\" : \"" + toPrint.getPublication() + "\",");
        }
        if (!toPrint.getInternalPageCode().equals("")) {
            print("\"internalPageCode\" : \"" + toPrint.getInternalPageCode() + "\",");
        }
        if (!toPrint.getProductionOffice().equals("")) {
            print("\"productionOffice\" : \"" + toPrint.getProductionOffice() + "\",");
        }
        if (!toPrint.getBody().equals("")) {
            print("\"body\" : \"" + toPrint.getBody() + "\",");
        }
        if (!toPrint.getStandfirst().equals("")) {
            print("\"standfirst\" : \"" + toPrint.getStandfirst() + "\",");
        }
        if (!toPrint.getShortUrl().equals("")) {
            print("\"shortUrl\" : \"" + toPrint.getShortUrl() + "\",");
        }
        if (!toPrint.getThumbnail().equals("")) {
            print("\"thumbnail\" : \"" + toPrint.getThumbnail() + "\",");
        }
        if (!toPrint.getTitle().equals("")) {
            print("\"title\" : \"" + toPrint.getTitle() + "\",");
        }
        if (!toPrint.getFullText().equals("")) {
            print("\"fulltext\" : \"" + toPrint.getFullText() + "\",");
        }
        if(!toPrint.getSourceSet().equals("")) {
            print("\"source_set\" : \"" + toPrint.getSourceSet() + "\",");
        }
        if(!toPrint.getSource().equals("")) {
            print("\"source\" : \"" + toPrint.getSource() + "\",");
        }
        if(!toPrint.getMain().equals("")) {
            print("\"main\" : \"" + toPrint.getMain() + "\",");
        }
        if(!toPrint.getBylineHtml().equals("")) {
            print("\"byline_html\" : \"" + toPrint.getBylineHtml() + "\",");
        }
        if(!toPrint.getBodyText().equals("")) {
            print("\"body_text\" : \"" + toPrint.getBodyText() + "\",");
        }
        if(!toPrint.getLang().equals("")) {
            print("\"lang\" : \"" + toPrint.getLang() + "\",");
        }  
        if (toPrint.getScore() != -1.0f) {
            print("\"starRating\" : " + toPrint.getScore() + ",");
        }
        if (toPrint.getStarRating() != -1) {
            print("\"starRating\" : " + toPrint.getStarRating() + ",");
        }
        if (toPrint.getNewspaperPageNumber() != -1) {
            print("\"newspaperPageNumber\" : " + toPrint.getNewspaperPageNumber() + ",");
        }
        if (toPrint.getCharCount() != -1) {
            print("\"charCount\" : " + toPrint.getCharCount() + ",");
        }
        if (toPrint.getWordcount() != -1) {
            print("\"wordCount\" : " + toPrint.getWordcount() + ",");
        }
        if (toPrint.getWebPublicationDate() != null) {
            print("\"webPublicationDate\": \"" + toPrint.getWebPublicationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"");
        }
        if (toPrint.getLastModified() != null) {
            print("\"lastModified\": \"" + toPrint.getLastModified().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"");
        }
        if (toPrint.getCommentCloseDate() != null) {
            print("\"commentCloseDate\": \"" + toPrint.getCommentCloseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"");
        }
        if (toPrint.getFirstPublicationDate() != null) {
            print("\"firstPublicationDate\": \"" + toPrint.getFirstPublicationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"");
        }
        if (toPrint.getNewspaperEditionDate() != null) {
            print("\"newspaperEditionDate\": \"" + toPrint.getNewspaperEditionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"");
        }
        if (toPrint.getCommentable() != null) {
            print("\"commentable\": " + toPrint.getCommentable() + ",");
        }
        if (toPrint.getPremoderated() != null) {
            print("\"premoderated\": " + toPrint.getPremoderated() + ",");
        }
        if (toPrint.getAllowUGc() != null) {
            print("\"allowUGc\": " + toPrint.getAllowUGc() + ",");
        }
        if (toPrint.getShouldHideAdverts() != null) {
            print("\"shouldHideAdverts\": " + toPrint.getShouldHideAdverts() + ",");
        }
        if (toPrint.getLiveBloggingNow() != null) {
            print("\"liveBloggingNow\": " + toPrint.getLiveBloggingNow() + ",");
        }
        if (toPrint.getHasStoryPackage() != null) {
            print("\"hasStoryPackage\": " + toPrint.getHasStoryPackage() + ",");
        }
        if (toPrint.getShowInRelatedContent() != null) {
            print("\"showInRelatedContent\": " + toPrint.getShowInRelatedContent() + ",");
        }
        if (toPrint.getLegallySensitive() != null) {
            print("\"legallySensitive\": " + toPrint.getLegallySensitive() + ",");
        }
        if (toPrint.getIsLive() != null) {
            print("\"isLive\": " + toPrint.getIsLive() + ",");
        }
        if (toPrint.getIsInappropriateForSponsorShip() != null) {
            print("\"isInappropriateForSponsorship\": " + toPrint.getIsInappropriateForSponsorShip() + ",");
        }
        if (toPrint.getShouldHideReaderRevenue() != null) {
            print("\"shouldHideReaderRevenue\": " + toPrint.getShouldHideReaderRevenue() + ",");
        }  
        if (toPrint.getShowAffiliateLinks() != null) {
            print("\"showAffiliateLinks\": " + toPrint.getShowAffiliateLinks() + ",");
        }
        if (toPrint.getShowTableOfContents() != null) {
            print("\"showTableOfContents\": " + toPrint.getShowTableOfContents() + ",");
        }
        print("}");
    }

    /**
     * Prints the articles in a Library object.
     *
     * @param toPrint the Library object containing articles to be printed
     * @throws IOException if an error occurs while printing
     */
    public void print(Library toPrint) throws IOException{
        for(int i = 1; i <= toPrint.getTotalArticleNumber(); i++){
            print(toPrint.getArticle(i));
        }
    }

    /**
     * Prints the key-value pairs in a LinkedHashMap.
     *
     * @param toPrint the LinkedHashMap to be printed
     * @throws IOException if an error occurs while printing
     */
    public void print(LinkedHashMap<String, Integer> toPrint) throws IOException{
        for (String key : toPrint.keySet()) {
            print(key + " " + toPrint.get(key)+"\n");
        }
    }

    /**
     * Checks if the necessary conditions are met before printing to a file.
     *
     * @throws IOException if the file is not set or cannot be created
     */
    private void check() throws IOException{
        if(toFile){
            if(this.file==""){
                throw new IOException("File were to print not setted");
            }
            File file = new File(this.file);
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
        }
    }
}
