package com.ArticleAnalyzer.Types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//My suggestion using this class is to never use constructor, different from Article(), all costructor are created anc configured but if u dont have ALL the things it wont work so I think that is better to use default constructor and use set to set all the things that u need to set.

public class Article {
    
    //variable
    private String id;
    private String type;
    private String sectionID;
    private LocalDateTime webPublicationDate;
    private String webUrl;
    private String apiUrl;
    private String trailText;
    private String headline;
    private Boolean showInRelatedContent;
    private String body;
    private LocalDateTime lastModified;
    private Boolean hasStoryPackage;
    private float score;
    private String standfirst;
    private String shortUrl;
    private String thumbnail;
    private int wordcount;
    private Boolean commentable;
    private Boolean isPremoderated;
    private Boolean allowUGc;
    private String byline;
    private String publication;
    private String internalPageCode;
    private String productionOffice;
    private Boolean shouldHideAdverts;
    private Boolean liveBloggingNow;
    private LocalDateTime commentCloseDate;
    private int starRating;
    private String title;
    private String fulltext;
    private String SourceSet;
    private String Source;
    private String main;
    private String bylineHtml;
    private String BodyText;
    private String lang;
    private int newspaperPageNumber;
    private int charCount;
    private LocalDateTime firstPublicationDate;
    private LocalDateTime newspaperEditionDate;
    private Boolean legallySensitive;
    private Boolean isLive;
    private Boolean isInappropriateForSponsorship;
    private Boolean shouldHideReaderRevenue;
    private Boolean showAffiliateLinks;
    private Boolean showTableOfContents;





    // Constructors

    //Default constructors
    public Article() {
        // Initialize default values for all fields
        this.id = "";
        this.type = "";
        this.sectionID = "";
        this.webUrl = "";
        this.apiUrl = "";
        this.trailText = "";
        this.headline = "";
        this.byline = "";
        this.publication = "";
        this.internalPageCode = "";
        this.productionOffice = "";
        this.body = "";
        this.standfirst = "";
        this.shortUrl = "";
        this.thumbnail = "";
        this.title = "";
        this.fulltext = "";
        this.SourceSet = "";
        this.Source = "";
        this.main = "";
        this.bylineHtml = "";
        this.BodyText = "";
        this.lang = "";
        this.score = -1.0f;
        this.starRating = -1;
        this.newspaperPageNumber = -1;
        this.charCount = -1;
        this.wordcount = -1;
        this.webPublicationDate = null;
        this.lastModified = null;
        this.commentCloseDate = null;
        this.firstPublicationDate = null;
        this.newspaperEditionDate = null;
        this.commentable = null;
        this.isPremoderated = null;
        this.allowUGc = null;
        this.shouldHideAdverts = null;
        this.liveBloggingNow = null;
        this.hasStoryPackage = null;
        this.showInRelatedContent = null;
        this.legallySensitive = null;
        this.isLive = null;
        this.isInappropriateForSponsorship = null;
        this.shouldHideReaderRevenue = null;
        this.showAffiliateLinks = null;
        this.showTableOfContents = null;
    }

    // Constructor that takes input for all fields in right format
    public Article(String id, String type, String sectionID, LocalDateTime webPublicationDate, String webUrl, String apiUrl,
                   String trailText, String headline, Boolean showInRelatedContent, String body, LocalDateTime lastModified,
                   Boolean hasStoryPackage, float score, String standfirst, String shortUrl, String thumbnail, int wordcount,
                   Boolean commentable, Boolean isPremoderated, Boolean allowUGc, String byline, String publication, String internalPageCode,
                   String productionOffice, Boolean shouldHideAdverts, Boolean liveBloggingNow, LocalDateTime commentCloseDate, int starRating,
                   String title, String fulltext, String SourceSet, String Source, String main, String bylineHtml, String BodyText, String lang, 
                   int newspaperPageNumber, int charCount, LocalDateTime firstPublicationDate, LocalDateTime newspaperEditionDate, Boolean legallySensitive,
                   Boolean isLive, Boolean isInappropriateForSponsorship, Boolean shouldHideReaderRevenue, Boolean showAffiliateLinks, Boolean showTableOfContents) {
        this.id = id;
        this.type = type;
        this.sectionID = sectionID;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.trailText = trailText;
        this.headline = headline;
        this.showInRelatedContent = showInRelatedContent;
        this.body = body;
        this.lastModified = lastModified;
        this.hasStoryPackage = hasStoryPackage;
        this.score = score;
        this.standfirst = standfirst;
        this.shortUrl = shortUrl;
        this.thumbnail = thumbnail;
        this.wordcount = wordcount;
        this.commentable = commentable;
        this.isPremoderated = isPremoderated;
        this.allowUGc = allowUGc;
        this.byline = byline;
        this.publication = publication;
        this.internalPageCode = internalPageCode;
        this.productionOffice = productionOffice;
        this.shouldHideAdverts = shouldHideAdverts;
        this.liveBloggingNow = liveBloggingNow;
        this.commentCloseDate = commentCloseDate;
        this.starRating = starRating;
        this.title = title;
        this.fulltext = fulltext;
        this.SourceSet = SourceSet;
        this.Source = Source;
        this.main = main;
        this.bylineHtml = bylineHtml;
        this.BodyText = BodyText;
        this.lang = lang;
        this.newspaperPageNumber = newspaperPageNumber;
        this.charCount = charCount;
        this.firstPublicationDate = firstPublicationDate;
        this.newspaperEditionDate = newspaperEditionDate;
        this.legallySensitive = legallySensitive;
        this.isLive = isLive;
        this.isInappropriateForSponsorship = isInappropriateForSponsorship;
        this.shouldHideReaderRevenue = shouldHideReaderRevenue;
        this.showAffiliateLinks = showAffiliateLinks;
        this.showTableOfContents = showTableOfContents;
    }

