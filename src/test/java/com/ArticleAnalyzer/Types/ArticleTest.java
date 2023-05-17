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
        assertEquals("", article.getByline());
        assertEquals("", article.getPublication());
        assertEquals("", article.getInternalPageCode());
        assertEquals("", article.getProductionOffice());
        assertEquals("", article.getBody());
        assertEquals("", article.getStandfirst());
        assertEquals("", article.getShortUrl());
        assertEquals("", article.getThumbnail());
        assertEquals("", article.getTitle());
        assertEquals("", article.getFullText());
        assertEquals("", article.getSourceSet());
        assertEquals("", article.getSource());
        assertEquals("", article.getMain());
        assertEquals("", article.getBylineHtml());
        assertEquals("", article.getBodyText());
        assertEquals("", article.getLang());
        assertEquals(-1.0f, article.getScore(), 0.0);
        assertEquals(-1, article.getStarRating());
        assertEquals(-1, article.getNewspaperPageNumber());
        assertEquals(-1, article.getCharCount());
        assertEquals(-1, article.getWordcount());
        assertNull(article.getWebPublicationDate());
        assertNull(article.getLastModified());
        assertNull(article.getCommentCloseDate());
        assertNull(article.getFirstPublicationDate());
        assertNull(article.getNewspaperEditionDate());
        assertNull(article.getCommentable());
        assertNull(article.getPremoderated());
        assertNull(article.getAllowUGc());
        assertNull(article.getShouldHideAdverts());
        assertNull(article.getLiveBloggingNow());
        assertNull(article.getHasStoryPackage());
        assertNull(article.getShowInRelatedContent());
        assertNull(article.getLegallySensitive());
        assertNull(article.getIsLive());
        assertNull(article.getIsInappropriateForSponsorShip());
        assertNull(article.getShouldHideReaderRevenue());
        assertNull(article.getShowAffiliateLinks());
        assertNull(article.getShowTableOfContents());
    }

}
