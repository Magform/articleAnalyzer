# Article analyzer

The main aim of this project is to develop a software capable of download, or load from file, articles from different online newspapers and to extract and visualize words, from their titles and bodies, and occurrences (counted once in each article) which appear more in the set of downloaded articles.

## Table Of Content
- [Getting started](#getting-started)
  - [Prerequisites](#prerequisites)
    - [Installing](#installing)
    - [Running the tests](#running-the-tests)
- [Usage](#usage)
    - [Configure downloader](#configure-downloader)
    - [Run](#run)
- [Classes](#classes)
- [Libraries used](#libraries-used)
- [Authors](#authors)
- [License](#license)


## Getting started
These instructions will help you set up and run the project on your local machine.

### Prerequisites
Before using this application, ensure you meet the following prerequisites:

- [Maven](https://maven.apache.org/) installed: Maven is a build and dependency management tool used for this project. Make sure you have Maven installed on your system.
- Developer key from [The Guardian Api](https://open-platform.theguardian.com/) site, if you want to download and load the article from the respective api.      
- CSV files from [New York Times](https://www.nytimes.com/), if you want to test loading articles from a CSV.     

### Installing
To build the project and install the necessary dependance you need to follow these steps:    
1. Open a terminal     
2. Navigate to the directory of the project     
3. Run the following command: 

   `mvn install`
   
### Running the tests
To execute the test and provide the results you need to follow these steps:      
1. Open a terminal      
2. Navigate to the directory of the project     
3. Run the following command:
   
   `mvn test`

## Usage
Once you have all the prerequisites and installed the software you can proceed, if you want to download articles from The Guardian, by writing the Downloader configuration file or, if you want to use a file, you can skip the Downloader configuration part.    

### Configure downloader
To configure downloader you need to create a new file, choose the endpoint that you want to use, and insert all the data needed for requested endpoint.         
You can find a list with all endpoint and the configuration needed for every endpoint at [this](endpoint.html) link.     

### Run
If you have correctly performed the installation you should have obtained, in the main project folder, a "target" folder containing, among other folders, a file named "articleAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar".           
If so then the installation was successful and we can proceed to use our software.    
To run the program, simply do:     
1. Open a terminal     
2. Navigate to the directory of the project      
3. Run the following command:

   `java -jar target/articleAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar <options>`

To start we suggest the use of the `-h` flag, which allows you to view all the available flags and their respective functions.        
For more information on the various flags and some examples go [here](run.html).     

## Classes
In this section we will explain all the classes.      
In particular we have 7 classes and the main:     

- Main: merges all classes and allows the user to interact     
- Article: implements an Article with all the variables    
- Library: implements a collection of Article objects     
- ArticleLoader: transforms data stored on a file into an instance of the Library class  
- Downloader: download data from a specified endpoint basing on a configuration file   
- Outputter: transforms objects into output (to file or command line) 
- Elaborator: processes a Library to get what is requested     
- Argparser: reads command-line arguments and processes them           

To go into more detail on how they interact with each other and how they work go [here](classes.html) 

## Libraries used
For the development of this project we have used:

- [Maven](https://maven.apache.org/): development environment management
- [OpenCSV](https://opencsv.sourceforge.net/): Parsing CSV
- [json-simple](https://code.google.com/archive/p/json-simple/): Parsing JSON
- [commons-cli](https://commons.apache.org/proper/commons-cli/): command-line argument handling
- [junit](https://junit.org/junit5/): testing environment management

## Authors
This is a project created by four students who are attending the second year of Computer Engineering at the University of Padua, Italy.
Here their names:
- Sara Calzavara
- Daniele Casson
- Nicolas Ferraresso
- Antonio Gastaldi

## License
This project is licensed under the Affero General Public License (A-GPL) version 3.0. 

The A-GPL is a copyleft license that requires anyone who uses, modifies, or distributes the code to make their source code available under the same license. This means that any changes or improvements made to the code must be released under the A-GPL.

By contributing to this project, you agree to the terms of the A-GPL. If you do not agree to these terms, do not contribute to this project.

For more information about the A-GPL, please see the [GNU website](https://www.gnu.org/licenses/agpl-3.0.en.html).
