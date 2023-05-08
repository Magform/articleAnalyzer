import java.time.LocalDateTime;
//not finished, there is a lot of copy-paste to do and it's pretty boring 



public class Article {
    //to add: main,newspaperPageNumber, fristPublicationDate, isInappropiateForSponsorShip, newspaperEditionDate, legallySensitive, lang, isLive, bodytext, charCount, shouldHideReaderRevenue, showAffiliateLinks, bylineHtml, showTableOfContents
    
    // variable
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
    }

    // Constructor that takes input for all fields in right format
    public Article(String id, String type, String sectionID, LocalDateTime webPublicationDate, String webUrl, String apiUrl,
                   String trailText, String headline, boolean showInRelatedContent, String body, LocalDateTime lastModified,
                   boolean hasStoryPackage, float score, String standfirst, String shortUrl, String thumbnail, int wordcount,
                   boolean commentable, boolean isPremoderated, boolean allowUGc, String byline, String publication, String internalPageCode,
                   String productionOffice, boolean shouldHideAdverts, boolean liveBloggingNow, LocalDateTime commentCloseDate, int starRating) {
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
    }

    //Constructor with all field as String


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


    //all other setters are not writed yet (you can use costructor or wait for an implementation)
    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl
