import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticlesOccurences { //esecuzione: circa 28 secondi
  public static void main(String[] args) {
    ReadXML xmlReader = new ReadXML("articlesTexts.xml");
    ArrayMap wordsMap = new ArrayMap();
    ArrayList<String> wordsNotConsidered = new ArrayList<String>(List.of("a", "also", "am", "and", "an", "are", "as", "at", "because", "be", "but" ,"by", "can", "did", "does", "do", "for", "from", "had", "has", "have", "if", "in", "is", "its", "it", "like", "my", "not", "no", "of", "on", "or", "so", "that", "then", "they", "the", "this", "to", "up", "was", "we", "why", "will", "with", "your", "you"));
    for (int i = 0; i < xmlReader.getArticlesNumber(); i++) {
      String articleText = xmlReader.getArticleTitle(i) + xmlReader.getArticleBody(i);
      String[] articleWords = articleText.split(" ");
      ArrayList<String> wordsWithoutRepetitions = new ArrayList<String>();
      for (int j = 0; j < articleWords.length; j++) {
        String aW = articleWords[j];
        while (aW.length() > 0 && (aW.charAt(aW.length() - 1) == '.' || aW.charAt(aW.length() - 1) == ',' || aW.charAt(aW.length() - 1) == ':' || aW.charAt(aW.length() - 1) == '?' || aW.charAt(aW.length() - 1) == ')' || aW.charAt(aW.length() - 1) == '!' || aW.charAt(aW.length() - 1) == '"' || aW.charAt(aW.length() - 1) == '\'' || aW.charAt(aW.length() - 1) == '-' || aW.charAt(aW.length() - 1) == '”' || aW.charAt(0) == '"' || aW.charAt(0) == '\'' || aW.charAt(0) == '~' || aW.charAt(0) == '-' || aW.charAt(0) == '(' || aW.charAt(0) == '“')) {
          if (aW.charAt(aW.length() - 1) == '.' || aW.charAt(aW.length() - 1) == ',' || aW.charAt(aW.length() - 1) == ':' || aW.charAt(aW.length() - 1) == '?' || aW.charAt(aW.length() - 1) == ')' || aW.charAt(aW.length() - 1) == '!' || aW.charAt(aW.length() - 1) == '"' || aW.charAt(aW.length() - 1) == '\''|| aW.charAt(aW.length() - 1) == '-' || aW.charAt(aW.length() - 1) == '”') {
            aW = aW.substring(0, aW.length() - 1);
          }
          if (aW.length() > 0 && (aW.charAt(0) == '"' || aW.charAt(0) == '~' || aW.charAt(0) == '-' || aW.charAt(0) == '\'' || aW.charAt(0) == '(' || aW.charAt(0) == '“')) {
            aW = aW.substring(1, aW.length());
          }
        }
        if (aW.length() > 0) {
          aW = aW.toLowerCase();
          if (!wordsWithoutRepetitions.contains(aW) && !wordsNotConsidered.contains(aW)) {
            wordsWithoutRepetitions.add(aW);
          }
        }
      }
      articleText = wordsWithoutRepetitions.get(0);
      for (int j = 1; j < wordsWithoutRepetitions.size(); j++) {
        articleText = articleText + " " + wordsWithoutRepetitions.get(j);
      }
      String[] words = articleText.split(" ");
      for (String aW : words) {
        if (wordsMap.get(aW) != -1) {
          wordsMap.put(aW, wordsMap.get(aW) + 1);
        }
        else {
          wordsMap.put(aW, 1);
        }
      }
    }
    wordsMap.orderByValue();
    String[] words = wordsMap.keys();
    final int count = 50;
    try {
      PrintWriter out = new PrintWriter("occurrences.txt");
      for (int i = 0; i < count && i < words.length; i++) {
        out.println(words[i] + " " + wordsMap.get(words[i]));
      }
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
