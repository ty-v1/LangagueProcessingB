package builder;

public class Builder {


	private StringBuilder sb = new StringBuilder();

	public Builder html() {
		sb.append("<html>\n");
		return this;
	}

	public Builder _html() {
		sb.append("</html>");
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
