package com.ArticleAnalyzer.Types;

/**
 * The Article class represents an article from an online newspaper.
*/
public class Article {
    private String identifier;
    private String section;
    private String source;
    private String publicationDate;
    private String language;
    private String url;
    private String title;
    private String subtitle;
    private String body;
    private int newspaperPage;
    private int words;

    /**
     * Initializes all attributes of the class to their default values.
    */
    public Article() {
        setIdentifier("");
        setSection("");
        setSource("");
        setPublicationDate("");
        setLanguage("");
        setUrl("");
        setTitle("");
        setSubtitle("");
        setBody("");
        setNewspaperPage(0);
        setWords(0);
    }

    /**
     * Returns the article's identifier.
     * @return the article's identifier
    */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Returns the article's section.
     * @return the article's section
    */
    public String getSection() {
        return section;
    }

    /**
     * Returns the article's source.
     * @return the article's source
    */
    public String getSource() {
        return source;
    }

    /**
     * Returns the article's first publication date.
     * @return the article's first publication date
    */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Returns the article's first publication date.
     * @return the article's first publication date
    */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the article's url.
     * @return the article's url
    */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the article's title.
     * @return the article's title
    */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the article's subtitle.
     * @return the article's subtitle
    */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Returns the article's body.
     * @return the article's body
    */
    public String getBody() {
        return body;
    }

    /**
     * Returns the article's page number in the newspaper.
     * @return the article's page number in the newspaper
    */
    public int getNewspaperPage() {
        return newspaperPage;
    }

    /**
     * Returns the article's words count.
     * @return the article's words count
    */
    public int getWords() {
        return words;
    }

    /**
     * Sets the article's identifier.
     * @param id the article's identifier
    */
    public void setIdentifier(String id) {
        identifier = id;
    }

    /**
     * Sets the article's section.
     * @param se the article's section
    */
    public void setSection(String se) {
        section = se;
    }

    /**
     * Sets the article's source.
     * @param so the article's source
    */
    public void setSource(String so) {
        source = so;
    }

    /**
     * Sets the article's first publication date.
     * @param pd the article's first publication date
    */
    public void setPublicationDate(String pd) {
        publicationDate = pd;
    }

    /**
     * Sets the article's language.
     * @param l the article's language
    */
    public void setLanguage(String l) {
        language = l;
    }

    /**
     * Sets the article's url.
     * @param u the article's url
    */
    public void setUrl(String u) {
        url = u;
    }

    /**
     * Sets the article's title.
     * @param t the article's title
    */
    public void setTitle(String t) {
        title = t;
    }

    /**
     * Sets the article's subtitle.
     * @param st the article's subtitle
    */
    public void setSubtitle(String st) {
        subtitle = st;
    }

    /**
     * Sets the article's body.
     * @param b the article's body
    */
    public void setBody(String b) {
        body = b;
    }

    /**
     * Sets the article's page number in the newspaper.
     * @param np the article's page number in the newspaper
    */
    public void setNewspaperPage(int np) {
        newspaperPage = np;
    }

    /**
     * Sets the article's words count.
     * @param w the article's words count
    */
    public void setWords(int w) {
        words = w;
    }

    /**
     * Sets the given Article attribute name to the given value.
     * @param attributeValue the attribute's value which needs to be set
     * @param attributeName the attribute's name to be set
     * @throws IllegalArgumentException if the attribute to be set is not one of the Article's attributes
    */
    public void fullSetter(String attributeValue, String attributeName) throws IllegalArgumentException {
        if (attributeName.equalsIgnoreCase("identifier")) {
            setIdentifier(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("section")) {
            setSection(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("source")) {
            setSource(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("publicationDate")) {
            setPublicationDate(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("language")) {
            setLanguage(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("url")) {
            setUrl(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("title")) {
            setTitle(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("subtitle")) {
            setSubtitle(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("body")) {
            setBody(attributeValue);
        }
        else if (attributeName.equalsIgnoreCase("newspaperPage")) {
            try {
                if (attributeValue == null) {
                  setNewspaperPage(0);
                }
                else {
                  setNewspaperPage(Integer.parseInt(attributeValue));
                }
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException(attributeValue + " must be a number if related to the page number in the newspaper");
            }
        }
        else if (attributeName.equalsIgnoreCase("words")) {
            try {
                if (attributeValue == null) {
                  setWords(0);
                }
                else {
                  setWords(Integer.parseInt(attributeValue));
                }
            }
            catch (NumberFormatException e) {
                throw new IllegalArgumentException(attributeValue + " must be a number if related to the article's words count");
            }
        }
        else {
            throw new IllegalArgumentException(attributeName + " is an invalid attribute name for an article");
        }
    }
}
