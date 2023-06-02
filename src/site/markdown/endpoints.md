# Endpoints
Here a list of all the endpoints which allow to download articles from different online newspapers.

## Table of contents
- [The Guardian](#The_Guardian)
    - [Examples](#Examples)
        - [Example 1](#Example_1)
        - [Example 2](#Example_2)
        - [Example 3](#Example_3)

## The Guardian
This endpoint allows you to obtain articles directly from The Guardian's website via an API key. For the configuration, you need to specify:

- endpoint: "TheGuardian"
- link: "https://content.guardianapis.com/search?"
- APIkey: your API key
- query: search queries [OPTIONAL]
- articleNumber: number of articles you want to load, (for this endpoint the maximum is 200). If not entered, the default number of results is 10 [OPTIONAL]
- JSONOutput: location where the results will be saved, if not entered a default output file is expected (downloaded.json) [OPTIONAL]

### Examples
Here some examples of valid configuration files. In these examples, the API-key is omitted for obvious security reasons.

#### Example 1
In this example the request is the default number of results without a specific query or output file.

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?"
    APIkey: "APIkey"

#### Example 2
In this example the request is 100 results with the query "nuclear power". The results will be saved into downloader_results.json.

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?"
    APIkey: "APIkey"
    query: "nuclear power"
    articleNumber: "100"
    JSONOutput: "outputter_results.json"

#### Example 3
This example is more advanced than the previous ones as a non-default parameter is also inserted. In particular, we are going to obtain the content of the second page of results, for all the possible non-default queries see the [official documentation](https://open-platform.theguardian.com/documentation/)

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?page=2"
    APIkey: "APIkey"
    query: "nuclear power"
    articleNumber: "100"
    JSONOutput: "outputter_results.json"
