package reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Book;

public class JSONReader extends AbstractReader {

  public JSONReader(File data) throws IOException {
    super(data);
  }

  @Override
  protected void readFile(final File data) throws IOException {
    final Gson gson = new Gson();

    TypeToken<List<Book>> booksType = new TypeToken<List<Book>>() {};
    books.addAll(gson.fromJson(new FileReader(data), booksType.getType()));
  }
}
