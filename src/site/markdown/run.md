# Run
To run the program, simply do:
1. Open a terminal
2. Navigate to the directory of the project
3. Run the following command:

  `java -jar target/articleAnalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar <options>`

To start we suggest the use of the `-h` flag, which allows you to view all the available flags and their respective functions.
In particular the `-h` flag should show you something like this:

`-h,--help`                      Print option list
`-d,--data <arg>`             Choose method of obtaining data (file or download)
`-c,--configurationFile <arg>`   Select the file that contain the configuration for the downloader
`-i,--inputFile <arg>`           Input file path
`-om,--outputMethod <arg>`       Choose output method (C -> console, F -> file, CF -> console and file)
`-o,--outputFile <arg>`          Output file path
`-e,--toExclude <arg>`           Choose some string to exclude from the output (Ex. "str1, str2, str3")
`-s,--show <arg>`                Choose the number of results to show

## Load data
We therefore have that with the -d flag we can choose the method of obtaining data we can use `-d file` to load the data from a file specificated with `-i <fileWithData>`, or we can use `-d download` to download data from a webAPI, for do this but we need to specify the downloader configuration file with `-c <downloaderConfigurationFile>`.
## Output      
As output instead we can choose, thanks to the `-om` flag whether to have it on the console with `-om C`, on a file with `-om F` or on both with `-om CF`, if we are outputting through a file we need also to choose the file with `-o <outputFile>`.   
## Other flag    
We can also choose if we want to exclude some string from the output, for example if we dont want common words (like a, and, or, etc...) to be shown in results with the flag `-e "<toExclude1>, <toExclude2>"` for example if we want to exclude the previusly mentioned words we can use `-e "a, and, or"`.
The last flag is `-s` and allows you to choose the number of results you want to return with `-s <resultsNumber>`

## Example

### Download

### Load from file

### Excluding some data
