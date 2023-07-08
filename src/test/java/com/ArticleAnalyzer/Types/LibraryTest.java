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
    public void DefaultConstructor_getTotalArticlesNumber() {
        Library test = new Library();
        assertEquals(0, test.getTotalArticlesNumber());
    }

    @Test
    public void DefaultConstructor_getNextArticle() {
        Library test = new Library();
        assertNull(test.getNextArticle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void DefaultConstructor_getArticle() {
        Library test = new Library();
        test.getArticle(0);
    }

    //test for other constructor
    @Test
    public void otherConstructor_getTotalArticlesNumber() {
        Library test = new Library(new Article());
        assertEquals(1, test.getTotalArticlesNumber());
    }

    @Test
    public void otherConstructorr_getNextArticle() {
        Library test = new Library(new Article());
        assertEquals("", test.getNextArticle().getIdentifier());
    }

    @Test(expected = IllegalArgumentException.class)
    public void otherConstructor_getArticleOverLimit() {
        Library test = new Library(new Article());
        test.getArticle(1);
    }

    @Test
    public void otherConstructor_getArticle() {
        Library test = new Library(new Article());
        assertEquals("", test.getArticle(0).getIdentifier());
    }

    //Test addArticle
    @Test
    public void testAddArticle() {
        Library test = new Library();
        Article toAdd = new Article();
        toAdd.setIdentifier("TestID");
        test.addArticle(toAdd);
        assertEquals(1, test.getTotalArticlesNumber());
        assertEquals("TestID", test.getNextArticle().getIdentifier());
    }

    //Test resetScannedArticle
    @Test
    public void testResetScannedArticle() {
        Library test = new Library();
        Article toAdd = new Article();
        Article toAdd2 = new Article();
        toAdd.setIdentifier("TestID");
        toAdd2.setIdentifier("TestID2");
        test.addArticle(toAdd);
        test.addArticle(toAdd2);
        assertEquals(2, test.getTotalArticlesNumber());
        assertEquals("TestID", test.getNextArticle().getIdentifier());
        assertEquals("TestID2", test.getNextArticle().getIdentifier());
        test.resetScannedArticle();
        assertEquals("TestID", test.getNextArticle().getIdentifier());
        assertEquals("TestID2", test.getNextArticle().getIdentifier());
    }
}
