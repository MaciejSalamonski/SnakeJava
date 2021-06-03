package FileHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;;

public class FileHandler {
	private String filePath_;
	private Thread thread_;
	
	public String GetFilePath() { return filePath_; }
	public Thread GetThread() { return thread_; }
	
	public void SetFilePath(String filePath) { filePath_ = filePath; }
	public void SetThread(Thread thread) { thread_ = thread; }
	
	public FileHandler(String filePath) {
		StartFileHandlerThread();
		SetFilePath(filePath);
	}
	
	public void StartFileHandlerThread() {
		SetThread(new Thread("File Handler"));
		GetThread().start();
	}
	
	public void StopFileHandlerThread() {
		try {
			GetThread().join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	public void WriteBestResultToFile(int bestResult) throws IOException {
		try {
			FileWriter writer = new FileWriter(GetFilePath());
			writer.write(String.valueOf(bestResult));
			writer.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
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