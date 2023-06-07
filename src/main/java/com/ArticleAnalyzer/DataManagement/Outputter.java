package com.ArticleAnalyzer.DataManagement;

import java.io.FileWriter;
import java.io.File;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

/**
 * The Outputter class provides functionality for printing various objects such as strings, Article objects, Library objects, and LinkedHashMap objects.
 *  It offers options to print the output to the console or to a file.
 */
public class Outputter {
    private boolean toConsole;
    private boolean toFile;
    private String file;

    /**
     * Initializes all attributes of the Outputter class to their default values.
     */
    public Outputter() {
        this.toConsole = false;
        this.toFile = false;
        this.file = "";
    }

  /**
    * Initializes the options to print output to the console or to a file.
    *
    * @param tc the option to print the output to the console
    * @param tf the option to print the output to a file
    */
  public Outputter(boolean tc, boolean tf) {
    toConsole = tc;
    toFile = tf;
    file = "";
  }

  /**
    * Initializes the file path with the given value
    * 
    * @see Outputter(boolean tc, boolean tf)
    * @param tc the option to print the output to the console
    * @param tf the option to print the output to a file
    * @param fn the file path
    * @throws IOException if the option to print to a file is active and the file path is not specified
    */
  public Outputter(boolean tc, boolean tf, String fileName) throws IOException {
    this(tc, tf);
    setFile(fileName);
 }

    /**
     * Returns the file path.
     *
     * @return the file path
     */
    public String getFile(){
        return this.file;
    }

    /**
     * Returns whether printing to console is enabled.
     *
     * @return true if printing to console is enabled, false otherwise.
     */
    public boolean getToConsole(){
        return this.toConsole;       
    }

    /**
     * Returns whether printing to a file is enabled.
     *
     * @return true if printing to a file is enabled, false otherwise.
     */
    public boolean getToFile(){
        return this.toFile;
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
      * Prints the given string to the console or to a file, based on the values assumed by the class attributes
      *
      * @see printToConsole(String s)
      * @see printToFile(String s)
      * @param toPrint the string to print to the console or to a file, based on to the values assumed by the class attributes
      * @throws IOException if there are errors while writing the file
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
     * Prints the given string to console.
     *
     * @param toPrint the string to be printed
     */
    private void printToConsole(String toPrint){
        System.out.print(toPrint);
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
     * @see #print(Article)
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
        if (!toPrint.getTitle().equals("")) {
            print("\"title\" : \"" + toPrint.getTitle() + "\",");
        }
        if(!toPrint.getMain().equals("")) {
            print("\"main\" : \"" + toPrint.getMain() + "\",");
        }
        if(!toPrint.getBodyText().equals("")) {
            print("\"body_text\" : \"" + toPrint.getBodyText() + "\",");
        }
        if(!toPrint.getLang().equals("")) {
            print("\"lang\" : \"" + toPrint.getLang() + "\",");
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
        print("}");
    }

    /**
     * Prints a Library object.
     *
     * @see #print(Article)
     * @param toPrint the Library object containing articles to be printed
     * @throws IOException if an error occurs while printing
     */
    public void print(Library toPrint) throws IOException{
        print("{\n");
        print("\"article\":\n");
        print("[\n");
        for(int i = 1; i <= toPrint.getTotalArticleNumber(); i++){
            print(toPrint.getArticle(i));
            if(i!=toPrint.getTotalArticleNumber()){
                print(",");
            }
        }
        print("]");
        print("}");
    }

    /**
     * Prints the given LinkedHashMap object with key-value pairs.
     *
     * @param toPrint the LinkedHashMap object with key-value pairs to print
     * @throws IOException if an error occurs while printing
     */
    public void print(LinkedHashMap<String, Integer> toPrint) throws IOException{
        for (String key : toPrint.keySet()) {
            print(key + " " + toPrint.get(key)+"\n");
        }
    }

    /**
      * Checks and throws the IOException exception if the option to print to a file is active and the file path is not specified.
      * It also takes care of recreating the file if it already exists or simply creating it if it doesn't exist
      *
      * @throws IOException if the option to print to a file is active and the file path is not specified
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
