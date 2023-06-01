package com.ArticleAnalyzer.DataProcesser;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
  * The Elaborator class retrieves from a Library object which contains Article objects the articles words and their occurrences (counted once in each article) and save them in a LinkedHashMap object.
*/
public class Elaborator {
  private LinkedHashMap <String, Integer> words;
  private Library library;

/**
  * Initializes the Library object with the given Library object and create an empty LinkedHashMap object.
  * @param l the Library object
  * It also retrieves from the specified Library object the articles words and their occurrences (counted once in each article) and save them in the LinkedHashMap object
  * @see analyze()
*/
  public Elaborator(Library l) {
    library = l;
    words = new LinkedHashMap<String, Integer>();
    analyze();
  }

/**
  * Retrieves from the Library object the articles words and their occurrences (counted once in each article) and save them in the LinkedHashMap object.
  * It also orders the LinkedHashMap object basing on which word contains more occurrences
  * @see orderMap()
*/
  private void analyze() {
    for (int i = 0; i < library.getTotalArticleNumber(); i++) {
      HashMap<String, Boolean> founded = new HashMap<String, Boolean>();
      Article article = library.getArticle(i);
      String text = article.getTitle() + article.getBody();
      Scanner scanner = new Scanner(text);
      scanner.useDelimiter("\\W+");
      while (scanner.hasNext()) {
        String word = scanner.next().toLowerCase();
        if (!founded.containsKey(word)) {
          founded.put(word, true);
          words.merge(word, 1, Integer::sum);
        }
      }
      scanner.close();
    }
    orderMap();
  }

/**
  * Orders the LinkedHashMap object basing on which word contains more occurrences
*/
  private void orderMap() {
    LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
    while (!words.isEmpty()) {
      Map.Entry<String, Integer> maxEntry = words.entrySet().iterator().next();
      Iterator<Map.Entry<String, Integer>> iterator = words.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<String, Integer> entry = iterator.next();
        if (entry.getValue() > maxEntry.getValue()) {
          maxEntry = entry;
        }
        if (entry.getValue() == maxEntry.getValue()) {
          if (entry.getKey().compareToIgnoreCase(maxEntry.getKey()) < 0) {
            maxEntry = entry;
          }
        }
      }
      temp.put(maxEntry.getKey(), maxEntry.getValue());
      words.remove(maxEntry.getKey());
    }
    words = temp;
  }

/**
  * Returns the LinkedHashMap object which contains the words and their occurrences
  * @return the LinkedHashMap object which contains the words and their occurrences
*/
  public LinkedHashMap<String, Integer> getWords() {
    return words;
  }

/**
  * Returns a new LinkedHashMap object which contains the words which are not included in the given array of strings and their occurrences
  * @param toExclude the array of strings to exclude
  * @return the LinkedHashMap object which contains the words which are not included in the given array of strings and their occurrences
*/
  public LinkedHashMap<String, Integer> getWords(String[] toExclude) {
    LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<String, Integer>();
    boolean good = false;
    while(!good) {
      Map.Entry<String, Integer> entry = words.entrySet().iterator().next();
      good = true;
      for (String str : toExclude) {
        if (str.equals(entry.getKey())) {
          good = false;
        }
      }
      if (good) {
        toReturn.put(entry.getKey(), entry.getValue());
      }
    }
    return toReturn;
  }

/**
  * Returns a new LinkedHashMap object which contains a given number of words and their occurrences
  * @param n the numbers of words and their occurrences to return
  * @return the LinkedHashMap object which contains a given number of words and their occurrences
  * @throws IllegalArgumentException if the number of results to show is invalid
*/
  public LinkedHashMap<String, Integer> getWords(int n) throws IllegalArgumentException {
    LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<String, Integer>();
    if (n == -1) {
      return getWords();
    }
    if (n < -1) {
      throw new IllegalArgumentException("Request a valid number of results");
    }
    for (int i = 0; i < n; i++) {
      try {
        Map.Entry<String, Integer> entry = getNthEntry(i, words);
        toReturn.put(entry.getKey(), entry.getValue());
      }
      catch (NoSuchElementException e) {
        if (toReturn.isEmpty()) {
          throw new IllegalArgumentException("No words found");
        }
        else {
          return toReturn;
        }
      }
    }
    return toReturn;
  }

/**
  * Returns a new LinkedHashMap object which contains a given number of words which are not included in the given array of strings and their occurrences
  * @param toExclude the array of strings to exclude
  * @param n the numbers of words and their occurrences to return
  * @return the LinkedHashMap object which contains a given number of words which are not included in the given array of strings and their occurrences
  * @throws IllegalArgumentException if the number of results to show is invalid
*/
  public LinkedHashMap<String, Integer> getWords(String[] toExclude, int n) throws IllegalArgumentException {
    LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<String, Integer>();
    if (n == -1) {
      return getWords(toExclude);
    }
    if (n < -1) {
      throw new IllegalArgumentException("Request a valid number of results");
    }
    int entryToLoad = 0;
    for (int i = 0; i < n; i++) {
      try {
        boolean good = false;
        while (!good) {
          Map.Entry<String, Integer> entry = getNthEntry(entryToLoad, words);
          good = true;
          for (String str : toExclude) {
            if (str.equals(entry.getKey())) {
              good = false;
            }
          }
          if (good) {
            toReturn.put(entry.getKey(), entry.getValue());
          }
          entryToLoad++;
        }
      }
      catch (NoSuchElementException e) {
        if (toReturn.isEmpty()) {
          throw new IllegalArgumentException("No words found");
        }
        else {
          return toReturn;
        }
      }
    }
    return toReturn;
  }

/**
  * Returns the given entry of the given LinkedHashMap object
  * @param n the entry to return
  * @param map the LinkedHashMap object where to find the entry to return
  * @return the given entry of the given LinkedHashMap object
*/
  private Map.Entry<String, Integer> getNthEntry(int n, LinkedHashMap<String, Integer> map) {
    Map.Entry<String, Integer> nthEntry = null;
    if (n >= 0 && n < map.size()) {
      int i = 0;
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        if (i == n) {
          nthEntry = entry;
          break;
        }
        i++;
      }
    }
    return nthEntry;
  }
}
