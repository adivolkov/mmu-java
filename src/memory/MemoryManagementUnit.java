package memory;

import algorithms.IAlgo;

public class MemoryManagementUnit {
	private IAlgo<Integer> algo;
	private RAM ram;
	private HardDisk hd;
	
	public MemoryManagementUnit(int ramCapacity, IAlgo<Integer> algo) {
		this.ram = new RAM(ramCapacity);
		this.algo = algo;
		this.hd = HardDisk.getInstance();
	}
	
	public Page<byte[]>[] getPages(Integer[] pageIds){
		
		@SuppressWarnings("unchecked")
		Page<byte[]>[] result = new Page[pageIds.length];
		//Page<byte[]>[] result = (Page<byte[]>[])new Object[pageIds.length];
		
		for(int i=0;i<pageIds.length;i++){
			if (ram.getPage(pageIds[i]) == null){
				if (!ram.isFull()){
					// adding the missing pageId to the ram algo
					algo.add(pageIds[i]);
					// adding the missing page to the ram
					result[i] = hd.pageFault(pageIds[i]);
					ram.addPage(result[i]);
				}
				else {
					// adding the missing pageId to the ram algo
					// saving the id of the removed page to save on the HD
					Integer pageIdToHd = algo.add(pageIds[i]);
					// getting the page
					Page<byte[]> pageToHd = ram.getPage(pageIdToHd);
					ram.removePage(pageToHd);
					result[i] = hd.pageReplacement(pageToHd, pageIds[i]);
					ram.addPage(result[i]);
				}
			}
			else {
				result[i] = ram.getPage(algo.get(pageIds[i]));
			}
		}
		return result;
	}
}
