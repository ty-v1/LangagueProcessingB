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

  private static final Logger log = LoggerFactory.getLogger(XMLReader.class);

  public XMLReader(File data) throws IOException {
    super(data);
  }

  @Override
  protected void readFile(File data) throws IOException {

    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    // XML を解析する
    try {
      final XMLStreamReader streamReader =
          xmlInputFactory.createXMLStreamReader(new FileReader(data));

      log.info("start analyzing " + data.getName());
      while (streamReader.hasNext()) {
        publishEvent(streamReader);

        if (streamReader.hasNext())
          streamReader.next();
      }
      streamReader.close();

    } catch (FileNotFoundException | XMLStreamException e) {
      e.printStackTrace();
    }
  }

  private void publishEvent(XMLStreamReader streamReader) throws XMLStreamException {
    log.info("publishEvent(XMLStreamReader) called");

    switch (streamReader.getEventType()) {
      case XMLStreamConstants.START_ELEMENT:
        log.info("publish tag started");

        if (!streamReader.hasNext()) {
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

    while (streamReader.hasNext()) {

      switch (streamReader.getEventType()) {
        case XMLStreamConstants.START_ELEMENT:
          log.info("book tag started");
          log.info("attribute " + streamReader.getAttributeValue(0) + " loaded");
          int year = Integer.parseInt(streamReader.getAttributeValue(0));

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
