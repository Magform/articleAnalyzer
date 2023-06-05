package com.ArticleAnalyzer.DataManagement;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ArticleAnalyzer.Types.Library;
import com.opencsv.exceptions.CsvValidationException;

@RunWith(JUnit4.class)
public class ArticleLoaderTest {

    String PATH = ""; //path of the ArticleLoader_file directory

    //Test for default constructor
    
    @Test(expected = FileNotFoundException.class)
    public void constructorFileWrong() throws FileNotFoundException{
        ArticleLoader test = new ArticleLoader(PATH+"/notExistinFile.txt");
    }

    @Test
    public void constructorWorking() throws FileNotFoundException{
        ArticleLoader test = new ArticleLoader(PATH+"/clearFile.txt");
    }

    //test loader
    @Test
    public void TestLoadCSV() throws CsvValidationException, IOException, ParseException, org.json.simple.parser.ParseException{
        ArticleLoader test = new ArticleLoader(PATH+"/validCSV.csv");
        Library testLibrary = test.getLoadedLibrary();
        assertEquals("testID", testLibrary.getArticle(1).getId());
        assertEquals("testBodyText", testLibrary.getArticle(1).getBodyText());
        assertEquals("other text, with, comma", testLibrary.getArticle(1).getHeadline());
        assertEquals("other test", testLibrary.getArticle(1).getWebUrl());

        assertEquals("testID2", testLibrary.getArticle(2).getId());
        assertEquals("testBodyText2", testLibrary.getArticle(2).getBodyText());
        assertEquals("other2 text2, with2, comma2", testLibrary.getArticle(2).getHeadline());
        assertEquals("other2 test2", testLibrary.getArticle(2).getWebUrl());
    }

    @Test
    public void TestLoadJSON() throws CsvValidationException, IOException, ParseException, org.json.simple.parser.ParseException{
        ArticleLoader test = new ArticleLoader(PATH+"/validJSON.json");
        Library testLibrary = test.getLoadedLibrary();

        assertEquals("testID", testLibrary.getArticle(1).getId());
        assertEquals("testBodyText", testLibrary.getArticle(1).getBodyText());
        assertEquals("other text, with, comma", testLibrary.getArticle(1).getHeadline());
        assertEquals("other test", testLibrary.getArticle(1).getWebUrl());

        assertEquals("testID2", testLibrary.getArticle(2).getId());
        assertEquals("testBodyText2", testLibrary.getArticle(2).getBodyText());
        assertEquals("other2 text2, with2, comma2", testLibrary.getArticle(2).getHeadline());
        assertEquals("other2 test2", testLibrary.getArticle(2).getWebUrl());
    
    }

    @Test
    public void TestLoadJSON2() throws CsvValidationException, IOException, ParseException, org.json.simple.parser.ParseException{
        ArticleLoader test = new ArticleLoader(PATH+"/validJSON2.json");
        Library testLibrary = test.getLoadedLibrary();

        assertEquals("artanddesign/2023/may/03/luxury-and-power-review-bender-persians-greeks-british-museum", testLibrary.getArticle(1).getId());
        assertEquals("artanddesign", testLibrary.getArticle(1).getSectionID());
        assertEquals("Luxury and Power review: boozed-up Persians and Greeks on a 500-year bender", testLibrary.getArticle(1).getHeadline());

        assertEquals("sport/2023/feb/24/horse-racing-talking-horses-frodon-bryony-frost-kempton-coral-trophy", testLibrary.getArticle(2).getId());
        assertEquals("sport", testLibrary.getArticle(2).getSectionID());
        assertEquals("Talking Horses: Our Power can light up Coral Trophy at Kempton", testLibrary.getArticle(2).getHeadline());
    
    }

    @Test
    public void TestLoadJSONWithProblem() throws FileNotFoundException{
        ArticleLoader test = new ArticleLoader(PATH+"/invalidJSON.json");
    }
    
}