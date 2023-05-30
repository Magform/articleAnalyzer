# Classes

## Summary
- [Article](#article)
- [Library](#library)
- [ArticleLoader](#articleloader)
- [Downloader](#downloader)
- [Outputter](#outputter)
- [Elaborator](#elaborator)
- [Argparser](#argparser)

## Article
The Article class describes an article from an online newspaper. Each article is composed of:

- id: the unique article id
- url: the web url of the page which contains the article
- source: the online newspaper the articles come from
- date: the article first publication date
- title: the article title
- body: the article body

The Article class provides:

- default constructor: initializes all the attributes of the class to their default values (every attribute of this class is a [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object, so they are initialized with "")
- constructor with parameters: initializes all the attributes with the respective given parameters
- getters for each attribute
- setters for each attribute

## Library
The Library class represents a collection of Article objects, implemented with an array.
Initially, the collection contains 10 Article objects initialized with the default constructor,
but there are two internal indexes which allow to work on the entire collection and, eventually, resize it:

- totalArticlesNumber: the number of effective articles contained in the collection
- scannedArticles: allows the collection to be scanned in order from start to finish

The Library class provides:

- default constructor: initializes all the attributes of the class to their default values (the two indexes are initialized to 0)
- constructor with an Article object as a parameter: add an Article object given as a parameter to the first position of the collection
- addArticle: add an Article object given as a parameter to the position pointed by the interna index totalArticlesNumber
- getTotalArticleNumber: returns the number of effective articles contained in the collection
- getArticle: returns the Article object contained in the position given as a parameter. If the position given in invalid, an [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html) exception is thrown
- getNextArticle: returns the next Article object scanned by the internal index scannedArticles. If there are not Article objects in the collection, it returns null
- resetScannedArticle: reset the internal index scannedArticles to 0 in order to allow the method getNextArticle to scan the collection from the start again

## ArticleLoader
The ArticleLoader class loads articles contained in different file extensions, such as CSV and JSON, in a Library object. Each ArticleLoader object created is composed of:

- file: the [java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) object which allows to open the file that contains the articles
- library: the Library object in which the articles will be loaded

The ArticleLoader provides:

- constructor with the name of the file to open: opens the file with the name given as a parameter and create the Library object with the default constructor. If the file does not exists, an [java.io.FileNotFoundException](https://docs.oracle.com/javase/8/docs/api/java/io/FileNotFoundException.html) exception is thrown
- loadLibrary: according to the file extension, the Library object is loaded with the articles contained in the file. Different exceptions are thrown, basing on the errors occurred during the execution of the method:
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
  - [com.opencsv.exceptions.CsvValidationException](https://opencsv.sourceforge.net/apidocs/com/opencsv/exceptions/CsvValidationException.html): if there are errors during the reading of CSV file
  - [org.json.simple.parser.ParseException](https://javadoc.io/static/com.googlecode.json-simple/json-simple/1.1.1/org/json/simple/parser/ParseException.html): if there are errors during the reading of the JSON file
- loadCSV: load to the Library object the articles contained in the CSV file (from The New York Times file format). Different exceptions are thrown, basing on the errors occurred during the execution of the method:
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
  - [com.opencsv.exceptions.CsvValidationException](https://opencsv.sourceforge.net/apidocs/com/opencsv/exceptions/CsvValidationException.html): if there are errors during the reading of CSV file
- loadJSON: load to the Library object the articles contained in the JSON file (from The Guardian file format). Different exceptions are thrown, basing on the errors occurred during the execution of the method:
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
  - [org.json.simple.parser.ParseException](https://javadoc.io/static/com.googlecode.json-simple/json-simple/1.1.1/org/json/simple/parser/ParseException.html): if there are errors during the reading of the JSON file
- getLibrary: returns the Library object after it has been loaded with the articles contained in the file. Different exceptions are thrown, basing on the errors occurred during the execution of the method:
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
  - [com.opencsv.exceptions.CsvValidationException](https://opencsv.sourceforge.net/apidocs/com/opencsv/exceptions/CsvValidationException.html): if there are errors during the reading of CSV file
  - [org.json.simple.parser.ParseException](https://javadoc.io/static/com.googlecode.json-simple/json-simple/1.1.1/org/json/simple/parser/ParseException.html): if there are errors during the reading of the JSON file
