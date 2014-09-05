package memory;

import util.HardDiskException;

public class HardDisk {
	final static String DEFAULT_FILE_NAME = "hdPages.txt";
	final static int _SIZE = 1000;
	private static HardDisk hardDisk = new HardDisk();
	
	public static HardDisk getInstance() throws HardDiskException {
		if (hardDisk == null){
			hardDisk = new HardDisk();
		}
		return hardDisk;
	}
	
	private HardDisk(){		
	}
	
	/* <Getters> */
	public static String getFileName(){
		return DEFAULT_FILE_NAME;
	}
	
	public static int getSize(){
		return _SIZE;
	}
	/* </Getters>*/
	
	public Page<byte[]> pageFault(int pageId){		
		
		return null;
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Integer moveToRamId)
	{
		return moveToHdPage;
	}

}
