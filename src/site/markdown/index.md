# Article analyzer

The main aim of this project is to develop software capable of download, or load from file, articles from online newspapers rendered available from various sources and to extract and visualize terms most presents in the set of downloaded articles.

## Table Of Content
- [Article analyzer](#article-analyzer)
  - [Table Of Content](#table-of-content)
  - [Getting started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installing](#installing)
    - [Running the tests](#running-the-tests)
  - [Usage](#usage)
    - [Configure downloader](#configure-downloader)
    - [Run](#run)
  - [Class](#class)
  - [Library used](#library-used)
  - [Author](#author)
  - [License](#license)

## Getting started
These instructions will help you set up and run the project on your local machine.

### Prerequisites
Before using this application, ensure you meet the following prerequisites:

- [maven](https://maven.apache.org/) installed: Maven is a build and dependency management tool used for this project. Make sure you have Maven installed on your system.
- Developer key from [The Guardian Api](https://open-platform.theguardian.com/) site, if you want to download and load the article from the respective api.      
- CSV files from [new york times](https://www.nytimes.com/), if you want to test loading articles from a CSV.     

### Installing
To build the project and install the necessary dependance you need to follow these steps:
1. Open a terminal
2. Navigate to the directory of the project
3. Run the following command:

    mvn install
   
### Running the tests
To execute the test and provide the results you need to follow these steps:
1. Open a terminal
2. Navigate to the directory of the project
3. Run the following command:

    mvn test

## Usage
Once you have all the prerequisites and installed the software you can proceed, if you want to download articles from The Guardian, by writing the Downloader configuration file or, if you want to use a file, you can skip the Downloader configuration part.

### Configure downloader
To configure downloader you need to create a new file and insert:

- endpoint: chose the endpoint from [this](endpoint.html) list
- link: link for the API
- APIkey: insert your API key (If needed)
- query: Choose a query (If needed) 
- articleNumber: Choose number of article to load (If needed)
- JSONoutput: Chose the file where the outputter is going to save the results (If not setted it use the default one)


### Run

## Class

## Library used
For the development of this project we have used:

- [Maven](https://maven.apache.org/): development environment management
- [OpenCSV](https://opencsv.sourceforge.net/): Parsing CSV
- [json-simple](https://code.google.com/archive/p/json-simple/): Parsing JSON
- [commons-cli](https://commons.apache.org/proper/commons-cli/): command-line argument handling
- [junit](https://junit.org/junit5/): testing environment management

## Author

## License
This project is licensed under the Affero General Public License (A-GPL) version 3.0. 

The A-GPL is a copyleft license that requires anyone who uses, modifies, or distributes the code to make their source code available under the same license. This means that any changes or improvements made to the code must be released under the A-GPL.

By contributing to this project, you agree to the terms of the A-GPL. If you do not agree to these terms, do not contribute to this project.

For more information about the A-GPL, please see the [GNU website](https://www.gnu.org/licenses/agpl-3.0.en.html).
