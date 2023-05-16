package com.ArticleAnalyzer.DataProcesser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

public class Elaborator {
    private LinkedHashMap <String, Integer> words;
    private Library toAnalyze;

    public Elaborator(Library toAnalyze){
        this.toAnalyze = toAnalyze;
        words = new LinkedHashMap<>();
        analyze();
    }

    private void analyze(){
        for(int i = 1; i<= toAnalyze.getTotalArticleNumber(); i++){
            Map<String, Boolean> founded = new HashMap<>();
            Article article = toAnalyze.getArticle(i);
            String body = article.getBodyText();
            Scanner scanner = new Scanner(body);
            scanner.useDelimiter("\\W+");
            while (scanner.hasNext()) {
                String word = scanner.next();
                if(!founded.containsKey(word)){
                    founded.put(word, true);
                    words.merge(word, 1, Integer::sum);
                }
            }
            scanner.close();
        }
        orderMap();
    }

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

    public LinkedHashMap<String, Integer> getWords(){
        return words;
    }

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
                Map.Entry<String, Integer> entry = getNthEntry(i, words);
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
                    Map.Entry<String, Integer> entry = getNthEntry(EntryToLoad, words);
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

    private Map.Entry<String, Integer> getNthEntry(int n, LinkedHashMap<String, Integer> map){
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