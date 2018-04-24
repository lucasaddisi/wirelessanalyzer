package fileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class FileLineReader {
	
	private BufferedReader fileStream;
	private boolean isEof;
	
	public FileLineReader(Reader reader) {
		
		fileStream = new BufferedReader(reader);
	}
	
	public String readLine(){
		try {
			String line = fileStream.readLine();
			this.isEof = line == null;
			return isEof ? "" : line;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean ifEof(){
		return this.isEof;
	}
	
}
