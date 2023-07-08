package com.ArticleAnalyzer.Types;

/**
 * The Library class represents a collection of Article objects and provides various functions in order to interact with the collection.
*/
public class Library {
    private int totalArticlesNumber;
    private int scannedArticles;
    private Article[] collection;
    private static final int INITIAL_CAPACITY = 10; //initial size of the collection with a fixed size of 10

    /**
     * Creates a Library object with an initial collection of articles and initializes other attributes to their default values.
    */
    public Library() {
        totalArticlesNumber = 0;
        scannedArticles = 0;
        collection = new Article[INITIAL_CAPACITY];
    }
    
    /**
     * Creates a Library object with an initial collection containing the given Article object.
     * @see addArticle(Article)
     * @param article the Article object to add to the collection
    */
    public Library(Article article) {
        this();
        addArticle(article);
    }

    /**
     * Adds the given Article object to the collection.
     * If the collection is full, the size of the collection is doubled before adding the Article object
     * @param article the Article object to add to the collection
    */
    public void addArticle(Article article) {
        if (totalArticlesNumber == collection.length) {
            Article[] newCollection = new Article[collection.length * 2];
            System.arraycopy(collection, 0, newCollection, 0, collection.length);
            collection = newCollection;
        }
        collection[totalArticlesNumber] = article;
        totalArticlesNumber++;
    }

    /**
     * Returns the total number of available Article objects in the collection.
     * @return the total number of available Article objects
    */
    public int getTotalArticlesNumber() {
        return totalArticlesNumber;
    }

    /**
     * Returns the Article object at the specified index.
     * @param articleNumber the index of the Article object to retrieve
     * @return the Article object at the specified index
     * @throws IllegalArgumentException if the article number is invalid
    */
    public Article getArticle(int articleNumber) throws IllegalArgumentException {
        if (articleNumber < 0 || articleNumber >= totalArticlesNumber) {
            throw new IllegalArgumentException("Invalid article number");
        }
        return collection[articleNumber];
    }

    /**
     * Retrieves the next Article object in the collection basing on an internal index.
     * Returns null if there are no more articles to retrieve.
     * @return the next Article object in the collection, or null if there are no more articles
    */
    public Article getNextArticle() {
        if (scannedArticles == totalArticlesNumber) {
            return null; //no more articles to scan
        }
        scannedArticles++;
        return collection[scannedArticles - 1];
    }

    /**
     * Resets the internal index for the getNextArticle() function, allowing it to start from the first article again.
     * @see getNextArticle()
    */
    public void resetScannedArticle() {
        scannedArticles = 0;
    }
}
