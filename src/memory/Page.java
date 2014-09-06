package memory;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Page <T> implements Serializable{
	private int pageId;
	private T content;
	
	public Page(int id, T content) {
		this.pageId = id;
		this.content = content;
	}
	public int getPageId() {
		return pageId;}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public T getContent() {
		return content;}
	public void setContent(T content) {
		this.content = content;
	}

}
