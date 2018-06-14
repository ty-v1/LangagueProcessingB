package builder;

public class Builder {

  private StringBuilder sb = new StringBuilder();

  public Builder html() {
    sb.append("<html>\n");
    return this;
  }

  public Builder _html() {
    sb.append("</html>\n");
    return this;
  }

  public Builder body() {
    sb.append("<body>\n");
    return this;
  }
  
  public Builder _body() {
    sb.append("</body>\n");
    return this;
  }
  
  public Builder append(String message) {
    sb.append(message);
    return this;
  }
  
  public Builder h1(String element) {
    sb.append("<h1>" + element + "</h1>\n");
    return this;
  }

  public Builder h2(String element) {
    sb.append("<h2>" + element + "</h2>\n");
    return this;
  }

  public String getResult() {
    return sb.toString();
  }
}
