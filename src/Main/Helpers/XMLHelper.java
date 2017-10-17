package Main.Helpers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLHelper {

public static Document getXMLFIle(String path){
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    Document doc=null;
    try{
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(path);
    } catch (ParserConfigurationException |IOException |SAXException e1) {
        e1.printStackTrace();
    }
    return doc;
}
}
