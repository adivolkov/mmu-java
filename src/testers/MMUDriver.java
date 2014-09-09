package testers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import memory.MemoryManagementUnit;
import processes.Process;
import processes.ProcessCycles;
import processes.RunConfiguration;
import util.MMULogger;
import algorithms.LRUAlgoImpl;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class MMUDriver {

	final static Integer RAM_CAPACITY = 4;
	final static String CONFIG_FILE_NAME = "Configuration.json";
	
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		
		MMULogger logger = MMULogger.getInstance();
		
		MemoryManagementUnit mmu = new MemoryManagementUnit(RAM_CAPACITY, new LRUAlgoImpl<Integer>(RAM_CAPACITY));
		logger.write("RC " + RAM_CAPACITY , Level.INFO);
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processesCycles = runConfig.getProcessesCycles();
		logger.write("PN " + processesCycles.size() + "\r\n", Level.INFO);
		List<Process> processes = createProcesses(processesCycles, mmu);
		runProcesses(processes);
	}
	
	public static RunConfiguration readConfigurationFile() {
		try{
			return new Gson().fromJson(new JsonReader(new FileReader(CONFIG_FILE_NAME)), RunConfiguration.class);
		}
		catch(Exception e){
			MMULogger.getInstance().write("There was an error reading the JSON file: " + e , Level.SEVERE);
		}
		return new RunConfiguration(new ArrayList<ProcessCycles>());
	}
	
	public static List<Process> createProcesses(List<ProcessCycles> processesCycles, MemoryManagementUnit mmu){
		List<Process> processList = new ArrayList<Process>();
		
		for(int i=0;i<processesCycles.size();i++){
			processList.add(new Process(i+1, mmu, processesCycles.get(i)));
		}
		
		return processList;
	}
	
	public static void runProcesses(List<Process> processes){
		for(Process p : processes){
			new Thread(p).start();
		}
	}

}
