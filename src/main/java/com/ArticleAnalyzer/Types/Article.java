package com.ArticleAnalyzer.Types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
  * The Article class represents an article from an online newspaper.
*/
public class Article {
    
    //variable
    private String id;
    private String type;
    private String sectionID;
    private String title;
    private LocalDateTime webPublicationDate;
    private String webUrl;
    private String apiUrl;
    private String headline;
    private String standfirst;
    private String trailText;
    private String main;
    private String body;
    private int newspaperPageNumber;
    private int wordcount;
    private String productionOffice;
    private String shortUrl;
    private String lang;
    private String BodyText;
    private int charCount;

    /**
     * Initializes all attributes of the class to their default values
     */
    public Article() {
        this.title = "";
        this.id = "";
        this.type = "";
        this.sectionID = "";
        this.webUrl = "";
        this.apiUrl = "";
        this.trailText = "";
        this.headline = "";
        this.productionOffice = "";
        this.body = "";
        this.standfirst = "";
        this.shortUrl = "";
        this.main = "";
        this.BodyText = "";
        this.lang = "";
        this.newspaperPageNumber = -1;
        this.charCount = -1;
        this.wordcount = -1;
        this.webPublicationDate = null;
    }

    /**
     * Returns the ID of the article.
     *
     * @return The ID of the article.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the title of the article.
     *
     * @return The title of the article.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the type of the article.
     *
     * @return The type of the article.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the section ID of the article.
     *
     * @return The section ID of the article.
     */
    public String getSectionID() {
        return sectionID;
    }

    /**
     * Returns the web publication date of the article.
     *
     * @return The web publication date of the article.
     */
    public LocalDateTime getWebPublicationDate() {
        return webPublicationDate;
    }

    /**
     * Returns the web URL of the article.
     *
     * @return The web URL of the article.
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * Returns the API URL of the article.
     *
     * @return The API URL of the article.
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Returns the trail text of the article.
     *
     * @return The trail text of the article.
     */
    public String getTrailText() {
        return trailText;
    }

    /**
     * Returns the headline of the article.
     *
     * @return The headline of the article.
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Returns the body of the article.
     *
     * @return The body of the article.
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the standfirst of the article.
     *
     * @return The standfirst of the article.
     */
    public String getStandfirst() {
        return standfirst;
    }

    /**
     * Returns the short URL of the article.
     *
     * @return The short URL of the article.
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Returns the word count of the article.
     *
     * @return The word count of the article.
     */
    public int getWordcount() {
        return wordcount;
    }

    /**
     * Returns the production office of the article.
     *
     * @return The production office of the article.
     */
    public String getProductionOffice() {
        return productionOffice;
    }

    /**
     * Returns the main content of the article.
     *
     * @return The main content of the article.
     */
    public String getMain() {
        return main;
    }

    /**
     * Returns the body text of the article.
     *
     * @return The body text of the article.
     */
    public String getBodyText() {
        return BodyText;
    }

    /**
     * Returns the language of the article.
     *
     * @return The language of the article.
     */
    public String getLang() {
        return lang;
    }

    /**
     * Returns the newspaper page number of the article.
     *
     * @return The newspaper page number of the article.
     */
    public int getNewspaperPageNumber() {
        return newspaperPageNumber;
    }

    /**
     * Returns the character count of the article.
     *
     * @return The character count of the article.
     */
    public int getCharCount() {
        return charCount;
    }


