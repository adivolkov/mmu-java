package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import memory.Page;

public class HardDiskInputStream extends ObjectInputStream {
	private String inputFileName;
	public HardDiskInputStream(InputStream in, String fileName) throws IOException {
		super(in);
		inputFileName = fileName;
		// TODO Auto-generated constructor stub
	}
	public Map<Integer, Page<byte[]>> readAllPages(){
		
		return null;
	}
	public Page<byte[]> readSinglePage(Integer pageId) throws IOException, ClassNotFoundException{
		//HardDisk hardDisk = HardDisk.getInstance();
		FileInputStream hardDiskFIS = new FileInputStream(this.inputFileName);
		ObjectInputStream hardDiskOIS = new ObjectInputStream(hardDiskFIS);
		@SuppressWarnings("unchecked")
		Map<Integer, Page<byte[]>> hardDiskHashMap = (HashMap<Integer, Page<byte[]>>) hardDiskOIS.readObject();
		hardDiskOIS.close();
		return hardDiskHashMap.get(pageId);
	}
}
