package com.ArticleAnalyzer.DataProcesser;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

@RunWith(JUnit4.class)
public class ElaboratorTest {
    
    @Test
    public void testGetWords() {
        // Create some sample articles and a library
        Article article1 = new Article();
        article1.setBody("test1 test1");
        Article article2 = new Article();
        article2.setBody("test1 test2");
        Article article3 = new Article();
        article3.setBody("test1 test2 test3");

        Library library = new Library();
        library.addArticle(article1);
        library.addArticle(article2);
        library.addArticle(article3);

        Elaborator elaborator = new Elaborator(library);

        LinkedHashMap<String, Integer> results = new LinkedHashMap<>();
        results.put("test1", 3);
        results.put("test2", 2);
        results.put("test3", 1);

        LinkedHashMap<String, Integer> words = elaborator.getWords();
        assertEquals(3, words.size());
        assertEquals(3, words.get("test1").intValue()); 
        assertEquals(2, words.get("test2").intValue()); 
        assertEquals(1, words.get("test3").intValue()); 
        assertEquals(words, results);
    }

    @Test
    public void testGetNWords() {
        // Create some sample articles and a library
        Article article1 = new Article();
        article1.setBody("test1 test1");
        Article article2 = new Article();
        article2.setBody("test1 test2");
        Article article3 = new Article();
        article3.setBody("test1 test2 test3");

        Library library = new Library();
        library.addArticle(article1);
        library.addArticle(article2);
        library.addArticle(article3);

        Elaborator elaborator = new Elaborator(library);

        LinkedHashMap<String, Integer> results = new LinkedHashMap<>();
        results.put("test1", 3);
        results.put("test2", 2);

        LinkedHashMap<String, Integer> words = elaborator.getWords(2);
        assertEquals(2, words.size());
        assertEquals(3, words.get("test1").intValue()); 
        assertEquals(2, words.get("test2").intValue()); 
        assertEquals(words, results);
    }
    
    @Test
    public void testGetNWordsWithoutSomeWords() {
        // Create some sample articles and a library
        Article article1 = new Article();
        article1.setBody("test1 test1 test4");
        Article article2 = new Article();
        article2.setBody("test1 test2 test4");
        Article article3 = new Article();
        article3.setBody("test1 test2 test3 test4");

        Library library = new Library();
        library.addArticle(article1);
        library.addArticle(article2);
        library.addArticle(article3);

        Elaborator elaborator = new Elaborator(library);

        LinkedHashMap<String, Integer> results = new LinkedHashMap<>();
        results.put("test1", 3);

        String[] toExclude = {"test3", "test4"};

        LinkedHashMap<String, Integer> words = elaborator.getWords(toExclude, 1);
        assertEquals(1, words.size());
        assertEquals(3, words.get("test1").intValue()); 
        assertEquals(words, results);
    }

}
