# Endpoints
Here a list of all the endpoints which allow to download articles from different online newspapers.

## Table of contents
- [The Guardian](#The_Guardian)
    - [Examples](#Examples)
        - [Example 1](#Example_1)
        - [Example 2](#Example_2)
        - [Example 3](#Example_3)
        - [Example 4](#Example_4)

## The Guardian
This endpoint allows you to obtain articles directly from The Guardian's website via an API key. For the configuration, you need to specify:

- endpoint: "TheGuardian"
- link: "https://content.guardianapis.com/search?"
- APIkey: your API key
- query: search queries [OPTIONAL]
- articlesPerPage: number of articles you want to load per page (for this endpoint the maximum is 200). If not entered, the default number of results per page is 10 [OPTIONAL]
- initialPage: number of the first page to download. If not entered, the default initial page to download is 1 [OPTIONAL]
- totalPageNumber: total number of pages to download. If not entered, the default number of pages to download is 1 [OPTIONAL]
- JSONOutput: location where the results will be saved. If not entered, the default output file is downloaded.json [OPTIONAL]

Each value of these configuration settings must be enclosed by "".

### Examples
Here some examples of valid configuration files. In these examples, the API-key is omitted for obvious security reasons.

#### Example 1
In this example the request is the default number of results without a specific query or output file. The results will be saved into downloader_results.json.

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?"
    APIkey: "APIkey"

#### Example 2
In this example the request is 100 results with the query "nuclear power". The results will be saved into outputter_results.json.

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?"
    APIkey: "APIkey"
    query: "nuclear power"
    articlesPerPage: "100"
    JSONOutput: "outputter_results.json"

#### Example 3
In this example the request is 400 results, in particular to download pages from 2 to 5 with 100 results per page, with the query "nuclear power". The results will be saved into outputter_results.json.

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?"
    APIkey: "APIkey"
    query: "nuclear power"
    articlesPerPage: "100"
    initalPage: "2"
    totalPageNumber: "4"
    JSONOutput: "outputter_results.json"

#### Example 4
This example is more advanced than the previous ones as a non-default parameter is also inserted. In particular, we are going to obtain only the page with a specific tag. For all the possible queries, see the [official documentation](https://open-platform.theguardian.com/documentation/).

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?tag=technology/apple"
    APIkey: "APIkey"
    query: "nuclear power"
    articlesPerPage: "100"
    JSONOutput: "outputter_results.json"
