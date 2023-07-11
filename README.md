# articleAnalyzer
A software capable of downloading, or loading from file, articles from different online newspapers and to extract and visualize words, from their titles, subtitles and bodies, and occurrences (counted once in each article) which appear more in the set of articles received.

## to build
    mvn install

Remember to specify your API-key in the files saved in the Downloader_configurationFile directory of the tests in order to run the project tests.

## to run
    java -jar target/articleAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar

These are the command line options to run the project

    -h, --help                      Possible actions
    -d, --data <arg>                Method of obtaining articles (values admitted: file, download)
    -i, --inputFile <arg>           File path which contains articles
    -c, --configurationFile <arg>   File path which contains the download configuration
    -om, --outputMethod <arg>       Method of printing the output (values admitted: C (console), F (file), CF (console and file))
    -o, --outputFile <arg>          Output file path
    -e, --toExclude <arg>           File path which contains the words to exclude
    -s, --show <arg>                Number of results to show

## to deploy javadoc
    mvn javadoc:javadoc

## to deploy documentation
    mvn site
    mvn site:run

Made for a university project
