package app.form;

public class PostForm {
	private String name;
	private String content;

	public PostForm() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "PostForm{" +
				"name='" + name + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
