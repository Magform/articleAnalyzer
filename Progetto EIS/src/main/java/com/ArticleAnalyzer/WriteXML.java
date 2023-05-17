package com.ArticleAnalyzer;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class WriteXML {
	private Document document;
	private Element root;
	private String fileName;

	public WriteXML(String fn) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder domParser = dbf.newDocumentBuilder();
			document = domParser.newDocument();
			root = document.createElement("articles");
			fileName = fn;
	    document.appendChild(root);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addArticle(Article a) {
		Element article = document.createElement("article");
		root.appendChild(article);
		Element source = document.createElement("source");
		source.appendChild(document.createTextNode(a.getSource()));
		article.appendChild(source);
		Element title = document.createElement("title");
		title.appendChild(document.createTextNode(a.getTitle()));
		article.appendChild(title);
		Element body = document.createElement("body");
		body.appendChild(document.createTextNode(a.getBody()));
		article.appendChild(body);
	}

	public void writeXMLFile() {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
