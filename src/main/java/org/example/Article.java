import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//not finished, there is a lot of copy-paste to do and it's pretty boring I hate it and ChatGPT wont help since code is too long 

//If someone has the desire and the time, he can order the variables by type

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
    private boolean showInRelatedContent;
    private String body;
    private LocalDateTime lastModified;
    private boolean hasStoryPackage;
    private float score;
    private String standfirst;
    private String shortUrl;
    private String thumbnail;
    private int wordcount;
    private boolean commentable;
    private boolean isPremoderated;
    private boolean allowUGc;
    private String byline;
    private String publication;
    private String internalPageCode;
    private String productionOffice;
    private boolean shouldHideAdverts;
    private boolean liveBloggingNow;
    private LocalDateTime commentCloseDate;
    private int starRating;
    private String title;
    private String fulltext;
    private String SourceSet;
    private String Source;

    //variable that are not configured yet
    private String main;
    private String bylineHtml;
    private String BodyText;
    private String lang;
    private int newspaperPageNumber;
    private int charCount;
    private LocalDateTime firstPublicationDate;
    private LocalDateTime newspaperEditionDate;
    private boolean legallySensitive;
    private boolean isLive;
    private boolean isInappropriateForSponsorship;
    private boolean shouldHideReaderRevenue;
    private boolean showAffiliateLinks;
    private boolean showTableOfContents;





    // Constructors

    //Default constructors
    public Article() {
        // Initialize default values for all fields
        this.id = "";
        this.type = "";
        this.sectionID = "";
        this.webPublicationDate = null;
        this.webUrl = "";
        this.apiUrl = "";
        this.trailText = "";
        this.headline = "";
        this.showInRelatedContent = false;
        this.body = "";
        this.lastModified = null;
        this.hasStoryPackage = false;
        this.score = 0.0f;
        this.standfirst = "";
        this.shortUrl = "";
        this.thumbnail = "";
        this.wordcount = 0;
        this.commentable = false;
        this.isPremoderated = false;
        this.allowUGc = false;
        this.byline = "";
        this.publication = "";
        this.internalPageCode = "";
        this.productionOffice = "";
        this.shouldHideAdverts = false;
        this.liveBloggingNow = false;
        this.commentCloseDate = null;
        this.starRating = 0;
        this.title = "";
        this.fulltext = "";
        this.SourceSet = "";
        this.Source = "";
        this.main = "";
        this.bylineHtml = "";
        this.BodyText = "";
        this.lang = "";
        this.newspaperPageNumber = 0;
        this.charCount = 0;
        this.firstPublicationDate = null;
        this.newspaperEditionDate = null;
        this.legallySensitive = false;
        this.isLive = false;
        this.isInappropriateForSponsorship = false;
        this.shouldHideReaderRevenue = false;
        this.showAffiliateLinks = false;
        this.showTableOfContents = false;
    }

    // Constructor that takes input for all fields in right format
    public Article(String id, String type, String sectionID, LocalDateTime webPublicationDate, String webUrl, String apiUrl,
                   String trailText, String headline, boolean showInRelatedContent, String body, LocalDateTime lastModified,
                   boolean hasStoryPackage, float score, String standfirst, String shortUrl, String thumbnail, int wordcount,
                   boolean commentable, boolean isPremoderated, boolean allowUGc, String byline, String publication, String internalPageCode,
                   String productionOffice, boolean shouldHideAdverts, boolean liveBloggingNow, LocalDateTime commentCloseDate, int starRating,
                   String title, String fulltext, String SourceSet, String Source, String main, String bylineHtml, String BodyText, String lang, 
                   int newspaperPageNumber, int charCount, LocalDateTime firstPublicationDate, LocalDateTime newspaperEditionDate, boolean legallySensitive,
                   boolean isLive, boolean isInappropriateForSponsorship, boolean shouldHideReaderRevenue, boolean showAffiliateLinks, boolean showTableOfContents) {
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

    public boolean isShowInRelatedContent() {
        return showInRelatedContent;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public boolean isHasStoryPackage() {
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

    public boolean isCommentable() {
        return commentable;
    }

    public boolean isPremoderated() {
        return isPremoderated;
    }

    public boolean isAllowUGc() {
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

    public boolean isShouldHideAdverts() {
        return shouldHideAdverts;
    }

    public boolean isLiveBloggingNow() {
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
    
    public boolean isLegallySensitive() {
        return legallySensitive;
    }
    
    public boolean isLive() {
        return isLive;
    }
    
    public boolean isInappropiateForSponsorShip() {
        return isInappropriateForSponsorship;
    }
    
    public boolean shouldHideReaderRevenue() {
        return shouldHideReaderRevenue;
    }
    
    public boolean isShowAffiliateLinks() {
        return showAffiliateLinks;
    }
    
    public boolean isShowTableOfContents() {
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
    
    public void setShowInRelatedContent(boolean showInRelatedContent) {
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
    public void setHasStoryPackage(boolean hasStoryPackage) {
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

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }
    
    public void setCommentable(String commentable) {
        this.commentable = Boolean.parseBoolean(commentable);
    }

    public void setIsPremoderated(boolean isPremoderated) {
        this.isPremoderated = isPremoderated;
    }
    
    public void setIsPremoderated(String isPremoderated) {
        this.isPremoderated = Boolean.parseBoolean(isPremoderated);
    }

    public void setAllowUGc(boolean allowUGc) {
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
    
    public void setShouldHideAdverts(boolean shouldHideAdverts) {
        this.shouldHideAdverts = shouldHideAdverts;
    }
    
    public void setShouldHideAdverts(String shouldHideAdverts) {
        this.shouldHideAdverts = Boolean.parseBoolean(shouldHideAdverts);
    }

    public void setLiveBloggingNow(boolean liveBloggingNow) {
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

    public void setLegallySensitive(boolean legallySensitive) {
        this.legallySensitive = legallySensitive;
    }

    public void setLegallySensitive(String legallySensitive) {
        this.legallySensitive = Boolean.parseBoolean(legallySensitive);
    }
    
    public void setLive(boolean live) {
        this.isLive = live;
    }
    
    public void setLive(String live) {
        this.isLive = Boolean.parseBoolean(live);
    }

    public void setInappropriateForSponsorship(boolean isInappropriateForSponsorship) {
        this.isInappropriateForSponsorship = isInappropriateForSponsorship;
    }
    
    public void setInappropriateForSponsorship(String isInappropriateForSponsorship) {
        this.isInappropriateForSponsorship = Boolean.parseBoolean(isInappropriateForSponsorship);
    }

    public void setShouldHideReaderRevenue(boolean shouldHideReaderRevenue) {
        this.shouldHideReaderRevenue = shouldHideReaderRevenue;
    }
    
    public void setShouldHideReaderRevenue(String shouldHideReaderRevenue) {
        this.shouldHideReaderRevenue = Boolean.parseBoolean(shouldHideReaderRevenue);
    }

    public void setShowAffiliateLinks(boolean showAffiliateLinks) {
        this.showAffiliateLinks = showAffiliateLinks;
    }
    
    public void setShowAffiliateLinks(String showAffiliateLinks) {
        this.showAffiliateLinks = Boolean.parseBoolean(showAffiliateLinks);
    }

    public void setShowTableOfContents(boolean showTableOfContents) {
        this.showTableOfContents = showTableOfContents;
    }
    
    public void setShowTableOfContents(String showTableOfContents) {
        this.showTableOfContents = Boolean.parseBoolean(showTableOfContents);
    }

}