package memory;

import java.io.IOException;

public class HardDisk {
	final static String DEFAULT_FILE_NAME = "hdPages.txt";
	final static int _SIZE = 1000;
	private static HardDisk hardDisk = new HardDisk();
	
	public static HardDisk getInstance() {
		if (hardDisk == null){
			hardDisk = new HardDisk();
		}
		return hardDisk;
	}
	
	private HardDisk(){		
	}
	
	public Page<byte[]> pageFault(int pageId) throws IOException, ClassNotFoundException{		
		
		return null;
	}
	
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Integer moveToRamId) throws IOException, ClassNotFoundException{
		return moveToHdPage;
	}

}
