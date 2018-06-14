package builder;

public class Builder {

  private StringBuilder sb = new StringBuilder();
  private final static String lineSeparator = System.getProperty("line.separator");

  public Builder html() {
    sb.append("<html>");
    sb.append(lineSeparator);
    return this;
  }

  public Builder _html() {
    sb.append("</html>");
    sb.append(lineSeparator);
    return this;
  }

  public Builder body() {
    sb.append("<body>");
    sb.append(lineSeparator);
    return this;
  }

  public Builder _body() {
    sb.append("</body>");
    sb.append(lineSeparator);
    return this;
  }

  public Builder append(String message) {
    sb.append(message);
    return this;
  }

  public Builder h1(String element) {
    sb.append("<h1>" + element + "</h1>");
    sb.append(lineSeparator);
    return this;
  }

  public Builder h2(String element) {
    sb.append("<h2>" + element + "</h2>");
    sb.append(lineSeparator);
    return this;
  }

  public String getResult() {
    return sb.toString();
  }
}