    //Constructor with all field as String
    public Article(String id, String type, String sectionID, String webPublicationDate,
               String webUrl, String apiUrl, String trailText, String headline,
               String showInRelatedContent, String body, String lastModified,
               String hasStoryPackage, String score, String standfirst, String shortUrl,
               String thumbnail, String wordcount, String commentable, String isPremoderated,
               String allowUGc, String byline, String publication, String internalPageCode,
               String productionOffice, String shouldHideAdverts, String liveBloggingNow,
               String commentCloseDate, String starRating, String title, String fulltext, 
               String SourceSet, String Source, String main, String bylineHtml, String BodyText,
               String lang, String newspaperPageNumber, String charCount, String firstPublicationDate,
               String newspaperEditionDate, String legallySensitive, String isLive,
               String isInappropriateForSponsorship, String shouldHideReaderRevenue, 
               String showAffiliateLinks, String showTableOfContents) {
    
    this.id = id;
    this.type = type;
    this.sectionID = sectionID;
    this.webPublicationDate = LocalDateTime.parse(webPublicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
    this.webUrl = webUrl;
    this.apiUrl = apiUrl;
    this.trailText = trailText;
    this.headline = headline;
    this.showInRelatedContent = Boolean.parseBoolean(showInRelatedContent);
    this.body = body;
    this.lastModified = LocalDateTime.parse(lastModified, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
    this.hasStoryPackage = Boolean.parseBoolean(hasStoryPackage);
    this.score = Float.parseFloat(score);
    this.standfirst = standfirst;
    this.shortUrl = shortUrl;
    this.thumbnail = thumbnail;
    this.wordcount = Integer.parseInt(wordcount);
    this.commentable = Boolean.parseBoolean(commentable);
    this.isPremoderated = Boolean.parseBoolean(isPremoderated);
    this.allowUGc = Boolean.parseBoolean(allowUGc);
    this.byline = byline;
    this.publication = publication;
    this.internalPageCode = internalPageCode;
    this.productionOffice = productionOffice;
    this.shouldHideAdverts = Boolean.parseBoolean(shouldHideAdverts);
    this.liveBloggingNow = Boolean.parseBoolean(liveBloggingNow);
    this.commentCloseDate = LocalDateTime.parse(commentCloseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
    this.starRating = Integer.parseInt(starRating);
    this.title = title;
    this.fulltext = fulltext;
    this.SourceSet = SourceSet;
    this.Source = Source;
    this.main = main;
    this.bylineHtml = bylineHtml;
    this.BodyText = BodyText;
    this.lang = lang;
    this.newspaperPageNumber = Integer.parseInt(newspaperPageNumber);
    this.charCount = Integer.parseInt(charCount);
    this.firstPublicationDate = LocalDateTime.parse(firstPublicationDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    this.newspaperEditionDate = LocalDateTime.parse(newspaperEditionDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    this.legallySensitive = Boolean.parseBoolean(legallySensitive);
    this.isLive = Boolean.parseBoolean(isLive);
    this.isInappropriateForSponsorship = Boolean.parseBoolean(isInappropriateForSponsorship);
    this.shouldHideReaderRevenue = Boolean.parseBoolean(shouldHideReaderRevenue);
    this.showAffiliateLinks = Boolean.parseBoolean(showAffiliateLinks);
    }

    // getters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSectionID() {
        return sectionID;
    }

    public LocalDateTime getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getTrailText() {
        return trailText;
    }

    public String getHeadline() {
        return headline;
    }

    public Boolean getShowInRelatedContent() {
        return showInRelatedContent;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public Boolean getHasStoryPackage() {
        return hasStoryPackage;
    }

    public float getScore() {
        return score;
    }

    public String getStandfirst() {
        return standfirst;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getWordcount() {
        return wordcount;
    }

    public Boolean getCommentable() {
        return commentable;
    }

    public Boolean getPremoderated() {
        return isPremoderated;
    }

    public Boolean getAllowUGc() {
        return allowUGc;
    }

    public String getByline() {
        return byline;
    }

    public String getPublication() {
        return publication;
    }

    public String getInternalPageCode() {
        return internalPageCode;
    }

    public String getProductionOffice() {
        return productionOffice;
    }

    public Boolean getShouldHideAdverts() {
        return shouldHideAdverts;
    }

    public Boolean getLiveBloggingNow() {
        return liveBloggingNow;
    }

    public LocalDateTime getCommentCloseDate() {
        return commentCloseDate;
    }

    public int getStarRating() {
        return starRating;
    }

    public String getTitle(){
        return title;
    }

    public String getFullText(){
        return fulltext;
    }

    public String getSourceSet(){
        return SourceSet;
    }

    public String getSource(){
        return Source;
    }

    public String getMain() {
        return main;
    }
    
    public String getBylineHtml() {
        return bylineHtml;
    }
    
    public String getBodyText() {
        return BodyText;
    }
    
    public String getLang() {
        return lang;
    }
    
    public int getNewspaperPageNumber() {
        return newspaperPageNumber;
    }
    
    public int getCharCount() {
        return charCount;
    }
    
    public LocalDateTime getFirstPublicationDate() {
        return firstPublicationDate;
    }
    
    public LocalDateTime getNewspaperEditionDate() {
        return newspaperEditionDate;
    }
    
    public Boolean getLegallySensitive() {
        return legallySensitive;
    }
    
    public Boolean getIsLive() {
        return isLive;
    }

    public Boolean getIsInappropriateForSponsorShip() {
        return isInappropriateForSponsorship;
    }
    
    public Boolean getShouldHideReaderRevenue() {
        return shouldHideReaderRevenue;
    }
    
    public Boolean getShowAffiliateLinks() {
        return showAffiliateLinks;
    }
    
    public Boolean getShowTableOfContents() {
        return showTableOfContents;
    }



    // setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }
    
    public void setWebPublicationDate(LocalDateTime webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }
    
    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = LocalDateTime.parse(webPublicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    
    public void setTrailText(String trailText) {
        this.trailText = trailText;
    }
    
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    public void setShowInRelatedContent(Boolean showInRelatedContent) {
        this.showInRelatedContent = showInRelatedContent;
    }
    
    public void setShowInRelatedContent(String showInRelatedContent) {
        this.showInRelatedContent = Boolean.parseBoolean(showInRelatedContent);
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
    
    public void setLastModified(String lastModified) {
        this.lastModified = LocalDateTime.parse(lastModified, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));;
    }
    public void setHasStoryPackage(Boolean hasStoryPackage) {
        this.hasStoryPackage = hasStoryPackage;
    }
    
    public void setHasStoryPackage(String hasStoryPackage) {
        this.hasStoryPackage = Boolean.parseBoolean(hasStoryPackage);
    }
    
    public void setScore(float score) {
        this.score = score;
    }
    
    public void setScore(String score) {
        this.score = Float.parseFloat(score);
    }

    public void setStandfirst(String standfirst) {
        this.standfirst = standfirst;
    }
    
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
    
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public void setWordcount(int wordcount) {
        this.wordcount = wordcount;
    }
    
    public void setWordcount(String wordcount) {
        this.wordcount = Integer.parseInt(wordcount);
    }

    public void setCommentable(Boolean commentable) {
        this.commentable = commentable;
    }
    
    public void setCommentable(String commentable) {
        this.commentable = Boolean.parseBoolean(commentable);
    }

    public void setIsPremoderated(Boolean isPremoderated) {
        this.isPremoderated = isPremoderated;
    }
    
    public void setIsPremoderated(String isPremoderated) {
        this.isPremoderated = Boolean.parseBoolean(isPremoderated);
    }

    public void setAllowUGc(Boolean allowUGc) {
        this.allowUGc = allowUGc;
    }

    public void setAllowUGc(String allowUGc) {
        this.allowUGc = Boolean.parseBoolean(allowUGc);
    }
    
    public void setByline(String byline) {
        this.byline = byline;
    }
    
    public void setPublication(String publication) {
        this.publication = publication;
    }
    
    public void setInternalPageCode(String internalPageCode) {
        this.internalPageCode = internalPageCode;
    }
    
    public void setProductionOffice(String productionOffice) {
        this.productionOffice = productionOffice;
    }
    
    public void setShouldHideAdverts(Boolean shouldHideAdverts) {
        this.shouldHideAdverts = shouldHideAdverts;
    }
    
    public void setShouldHideAdverts(String shouldHideAdverts) {
        this.shouldHideAdverts = Boolean.parseBoolean(shouldHideAdverts);
    }

    public void setLiveBloggingNow(Boolean liveBloggingNow) {
        this.liveBloggingNow = liveBloggingNow;
    }
    
    public void setLiveBloggingNow(String liveBloggingNow) {
        this.liveBloggingNow = Boolean.parseBoolean(liveBloggingNow);
    }

    public void setCommentCloseDate(LocalDateTime commentCloseDate) {
        this.commentCloseDate = commentCloseDate;
    }
    
    public void setCommentCloseDate(String commentCloseDate) {
        this.commentCloseDate = LocalDateTime.parse(commentCloseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"));;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = Integer.parseInt(starRating);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setFullText(String fulltext){
        this.fulltext = fulltext;
    }

    public void setSourceSet(String SourceSet){
        this.SourceSet = SourceSet;
    }

    public void setSource(String Source){
        this.Source = Source;
    }

    public void setMain(String main) {
        this.main = main;
    }
    
    public void setBylineHtml(String bylineHtml) {
        this.bylineHtml = bylineHtml;
    }
    
    public void setBodyText(String bodyText) {
        this.BodyText = bodyText;
    }
    
    public void setLang(String lang) {
        this.lang = lang;
    }
    
    public void setNewspaperPageNumber(int newspaperPageNumber) {
        this.newspaperPageNumber = newspaperPageNumber;
    }
    
    public void setNewspaperPageNumber(String newspaperPageNumber) {
        this.newspaperPageNumber = Integer.parseInt(newspaperPageNumber);
    }

    public void setCharCount(int charCount) {
        this.charCount = charCount;
    }
    
    public void setCharCount(String charCount) {
        this.charCount = Integer.parseInt(charCount);
    }

    public void setFirstPublicationDate(LocalDateTime firstPublicationDate) {
        this.firstPublicationDate = firstPublicationDate;
    }
    
    public void setFirstPublicationDate(String firstPublicationDate) {
        this.firstPublicationDate = LocalDateTime.parse(firstPublicationDate);
    }

    public void setNewspaperEditionDate(LocalDateTime newspaperEditionDate) {
        this.newspaperEditionDate = newspaperEditionDate;
    }

    public void setNewspaperEditionDate(String newspaperEditionDate) {
        this.newspaperEditionDate = LocalDateTime.parse(newspaperEditionDate);
    }

    public void setLegallySensitive(Boolean legallySensitive) {
        this.legallySensitive = legallySensitive;
    }

    public void setLegallySensitive(String legallySensitive) {
        this.legallySensitive = Boolean.parseBoolean(legallySensitive);
    }
    
    public void setLive(Boolean live) {
        this.isLive = live;
    }
    
    public void setLive(String live) {
        this.isLive = Boolean.parseBoolean(live);
    }

    public void setInappropriateForSponsorship(Boolean isInappropriateForSponsorship) {
        this.isInappropriateForSponsorship = isInappropriateForSponsorship;
    }
    
    public void setInappropriateForSponsorship(String isInappropriateForSponsorship) {
        this.isInappropriateForSponsorship = Boolean.parseBoolean(isInappropriateForSponsorship);
    }

    public void setShouldHideReaderRevenue(Boolean shouldHideReaderRevenue) {
        this.shouldHideReaderRevenue = shouldHideReaderRevenue;
    }
    
    public void setShouldHideReaderRevenue(String shouldHideReaderRevenue) {
        this.shouldHideReaderRevenue = Boolean.parseBoolean(shouldHideReaderRevenue);
    }

    public void setShowAffiliateLinks(Boolean showAffiliateLinks) {
        this.showAffiliateLinks = showAffiliateLinks;
    }
    
    public void setShowAffiliateLinks(String showAffiliateLinks) {
        this.showAffiliateLinks = Boolean.parseBoolean(showAffiliateLinks);
    }

    public void setShowTableOfContents(Boolean showTableOfContents) {
        this.showTableOfContents = showTableOfContents;
    }
    
    public void setShowTableOfContents(String showTableOfContents) {
        this.showTableOfContents = Boolean.parseBoolean(showTableOfContents);
    }

}