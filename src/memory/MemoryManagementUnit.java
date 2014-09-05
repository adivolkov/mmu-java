package memory;

import java.util.ArrayList;

import algorithms.IAlgo;

public class MemoryManagementUnit {
	private IAlgo<Integer> algo;
	private RAM ram;
	
	public MemoryManagementUnit(int ramCapacity, IAlgo<Integer> algo) {
		ram = new RAM(ramCapacity);
		this.algo = algo;
	}
	
	public Page<byte[]>[] getPages(Integer[] pageIds){
		
		@SuppressWarnings("unchecked")
		Page<byte[]>[] result = (Page<byte[]>[])new Object[pageIds.length];
		
		for(int i=0;i<pageIds.length;i++){
			if (ram.getPage(pageIds[i]) == null){
				if (!ram.isFull()){
					//TODO: PageFault
				}
				else {
					//TODO: Do logic of full RAM (pageReplacement)
				}
			}
			else {
				//result[i] = algo.get(pageIds[i]));
			}
		}
		
		return null;
	}
}
