package com.ArticleAnalyzer.DataManagement;

import java.io.FileWriter;
import java.io.File;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import com.ArticleAnalyzer.Types.Article;
import com.ArticleAnalyzer.Types.Library;

public class Outputter {
    private boolean toConsole;
    private boolean toFile;
    private String file;

    public Outputter() {
        this.toConsole = false;
        this.toFile = false;
        this.file = "";
    }

    public Outputter(boolean toConsole, boolean toFile) throws IOException {
        this.toConsole = toConsole;
        this.toFile = toFile;
        this.file = "";
    }

    public Outputter(boolean toConsole, boolean toFile, String file) throws IOException {
        this(toConsole, toFile);
        this.file = file;
        check();
    }

    public String getFile(){
        return this.file;
    }

    public boolean getToConsole(){
        return this.toConsole;       
    }

    public void setFile(String toSet) throws IOException{
        this.file = toSet;   
        check();    
    }

    public boolean getToFile(){
        return this.toFile;
    }

    //Funzioni di print di una stringa (le funzioni sono private per scelta implementativa)
    public void print(String toPrint) throws IOException{
        if(this.toConsole){
            this.printToConsole(toPrint);
        }
        if(this.toFile){
            this.printToFile(toPrint);
        }
    }

    private void printToConsole(String toPrint){
        System.out.println(toPrint);
    }

    
    private void printToFile(String toPrint) throws IOException{
        try {
            File file = new File(this.file);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(this.file, true);
            fileWriter.write(toPrint);
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + this.file, e);
        }
    }

    public void print(Article toPrint) throws IOException{
        print("{\n");
        if(!toPrint.getId().equals("")){
            print("\"id\" : \"" + toPrint.getId() + "\",\n");
        }
        if(!toPrint.getType().equals("")){
            print("\"type\" : \"" + toPrint.getType() + "\",\n");
        }
        if (!toPrint.getSectionID().equals("")) {
            System.out.println("\"sectionID\" : \"" + toPrint.getSectionID() + "\",\n");
        }
        if (!toPrint.getWebUrl().equals("")) {
            System.out.println("\"webUrl\" : \"" + toPrint.getWebUrl() + "\",\n");
        }
        if (!toPrint.getApiUrl().equals("")) {
            System.out.println("\"apiUrl\" : \"" + toPrint.getApiUrl() + "\",\n");
        }
        if (!toPrint.getTrailText().equals("")) {
            System.out.println("\"trailText\" : \"" + toPrint.getTrailText() + "\",\n");
        }
        if (!toPrint.getHeadline().equals("")) {
            System.out.println("\"headline\" : \"" + toPrint.getHeadline() + "\",\n");
        }
        if (!toPrint.getByline().equals("")) {
            System.out.println("\"byline\" : \"" + toPrint.getByline() + "\",\n");
        }
        if (!toPrint.getPublication().equals("")) {
            System.out.println("\"publication\" : \"" + toPrint.getPublication() + "\",\n");
        }
        if (!toPrint.getInternalPageCode().equals("")) {
            System.out.println("\"internalPageCode\" : \"" + toPrint.getInternalPageCode() + "\",\n");
        }
        if (!toPrint.getProductionOffice().equals("")) {
            System.out.println("\"productionOffice\" : \"" + toPrint.getProductionOffice() + "\",\n");
        }
        if (!toPrint.getBody().equals("")) {
            System.out.println("\"body\" : \"" + toPrint.getBody() + "\",\n");
        }
        if (!toPrint.getStandfirst().equals("")) {
            System.out.println("\"standfirst\" : \"" + toPrint.getStandfirst() + "\",\n");
        }
        if (!toPrint.getShortUrl().equals("")) {
            System.out.println("\"shortUrl\" : \"" + toPrint.getShortUrl() + "\",\n");
        }
        if (!toPrint.getThumbnail().equals("")) {
            System.out.println("\"thumbnail\" : \"" + toPrint.getThumbnail() + "\",\n");
        }
        if (!toPrint.getTitle().equals("")) {
            System.out.println("\"title\" : \"" + toPrint.getTitle() + "\",\n");
        }
        if (!toPrint.getFullText().equals("")) {
            System.out.println("\"fulltext\" : \"" + toPrint.getFullText() + "\",\n");
        }
        if(!toPrint.getSourceSet().equals("")) {
            print("\"source_set\" : \"" + toPrint.getSourceSet() + "\",\n");
        }
        if(!toPrint.getSource().equals("")) {
            print("\"source\" : \"" + toPrint.getSource() + "\",\n");
        }
        if(!toPrint.getMain().equals("")) {
            print("\"main\" : \"" + toPrint.getMain() + "\",\n");
        }
        if(!toPrint.getBylineHtml().equals("")) {
            print("\"byline_html\" : \"" + toPrint.getBylineHtml() + "\",\n");
        }
        if(!toPrint.getBodyText().equals("")) {
            print("\"body_text\" : \"" + toPrint.getBodyText() + "\",\n");
        }
        if(!toPrint.getLang().equals("")) {
            print("\"lang\" : \"" + toPrint.getLang() + "\",\n");
        }  
        if (toPrint.getScore() != -1.0f) {
            System.out.println("\"starRating\" : " + toPrint.getScore() + ",\n");
        }
        if (toPrint.getStarRating() != -1) {
            System.out.println("\"starRating\" : " + toPrint.getStarRating() + ",\n");
        }
        if (toPrint.getNewspaperPageNumber() != -1) {
            System.out.println("\"newspaperPageNumber\" : " + toPrint.getNewspaperPageNumber() + ",\n");
        }
        if (toPrint.getCharCount() != -1) {
            System.out.println("\"charCount\" : " + toPrint.getCharCount() + ",\n");
        }
        if (toPrint.getWordcount() != -1) {
            System.out.println("\"wordCount\" : " + toPrint.getWordcount() + ",\n");
        }
        if (toPrint.getWebPublicationDate() != null) {
            print("\"webPublicationDate\": \"" + toPrint.getWebPublicationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"\n");
        }
        if (toPrint.getLastModified() != null) {
            print("\"lastModified\": \"" + toPrint.getLastModified().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"\n");
        }
        if (toPrint.getCommentCloseDate() != null) {
            print("\"commentCloseDate\": \"" + toPrint.getCommentCloseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"\n");
        }
        if (toPrint.getFirstPublicationDate() != null) {
            print("\"firstPublicationDate\": \"" + toPrint.getFirstPublicationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"\n");
        }
        if (toPrint.getNewspaperEditionDate() != null) {
            print("\"newspaperEditionDate\": \"" + toPrint.getNewspaperEditionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")) + "\"\n");
        }
        if (toPrint.getCommentable() != null) {
            print("\"commentable\": " + toPrint.getCommentable() + ",\n");
        }
        if (toPrint.getPremoderated() != null) {
            print("\"premoderated\": " + toPrint.getPremoderated() + ",\n");
        }
        if (toPrint.getAllowUGc() != null) {
            print("\"allowUGc\": " + toPrint.getAllowUGc() + ",\n");
        }
        if (toPrint.getShouldHideAdverts() != null) {
            print("\"shouldHideAdverts\": " + toPrint.getShouldHideAdverts() + ",\n");
        }
        if (toPrint.getLiveBloggingNow() != null) {
            print("\"liveBloggingNow\": " + toPrint.getLiveBloggingNow() + ",\n");
        }
        if (toPrint.getHasStoryPackage() != null) {
            print("\"hasStoryPackage\": " + toPrint.getHasStoryPackage() + ",\n");
        }
        if (toPrint.getShowInRelatedContent() != null) {
            print("\"showInRelatedContent\": " + toPrint.getShowInRelatedContent() + ",\n");
        }
        if (toPrint.getLegallySensitive() != null) {
            print("\"legallySensitive\": " + toPrint.getLegallySensitive() + ",\n");
        }
        if (toPrint.getIsLive() != null) {
            print("\"isLive\": " + toPrint.getIsLive() + ",\n");
        }
        if (toPrint.getIsInappropriateForSponsorShip() != null) {
            print("\"isInappropriateForSponsorship\": " + toPrint.getIsInappropriateForSponsorShip() + ",\n");
        }
        if (toPrint.getShouldHideReaderRevenue() != null) {
            print("\"shouldHideReaderRevenue\": " + toPrint.getShouldHideReaderRevenue() + ",\n");
        }  
        if (toPrint.getShowAffiliateLinks() != null) {
            print("\"showAffiliateLinks\": " + toPrint.getShowAffiliateLinks() + ",\n");
        }
        if (toPrint.getShowTableOfContents() != null) {
            print("\"showTableOfContents\": " + toPrint.getShowTableOfContents() + ",\n");
        }
        print("}\n");
    }

    public void print(Library toPrint) throws IOException{
        for(int i = 1; i < toPrint.getTotalArticleNumber(); i++){
            print(toPrint.getArticle(i));
        }
    }

    public void print(LinkedHashMap<String, Integer> toPrint) throws IOException{
        for (String key : toPrint.keySet()) {
            print(key + " " + toPrint.get(key)+"\n");
        }
    }

    private void check() throws IOException{
        if(toFile){
            if(this.file==""){
                throw new IOException("File were to print not setted");
            }
            File file = new File(this.file);
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
        }
    }
}
