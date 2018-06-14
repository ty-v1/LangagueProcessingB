package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import data.Book;

public class XMLReader extends AbstractReader {


  public static void main(String args[]) {
    try {
      new XMLReader(new File("./testdata/book.xml"));
    } catch (IOException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }
  }

  private static final Logger log = LoggerFactory.getLogger(XMLReader.class);

  public XMLReader(File data) throws IOException {
    super(data);
  }

  @Override
  protected void readFile(File data) throws IOException {
//    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    final DocumentBuilder builder;
//    try {
//      builder = factory.newDocumentBuilder();
//      final Document document = builder.parse(data);
//
//      final NodeList childNodeList = document.getChildNodes();
//      for(int i=0;i<childNodeList.getLength();i++) {
//        final Node bookNode = childNodeList.item(i);
//        if(bookNode.getNodeType() == Node.ELEMENT_NODE) {
//          final Element element = (Element)bookNode;
//          final String name = bookNode.getTextContent();
//          final int year = Integer.parseInt(element.getAttribute("year"));
//          books.add(new Book(name, year));
//        }
//      }
//    } catch (SAXException | ParserConfigurationException e)  {
//      e.printStackTrace();
//    }

    a(data);
  }


  public void a(File file) {

    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    // XML を解析する
    try {
      final XMLStreamReader steamReader = xmlInputFactory.createXMLStreamReader(new FileReader(file));

      log.info("start analyzing " + file.getName());
      while(steamReader.hasNext()){
        publishEvent(steamReader);

        if(steamReader.hasNext())
          steamReader.next();
      }
      steamReader.close();

    } catch (FileNotFoundException | XMLStreamException e) {
      e.printStackTrace();
    }
  }

  private void publishEvent(XMLStreamReader streamReader) throws XMLStreamException {
    log.info("publishEvent(XMLStreamReader) called");

    switch (streamReader.getEventType()) {
      case XMLStreamConstants.START_ELEMENT:
        log.info("publish tag started");

        if(!streamReader.hasNext()) {
          log.info("publish tag has not book tag");
          return;
        }
        streamReader.next();
        bookEvent(streamReader);
        break;
      case XMLStreamConstants.END_ELEMENT:
        log.info("publish tag ended");
        return;
    }
  }

  private void bookEvent(XMLStreamReader streamReader) throws XMLStreamException {
    log.info("bookEvent(XMLStreamReader) called");

    while(streamReader.hasNext()) {

      switch (streamReader.getEventType()) {
        case XMLStreamConstants.START_ELEMENT:
          log.info("book tag started");
          log.info("attribute " + streamReader.getAttributeValue(0) + " loaded");
          int year =Integer.parseInt(streamReader.getAttributeValue(0));

          String name = streamReader.getElementText();
          log.info("element text " + name + " loaded");

          log.info("book instance created");
          log.info("books size : " + books.size());

          books.add(new Book(name, year));
          break;
        case XMLStreamConstants.END_ELEMENT:
          log.info("book tag ended");
          break;
        case XMLStreamConstants.CDATA:
        case XMLStreamConstants.COMMENT:
        case XMLStreamConstants.ENTITY_REFERENCE:
        case XMLStreamConstants.CHARACTERS:
          name = streamReader.getText();
          log.info("text : " + name);
          break;
      }
      streamReader.next();
    }
  }
}
