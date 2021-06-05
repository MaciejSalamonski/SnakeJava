package FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * FileHandler class
 * 
 * A class that handles writing and reading the best result to a file.
 */

public class FileHandler {
	
	/*
	 * Class variables 
	 * 
	 * filePath_ - file path
	 * thread_ - thread object
	 */
	
	private String filePath_;
	private Thread thread_;
	
	/*
	 * Getters
	 */
	
	public String GetFilePath() { return filePath_; }
	public Thread GetThread() { return thread_; }
	
	/*
	 * Setters 
	 */
	
	public void SetFilePath(String filePath) { filePath_ = filePath; }
	public void SetThread(Thread thread) { thread_ = thread; }
	
	/*
	 * FileHandler constructor
	 * 
	 * Constructor calls the method responsible for creating the thread
	 * and initializes a variable that contains the path to the file. 
	 *  
	 * filePath - file path 
	 */
	
	public FileHandler(String filePath) {
		StartFileHandlerThread();
		SetFilePath(filePath);
	}
	
	/*
	 * StartFileHandlerThread method
	 * 
	 * Creation and start of thread operation.
	 */
	
	private void StartFileHandlerThread() {
		SetThread(new Thread("File Handler"));
		GetThread().start();
	}
	
	/*
	 * StopFileHandlerThread method
	 * 
	 * Stops the thread. Handles possible exceptions.
	 */
	
	public void StopFileHandlerThread() {
		try {
			GetThread().join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	/*
	 * WriteBestResultToFile method
	 * 
	 * Method that handles writing the best result to a file.
	 * Handles possible exceptions.
	 * 
	 * bestResult - best result obtained by the player
	 */

	public void WriteBestResultToFile(int bestResult) throws IOException {
		try {
			FileWriter writer = new FileWriter(GetFilePath());
			writer.write(String.valueOf(bestResult));
			writer.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/*
	 * ReadBestResultFromFile method
	 * 
	 * Method that handles reading the best result from a file.
	 * Handles possible exceptions.
	 */
	
	public int ReadBestResultFromFile() {
		int bestResult;
		
		File file = new File(GetFilePath());
		try {
			Scanner scanner = new Scanner(file);
			String data = scanner.nextLine();
			bestResult = Integer.parseInt(data);
			scanner.close();
		} catch(FileNotFoundException exception) {
			exception.printStackTrace();
			bestResult = 0;
		}
		
		return bestResult;
	}
}