package mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class MMUView {
	
		private final int DATA_LENGTH = 5;

		private Display display;
		private Shell shlMmuChampion;
	
		private List<String> commands;
		private Text txtPfCount;
		private Table tblRam;
		private Label lblPfCount;
		private Composite cmpPageCounts;
		private Text txtPrCount;
		private Text txtCommandNumber;
		org.eclipse.swt.widgets.List list;
		private Button btnPlay;
		private Button btnPlayAll;
		private Button btnReset;
		
		private int processNumber;
		private int ramCapacity;
		private int commandNumber;
		private int pfCount;
		private int prCount;
		
		private Map<Integer,Integer> pageReplacements;
		private Map<Integer, Integer> pageColumnMap;
		private boolean[] ramVacancy;
		

		public MMUView(List<String> commands) {
			this.commands = commands;
			processNumber = getProcessNumber();
			ramCapacity = getRamCapacity();

			display = new Display();
			shlMmuChampion = new Shell(display);
			shlMmuChampion.setText("MMU Champion - Ram Capacity: " + ramCapacity + " Commands " + (commands.size()-2));
			shlMmuChampion.setLayout(new GridLayout(2, false));
			shlMmuChampion.setSize(720, 380);
			
			createTable();
			createPageCounters();
			createButtons();
			createProcessList();
			
			init();
		}
		
		public void open() {
			shlMmuChampion.open();
			while(!shlMmuChampion.isDisposed()){
				if(!display.readAndDispatch()){
					display.sleep();
				}
			}
			display.dispose();
		}
		
		
		private void createTable(){
			tblRam = new Table(shlMmuChampion, SWT.BORDER | SWT.FULL_SELECTION);
			GridData gd_tblRam = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1);
			gd_tblRam.heightHint = 150;
			tblRam.setLayoutData(gd_tblRam);
			tblRam.setHeaderVisible(true);
			tblRam.setLinesVisible(true);
			
			for (Integer i=0;i<ramCapacity;i++){
				@SuppressWarnings("unused")
				TableColumn column = new TableColumn(tblRam, SWT.NONE);
			}
			
			for (Integer i=0;i<DATA_LENGTH;i++){
				@SuppressWarnings("unused")
				TableItem tableItem = new TableItem(tblRam, SWT.NONE);
			}
			
			//tblRam.pack();
		}
		
		private void createPageCounters(){
			
			
			
			cmpPageCounts = new Composite(shlMmuChampion, SWT.NONE);
			cmpPageCounts.setLayout(new GridLayout(2, false));
			
			lblPfCount = new Label(cmpPageCounts, SWT.NONE);
			lblPfCount.setText("Page fault count:");
			
			txtPfCount = new Text(cmpPageCounts, SWT.BORDER);
			GridData txtPfGridData = new GridData(15, -1);
			txtPfCount.setLayoutData(txtPfGridData);
			txtPfCount.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			txtPfCount.setEditable(false);
			
			
			
			Label lblPrCount = new Label(cmpPageCounts, SWT.NONE);
			lblPrCount.setText("Page replacement count:");
			
			txtPrCount = new Text(cmpPageCounts, SWT.BORDER);
			GridData txtPrGridData = new GridData(15, -1);
			txtPrCount.setLayoutData(txtPrGridData);
			txtPrCount.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			txtPrCount.setEditable(false);
			
			Label lblCommandNumber = new Label(cmpPageCounts, SWT.NONE);
			lblCommandNumber.setText("Last command:");
			
			txtCommandNumber = new Text(cmpPageCounts, SWT.BORDER);
			GridData txtCmGridData = new GridData(15, -1);
			txtCommandNumber.setLayoutData(txtCmGridData);
			txtCommandNumber.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			txtCommandNumber.setEditable(false);

			
		}
		
		private void createButtons(){
			Composite cmpButtons = new Composite(shlMmuChampion, SWT.NONE);
			RowLayout rl_cmpButtons = new RowLayout(SWT.HORIZONTAL);
			rl_cmpButtons.spacing = 10;
			cmpButtons.setLayout(rl_cmpButtons);
			cmpButtons.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
			//cmpButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			btnPlay = new Button(cmpButtons, SWT.NONE);
			btnPlay.setText("Play");
			
			// adding an async opeation to avoid UI freeze
			btnPlay.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
				@Override
				public void widgetSelected(SelectionEvent e) {
					//play();
					Thread operationThread = new Thread() { 
						public void run() { 
							display.asyncExec(new Runnable() {
								public void run() { 
									play();
								}
							});
						}
					};
					operationThread.start();
					
					
				}
				
			});
			
			btnPlayAll = new Button(cmpButtons, SWT.NONE);
			btnPlayAll.setText("Play All");
			
			// adding an async opeation to avoid UI freeze
			btnPlayAll.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
				@Override
				public void widgetSelected(SelectionEvent e) {
					Thread operationThread = new Thread() { 
						public void run() { 
							display.asyncExec(new Runnable() {
								public void run() { 
									System.out.println("Play All");
									playAll(); 
								}
							});
						}
					};
					operationThread.start();
				}
			});
			
						
			btnReset = new Button(cmpButtons, SWT.NONE);
			btnReset.setText("Reset");
			
			btnReset.addSelectionListener(new SelectionListener() {
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
				@Override
				public void widgetSelected(SelectionEvent e) {
					Thread operationThread = new Thread() { 
						public void run() { 
							display.asyncExec(new Runnable() {
								public void run() { 
									System.out.println("Reset");
									init();
								}
							});
						}
					};
					operationThread.start();
				}
			});
			

		}
		
		private void createProcessList(){
			Composite cmpProcesses = new Composite(shlMmuChampion, SWT.NONE);
			cmpProcesses.setLayout(new GridLayout(1, true));
			cmpProcesses.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, true, 1, 1));
			
			Label lblProcesses = new Label(cmpProcesses, SWT.NONE);
			lblProcesses.setText("Processes");
			
			list = new org.eclipse.swt.widgets.List(cmpProcesses, SWT.BORDER | SWT.MULTI);
			list.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
			
			String[] listItems = new String[processNumber];
			for(int i=0;i<processNumber;i++){
				listItems[i] = "Process " + (i+1);
			}
			
			list.setItems(listItems);
		}
		
		private void init(){
			// begin after the ramCapacity and processCount
			commandNumber = 2;
			
			for (Integer i=0;i<ramCapacity;i++){
				tblRam.getColumn(i).setText("");
			}
			
			for (Integer i=0;i<5;i++){
				for (Integer j=0;j<ramCapacity;j++){
					tblRam.getItem(i).setText(j, "");
				}
			}
			
			for (Integer i=0;i<ramCapacity;i++){
				tblRam.getColumn(i).pack();
			}
			
			pfCount = 0;
			prCount = 0;
			
			txtPfCount.setText("" + pfCount);
			txtPrCount.setText("" + prCount);
			txtCommandNumber.setText("" + (commandNumber - 2));
			btnPlay.setEnabled(true);
			btnPlayAll.setEnabled(true);
			
			pageReplacements = new HashMap<Integer, Integer>();
			pageColumnMap = new HashMap<Integer, Integer>();
			ramVacancy = new boolean[ramCapacity];
			for(int i=0;i<ramCapacity;i++){
				ramVacancy[i] = true;
			}
		}
		
		private void playAll(){
			while (commandNumber < commands.size()){
				play();
			}
		}
		
		private void play(){
			
			txtCommandNumber.setText("" + (commandNumber-1));
			
			String command = commands.get(commandNumber);
			
			System.out.println("Command: " + command);
			Scanner scanner = new Scanner(command);
			scanner.useDelimiter(" ");
			String cmdName = scanner.next();
			
			// incrementing PageFault counter
			if (cmdName.equals("PF")){
				pfCount++;
				txtPfCount.setText("" + pfCount);
			}
			
			// incrementing PageReplacement counter and store MTH and MTR
			else if (cmdName.equals("PR")){
				prCount++;
				txtPrCount.setText("" + prCount);
				scanner.next();
				int MTH = scanner.nextInt();
				scanner.next();
				int MTR = scanner.nextInt();
				pageReplacements.put(MTR, MTH);
			}
			
			else if (cmdName.startsWith("P")){
				int process = Integer.parseInt(cmdName.substring(1, 2));
				if (isProcessChosen(process - 1)){
					// getting the process details
					scanner.next();
					int pageId = scanner.nextInt();
					scanner.next();
					String arrayString = scanner.nextLine();
					String[] pageArray = parseArray(arrayString);
					
					// removing the MTH page from the ram table if there's an MTR
					if (pageReplacements.containsKey(pageId)){
						if (pageColumnMap.containsKey(pageReplacements.get(pageId))){
							int mthColumn = pageColumnMap.get(pageReplacements.get(pageId));
							tblRam.getColumn(mthColumn).setText("");
							for(int i=0;i<ramCapacity;i++){
								tblRam.getItem(i).setText(mthColumn, "0");
							}
							pageColumnMap.remove(pageId);
							ramVacancy[mthColumn] = true;
						}

						pageReplacements.remove(pageId);
						
						
					}
					
					// finding an available column
					int column = -1;
					if(pageColumnMap.containsKey(pageId)){
						column = pageColumnMap.get(pageId);
					}
					else{
						for (int i=0;i<ramCapacity;i++){
							if (ramVacancy[i]){
								column = i;
								ramVacancy[i] = false;
								pageColumnMap.put(pageId, i);
								break;
							}
						}
					}

					// updating the column
					tblRam.getColumn(column).setText("" + pageId);
					for(int i=0;i<pageArray.length;i++){
						tblRam.getItem(i).setText(column, pageArray[i]);
					}
					tblRam.getColumn(column).pack();
					
					
				}
			}
			
			
			scanner.close();
			commandNumber++;
			if (commandNumber == commands.size()){
				btnPlay.setEnabled(false);
				btnPlayAll.setEnabled(false);
				return;
			}
		}
		
		private int getProcessNumber(){
			int processNumber = 0;
			for(String cmd : commands){
				Scanner scanner = new Scanner(cmd);
				scanner.useDelimiter(" ");
				String cmdName = scanner.next();
				if (cmdName.equals("PN")){
					processNumber = scanner.nextInt();
					scanner.close();
					return processNumber;
				}
				scanner.close();
			}
			return processNumber;
		}
		
		private int getRamCapacity(){
			int ramCapacity = 0;
			for(String cmd : commands){
				Scanner scanner = new Scanner(cmd);
				scanner.useDelimiter(" ");
				String cmdName = scanner.next();
				if (cmdName.equals("RC")){
					ramCapacity = scanner.nextInt();
					scanner.close();
					return ramCapacity;
				}
				scanner.close();
			}
			return ramCapacity;
		}
		
		private String[] parseArray(String arrayString){
			String cleanString = arrayString.replace("[","").replace("]", "").replace(" ", "");
			return cleanString.split(",");
		}
		
		private boolean isProcessChosen(int processId){
			int[] selected = list.getSelectionIndices();
			for(int i=0;i<selected.length;i++){
				if (selected[i] == processId) return true;
			}
			return false;
		}
}
