import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

public class ScoreSouse {
	
	public Vector<String> sR=new Vector<String>();

	private File file;
	public String buf;
	private String filename;
	
	public int score=0;
	
	public ScoreSouse(int level) {
		filename="Score"+level;
		file = new File(filename);
		
		try {
			fileOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void increase(int level) {
		score+=(10*(level+1));
		buf=Integer.toString(score);
	}
	
	public void record() {
		try {
			fileInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void fileOutput()throws IOException{
		FileReader rw=new FileReader(file);
		BufferedReader br=new BufferedReader(rw);
		
		String readLine=null;
		
		while((readLine=br.readLine())!=null){
			sR.add(readLine);
		}
	}

	private void fileInput() throws IOException {
		BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
		PrintWriter pw=new PrintWriter(bw,true);
		pw.write(Integer.toString(score)+"\n");

		pw.flush();
		pw.close();
	}

}
