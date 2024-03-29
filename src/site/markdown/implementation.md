# Implementation
The implementation side of the ArticleAnalyzer project, from all the [Java](https://docs.oracle.com/javase/8/docs/api/) classes which allow to realize its aim, to the [Javadoc](https://www.oracle.com/it/technical-resources/articles/java/javadoc-tool.html) comments, in order to create a graphic description of all the program.

## Table of contents
- [Classes](#Classes)
    - [Types](#Types)
        - [Article](#Article)
        - [Library](#Library)
    - [Data Management](#Data_Management)
        - [ArticleLoader](#ArticleLoader)
        - [Downloader](#Downloader)
        - [Outputter](#Outputter)
    - [Data Processer](#Data_Processer)
        - [Elaborator](#Elaborator)
    - [Helper](#Helper)
        - [Argparser](#Argparser)
- [Javadoc](#Javadoc)

## Classes
Here a panoramic of all the [Java](https://docs.oracle.com/javase/8/docs/api/) classes which allow the ArticleAnalyzer project to download articles from different online newspapers and to extract and visualize the words and their occurrences which appear more in the set of articles received. Each class has its own package, the same directory tree created with the effective [Java](https://docs.oracle.com/javase/8/docs/api/) files.

### Types

#### Article
The Article class describes an article from an online newspaper.

Each Article object created is composed of:

- identifier: the unique article identifier
- section: the argument section regarding the article
- source: the online newspaper the article comes from
- publicationDate: the date of the first publication of the article
- language: the article writing language
- url: the web url of the page which contains the article
- title: the article's title
- subtitle: the article's subtitle
- body: the article's body
- newspaperPage: the page of the newspaper which contains the article
- words: the body words count of the article

The Article class provides:

- default constructor: initializes all the attributes of the class to their default values (every attribute of this class is a [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object, so they are initialized with "")
- getters for each attribute
- setters for each attribute
- fullSetter: sets the given Article object attribute name to the given value. A [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html) exception is thrown if the given attribute name to be set is not one of the Article object's attributes

#### Library
The Library class represents a collection of [Article](#Article) objects, implemented with an array.

Initially, the collection contains 10 [Article](#Article) objects initialized with the default constructor, but there are two internal indexes which allow to work on the entire collection and, eventually, resize it:

- totalArticlesNumber: the number of effective articles contained in the collection
- scannedArticles: allows the collection to be scanned in order from start to finish

The Library class provides:

- default constructor: initializes all the attributes of the class to their default values (the two indexes are initialized to 0)
- constructor with an [Article](#Article) object as a parameter: add the given [Article](#Article) object to the first position of the collection
- addArticle: add the given [Article](#Article) object to the position pointed by the interna index totalArticlesNumber
- getTotalArticlesNumber: returns the number of effective articles contained in the collection
- getArticle: returns the [Article](#Article) object contained in the position given. If the position given in invalid, an [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html) exception is thrown
- getNextArticle: returns the next [Article](#Article) object scanned by the internal index scannedArticles. If there are not [Article](#Article) objects in the collection, it returns null
- resetScannedArticle: reset the internal index scannedArticles to 0 in order to allow the method getNextArticle to scan the collection from the start again

### Data Management

#### ArticleLoader
The ArticleLoader class loads articles contained in different file extensions, such as CSV and JSON, in a [Library](#Library) object.

Each ArticleLoader object created is composed of:

- file: the [java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) object which allows to open the file that contains the articles
- library: the [Library](#Library) object in which the articles will be loaded

The ArticleLoader class provides:

- constructor with the file path of the file to open as a parameter: opens the file having the given file path and create the [Library](#Library) object with the default constructor. If the file does not exist, an [java.io.FileNotFoundException](https://docs.oracle.com/javase/8/docs/api/java/io/FileNotFoundException.html) exception is thrown
- loadLibrary: according to the file extension, the [Library](#Library) object is loaded with the articles contained in the file. Different exceptions are thrown, basing on the errors occurred during the execution of the method:
    - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
    - [com.opencsv.exceptions.CsvValidationException](https://opencsv.sourceforge.net/apidocs/com/opencsv/exceptions/CsvValidationException.html): if there are errors during the reading of CSV file
    - [org.json.simple.parser.ParseException](https://code.google.com/archive/p/json-simple/): if there are errors during the reading of the JSON file
- loadCSV: load to the [Library](#Library) object the articles contained in the CSV file (from The New York Times file format). Different exceptions are thrown, basing on the errors occurred during the execution of the method:
  - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
  - [com.opencsv.exceptions.CsvValidationException](https://opencsv.sourceforge.net/apidocs/com/opencsv/exceptions/CsvValidationException.html): if there are errors during the reading of CSV file
- loadJSON: load to the [Library](#Library) object the articles contained in the JSON file (from The Guardian file format). Different exceptions are thrown, basing on the errors occurred during the execution of the method:
    - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
    - [org.json.simple.parser.ParseException](https://code.google.com/archive/p/json-simple/): if there are errors during the reading of the JSON file
- getLibrary: returns the [Library](#Library) object after it has been loaded with the articles contained in the file. Different exceptions are thrown, basing on the errors occurred during the execution of the method:
    - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the file extension is not specified or there are errors during the reading of the file
    - [com.opencsv.exceptions.CsvValidationException](https://opencsv.sourceforge.net/apidocs/com/opencsv/exceptions/CsvValidationException.html): if there are errors during the reading of CSV file
    - [org.json.simple.parser.ParseException](https://code.google.com/archive/p/json-simple/): if there are errors during the reading of the JSON file

#### Downloader
The Downloader class allows to download articles from online newspapers (currently only The Guardian), basing on a configuration file created before.

Each Downloader object created is composed of:

- configurationFile: the [java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) object which allows to open the file that contains the configuration settings for the download
- endpoint: the name which uniquely identify the online newspaper from where the articles will be downloaded
- link: the base web link which allows the connection for the download
- APIKey: the user api-key which specify that this user is allowed to download the articles
- query: the keywords which allows to download articles regarding specific arguments
- JSONOutput: the file name where to save the response received from the download
- articlesPerPage: the number of articles you want to load per page
- initialPage: the number of the first page to download
- totalPageNumber: the total number of pages to download

The Downloader class provides:

- default constructor: initializes all the attributes of the class to their default values (the [java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) object is initialized to null; all the other attributes, which are [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects, are initialized with "")
- constructor with the file path of the file to open as a parameter: opens the download configuration file having the given name, verify if settings given in it are valid and download the articles. Different exceptions are thrown, basing on the errors occurred during the execution of the constructor:
    - [java.io.FileNotFoundException](https://docs.oracle.com/javase/8/docs/api/java/io/FileNotFoundException.html): if the configuration file does not exist
    - [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html): if the configuration file has invalid contents
    - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if there are errors during the download process
- getJSONOutput: returns the file path of the saved response data
- download: starts the download process based on a specific endpoint (currently the download is possible only for The Guardian newspaper). Different exceptions are thrown, basing on the errors occurred during the execution of the method:
    - [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html): if the endpoint specified in the configuration file is invalid
    - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if there are errors during the download process
- downloadFromTheGuardian: downloads the articles from The Guardian newspaper (TheGuardian is the specified endpoint in order to download from this newspaper) contained at the specified pages with the specified page size. The method creates the full web url including:

    - the base link
    - the api-key
    - show-fields=all, which allows to receive the title and the body of each article
    - the query
    - the page size; if not specified, the default value is 10
    - the page to download; if not specified, the default initial page value where to start the download is 1

    It establishes the HTTP connection for each page to download with the GET request method using a [java.net.HttpURLConnection](https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html) object and opens the connection with The Guardian API. In the end, it closes the connection, merge the current response received with the others already merged, saves the response in a file using an [Outputter](#Outputter) object. Different exceptions are thrown, basing on the errors occurred during the execution of the method:

      - [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html): if different contents in the configuration file are invalid
      - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if there are errors during the download process

- mergeTheGuardianResponses: returns all the responses given by The Guardian merged in a unique JSON response with all the modified settings. Specifically, it takes the given response contained in a [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object and merges it in the other given [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object which contains all the other part of the response already merged during a past call of this method, called by the downloadFromTheGuardian method

#### Outputter
The Outputter class is responsible for printing [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects, [Article](#Article) objects, [Library](#Library) objects and [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/java.util.LinkedHashMap.html) objects to the console or to a file.

Each Outputter object created is composed of:

- toConsole: a boolean value which specify if the printing is happening to console
- toFile: a boolean value which specify if the printing is happening to a file
- fileName: the file path of the file where to save the data

The Outputter class provides:

- default constructor: initializes all the attributes of the class to their default values (the two boolean attributes are initialized to false; the file path is initialized to "")
- constructor with the two boolean given as parameters: initializes the respective boolean attributes to their given values and set the file path to ""
- constructor with parameters: each attribute is initialized with the respective given values
- getFile: returns the file path of the file where to save the data
- getToConsole: returns if the printing is happening to console
- getToFile: returns if the printing is happening to a file
- setFile: initializes the file path with the given path and checks if it is valid. It throws an [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) exception if the given path is ""
- print: prints the given [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object to console or to file, depending on how the two boolean attributes are set
- printToConsole: prints the given [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object to console
- printToFile: prints the given [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object to a file. At every call of this method, the writing of the file is appended to the rest of the content that was saved before.
- print with an [Article](#Article) object as a parameter: prints the given [Article](#Article) object to JSON format
- print with a [Library](#Library) object as a parameter: prints the [Article](#Article) contained in the given [Library](#Library) object to JSON format
- print with a [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object with key-value pairs of type String-Integer as a parameter: prints each key-value pair of the given [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object
- check: checks if the given path is valid. If the file already exists, the method deletes it and creates a new one. It throws an [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) exception if the given path is ""
- All the overloaded print methods throw a [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html) exception if there are errors during the writing of the file when the printing to a file is enabled

### Data Processer

#### Elaborator
The Elaborator class elaborates the [Article](#Article) objects contained in a [Library](#Library) object and for each article retrieves the words, from its title and body, and their occurrences, counted once in each article, and save them in a [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object with key-value pairs of type String-Integer (key-value of the same type also returned in the class methods which return a [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object).

Each Elaborator object created is composed of:

- words: a [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/java.util.LinkedHashMap.html) object where to save the words and their occurrences
- library: a [Library](#Library) object which contains the [Article](#Article) objects

The Elaborator class provides:

- constructor with a [Library](#Library) object: initializes the [Library](#Library) with the respective given object and create an empty [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object
- analyze: retrieves from the Library object the articles words, from its title and body, and their occurrences, counted once in each article, and save them in the [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object
- orderMap: orders the [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object basing on which word contains more occurrences
- getWords: returns the [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object which contains the words and their occurrences
- getWords with an array of [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects as a parameter: returns a [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object which contains the words which are not included in the given array of [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects and their occurrences
- getWords with an int as a parameter: returns a [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object which contains the given number of words and their occurrences. This method throws a [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html) exception if the given number of words to return is invalid
- getWords with an array of [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects and an int as parameters: returns a [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object which contains the given number of words which are not included in the given array of [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects. This method throws a [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html) exception if the given number of words to return is invalid
- getNthEntry: returns the given entry of the given [java.util.LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html) object

### Helper

#### Argparser
The Argparser class provides different command line argument options in order to execute the ArticleAnalyzer project. For more information about these options, go [here](run.html).

Each Argparser object created is composed of:

- outputter: the [Outputter](#Outputter) object
- articleLoader: the [ArticleLoader](#ArticleLoader) object
- dataFromFile: a boolean value which specify if the articles are retrieved from file
- downloader: the [Downloader](#Downloader) object
- toExclude: the array of [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects to exclude from the printing of the occurrences
- toShow: the number of words and their occurrences to show

The Argparser class provides:

- getOutputter: returns the [Outputter](#Outputter) object
- getToExclude: returns the array of [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects to exclude from the printing of the occurrences
- getToShow: returns the number of words and their occurrences to show
- getArticleLoader: returns the [ArticleLoader](#ArticleLoader) object
- getDownloader: returns the [Downloader](#Downloader) object
- getDataFromFile: returns if the articles are retrieved from file
- constructor with an array of [java.lang.String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) objects: creates an Argparser object providing various command line argument options and checking if the given arguments are valid or not. Different exceptions are thrown, basing on the errors occurred during the execution of the method:
    - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if the files required are not specified
    - [java.lang.IllegalArgumentException](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html): if the given command line arguments are invalid for different reasons
    - [java.io.IOException](https://docs.oracle.com/javase/8/docs/api/java/io/IOException.html): if there are errors during the parsing of the arguments

## Javadoc
For each class described before, [Javadoc](https://www.oracle.com/it/technical-resources/articles/java/javadoc-tool.html) comments have been written in every constructor, method and as an introduction to the class. Each [Javadoc](https://www.oracle.com/it/technical-resources/articles/java/javadoc-tool.html) comment might contain these information:

- a brief description of what each constructor, method or class do
- the @param option, which allow to specify a parameter in the constructor or the method
- the @return option, which allow to specify what a method returns after its execution
- the @see option, which allow to redirect to another constructor or method used in the current constructor or method
- the @throws option, which allow to specify which exception may be thrown during the execution of the constructor or the method

In order to deploy the [Javadoc](https://www.oracle.com/it/technical-resources/articles/java/javadoc-tool.html) documentation, in the file pom.xml there is the need to specify, on the plugins side, the plugin composed of these attributes:

- groupId: org.apache.maven.plugins
- artifactId: maven-javadoc-plugin
- version: 3.5.0
- configuration
    - show: private, which allows to display all the private attributes and methods of the classes

After all the [Javadoc](https://www.oracle.com/it/technical-resources/articles/java/javadoc-tool.html) comments are written, it is possible to create a graphic documentation of the classes which faithfully reproduces a typical [Java API documentation](https://docs.oracle.com/javase/8/docs/api/). To create it, run on the command line:

`mvn javadoc:javadoc`

and, to show the documentation, open the file index.html located in the path target/site/apidocs in a web browser.
