package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import memory.Page;

public class HardDiskInputStream extends ObjectInputStream {
	public HardDiskInputStream(FileInputStream fis) throws IOException {
		super(fis);
		
	}
	
	public Map<Integer, Page<byte[]>> readAllPages() throws ClassNotFoundException, IOException{
		@SuppressWarnings("unchecked")
		Map<Integer, Page<byte[]>> hardDiskHashMap = (HashMap<Integer, Page<byte[]>>) this.readObject();
		this.close();
		return hardDiskHashMap;
	}
	public Page<byte[]> readSinglePage(Integer pageId) throws IOException, ClassNotFoundException{
		@SuppressWarnings("unchecked")
		Map<Integer, Page<byte[]>> hardDiskHashMap = (HashMap<Integer, Page<byte[]>>) this.readObject();
		this.close();
		return hardDiskHashMap.get(pageId);
	}
}
