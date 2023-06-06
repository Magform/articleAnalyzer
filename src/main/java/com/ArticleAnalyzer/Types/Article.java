package com.ArticleAnalyzer.Types;

/**
  * The Article class represents an article from an online newspaper.
*/
public class Article {
  private String id;
  private String url;
  private String source;
  private String date;
  private String title;
  private String body;

/**
  * Initializes all attributes of the class to their default values
*/
  public Article() {
    setId("");
    setUrl("");
    setSource("");
    setDate("");
    setTitle("");
    setBody("");
  }
/**
  * Initializes all attributes of the class with the respective given values
  * @param i the article id
  * @param u the article url
  * @param s the online newspaper from which the article comes from
  * @param d the date of first publication of the article
  * @param t the article title
  * @param b the article body
*/
  public Article(String i, String u, String s, String d, String t, String b) {
    setId(i);
    setUrl(u);
    setSource(s);
    setDate(d);
    setTitle(t);
    setBody(b);
  }

/**
  * Returns the article id
  * @return the article id
*/
  public String getId() {
    return id;
  }

/**
  * Returns the article url
  * @return the article url
*/
  public String getUrl() {
    return url;
  }

/**
  * Returns the online newspaper from which the article comes from
  * @return the online newspaper from which the article comes from
*/
  public String getSource() {
    return source;
  }

/**
  * Returns the date of first publication of the article
  * @return the date of first publication of the article
*/
  public String getDate() {
    return date;
  }

/**
  * Returns the article title
  * @return the article title
*/
  public String getTitle() {
    return title;
  }

  /**
    * Returns the article body
    * @return the article body
  */
  public String getBody() {
    return body;
  }

/**
  * Initializes the article id
  * @param i the article id
*/
  public void setId(String i) {
    id = i;
  }

/**
  * Initializes the article url
  * @param u the article url
*/
  public void setUrl(String u) {
    url = u;
  }

/**
  * Initializes the online newspaper from which the article comes from
  * @param s the online newspaper from which the article comes from
*/
  public void setSource(String s) {
    source = s;
  }

/**
  * Initializes the date of first publication of the article
  * @param d the date of first publication of the article
*/
  public void setDate(String d) {
    date = d;
  }

/**
  * Initializes the article title
  * @param t the article title
*/
  public void setTitle(String t) {
    title = t;
  }

  /**
    * Initializes the article body
    * @param b the article body
  */
  public void setBody(String b) {
    body = b;
  }
}
