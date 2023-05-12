package com.ArticleAnalyzer;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.ArrayList;

public class ReadXML {
	private Element root;

	public ReadXML(String fileName) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder domParser = dbf.newDocumentBuilder();
			Document document = domParser.parse(new File(fileName));
			root = document.getDocumentElement();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRootName() {
		return root.getNodeName();
	}

	public int getArticlesNumber() {
		return root.getChildNodes().getLength();
	}

	public String getArticleId(int index) {
		String id = "";
		Node indexArticle = root.getChildNodes().item(index);
		for (int i = 0; i < indexArticle.getChildNodes().getLength(); i++) {
			if (indexArticle.getChildNodes().item(i).getNodeName().equals("id")) {
				id = indexArticle.getChildNodes().item(i).getTextContent();
				break;
			}
		}
		return id;
	}

	public String getArticleTitle(int index) {
		String title = "";
		Node indexArticle = root.getChildNodes().item(index);
		for (int i = 0; i < indexArticle.getChildNodes().getLength(); i++) {
			if (indexArticle.getChildNodes().item(i).getNodeName().equals("title")) {
				title = indexArticle.getChildNodes().item(i).getTextContent();
				break;
			}
		}
		return title;
	}

	public String getArticleBody(int index) {
		String body = "";
		Node indexArticle = root.getChildNodes().item(index);
		for (int i = 0; i < indexArticle.getChildNodes().getLength(); i++) {
			if (indexArticle.getChildNodes().item(i).getNodeName().equals("body")) {
				body = indexArticle.getChildNodes().item(i).getTextContent();
				break;
			}
		}
		return body;
	}
}
