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

The ArticleLoader class provides:

- constructor with the file path of the file to open as a parameter: opens the file having the file path given as a parameter and create the Library object with the default constructor. If the file does not exist, an [java.io.FileNotFoundException](https://docs.oracle.com/javase/8/docs/api/java/io/FileNotFoundException.html) exception is thrown
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

## Downloader
The Downloader class allows to download articles from online newspapers (currently only The Guardian), basing on a configuration file created before. Each Downloader object created is composed of:

- configurationFile: the [java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) object which allows to open the file that contains the configuration settings for the download
- endpoint: the name which uniquely identify the online newspaper from where the articles will be downloaded
- link: the base web link which allows the connection for the download
- APIKey: the user api-key which specify that this user is allowed to download the articles
- query: the keywords which allows to download articles regarding specific arguments
- JSONOutput: the file name where to save the response received from the download
- articleNumber: the number of articles to download

The Downloader class provides:

- default constructor: initializes all the attributes of the class to their default values (the [java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) object is initialized to null; all the other attributes, which are [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects, are initialized with "")
- constructor with the file path of the file to open as a parameter: opens the download configuration file having the name given as a parameter, verify if settings given in it are valid and download the articles. Different exceptions are thrown, basing on the errors occurred during the execution of the constructor:
  - [java.io.FileNotFoundException](https://docs.oracle.com/javase/8/docs/api/java/io/FileNotFoundException.html): if the configuration file does not exist
  - [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html): if the configuration file has invalid contents
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if there are errors during the download process
- getJSONOutput: returns the file path of the saved response data
- download: starts the download process based on a specific endpoint (currently the download is possible only for The Guardian newspaper). Different exceptions are thrown, basing on the errors occurred during the execution of the method:
  - [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html): if the endpoint specified in the configuration file is invalid
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if there are errors during the download process
- downloadFromTheGuardian: download the articles from The Guardian newspaper (TheGuardian is the specified endpoint in order to download from this newspaper). The method creates the full web url including:
    - the base link
    - the api-key
    - show-fields=all, which allows to receive the title and the body of each article
    - the number of articles to download
    - the query
  It establishes the HTTP connection with the GET request method and opens the connection with The Guardian API. In the end, it closes the connection and saves the response in a file using an [Outputter](#outputter) object. Different exceptions are thrown, basing on the errors occurred during the execution of the method:
  - [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html): if different contents in the configuration file are invalid
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if there are errors during the download process

## Outputter

The Outputter class is responsible for printing String objects, Article objects, Library objects and LinkedHashMap objects to the console or to a file. Each Outputter object is composed of:
- toConsole: a boolean value which specify if the printing is happening to console
- toFile: a boolean value which specify if the printing is happening to a file
- fileName: the file path of the file where to save the data

The Outputter class provides:

- default constructor: initializes all the attributes of the class to their default values (the two boolean attributes are initialized to false; the file path is initialized to "")
- constructor with the two boolean given as parameters: initializes the respective boolean attributes to their given values and set the file path to ""
- constructor with parameters: each attribute is initialized with the respective given values
- getToConsole: returns if the printing is happening to console
- getToFile: returns if the printing is happening to a file
- getFileName: returns the file path of the file where to save the data
- setFileName: initializes the file path with the given path and checks if the given path is valid. It throws an [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) exception if the given path is ""
- print: prints the given [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object to console or to file, depending on how the two boolean attributes are set
- printToConsole: prints the given [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object to console
- printToFile: prints the given [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object to a file. At every call of this method, the writing of the file is appended to the rest of the content that was saved before.
- print with an Article object as a parameter: prints the Article object given as a parameter to JSON format
- print with a Library object as a parameter: prints the Article contained in the Library object given as parameter to JSON format
- print with a LinkedHashMap object with key-value pairs of type String-Integer as a parameter: prints each key-value pair of the LinkedHashMap object given as a parameter
- check: checks if the given path is valid. If the file already exists, the method deletes it and creates a new one. It throws an [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) exception if the given path is ""
- All the overloaded print methods throw a [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) exception is thrown if there are errors during the writing of the file when the printing to a file is enabled