import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	
	private Color fontColor=new Color(0X8bb1c4);
	private JLabel textLabel = new JLabel("·©Å·");
	
	public ScorePanel(int level) {
		this.setBackground(Color.BLACK);
		setLayout(null);
		
		set(level);
	}
	
	public void change(int level) {
		
		removeAll();
		
		set(level);
		
		repaint();
	}
	
	private void set(int level) {
		ScoreSouse scoreSouse=new ScoreSouse(level);
		Vector<JLabel> scoreRecordLabel=new Vector<JLabel>();
		
		textLabel.setSize(50, 20);
		textLabel.setLocation(50, 10);
		textLabel.setForeground(fontColor);
		add(textLabel);
		
		if(!(scoreSouse.sR.isEmpty())){
			Object[] buf=scoreSouse.sR.toArray();
			
			Vector<Integer> buffer=new Vector<Integer>();
			
			for(int i=0; i<buf.length;i++) {
				buffer.add(Integer.parseInt((String) buf[i]));
			}
			
			Object[] objs=buffer.toArray();
			Arrays.sort(objs,Collections.reverseOrder());
			
			for(int i=0;i<5&&i<objs.length;i++) {
				JLabel bufLabel=new JLabel();
				bufLabel.setText((i+1)+". "+objs[i]);
				scoreRecordLabel.add(bufLabel);
				scoreRecordLabel.get(i).setLocation(20,50+(i*30));
				scoreRecordLabel.get(i).setSize(50,20);
				scoreRecordLabel.get(i).setForeground(fontColor);
				add(scoreRecordLabel.get(i));
			}
		}
	}

}

