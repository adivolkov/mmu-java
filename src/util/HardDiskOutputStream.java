package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import memory.Page;

public class HardDiskOutputStream extends ObjectOutputStream {

	public HardDiskOutputStream(FileOutputStream fos) throws IOException {
		super(fos);
	}
	
	public void writeAllPages(Map<Integer, Page<byte[]>> pages) throws ClassNotFoundException, IOException{
		this.writeObject(pages);
	}

}
