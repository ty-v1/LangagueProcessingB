package data;

import com.google.gson.annotations.SerializedName;
import builder.Builder;

public class Book {

  private int year;

  @SerializedName("book")
  private String name;

  public Book(String _name, int _year) {
    name = _name;
    year = _year;
  }
  
  void setYear(int _year) {
    year = _year;
  }

  void setName(String _name) {
    name = _name;
  }
  
  public int getYear(){
    return year;
  }
  
  public String build() {
    Builder htmlBuilder = new Builder();
    htmlBuilder.h2(String.format("%s(%d年)", name, year));
    return htmlBuilder.getResult();
  }
}
