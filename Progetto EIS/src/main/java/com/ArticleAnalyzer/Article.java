package com.ArticleAnalyzer;

public class Article {
  private String source;
  private String title;
  private String body;

  public Article(String s, String t, String b) {
    source = s;
    title = t;
    body = b;
  }

  public String getSource() {
    return source;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }
}
