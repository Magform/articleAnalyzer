package com.ArticleAnalyzer.Types;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.IllegalArgumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LibraryTest {


    //Test for default constructor
    @Test
    public void testDefaultConstructor_getTotalArticleNumber(){
        Library test = new Library();
        assertEquals(0, test.getTotalArticleNumber());
    }

    @Test
    public void testDefaultConstructor_getNextArticle(){
        Library test = new Library();
        assertNull(test.getNextArticle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDefaultConstructor_getArticle(){
        Library test = new Library();
        test.getArticle(1);
    }

    //test for other constructor
    @Test
    public void otherConstructor_getTotalArticleNumber(){
        Library test = new Library(new Article());
        assertEquals(1, test.getTotalArticleNumber());
    }

    @Test
    public void otherConstructorr_getNextArticle(){
        Library test = new Library(new Article());
        assertEquals("", test.getNextArticle().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void otherConstructor_getArticleOverLimit(){
        Library test = new Library(new Article());
        test.getArticle(2);
    }

    @Test
    public void otherConstructor_getArticle(){
        Library test = new Library(new Article());
        assertEquals("", test.getArticle(1).getId());
    }

    //Test addArticle
    @Test
    public void testAddArticle(){
        Library test = new Library();
        Article toAdd = new Article();
        toAdd.setId("TestID");
        test.addArticle(toAdd);
        assertEquals(1, test.getTotalArticleNumber());
        assertEquals("TestID", test.getNextArticle().getId());
    }

    //Test resetScannedArticle
    @Test
    public void testResetScannedArticle(){
        Library test = new Library();
        Article toAdd = new Article();
        Article toAdd2 = new Article();
        toAdd.setId("TestID");
        toAdd2.setId("TestID2");
        test.addArticle(toAdd);
        test.addArticle(toAdd2);
        assertEquals(2, test.getTotalArticleNumber());
        assertEquals("TestID", test.getNextArticle().getId());
        assertEquals("TestID2", test.getNextArticle().getId());
        test.resetScannedArticle();
        assertEquals("TestID", test.getNextArticle().getId());
        assertEquals("TestID2", test.getNextArticle().getId());
    }
}
