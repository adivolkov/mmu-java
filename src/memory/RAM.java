package memory;

import java.util.HashMap;
import java.util.Map;

public class RAM {
	private Map<Integer, Page<byte[]>> pages;
	public RAM(int initialCapacity) {
		this.pages = new HashMap<Integer, Page<byte[]>>(initialCapacity);
	}
	public Page<byte[]> getPage(int pageId) {
		return pages.get(pageId);}
	public void addPage(Page<byte[]> addPage) {
		pages.put(addPage.getPageId(), addPage);
	}
	public void removePage(Page<byte[]> removePage) {
		pages.remove(removePage.getPageId());
	}
	public Page<byte[]>[] getPages(Integer[] pageIds) {
		@SuppressWarnings("unchecked")
		Page<byte[]>[] pc = (Page<byte[]>[]) new Page[pageIds.length];
		for (int i=0; i<pageIds.length; i++)
		{
			pc[i] = getPage(pageIds[i]);
		}		
		return pc;}		
	public void addPages(Page<byte[]>[] addPages) {
		for (int i=0; i<addPages.length; i++)
		{
			addPage(addPages[i]);
		}		
	}
	public void removePages(Page<byte[]>[] removePages) {
		for (int i=0; i<removePages.length; i++)
		{	
			removePage(removePages[i]);
		}
	}
	
	
}
