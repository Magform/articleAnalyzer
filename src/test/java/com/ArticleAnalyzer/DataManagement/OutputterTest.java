package com.ArticleAnalyzer.DataManagement;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

@RunWith(JUnit4.class)
public class OutputterTest {

    //Test for default constructor
    @Test
    public void defaultConstructor() {
        Outputter test = new Outputter();
        assertEquals("", test.getFile());
        assertEquals(false, test.getToConsole());
        assertEquals(false, test.getToFile());
    }

    @Test
    public void constructorNoFile() throws IOException {
        Outputter test = new Outputter(false, false);
        assertEquals("", test.getFile());
        assertEquals(false, test.getToConsole());
        assertEquals(false, test.getToFile());
        test = new Outputter(true, true);
        assertEquals("", test.getFile());
        assertEquals(true, test.getToConsole());
        assertEquals(true, test.getToFile());
    }

    @Test
    public void constructorWithFile() throws IOException {
        Outputter test = new Outputter(false, false, "test.txt");
        assertEquals("test.txt", test.getFile());
        assertEquals(false, test.getToConsole());
        assertEquals(false, test.getToFile());
        test = new Outputter(true, true, "test.txt");
        assertEquals(true, test.getToConsole());
        assertEquals(true, test.getToFile());
        assertEquals("test.txt", test.getFile());
    }

    @Test
    public void testToFile() throws IOException {
        Outputter test = new Outputter(false, false);
        assertEquals("", test.getFile());
        test.setFile("test.txt");
        assertEquals("test.txt", test.getFile());
    }

    //Test printing to console
    @Test
    public void testPrintStringToConsole() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        Outputter test = new Outputter(true, false);
        test.print("test test test");

        System.out.flush();
        System.setOut(originalOut);
        String printedOutput = outputStream.toString().trim();
        assertEquals("test test test", printedOutput);
    }

    @Test
    public void testPrintArticleToConsole() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        Article testArticle = new Article();
        testArticle.setIdentifier("test");
        testArticle.setBody("test");

        Outputter test = new Outputter(true, false);
        test.print(testArticle);

        System.out.flush();
        System.setOut(originalOut);
        String printedOutput = outputStream.toString().trim();

        String expected = "{\"id\":\"test\",\"body\":\"test\",}";
        expected = expected.replaceAll("\\s+", "");
        printedOutput = printedOutput.replaceAll("\\s+", "");

        assertEquals(expected, printedOutput);
    }

    @Test
    public void testPrintLibraryToConsole() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        Article testArticle1 = new Article();
        testArticle1.setIdentifier("test1");
        Article testArticle2 = new Article();
        testArticle2.setIdentifier("test2");

        Library testLibrary = new Library(testArticle1);
        testLibrary.addArticle(testArticle2);

        Outputter test = new Outputter(true, false);
        test.print(testLibrary);

        System.out.flush();
        System.setOut(originalOut);
        String printedOutput = outputStream.toString().trim();

        String expected = "{\"article\":[{\"id\":\"test1\",},{\"id\":\"test2\",}]}";
        expected = expected.replaceAll("\\s+", "");
        printedOutput = printedOutput.replaceAll("\\s+", "");

        assertEquals(expected, printedOutput);
    }

    @Test
    public void testPrintHashMapToConsole() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        LinkedHashMap<String, Integer> testHashMap = new LinkedHashMap<>();

        testHashMap.put("test1", 1);
        testHashMap.put("test2", 2);

        Outputter test = new Outputter(true, false);
        test.print(testHashMap);

        System.out.flush();
        System.setOut(originalOut);
        String printedOutput = outputStream.toString().trim();

        String expected = "test1 1 \n test2 2";
        expected = expected.replaceAll("\\s+", "");
        printedOutput = printedOutput.replaceAll("\\s+", "");

        assertEquals(expected, printedOutput);
    }

    //Test printing to file
    @Test
    public void testPrintStringToFile() throws IOException {


        Outputter test = new Outputter(false, true, "test.txt");
        test.print("test test test");

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    content.append(System.lineSeparator()); // Append a new line for empty lines
                } else {
                    content.append(line);
                }
            }
        }

        // Perform assertions on the file content
        String expected = "test test test"; // Replace with the expected output
        assertEquals(expected, content.toString());

    }

    @Test
    public void testPrintArticleToFile() throws IOException {

        Article testArticle = new Article();
        testArticle.setIdentifier("test");
        testArticle.setBody("test");

        Outputter test = new Outputter(false, true, "test.txt");
        test.print(testArticle);

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    content.append(System.lineSeparator()); // Append a new line for empty lines
                } else {
                    content.append(line);
                }
            }
        }

        String printedOutput = content.toString();
        String expected = "{\"id\":\"test\",\"body\":\"test\",}";
        expected = expected.replaceAll("\\s+", "");
        printedOutput = printedOutput.replaceAll("\\s+", "");

        assertEquals(expected, printedOutput);
    }

    @Test
    public void testPrintLibraryToFile() throws IOException{

        Article testArticle1 = new Article();
        testArticle1.setIdentifier("test1");
        Article testArticle2 = new Article();
        testArticle2.setIdentifier("test2");

        Library testLibrary = new Library(testArticle1);
        testLibrary.addArticle(testArticle2);

        Outputter test = new Outputter(false, true, "test.txt");
        test.print(testLibrary);
        
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    content.append(System.lineSeparator()); // Append a new line for empty lines
                } else {
                    content.append(line);
                }
            }
        }

        String printedOutput = content.toString();
        String expected = "{\"article\":[{\"id\":\"test1\",},{\"id\":\"test2\",}]}";
        expected = expected.replaceAll("\\s+", "");
        printedOutput = printedOutput.replaceAll("\\s+", "");

        assertEquals(expected, printedOutput);
    }

    @Test
    public void testPrintHashMapToFile() throws IOException {

        LinkedHashMap<String, Integer> testHashMap = new LinkedHashMap<>();

        testHashMap.put("test1", 1);
        testHashMap.put("test2", 2);

        Outputter test = new Outputter(false, true, "test.txt");
        test.print(testHashMap);

        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    content.append(System.lineSeparator()); // Append a new line for empty lines
                } else {
                    content.append(line);
                }
            }
        }

        String printedOutput = content.toString();
        String expected = "test1 1 \n test2 2";
        expected = expected.replaceAll("\\s+", "");
        printedOutput = printedOutput.replaceAll("\\s+", "");

        assertEquals(expected, printedOutput);
    }
}
