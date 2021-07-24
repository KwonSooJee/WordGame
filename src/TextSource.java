import java.util.Vector;
import java.io.*;

public class TextSource {
	private static Vector<Vector<String>> v = new Vector<Vector<String>>(5);
	static public Vector<String> textBonus=new Vector<String>();
	
	public TextSource() { // 파일에서 읽기
		File file= new File("stage1.txt");
		putStageText(file);
		file=new File("stage2.txt");
		putStageText(file);
		file=new File("stage3.txt");
		putStageText(file);
		file=new File("stage4.txt");
		putStageText(file);
		textBonus.add("hello");
	}
	
	private void putStageText(File file) {
		Vector<String> stageText=new Vector<String>(80,5);

		try {
			FileReader filereader=new FileReader(file);
			BufferedReader bufReader=new BufferedReader(filereader);
			String line="";
			while((line=bufReader.readLine())!=null) {
				stageText.add(line);
			}
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}
		v.add(stageText);
	}
	public String get(int level) {
		int index = (int)(Math.random()*(v.get(level).size()));
		return v.get(level).get(index);
	}
}
