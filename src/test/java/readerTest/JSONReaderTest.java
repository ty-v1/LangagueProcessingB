package readerTest;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import reader.JSONReader;

public class JSONReaderTest {

  @Test
  public void testBuild() throws IOException {

    BufferedReader fileReader =
        new BufferedReader(new FileReader(new File("./testdata/result.html")));
    StringBuilder sb = new StringBuilder();
    String line;
    String lineSeparator = System.getProperty("line.separator");
    while ((line = fileReader.readLine()) != null) {
      sb.append(line);
      sb.append(lineSeparator);
    }
    fileReader.close();

    JSONReader reader = new JSONReader(new File("./testdata/book.json"));
    // System.out.println(reader.build());
    assertEquals(sb.toString(), reader.build());
  }

}
