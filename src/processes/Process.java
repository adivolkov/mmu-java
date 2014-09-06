package processes;

import java.util.Arrays;
import java.util.List;

import memory.MemoryManagementUnit;
import memory.Page;

public class Process implements Runnable {
	private int id;
	private ProcessCycles processCycles;
	private MemoryManagementUnit mmu;
	
	public Process(int id, MemoryManagementUnit mmu, ProcessCycles processCycles) {
		this.id = id;
		this.processCycles = processCycles;
		this.mmu = mmu;
	}
	
	@Override
	public void run() {
		
		List<ProcessCycle> cycles = processCycles.getProcessCycles();
		int cyclesCount = cycles.size();
		
		for(int i=0;i<cyclesCount;i++) {
			System.out.println("ProcessId: " + id + ", Cycle: " + (i+1));
			
			ProcessCycle cycle = cycles.get(i);
			List<Integer> pagesIds = cycle.getPages();
			Page<byte[]>[] pages = mmu.getPages(pagesIds.toArray(new Integer[pagesIds.size()]));
			
			for(int j=0;j<pages.length;j++){
				pages[j].setContent(cycle.getData().get(j)); 
				System.out.println("page " + pages[j].getPageId() + ": " + Arrays.toString(pages[j].getContent()));
			}
			
			// waiting * 1000 for more visualization
			try {
				Thread.sleep(cycle.getSleepMs() * 1000);
			} catch (InterruptedException e) {
				System.out.println("sleep interruption, shouldn't happen...");
			}
		}

	}

}
