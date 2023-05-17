package com.ArticleAnalyzer.Types;

/* 
 * This is the basic type of our project, it implements an Article and contains, set with private status, all the variables that we believe may
 *      be useful in this or other projects that deal with the analysis or writing of articles.
 * Each variable then has a setter and a getter, the getter returns as type the type of the variable while for the setter there is an implementation
 *      for the type of the variable and for the String type (in the case of the date the valid format is yyyy- MM-dd'T'HH:mm:ss'Z').
 * There are also three constructors:
 * - Default constructor --> that takes no parameters and sets String with an empty string, int and float with -1 and all other objects to null
 * - Constructor with type --> that takes as parameters ALL the variables implemented in an article in the type in which they are implemented
 * - Constructor from string --> which requires as parameters ALL the variables implemented in an article given as String
 * It is not recommended to use constructors other than the default one, as it would significantly worsen the readability of the code, and it is rather advisable to use setters
 * There is also a fullSetter that accepts two parameters, respectively String toSet and String whereToSet, which allows you to set the variable called as the string whereToSet to toSet,
 *     if the string whereToSet does not contain a valid variable name an IOException is thrown
*/


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.lang.reflect.Field; //used in an alternative fullSetter implementation (Line )

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

    //Default constructor
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

    // Constructor with type
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

    //Constructor from string
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
    this.webPublicationDate = LocalDateTime.parse(webPublicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    this.webUrl = webUrl;
    this.apiUrl = apiUrl;
    this.trailText = trailText;
    this.headline = headline;
    this.showInRelatedContent = Boolean.parseBoolean(showInRelatedContent);
    this.body = body;
    this.lastModified = LocalDateTime.parse(lastModified, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
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
    this.commentCloseDate = LocalDateTime.parse(commentCloseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
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
        this.webPublicationDate = LocalDateTime.parse(webPublicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
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
        this.lastModified = LocalDateTime.parse(lastModified, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
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
        this.commentCloseDate = LocalDateTime.parse(commentCloseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
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
        this.firstPublicationDate = LocalDateTime.parse(firstPublicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }

    public void setNewspaperEditionDate(LocalDateTime newspaperEditionDate) {
        this.newspaperEditionDate = newspaperEditionDate;
    }

    public void setNewspaperEditionDate(String newspaperEditionDate) {
        this.newspaperEditionDate = LocalDateTime.parse(newspaperEditionDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
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


    //fullSetter
    public void fullSetter(String toSet, String whereToSet) throws IOException{
        if(whereToSet.equalsIgnoreCase("id")){
            this.setId(toSet);
        }else if(whereToSet.equalsIgnoreCase("type")){
            this.setType(toSet);
        }else if (whereToSet.equalsIgnoreCase("sectionID")) {
            this.setSectionID(toSet);
        } else if (whereToSet.equalsIgnoreCase("webUrl")) {
            this.setWebUrl(toSet);
        } else if (whereToSet.equalsIgnoreCase("apiUrl")) {
            this.setApiUrl(toSet);
        } else if (whereToSet.equalsIgnoreCase("trailText")) {
            this.setTrailText(toSet);
        } else if (whereToSet.equalsIgnoreCase("headline")) {
            this.setHeadline(toSet);
        } else if (whereToSet.equalsIgnoreCase("byline")) {
            this.setByline(toSet);
        } else if (whereToSet.equalsIgnoreCase("publication")) {
            this.setPublication(toSet);
        } else if (whereToSet.equalsIgnoreCase("internalPageCode")) {
            this.setInternalPageCode(toSet);
        } else if (whereToSet.equalsIgnoreCase("productionOffice")) {
            this.setProductionOffice(toSet);
        } else if (whereToSet.equalsIgnoreCase("body")) {
            this.setBody(toSet);
        } else if (whereToSet.equalsIgnoreCase("standfirst")) {
            this.setStandfirst(toSet);
        } else if (whereToSet.equalsIgnoreCase("shortUrl")) {
            this.setShortUrl(toSet);
        } else if (whereToSet.equalsIgnoreCase("thumbnail")) {
            this.setThumbnail(toSet);
        } else if (whereToSet.equalsIgnoreCase("title")) {
            this.setTitle(toSet);
        } else if (whereToSet.equalsIgnoreCase("fulltext")) {
            this.setFullText(toSet);
        } else if (whereToSet.equalsIgnoreCase("SourceSet")) {
            this.setSourceSet(toSet);
        } else if (whereToSet.equalsIgnoreCase("Source")) {
            this.setSource(toSet);
        } else if (whereToSet.equalsIgnoreCase("main")) {
            this.setMain(toSet);
        } else if (whereToSet.equalsIgnoreCase("bylineHtml")) {
            this.setBylineHtml(toSet);
        } else if (whereToSet.equalsIgnoreCase("BodyText")) {
            this.setBodyText(toSet);
        } else if (whereToSet.equalsIgnoreCase("lang")) {
            this.setLang(toSet);
        } else if (whereToSet.equalsIgnoreCase("score")) {
            this.setScore(toSet);
        } else if (whereToSet.equalsIgnoreCase("starRating")) {
            this.setStarRating(toSet);
        } else if (whereToSet.equalsIgnoreCase("newspaperPageNumber")) {
            this.setNewspaperPageNumber(toSet);
        } else if (whereToSet.equalsIgnoreCase("charCount")) {
            this.setCharCount(toSet);
        } else if (whereToSet.equalsIgnoreCase("wordcount")) {
            this.setWordcount(toSet);
        } else if (whereToSet.equalsIgnoreCase("webPublicationDate")) {
            this.setWebPublicationDate(toSet);
        } else if (whereToSet.equalsIgnoreCase("lastModified")) {
            this.setLastModified(toSet);
        } else if (whereToSet.equalsIgnoreCase("commentCloseDate")) {
            this.setCommentCloseDate(toSet);
        } else if (whereToSet.equalsIgnoreCase("firstPublicationDate")) {
            this.setFirstPublicationDate(toSet);
        } else if (whereToSet.equalsIgnoreCase("newspaperEditionDate")) {
            this.setNewspaperEditionDate(toSet);
        } else if (whereToSet.equalsIgnoreCase("commentable")) {
            this.setCommentable(toSet);
        } else if (whereToSet.equalsIgnoreCase("isPremoderated")) {
            this.setIsPremoderated(toSet);
        } else if (whereToSet.equalsIgnoreCase("allowUGc")) {
            this.setAllowUGc(toSet);
        } else if (whereToSet.equalsIgnoreCase("shouldHideAdverts")) {
            this.setShouldHideAdverts(toSet);
        } else if (whereToSet.equalsIgnoreCase("liveBloggingNow")) {
            this.setLiveBloggingNow(toSet);
        } else if (whereToSet.equalsIgnoreCase("hasStoryPackage")) {
            this.setHasStoryPackage(toSet);
        } else if (whereToSet.equalsIgnoreCase("showInRelatedContent")) {
            this.setShowInRelatedContent(toSet);
        } else if (whereToSet.equalsIgnoreCase("legallySensitive")) {
            this.setLegallySensitive(toSet);
        } else if (whereToSet.equalsIgnoreCase("isLive")) {
            this.setLive(toSet);
        } else if (whereToSet.equalsIgnoreCase("isInappropriateForSponsorship")) {
            this.setInappropriateForSponsorship(toSet);
        } else if (whereToSet.equalsIgnoreCase("shouldHideReaderRevenue")) {
            this.setShouldHideReaderRevenue(toSet);
        } else if (whereToSet.equalsIgnoreCase("showAffiliateLinks")) {
            this.setShowAffiliateLinks(toSet);
        } else if (whereToSet.equalsIgnoreCase("showTableOfContents")) {
            this.setShowTableOfContents(toSet);
        } else {
            throw new IOException(whereToSet+" is not a valid key");
        }
    }

    //This approach might make the code more concise and easier to maintain, although it comes with its own trade-offs, such as reduced performance and less compile-time safety.
    //And also not tested
    /*
    public void fullSetter(String toSet, String whereToSet) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(whereToSet);
        field.setAccessible(true);
        field.set(this, toSet);
    }
    */

}