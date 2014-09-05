package memory;

import algorithms.IAlgo;

public class MemoryManagementUnit {
	private IAlgo<Integer> algo;
	private RAM ram;
	
	public MemoryManagementUnit(int ramCapacity, IAlgo<Integer> algo) {
		ram = new RAM(ramCapacity);
		this.algo = algo;
	}
	
	public Page<byte[]>[] getPages(Integer[] pageIds){
		// TODO: implement
		for(int i=0;i<pageIds.length;i++){
			if (ram.getPage(pageIds[i]) == null){
				if (ram.)
			}
		}
	}
}
