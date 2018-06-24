package reader;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import data.Book;

public class XMLReader extends AbstractReader {

  private static final Logger log = LoggerFactory.getLogger(XMLReader.class);

  public XMLReader(File data) throws IOException {
    super(data);
  }

  @Override
  protected void readFile(File data) throws IOException {

    SAXParserFactory spfactory = SAXParserFactory.newInstance();
    try {
      SAXParser parser = spfactory.newSAXParser();
      parser.parse(data, new BookVisitor());
    } catch (ParserConfigurationException | SAXException e) {
      e.printStackTrace();
    }
  }

  private class BookVisitor extends DefaultHandler{

    private String name;
    private int year;
    private boolean isBookElement = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      isBookElement = (qName.equals("book"));
      year = (isBookElement)? Integer.parseInt(attributes.getValue("year")) : -1;
    }

    @Override
    public void characters(char[] ch, int offset, int length) {
      name = (isBookElement)? new String(ch, offset, length) : null;
    }

    //End Element
    public void endElement(String uri, String localName, String qName) {
      if(qName.equals("book"))
        books.add(new Book(name, year));
    }
  }
}
