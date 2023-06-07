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

        assertEquals("", article.getId());
        assertEquals("", article.getType());
        assertEquals("", article.getSectionID());
        assertEquals("", article.getWebUrl());
        assertEquals("", article.getApiUrl());
        assertEquals("", article.getTrailText());
        assertEquals("", article.getHeadline());
        assertEquals("", article.getProductionOffice());
        assertEquals("", article.getBody());
        assertEquals("", article.getStandfirst());
        assertEquals("", article.getShortUrl());
        assertEquals("", article.getTitle());
        assertEquals("", article.getMain());
        assertEquals("", article.getBodyText());
        assertEquals("", article.getLang());
        assertEquals(-1, article.getNewspaperPageNumber());
        assertEquals(-1, article.getCharCount());
        assertEquals(-1, article.getWordcount());
        assertNull(article.getWebPublicationDate());
    }

}
