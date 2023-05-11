import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadCSV {
  public static void main(String[] args) {
    try {
      FileReader fileReader = new FileReader("articles.csv");
      WriteXML xmlWriter = new WriteXML("articlesTexts.xml");
      Scanner sc = new Scanner(fileReader);
      String line = sc.nextLine();
      while (sc.hasNextLine()) {
        line = sc.nextLine();
        String[] articlesFields = line.split(",");
        String articleTitle = articlesFields[2];
        boolean quotationFound = false;
        if (articleTitle.charAt(0) == '"') { //se nel titolo è presente almeno una virgola
          for (int j = 3; j < articlesFields.length; j++) {
            articleTitle = articleTitle + ",";
            for (int i = 0; i < articlesFields[j].length(); i++) {
              if (articlesFields[j].charAt(i) != '"') {
                articleTitle = articleTitle + articlesFields[j].charAt(i);
              }
              else {
                articleTitle = articleTitle + "\"";
                quotationFound = true;
                break;
              }
            }
            if (quotationFound) {
              break;
            }
          }
        }
        int commasInTitle = 0;
        for (int i = 0; i < articleTitle.length(); i++) {
          if (articleTitle.charAt(i) == ',') {
            commasInTitle++;
          }
        }
        int startBodyIndex = 3 + commasInTitle;
        String allWords =  articlesFields[startBodyIndex];
        for (int i = startBodyIndex + 1; i < articlesFields.length - 3; i++) {
          allWords = allWords + "," + articlesFields[i];
        }
        String articleBody = allWords;
        xmlWriter.addArticle("New York Times", articleTitle, articleBody);
      }
      xmlWriter.writeXMLFile();
      sc.close();
      fileReader.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
