package com.ArticleAnalyzer.DataManagement;

import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DownloaderTest {

    //Test for default constructor
    @Test
    public void defaultConstructor(){
        Downloader test = new Downloader();
        assertNull(test.getJSONoutput());
    }

    @Test(expected = FileNotFoundException.class)
    public void constructor_invalidFile() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader("Invalid String");
    }

    //These test need to be configured with the correct path using PATH variable, and also you need to configure your APIKey inside the Downloader_configurationFile
    /*
    private String PATH = "";

    @Test(expected = IllegalArgumentException.class)
    public void constructor_noAPI() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/downloader_configurationFile/noAPI.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_noEndpoint() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/downloader_configurationFile/noEndpoint.txt");

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_extraKey() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/downloader_configurationFile/extraKey.txt");

    }

    @Test(expected = IOException.class)
    public void constructor_invalidLink() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/downloader_configurationFile/invalidLink.txt");


    }

    @Test
    public void constructor_working() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/downloader_configurationFile/working.txt");
        //if nothing is throw I suppose that evrything worked fine
    }

    @Test
    public void constructor_workingGetJSONoutput() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/downloader_configurationFile/working.txt");
        assertEquals("outputter_results.json", test.getJSONoutput());
    }
    */
}