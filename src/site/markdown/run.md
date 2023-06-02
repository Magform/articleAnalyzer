# Run
The execution side of the project, from the command to run to the command line argument options to include in it.

## Table of contents
- [Execution](#Execution)
- [Options](#Options)
    - [Load data](#Load_data)
    - [Output](#Output)
    - [Other options](#Other_options)
- [Examples](#Examples)
    - [Example 1](#Example_1)
    - [Example 2](#Example_2)
    - [Example 3](#Example_3)

## Execution
To run the program, simply do:

1. Open a terminal
2. Navigate to the directory of the project
3. Run the following command:

    `java -jar target/articleAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar <options>`

To start we suggest the use of the `-h` option, which allows you to view all the available options and their respective functions.
In particular the `-h` option should show you something like this:

  `-h,--help` ` ` &#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;
  Possible actions        
  `-d,--data <arg>`
  &#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;
  Method of obtaining articles (values admitted: file, download)  
  `-i,--inputFile <arg>` ` `
  &#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;
  File path which contains articles           
  `-c,--configurationFile <arg>` ` `
  &#xa0;&#xa0;
  File path which contains the download configuration                 
  `-om,--outputMethod <arg>`
  &#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;
  Method of printing the output (values admitted: C (console), F (file), CF (console and file))        
  `-o,--outputFile <arg>` ` `
  &#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;
  Output file path        
  `-e,--toExclude <arg>` ` `
  &#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;
  Words to exclude (example: "an, have, the")       
  `-s,--show <arg>` ` `
  &#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;&#xa0;
  Number of results to show           

## Options
Here the options to specify in the command line in order to properly execute the program.

### Load data
There is therefore the possibily with the -d option to choose the method of obtaining data.

- Using `-d file` the articles are loaded from the file specified with `-i <fileWithData>`.
- Using `-d download` the articles are downloaded from the web, specifying the downloader configuration file with `-c <downloaderConfigurationFile>`.

### Output
As output instead we can choose, thanks to the `-om` option whether to have it on the console with `-om C`, on a file with `-om F` or on both with `-om CF`, if we are outputting through a file we need also to choose the file specifying `-o <outputFile>`.

### Other options
We can also choose if we want to exclude some words from the output. For example, if we don't want common words (like a, and, or, the, ...) to be shown in the results with the option `-e "<toExclude1>, <toExclude2>"`, we use this feature as `-e "a, and, or, the"`.        
The last option is `-s` which allows to choose the number of results you want to show, using `-s <resultsNumber>`

## Examples
Here some examples on how to use the command line options. Remember that these options must be specified after writing the execution command `java -jar target/articleAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar`.

### Example 1
This example shows that the articles are downloaded from the web basing on the file downlaodConfiguration.txt and all the words and occurrences (counted once in each article) contained in the articles are printed to console ordered by the words which appears more.

    -d download -c downlaodConfiguration.txt -om C

### Example 2
This example shows that the articles are loaded from the file articles.csv and all the words and occurrences (counted once in each article) contained in the articles are printed to the file occurrences.txt ordered by the words which appears more.

    -d file -i articles.csv -om F -o occurrences.txt

### Example 3
This example is more advanced than the previous ones as the articles are obtained by the file articles.json and the 50 words with more occurrences (counted once in each article and order by the words which appear more) which are not a, an, the, or, be are printed both to console and the file occurrences.txt. In this example, the options are specified by their full names using the `--` clause.

    --data file --inputFile articles.json --outoutMethod CF --outputFile occurrences.txt --toExclude "a, an, the, or, be" --show 50
