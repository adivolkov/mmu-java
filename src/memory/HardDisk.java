package memory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import util.HardDiskInputStream;
import util.HardDiskOutputStream;

public class HardDisk {
	final static String DEFAULT_FILE_NAME = "hdPages.txt";
	final static int SIZE = 1000;
	private static HardDisk hardDisk = new HardDisk();
	private static Map<Integer, Page<byte[]>> hdmap;

	public static HardDisk getInstance() {
		return hardDisk;
	}

	private HardDisk() {
		hdmap = new HashMap<Integer, Page<byte[]>>(SIZE);
		try {
			FileInputStream fis = new FileInputStream(DEFAULT_FILE_NAME);
			HardDiskInputStream hdis = new HardDiskInputStream(fis);
			hdmap = hdis.readAllPages();
			hdis.close();
		} catch (Exception ex) {
			// there is no such file actually, so we just move on with an empty HashMap
		}
	}

	public Page<byte[]> pageFault(int pageId) {
		return hdmap.remove(pageId);	
	}

	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage,
			Integer moveToRamId) {
		hdmap.put(moveToHdPage.getPageId(), moveToHdPage);
		return hdmap.remove(moveToRamId);
	}
	
	public void SeedDataToFile(Map<Integer, Page<byte[]>> data) throws ClassNotFoundException, IOException{
		hdmap = data;
		FileOutputStream fos = new FileOutputStream(DEFAULT_FILE_NAME);
		HardDiskOutputStream hdos = new HardDiskOutputStream(fos);
		hdos.writeAllPages(data);
		hdos.close();
	}

}