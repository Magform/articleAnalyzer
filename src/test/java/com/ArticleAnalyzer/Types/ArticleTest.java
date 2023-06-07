package com.ArticleAnalyzer.Types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ArticleTest {

    @Test
    public void DefaultConstructor() {
        Article article = new Article();
        assertEquals("", article.getIdentifier());
        assertEquals("", article.getSection());
        assertEquals("", article.getSource());
        assertEquals("", article.getPublicationDate());
        assertEquals("", article.getLanguage());
        assertEquals("", article.getUrl());
        assertEquals("", article.getTitle());
        assertEquals("", article.getSubtitle());
        assertEquals("", article.getBody());
        assertEquals(0, article.getNewspaperPage());
        assertEquals(0, article.getWords());
    }

}
