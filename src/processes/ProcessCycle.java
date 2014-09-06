package processes;

import java.util.List;

public class ProcessCycle {
	private List<Integer> pages;
	private int sleepMs;
	private List<byte[]> data;
	
	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public int getSleepMs() {
		return sleepMs;
	}

	public void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}

	public List<byte[]> getData() {
		return data;
	}

	public void setData(List<byte[]> data) {
		this.data = data;
	}

	public ProcessCycle(List<Integer> pages, int sleepMs, List<byte[]> data){
		this.pages = pages;
		this.sleepMs = sleepMs;
		this.data = data;
	}
}
