package com.ArticleAnalyzer.DataManagement;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ArticleAnalyzer.Types.Library;
import com.opencsv.exceptions.CsvValidationException;

@RunWith(JUnit4.class)
public class ArticleLoaderTest {

    String PATH = "src/test/java/com/ArticleAnalyzer/DataManagement/ArticleLoader_file/"; //path of the ArticleLoader_file directory

    //Test for default constructor

    @Test(expected = FileNotFoundException.class)
    public void constructorFileWrong() throws FileNotFoundException {
        ArticleLoader test = new ArticleLoader(PATH + "notExistinFile.txt");
    }

    @Test
    public void constructorWorking() throws FileNotFoundException {
        ArticleLoader test = new ArticleLoader(PATH + "clearFile.txt");
    }

    //test loader
    @Test
    public void TestLoadCSV() throws CsvValidationException, IOException, ParseException, org.json.simple.parser.ParseException {
        ArticleLoader test = new ArticleLoader(PATH + "validCSV.csv");
        Library testLibrary = test.getLibrary();
        assertEquals("testID", testLibrary.getArticle(0).getIdentifier());
        assertEquals("testBody", testLibrary.getArticle(0).getBody());
        assertEquals("other text, with, comma", testLibrary.getArticle(0).getTitle());
        assertEquals("other test", testLibrary.getArticle(0).getUrl());

        assertEquals("testID2", testLibrary.getArticle(1).getIdentifier());
        assertEquals("testBody2", testLibrary.getArticle(1).getBody());
        assertEquals("other2 text2, with2, comma2", testLibrary.getArticle(1).getTitle());
        assertEquals("other2 test2", testLibrary.getArticle(1).getUrl());
    }

    @Test
    public void TestLoadJSON() throws CsvValidationException, IOException, ParseException {
        ArticleLoader test = new ArticleLoader(PATH + "validJSON.json");
        Library testLibrary = test.getLibrary();

        assertEquals("artanddesign/2023/may/03/luxury-and-power-review-bender-persians-greeks-british-museum", testLibrary.getArticle(0).getIdentifier());
        assertEquals("artanddesign", testLibrary.getArticle(0).getSection());
        assertEquals("Luxury and Power review: boozed-up Persians and Greeks on a 500-year bender", testLibrary.getArticle(0).getTitle());

        assertEquals("sport/2023/feb/24/horse-racing-talking-horses-frodon-bryony-frost-kempton-coral-trophy", testLibrary.getArticle(1).getIdentifier());
        assertEquals("sport", testLibrary.getArticle(1).getSection());
        assertEquals("Talking Horses: Our Power can light up Coral Trophy at Kempton", testLibrary.getArticle(1).getTitle());

    }

    @Test
    public void TestLoadJSONWithProblem() throws FileNotFoundException {
        ArticleLoader test = new ArticleLoader(PATH + "invalidJSON.json");
    }

}
