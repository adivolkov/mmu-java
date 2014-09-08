package mvc;

import java.util.ArrayList;
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


// TODO: Only one process to select every time, clear table on every opearation
public class MMUView {
	
		private final int TABLE_COLUMNS = 20;

		private Display display;
		private Shell shlMmuChampion;
	
		private List<String> commands;
		private Text txtPfCount;
		private Table tblRam;
		private Label lblPfCount;
		private Composite cmpPageCounts;
		private Text txtPrCount;
		org.eclipse.swt.widgets.List list;
		
		private int processNumber;
		
		
		public MMUView(List<String> commands) {
			this.commands = commands;
			processNumber = getProcessNumber();
			
			display = new Display();
			shlMmuChampion = new Shell(display);
			shlMmuChampion.setText("MMU Champion");
			shlMmuChampion.setLayout(new GridLayout(2, false));
			shlMmuChampion.setSize(720, 380);
			
			createTable();
			createPageCounters();
			createButtons();
			createProcessList();
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
			
			for (Integer i=0;i<TABLE_COLUMNS;i++){
				TableColumn column = new TableColumn(tblRam, SWT.NONE);
			}
			
			for (Integer i=0;i<5;i++){
				TableItem tableItem = new TableItem(tblRam, SWT.NONE);
				for (Integer j=0;j<TABLE_COLUMNS;j++){
					tableItem.setText(j, "0");
				}
			}
			
			for (Integer i=0;i<TABLE_COLUMNS;i++){
				tblRam.getColumn(i).pack();
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
			txtPfCount.setText("0");
			
			
			Label lblPrCount = new Label(cmpPageCounts, SWT.NONE);
			lblPrCount.setText("Page replacement count:");
			
			txtPrCount = new Text(cmpPageCounts, SWT.BORDER);
			GridData txtPrGridData = new GridData(15, -1);
			txtPrCount.setLayoutData(txtPrGridData);
			txtPrCount.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			txtPrCount.setEditable(false);
			txtPrCount.setText("0");
			
		}
		
		private void createButtons(){
			Composite cmpButtons = new Composite(shlMmuChampion, SWT.NONE);
			RowLayout rl_cmpButtons = new RowLayout(SWT.HORIZONTAL);
			rl_cmpButtons.spacing = 10;
			cmpButtons.setLayout(rl_cmpButtons);
			cmpButtons.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
			//cmpButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			Button btnPlay = new Button(cmpButtons, SWT.NONE);
			btnPlay.setText("Play");
			// adding an async opeation to avoid UI freeze
			btnPlay.addSelectionListener(new SelectionListener() {
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
									int[] selected = list.getSelectionIndices();
									for(int i=0;i<selected.length;i++){
										playProcess(selected[i]+1);
										System.out.println("Play " + (selected[i]+1));
									}
									
								}
							});
						}
					};
					operationThread.start();
					
					
				}
				
			});
			
			Button btnPlayAll = new Button(cmpButtons, SWT.NONE);
			
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
									playAllProcesses(); 
								}
							});
						}
					};
					operationThread.start();
				}
			});
			
			btnPlayAll.setText("Play All");
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
		
		private void playAllProcesses(){
			for (int i=1;i<=processNumber;i++){
				playProcess(i);
			}
		}
		
		private void playProcess(Integer processId){
			int pageFaultCount = 0;
			int pageReplacementCount = 0;
			List<Integer> pageFaults = new ArrayList<Integer>();
			Map<Integer, Integer> pageReplacements = new HashMap<Integer, Integer>();
			
			for (String cmd : commands){
				Scanner scanner = new Scanner(cmd);
				scanner.useDelimiter(" ");
				String cmdName = scanner.next();
				// increment PageFault counter
				if (cmdName.equals("PF")){
					pageFaultCount++;
					pageFaults.add(scanner.nextInt());
				}
				// increment PageReplacement counter
				else if (cmdName.equals("PR")){
					pageReplacementCount++;
					scanner.next();
					int MTH = scanner.nextInt();
					scanner.next();
					int MTR = scanner.nextInt();
					pageReplacements.put(MTH, MTR);
				}
				else if (cmdName.startsWith("P")){
					// if it is not the correct process we reset the counters
					if (!cmdName.substring(1, 2).equals(processId.toString())){
						pageFaultCount = 0;
						pageReplacementCount = 0;
						pageFaults.clear();
						pageReplacements.clear();
					}
					// correct process
					else{
						// updates the page counters
						Integer newPfCount = Integer.parseInt(txtPfCount.getText()) + pageFaultCount;
						txtPfCount.setText(newPfCount.toString());
						Integer newPrCount = Integer.parseInt(txtPrCount.getText()) + pageReplacementCount;
						txtPrCount.setText(newPrCount.toString());

						// updates the columns headers
						for(Integer pf : pageFaults){
							tblRam.getColumn(pf).setText(pf.toString());
						}
						for(Integer pr : pageReplacements.keySet()){
							tblRam.getColumn(pr).setText("");
							Integer mtr = pageReplacements.get(pr);
							tblRam.getColumn(mtr).setText(mtr.toString());
						}
						
						// getting the page updates
						scanner.next();
						Integer pageId = scanner.nextInt();
						scanner.next();
						String arrayString = scanner.nextLine();
						String[] pageArray = parseArray(arrayString);
						
						// updating the column
						tblRam.getColumn(pageId).setText(pageId.toString());
						for(int i=0;i<pageArray.length;i++){
							tblRam.getItem(i).setText(pageId, pageArray[i]);
						}
						tblRam.getColumn(pageId).pack();
						
					}
				}
				scanner.close();
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
		
		private String[] parseArray(String arrayString){
			String cleanString = arrayString.replace("[","").replace("]", "").replace(" ", "");
			return cleanString.split(",");
		}
}
