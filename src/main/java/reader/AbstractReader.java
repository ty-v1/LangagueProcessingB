package reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import builder.Builder;
import data.Book;

public abstract class AbstractReader {

  protected final List<Book> books;
  
  public AbstractReader(final File data) throws IOException{
    books = new ArrayList<>();
    readFile(data);
    sortBooks();
  }
  
  protected abstract void readFile(final File data) throws IOException;
  
  private void sortBooks() {
//    Collections.sort(books, (a, b) -> { return a. - b; });
  }
  
  public final String build() {
    Builder htmlBuilder = new Builder();
    
    htmlBuilder.html();
    htmlBuilder.body();
    for(Book book : books) {
      htmlBuilder.append(book.build());
    }
    htmlBuilder._body();
    htmlBuilder._html();
    return htmlBuilder.getResult();
  }
}
