public class Library {
    private int totalArticlesNumber;
    private int scannedArticles;
    private Article[] collection;
    private static final int INITIAL_CAPACITY = 10; // choose the initial size of the collection with a fixed size of 10

    public Library() {
        this.totalArticlesNumber = 0;
        this.scannedArticles = -1; // initialize to -1 so that getNextArticle() starts with the first article
        this.collection = new Article[INITIAL_CAPACITY];
    }

    public Library(Article article) {
        this();
        addArticle(article);
    }

    public boolean addArticle(Article article){
        if (this.totalArticlesNumber == this.collection.length) { // collection is full we need to resize
            Article[] newCollection = new Article[this.collection.length * 2];   //I chose to double te lenght of article array
            System.arraycopy(this.collection, 0, newCollection, 0, this.collection.length);
            this.collection = newCollection;
        }
        this.collection[this.totalArticlesNumber] = article;
        this.totalArticlesNumber++;
        return true;
    }

    public int getTotalArticleNumber(){
        return this.totalArticlesNumber;
    }

    public Article getNextArticle(){
        if (this.scannedArticles == this.totalArticlesNumber - 1) {
            return null; // no more articles to scan
        }
        this.scannedArticles++;
        return this.collection[this.scannedArticles];
    }

    public Article getArticle(int articleNumber){
        if(articleNumber <= 0 || articleNumber > this.totalArticlesNumber){
            throw new IllegalArgumentException("Invalid articleNumber");
        }
        return this.collection[articleNumber - 1];
    }
}
