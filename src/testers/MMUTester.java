package testers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import memory.HardDisk;
import memory.MemoryManagementUnit;
import memory.Page;
import algorithms.IAlgo;
import algorithms.LRUAlgoImpl;

// import memory.MemoryManagementUnit;

public class MMUTester {
	
	final static Integer RAM_CAPACITY = 4;

	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		HardDisk hd = HardDisk.getInstance();		
	
		// this map will be stored to the hardDisk as data seed
		Map<Integer, Page<byte[]>> data = new HashMap<Integer, Page<byte[]>>(10);
		
		data.put(0, new Page<byte[]>(0, new byte[]{0,1,2,3}));
		data.put(1, new Page<byte[]>(1, new byte[]{1,2,3,4}));
		data.put(2, new Page<byte[]>(2, new byte[]{2,3,4,5}));
		data.put(3, new Page<byte[]>(3, new byte[]{3,4,5,6}));
		data.put(4, new Page<byte[]>(4, new byte[]{4,5,6,7}));
		data.put(5, new Page<byte[]>(5, new byte[]{5,2,3,4}));
		data.put(6, new Page<byte[]>(6, new byte[]{6,7,3,4}));
		data.put(7, new Page<byte[]>(7, new byte[]{7,8,9,10}));
		data.put(8, new Page<byte[]>(8, new byte[]{8,9,10,11}));
		data.put(9, new Page<byte[]>(9, new byte[]{9,10,11,12}));
		
		try {
			// we want to seed the data if hdPages.txt doesn't exist or empty
			//hd.seedDataToFile(data);
		} catch (Exception e) {
			System.out.println("Seeding data failed: " + e.getMessage());
		}
		
		
		
		IAlgo<Integer> lruAlgo = new LRUAlgoImpl<Integer>(RAM_CAPACITY);
		MemoryManagementUnit mmu = new MemoryManagementUnit(RAM_CAPACITY, lruAlgo);
	
		Page<byte[]>[] pages = mmu.getPages(new Integer[]{1, 3, 5, 7, 9});
		
		for (int i=0;i<pages.length;i++){
			System.out.println("Page ID: " + pages[i].getPageId());
			System.out.println(Arrays.toString(pages[i].getContent()));
		}
		
	}

}
