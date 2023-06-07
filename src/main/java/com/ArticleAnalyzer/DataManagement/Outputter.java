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
 * It offers options to print the output to the console or to a file.
*/
public class Outputter {
    private boolean toConsole;
    private boolean toFile;
    private String file;

    /**
     * Initializes all attributes to their default values.
    */
    public Outputter() {
        toConsole = false;
        toFile = false;
        file = "";
    }

    /**
     * Initializes the options to print output to the console or to a file.
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
     * @see Outputter(boolean, boolean)
     * @param tc the option to print the output to the console
     * @param tf the option to print the output to a file
     * @param fileName the file path
     * @throws IOException if the option to print to a file is active and the file path is not specified
    */
    public Outputter(boolean tc, boolean tf, String fileName) throws IOException {
        this(tc, tf);
        setFile(fileName);
    }

    /**
     * Returns the file path.
     * @return the file path
    */
    public String getFile() {
        return file;
    }

    /**
     * Returns whether printing to console is enabled.
     * @return true if printing to console is enabled, false otherwise.
    */
    public boolean getToConsole() {
        return toConsole;
    }

    /**
     * Returns whether printing to a file is enabled.
     * @return true if printing to a file is enabled, false otherwise.
    */
    public boolean getToFile() {
        return toFile;
    }

    /**
     * Sets the file path for printing.
     * @param fileName the file path to set
     * @throws IOException if an I/O error occurs
    */
    public void setFile(String fileName) throws IOException {
        file = fileName;
        check();
    }

    /**
      * Prints the given string to the console or to a file, based on the values assumed by the class attributes
      * @see printToConsole(String)
      * @see printToFile(String)
      * @param toPrint the string to print to the console or to a file, based on to the values assumed by the class attributes
      * @throws IOException if there are errors while writing the file
    */
    public void print(String toPrint) throws IOException {
        if (toConsole) {
            printToConsole(toPrint);
        }
        if (toFile) {
            printToFile(toPrint);
        }
    }

    /**
     * Prints the given string to console.
     * @param toPrint the string to be printed
    */
    private void printToConsole(String toPrint) {
        System.out.print(toPrint);
    }

    /**
     * Prints the given string to a file.
     * @param toPrint the string to be printed
     * @throws IOException if an error occurs while writing to the file
    */
    private void printToFile(String toPrint) throws IOException {
        try {
            File f = new File(file);
            f.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(toPrint);
            fileWriter.close();
        }
        catch (IOException e) {
            throw new IOException("Error writing to file: " + file);
        }
    }

    /**
     * Prints the properties of an Article object.
     * @see print(Article)
     * @param toPrint the Article object to be printed
     * @throws IOException if an error occurs while printing
    */
    public void print(Article toPrint) throws IOException {
        print("{");
        if (!toPrint.getIdentifier().equals("")) {
            print("\"id\" : \"" + toPrint.getIdentifier() + "\",");
        }
        if (!toPrint.getSection().equals("")) {
            print("\"section\" : \"" + toPrint.getSection() + "\",");
        }
        if (!toPrint.getSource().equals("")) {
            print("\"source\" : \"" + toPrint.getSource() + "\",");
        }
        if (!toPrint.getPublicationDate().equals("")) {
            print("\"publicationDate\" : \"" + toPrint.getPublicationDate() + "\",");
        }
        if (!toPrint.getLanguage().equals("")) {
            print("\"language\" : \"" + toPrint.getLanguage() + "\",");
        }
        if (!toPrint.getUrl().equals("")) {
            print("\"url\" : \"" + toPrint.getUrl() + "\",");
        }
        if (!toPrint.getTitle().equals("")) {
            print("\"title\" : \"" + toPrint.getTitle() + "\",");
        }
        if (!toPrint.getSubtitle().equals("")) {
            print("\"subtitle\" : \"" + toPrint.getSubtitle() + "\",");
        }
        if (!toPrint.getBody().equals("")) {
            print("\"body\" : \"" + toPrint.getBody() + "\",");
        }
        if (toPrint.getNewspaperPage() != 0) {
            print("\"newspaperPage\" : \"" + toPrint.getNewspaperPage() + "\",");
        }
        if (toPrint.getWords() != 0) {
            print("\"words\" : \"" + toPrint.getWords() + "\"");
        }
        print("}\n");
    }

    /**
     * Prints a Library object.
     * @see print(Article)
     * @param toPrint the Library object containing articles to be printed
     * @throws IOException if an error occurs while printing
    */
    public void print(Library toPrint) throws IOException {
      for (int i = 0; i < toPrint.getTotalArticlesNumber(); i++) {
        print(toPrint.getArticle(i));
        if (i != toPrint.getTotalArticlesNumber() - 1) {
          print(",\n");
        }
      }
    }

    /**
     * Prints the given LinkedHashMap object with key-value pairs.
     * @param toPrint the LinkedHashMap object with key-value pairs to print
     * @throws IOException if an error occurs while printing
    */
    public void print(LinkedHashMap<String, Integer> toPrint) throws IOException {
        for (String key : toPrint.keySet()) {
            print(key + " " + toPrint.get(key) + "\n");
        }
    }

    /**
      * Checks and throws the IOException exception if the option to print to a file is active and the file path is not specified.
      * It also takes care of recreating the file if it already exists or simply creating it if it doesn't exist
      * @throws IOException if the option to print to a file is active and the file path is not specified
    */
    private void check() throws IOException {
        if (toFile) {
            if (file.equals("")) {
                throw new IOException("File where to print not setted");
            }
            File f = new File(file);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
        }
    }
}
