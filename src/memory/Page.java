package memory;

public class Page <T> {
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
	@Override
	public int hashCode() {
		return content.hashCode();}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Page))
            return false;
        if (((Page<?>)obj).getPageId() == this.pageId)
            return true;
		return false;}
	@Override
	public String toString() {
		return content.toString();}
}
