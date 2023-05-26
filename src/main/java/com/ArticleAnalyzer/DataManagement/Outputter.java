package com.ArticleAnalyzer.DataManagement;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
  * The Outputter class provides functionality for printing Article objects, Library objects and LinkedHashMap objects.
  * It offers options to print the output to the console or to a file.
*/
public class Outputter {
  private boolean toConsole;
  private boolean toFile;
  private String fileName;

/**
  * Initializes all attributes of the class to their default values
*/
  public Outputter() {
    toConsole = false;
    toFile = false;
    fileName = "";
  }

/**
  * Initializes the options to print the output to the console or to a file.
  * @param tc the option to print the output to the console
  * @param tf the option to print the output to a file
*/
  public Outputter(boolean tc, boolean tf) {
    toConsole = tc;
    toFile = tf;
    fileName = "";
  }

/**
  * Initializes the file path with the given value
  * @see Outputter(boolean tc, boolean tf)
  * @param tc the option to print the output to the console
  * @param tf the option to print the output to a file
  * @param fn the file path
  * @throws IOException if the option to print to a file is active and the file path is not specified
*/
  public Outputter(boolean tc, boolean tf, String fn) throws IOException {
    this(tc, tf);
    setFileName(fn);
  }

/**
  * Returns the option to print the output to the console
  * @return the option to print the output to the console
*/
  public boolean getToConsole() {
    return toConsole;
  }

/**
  * Returns the option to print the output to a file
  * @return the option to print the output to a file
*/
  public boolean getToFile(){
    return toFile;
  }

/**
  * Returns the file path
  * @return the file path
*/
  public String getFileName() {
    return fileName;
  }

/**
  * Initializes the file path with the given value
  * @param the file path with the given value
  * @throws IOException if the option to print to a file is active and the file path is not specified
*/
  public void setFileName(String fn) throws IOException {
    fileName = fn;
    check();
  }

/**
  * Prints the given string to the console or to a file, according to the values assumed by the class attributes
  * @see printToConsole(String s)
  * @see printToFile(String s)
  * @param s the string to print to the console or to a file, according to the values assumed by the class attributes
*/
  public void print(String s) throws IOException {
    if (toConsole) {
      printToConsole(s);
    }
    if (toFile) {
      printToFile(s);
    }
  }

/**
  * Prints the given string to the console
  * @param s the string to print to the console
*/
  private void printToConsole(String s) {
    System.out.println(s);
  }

/**
  * Print the given string to a file
  * @param s the string to print to a file
  * @throws IOException if there are errors during the writing of the file
*/
  private void printToFile(String s) throws IOException {
    try {
      File file = new File(fileName);
      file.createNewFile();
      FileWriter fileWriter = new FileWriter(file, true);
      fileWriter.write(s);
      fileWriter.close();
    }
    catch (IOException e) {
      throw new IOException("Errore nella scrittura del file: " + fileName);
    }
  }

/**
  * Print the given Article object to JSON format
  * @see print(String s)
  * @param a the Article object to be printed
  * @throws IOException if there are errors during the writing of the file
*/
  public void print(Article a) throws IOException {
    print("{\n");
    if (!a.getId().equals("")) {
      print("\"id\": \"" + a.getId() + "\",\n");
    }
    if (!a.getUrl().equals("")) {
      print("\"url\": \"" + a.getUrl() + "\",\n");
    }
    if (!a.getSource().equals("")) {
      print("\"source\": \"" + a.getSource() + "\",\n");
    }
    if (!a.getDate().equals("")) {
      print("\"date\": \"" + a.getDate() + "\",\n");
    }
    if (!a.getTitle().equals("")) {
      print("\"title\": \"" + a.getTitle() + "\",\n");
    }
    if (!a.getBody().equals("")) {
      print("\"body\": \"" + a.getBody() + "\"\n");
    }
    print("}\n");
  }

/**
  * Print the Article object contained in the given Library object to JSON format
  * @see print(Article a)
  * @param l the Library object which contains the Article objects to be printed
  * @throws IOException if there are errors during the writing of the file
*/
  public void print(Library l) throws IOException {
    for (int i = 0; i < l.getTotalArticleNumber(); i++) {
      print(l.getArticle(i));
    }
  }

/**
  * Print the given LinkedHashMap object with key-value pairs
  * @param hashMap the LinkedHashMap object with key-value pairs to be printed
  * @throws IOException if there are errors during the writing of the file
*/
  public void print(LinkedHashMap<String, Integer> hashMap) throws IOException {
    for (String key : hashMap.keySet()) {
      print(key + " " + hashMap.get(key) + "\n");
    }
  }

/**
  * Check and throw the IOException exception if the option to print to a file is active and the file path is not specified
  * @throws IOException if the option to print to a file is active and the file path is not specified
*/
  private void check() throws IOException {
    if (toFile) {
      if (fileName == "") {
        throw new IOException("Nome del file non inserito");
      }
      File file = new File(fileName);
      if (file.exists()) {
        file.delete();
      }
      file.createNewFile();
    }
  }
}
