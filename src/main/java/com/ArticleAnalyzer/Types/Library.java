package com.ArticleAnalyzer.Types;

/* 
 * The Library class represents a collection of Article objects and provides various functions for interacting with the collection.
 * 
 * It has a default constructor that initializes the collection with an array of 10 articles created using the default Article constructor.
 * Additionally, there is a constructor that accepts an Article parameter, which initializes the first article in the collection with the given article.
 * 
 * Public functions:
 * - addArticle(Article): Adds the given article to the collection.
 * - getTotalArticleNumber(): Returns the total number of available articles as an integer.
 * - getArticle(int): Returns the Article corresponding to the given integer index.
 * - getNextArticle(): Retrieves the next article in the collection based on an internal index. Returns null if there are no more articles.
 * - resetScannedArticle(): Resets the internal index for the getNextArticle() function, allowing it to start from the first article again.
 */

public class Library {
    private int totalArticlesNumber;
    private int scannedArticles;
    private Article[] collection;
    private static final int INITIAL_CAPACITY = 10; // choose the initial size of the collection with a fixed size of 10

    /**
     * Constructs a Library object with an initial collection of articles.
     * The collection is initialized with an array of INITIAL_CAPACITY articles using the default Article constructor.
     * The total number of articles and the number of scanned articles are set to 0.
     */
    public Library() {
        totalArticlesNumber = 0;
        scannedArticles = 0;
        collection = new Article[INITIAL_CAPACITY];
    }

    /**
     * Constructs a Library object with an initial collection of articles.
     * The collection is initialized with an array of INITIAL_CAPACITY articles using the default Article constructor.
     * The given article is added as the first article in the collection.
     *
     * @param article The initial article to add to the collection.
     */
    public Library(Article article) {
        this();
        addArticle(article);
    }

    /**
     * Adds the given article to the collection.
     * If the collection is full, the size of the collection is doubled before adding the article.
     *
     * @param article The article to add to the collection.
     */
    public void addArticle(Article article){
        //Check if collection[] is full, if yes it double it's lenght
        if (totalArticlesNumber == collection.length) {
            Article[] newCollection = new Article[collection.length * 2];
            System.arraycopy(collection, 0, newCollection, 0, collection.length);
            collection = newCollection;
        }
        collection[totalArticlesNumber] = article;
        totalArticlesNumber++;
    }

    /**
     * Returns the total number of available articles in the collection .
     *
     * @return The total number of available articles as an integer.
     */
    public int getTotalArticleNumber(){
        return this.totalArticlesNumber;
    }

    /**
     * Returns the Article corresponding to the given integer index.
     * Throws an IllegalArgumentException if the articleNumber is invalid (less than 1 or greater than the total number of articles).
     *
     * @param articleNumber The index of the article to retrieve.
     * @return The Article at the specified index.
     * @throws IllegalArgumentException If the articleNumber is invalid.
     */
    public Article getArticle(int articleNumber) throws IllegalArgumentException{
        if(articleNumber <= 0 || articleNumber > totalArticlesNumber){
            throw new IllegalArgumentException("Invalid articleNumber");
        }
        return this.collection[articleNumber - 1];
    }

    /**
     * Retrieves the next article in the collection based on an internal index.
     * Returns null if there are no more articles to retrieve.
     *
     * @return The next Article in the collection, or null if there are no more articles.
     */
    public Article getNextArticle(){
        if (scannedArticles == totalArticlesNumber) {
            return null; // no more articles to scan
        }
        scannedArticles++;
        return collection[scannedArticles - 1];
    }

    /**
     * Resets the internal index for the getNextArticle() function, allowing it to start from the first article again.
     */
    public void resetScannedArticle(){
        scannedArticles = 0;
    }

}
