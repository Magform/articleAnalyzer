package com.ArticleAnalyzer.DataProcesser;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * The Elaborator class retrieves words and their occurrences from a Library object, which contains Article objects, and saves them in a LinkedHashMap.
 * The class provides methods to analyze and manipulate the word occurrences data.
 */
public class Elaborator {
    private LinkedHashMap <String, Integer> words;
    private Library toAnalyze;

    /**
     * Initializes the Elaborator class with the given Library object and creates an empty LinkedHashMap.
     *
     * @param toAnalyze the Library object to analyze
     * @see #analyze()
     */
    public Elaborator(Library toAnalyze){
        this.toAnalyze = toAnalyze;
        words = new LinkedHashMap<>();
        analyze();
    }

    /**
     * Retrieves words and their occurrences from the Library object and saves them in the LinkedHashMap.
     * The method also orders the LinkedHashMap in descending order based on the occurrences.
     *
     * @see #orderMap()
     */
    private void analyze(){
        for(int i = 1; i<= toAnalyze.getTotalArticleNumber(); i++){
            Map<String, Boolean> founded = new HashMap<>();
            Article article = toAnalyze.getArticle(i);
            String body = article.getBodyText();
            Scanner scanner = new Scanner(body);
            scanner.useDelimiter("\\W+");
            while (scanner.hasNext()) {
                String word = scanner.next();
                word = word.toLowerCase();
                if(!founded.containsKey(word)){
                    founded.put(word, true);
                    words.merge(word, 1, Integer::sum);
                }
            }
            scanner.close();
        }
        orderMap();
    }

    /**
     * Orders the LinkedHashMap in descending order based on the word occurrences.
     */
    private void orderMap(){
        LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<>();
        while(!words.isEmpty()){
            Map.Entry<String, Integer> maxEntry = words.entrySet().iterator().next();
            Iterator<Map.Entry<String, Integer>> iterator = words.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, Integer> entry = iterator.next();
                if(entry.getValue()>maxEntry.getValue()){
                    maxEntry = entry;
                }if(entry.getValue()==maxEntry.getValue()){
                    if(entry.getKey().compareToIgnoreCase(maxEntry.getKey()) < 0){
                        maxEntry = entry;
                    }
                }
            }
            toReturn.put(maxEntry.getKey(), maxEntry.getValue());
            words.remove(maxEntry.getKey());
        }
        words = toReturn;
    }

    /**
     * Returns the LinkedHashMap object that contains the words and their occurrences.
     *
     * @return the LinkedHashMap object containing the words and their occurrences
     */
    public LinkedHashMap<String, Integer> getWords(){
        return words;
    }

    /**
     * Returns a new LinkedHashMap object that contains words and their occurrences, excluding the words specified in the given array.
     *
     * @param toExclude the array of words to exclude
     * @return the LinkedHashMap object containing the words and their occurrences, excluding the specified words
     */
    public LinkedHashMap<String, Integer> getWords(String[] toExclude){
        LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<>();
        Boolean good = false;
        while(!good){
            Map.Entry<String, Integer> entry = words.entrySet().iterator().next();
            good = true;
            for (String str : toExclude) {
                if (str.equals(entry.getKey())) {
                    good = false;
                }
            }
            if(good){
                toReturn.put(entry.getKey(), entry.getValue());
            }
        }
        return toReturn;
    }

    /**
     * Returns a new LinkedHashMap object that contains a given number of words and their occurrences.
     *
     * @see #getNthEntry(int)
     * @param n the number of words to return
     * @return the LinkedHashMap object containing a given number of words and their occurrences
     * @throws IllegalArgumentException if the specified number of words is invalid
     */
    public LinkedHashMap<String, Integer> getWords(int n) throws IllegalArgumentException, IOException{
        LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<>();
        if(n == -1){
            return this.getWords();
        }
        if(n < -1){
            throw new IllegalArgumentException("Request an invalid number of results");
        }
        for(int i = 0; i < n; i++){
            try{
                Map.Entry<String, Integer> entry = getNthEntry(i);

                toReturn.put(entry.getKey(), entry.getValue());
            }catch(NoSuchElementException e){
                if(toReturn.isEmpty()){
                    throw new IllegalArgumentException("Not enough entry");
                }else{
                    return toReturn;
                }
            }
        }
        return toReturn;
    }

    /**
     * Returns a new LinkedHashMap object that contains a given number of words and their occurrences, excluding the words specified in the given array.
     *
     * @see #getNthEntry(int)
     * @param toExclude the array of words to exclude
     * @param n         the number of words to return
     * @return the LinkedHashMap object containing a given number of words and their occurrences, excluding the specified words
     * @throws IllegalArgumentException if the specified number of words is invalid
     */
    public LinkedHashMap<String, Integer> getWords(String[] toExclude, int n) throws IllegalArgumentException{
        LinkedHashMap<String, Integer> toReturn = new LinkedHashMap<>();
        if(n == -1){
            return this.getWords(toExclude);
        }
        if(n < -1){
            throw new IllegalArgumentException("Request an invalid number of results");
        }

        int EntryToLoad=0;
        for(int i = 0; i < n; i++){
            try{
                Boolean good = false;
                while(!good){
                    Map.Entry<String, Integer> entry = getNthEntry(EntryToLoad);
                    good = true;
                    for (String str : toExclude) {
                        if (str.equals(entry.getKey())) {
                            good = false;
                        }
                    }
                    if(good){
                        toReturn.put(entry.getKey(), entry.getValue());
                    }
                    EntryToLoad++;
                }
            }catch(NullPointerException e){
                if(toReturn.isEmpty()){
                    throw new IllegalArgumentException("Not enough entry");
                }else{
                    return toReturn;
                }
            }
        }
        return toReturn;
    }

    /**
     * Returns the nth entry of the LinkedHashMap object.
     *
     * @param n   the index of the entry to return
     * @return the nth entry of the LinkedHashMap object
     * @throws NullPointerException if the specified index is out of bounds
     */
    private Map.Entry<String, Integer> getNthEntry(int n) throws NullPointerException{
        Map.Entry<String, Integer> nthEntry = null;
        if (n >= 0 && n < words.size()) {
            int i = 0;
            for (Map.Entry<String, Integer> entry : words.entrySet()) {
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