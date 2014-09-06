package util;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MMULogger {

		final static String DEFAULT_FILE_NAME = "log.txt";
		private FileHandler handler;
		
		private static MMULogger logger = new MMULogger( );
		
		private MMULogger(){
			try {
				handler = new FileHandler(DEFAULT_FILE_NAME);
				handler.setFormatter(new OnlyMessageFormatter());
			} catch (Exception ex) {
				System.out.println("Trouble with the logger file: " + ex.getMessage());
			}
		}
		
		public static MMULogger getInstance() {
			return logger;
		}
		
		public void write(String command, Level level){
			handler.publish(new LogRecord(level, command));
		}
		
		public class OnlyMessageFormatter extends Formatter{
			public OnlyMessageFormatter(){super();}
			
			@Override
			public String format(LogRecord record) {
				return record.getMessage()+"\r\n";
			}
		}
}


