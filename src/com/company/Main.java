package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        // Что бы прочитать XML файл, нужно создать сборщик ( ◡‿◡ *)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("text.xml"));

        // для быстрого поиска
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        // указать прямой путь к нужной информации
        System.out.println(xPath.evaluate("bookstore/book[1]", document));
        System.out.println(xPath.evaluate("bookstore/book[2]", document));
        System.out.println(xPath.evaluate("bookstore/book[3]", document));

        //можно получить полный нод(все элементы)
        NodeList list = (NodeList) xPath.evaluate("bookstore/book", document, XPathConstants.NODESET);
        printElement(list);

        //Кол-во элементов
        int count = ((Number)xPath.evaluate("count(bookstore/book)", document, XPathConstants.NUMBER)).intValue();
        System.out.println(count);

    }

        static void printElement(NodeList nodeList){

            for (int i =0; i<nodeList.getLength(); i++){
                if (nodeList.item(i) instanceof Element){
                    System.out.print(((Element) nodeList.item(i)).getTagName());
                    if(((Element) nodeList.item(i)).hasAttribute("category")){
                        System.out.println(" = "+((Element) nodeList.item(i)).getAttribute("category"));
                    }else{
                        System.out.println(" = "+((Element) nodeList.item(i)).getTextContent());
                    }
                    if (nodeList.item(i).hasChildNodes()){
                        printElement(nodeList.item(i).getChildNodes());
                    }
                }
            }

        }
}
