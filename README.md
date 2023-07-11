# articleAnalyzer
A software capable of downloading, or loading from file, articles from different online newspapers and to extract and visualize words, from their titles, subtitles and bodies, and occurrences (counted once in each article) which appear more in the set of articles received.

## to build
    mvn install

Remember to specify your API-key in the files saved in the Downloader_configurationFile directory of the tests in order to run the project tests.

## to run
    java -jar target/articleAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar

These are the command line options to run the project

    -c, --configurationFile <arg>   File che contiene la configurazione del download
    -d, --data <arg>                Metodo di ottenimento degli articoli (valori ammessi: file, download)
    -e, --toExclude <arg>           Parole da escludere ("an, have, the")
    -h, --help                      Azioni possibili
    -i, --inputFile <arg>           Path del file di input
    -o, --outputFile <arg>          Path del file di output
    -om, --outputMethod <arg>       Metodo di stampa dell'output (valori ammessi: C (console), F (file), CF (console e file)
    -s, --show <arg>                Numero di risultati da mostrare

## to deploy javadoc
    mvn javadoc:javadoc

## to deploy documentantion
    mvn site
    mvn site:run

Made for a university project
