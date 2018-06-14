package data;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import reader.AbstractReader;

public class XMLReader extends AbstractReader {

  public XMLReader(File data) throws IOException {
    super(data);
  }

  @Override
  protected void readFile(File data) throws IOException {
    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    final DocumentBuilder builder;
    try {
      builder = factory.newDocumentBuilder();
      final Document document = builder.parse(data);
      
      final NodeList childNodeList = document.getChildNodes();
      for(int i=0;i<childNodeList.getLength();i++) {
        final Node bookNode = childNodeList.item(i);
        if(bookNode.getNodeType() == Node.ELEMENT_NODE) {
          final Element element = (Element)bookNode;
          final String name = bookNode.getTextContent();
          final int year = Integer.parseInt(element.getAttribute("year"));
          books.add(new Book(name, year));
        }
      }
    } catch (SAXException | ParserConfigurationException e)  {
      e.printStackTrace();
    }
  }
}
