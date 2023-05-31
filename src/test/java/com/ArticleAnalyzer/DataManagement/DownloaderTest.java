package com.ArticleAnalyzer.DataManagement;

import static org.junit.Assert.assertEquals;
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
    
    private String PATH = ""; //path of the Downloader_configurationFile directory

    
    @Test(expected = IllegalArgumentException.class)
    public void constructor_noAPI() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/noAPI.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_noEndpoint() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/noEndpoint.txt");

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_extraKey() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/extraKey.txt");

    }
  
    @Test(expected = IOException.class)
    public void constructor_invalidLink() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/invalidLink.txt");
    }

    @Test
    public void constructor_working() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/working.txt");
        //if nothing is throw I suppose that everything worked fine
    }

    @Test
    public void constructor_workingGetJSONoutput() throws FileNotFoundException, IllegalArgumentException, IOException{
        Downloader test = new Downloader(PATH+"/working.txt");
        assertEquals("outputter_results.json", test.getJSONoutput());
    }
    
}