    /**
     * Sets the ID of the article.
     *
     * @param id The ID of the article.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the type of the article.
     *
     * @param type The type of the article.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the section ID of the article.
     *
     * @param sectionID The section ID of the article.
     */
    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    /**
     * Sets the web publication date of the article.
     *
     * @param webPublicationDate The web publication date of the article.
     */
    public void setWebPublicationDate(LocalDateTime webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    /**
     * Sets the web publication date of the article by parsing the provided string.
     *
     * @param webPublicationDate The web publication date string in the format "yyyy-MM-dd'T'HH:mm:ss'Z'".
     */
    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = LocalDateTime.parse(webPublicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }

    /**
     * Sets the web URL of the article.
     *
     * @param webUrl The web URL of the article.
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * Sets the API URL of the article.
     *
     * @param apiUrl The API URL of the article.
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * Sets the trail text of the article.
     *
     * @param trailText The trail text of the article.
     */
    public void setTrailText(String trailText) {
        this.trailText = trailText;
    }

    /**
     * Sets the headline of the article.
     *
     * @param headline The headline of the article.
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     * Sets the body of the article.
     *
     * @param body The body of the article.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Sets the standfirst of the article.
     *
     * @param standfirst The standfirst of the article.
     */
    public void setStandfirst(String standfirst) {
        this.standfirst = standfirst;
    }

    /**
     * Sets the short URL of the article.
     *
     * @param shortUrl The short URL of the article.
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * Sets the word count of the article.
     *
     * @param wordcount The word count of the article.
     */
    public void setWordcount(int wordcount) {
        this.wordcount = wordcount;
    }

    /**
     * Sets the word count of the article by parsing the provided string.
     *
     * @param wordcount The word count of the article as a string.
     */
    public void setWordcount(String wordcount) {
        this.wordcount = Integer.parseInt(wordcount);
    }

    /**
     * Sets the production office of the article.
     *
     * @param productionOffice The production office of the article.
     */
    public void setProductionOffice(String productionOffice) {
        this.productionOffice = productionOffice;
    }

    /**
     * Sets the title of the article.
     *
     * @param title The title of the article.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the main content of the article.
     *
     * @param main The main content of the article.
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * Sets the body text of the article.
     *
     * @param bodyText The body text of the article.
     */
    public void setBodyText(String bodyText) {
        this.BodyText = bodyText;
    }

    /**
     * Sets the language of the article.
     *
     * @param lang The language of the article.
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * Sets the newspaper page number of the article.
     *
     * @param newspaperPageNumber The newspaper page number of the article.
     */
    public void setNewspaperPageNumber(int newspaperPageNumber) {
        this.newspaperPageNumber = newspaperPageNumber;
    }

    /**
     * Sets the newspaper page number of the article by parsing the provided string.
     *
     * @param newspaperPageNumber The newspaper page number of the article as a string.
     */
    public void setNewspaperPageNumber(String newspaperPageNumber) {
        this.newspaperPageNumber = Integer.parseInt(newspaperPageNumber);
    }

    /**
     * Sets the character count of the article.
     *
     * @param charCount The character count of the article.
     */
    public void setCharCount(int charCount) {
        this.charCount = charCount;
    }

    /**
     * Sets the character count of the article by parsing the provided string.
     *
     * @param charCount The character count of the article as a string.
     */
    public void setCharCount(String charCount) {
        this.charCount = Integer.parseInt(charCount);
    }



    /**
     * Set a variable with a given name to a specified value
     * 
     * @param toSet value to which the variable need to be set
     * @param whereToSet variable to be set
     * @throws IllegalArgumentException if the variable to be set is not present in the Article class.
     */ 
    public void fullSetter(String toSet, String whereToSet) throws IllegalArgumentException{
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
        } else if (whereToSet.equalsIgnoreCase("productionOffice")) {
            this.setProductionOffice(toSet);
        } else if (whereToSet.equalsIgnoreCase("body")) {
            this.setBody(toSet);
        } else if (whereToSet.equalsIgnoreCase("standfirst")) {
            this.setStandfirst(toSet);
        } else if (whereToSet.equalsIgnoreCase("shortUrl")) {
            this.setShortUrl(toSet);
        } else if (whereToSet.equalsIgnoreCase("title")) {
            this.setTitle(toSet);
        } else if (whereToSet.equalsIgnoreCase("main")) {
            this.setMain(toSet);
        } else if (whereToSet.equalsIgnoreCase("BodyText")) {
            this.setBodyText(toSet);
        } else if (whereToSet.equalsIgnoreCase("lang")) {
            this.setLang(toSet);
        } else if (whereToSet.equalsIgnoreCase("newspaperPageNumber")) {
            this.setNewspaperPageNumber(toSet);
        } else if (whereToSet.equalsIgnoreCase("charCount")) {
            this.setCharCount(toSet);
        } else if (whereToSet.equalsIgnoreCase("wordcount")) {
            this.setWordcount(toSet);
        } else if (whereToSet.equalsIgnoreCase("webPublicationDate")) {
            this.setWebPublicationDate(toSet);
        } else {
            throw new IllegalArgumentException(whereToSet+" is not a valid key");
        }
    }

}