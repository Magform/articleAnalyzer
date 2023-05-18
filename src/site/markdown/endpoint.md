# Endpoint lists

## The Guardian
This endpoint allows you to obtain articles directly from TheGuardian's website via an API key, for the configuration you need to put:

- endpoint: "TheGuardian"
- link: "https://content.guardianapis.com/search?"
- APIkey: your API key
- query: search queries [OPTIONAL]
- articleNumber: number of article you want to load, for this endpoint the maximum is 100 [OPTIONAL]
- JSONoutput: location where the results are saved, if not entered a default output file is expected (downloaded.json) [OPTIONAL]

### Example
A couple of examples of valid configuration files will be provided:

#### Example 1
in this example they request the default number of results (10) without a specific query or output file (the API key is omitted for obvious security reasons).

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?"
    APIkey: "APIkey"

#### Example 2
In this example we request 100 results with the query "nuclear power" and save the results to downloader_results.json (the API key is omitted for obvious security reasons).

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?"
    APIkey: "APIkey"
    query: "nuclear power"
    articleNumber: "100"
    JSONoutput: "outputter_results.json"

#### Example 3
This example is more advanced than the previous ones as a non-default parameter is also inserted. In particular, we are going to obtain the content of the second page of results, for all the possible non-default queries see the [official documentation](https://open-platform.theguardian.com/documentation/)

    endpoint: "TheGuardian"
    link: "https://content.guardianapis.com/search?page=2"
    APIkey: "APIkey"
    query: "nuclear power"
    articleNumber: "100"
    JSONoutput: "outputter_results.json"
