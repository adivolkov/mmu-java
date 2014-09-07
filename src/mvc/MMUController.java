package mvc;

import java.util.List;

public class MMUController {
	
	private final String COMMANDS_FILE_NAME = "log.txt";
	MMUModel model;
	
	public MMUController() {
		model = new MMUModel(COMMANDS_FILE_NAME);
	}
	public void start() {
		List<String> commands = model.getModel();
		MMUView view = new MMUView(commands);
		view.open();
	}
}
