import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameScorePanel extends JPanel{
	private int score = 0;
	private JLabel textLabel = new JLabel("Á¡¼ö");
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private Color fontColor=new Color(0X8bb1c4);
	private Vector<JLabel> scoreRecordLabel=new Vector<JLabel>();
	private ScoreSouse scoreSouse;
	private int level;
	
	public GameScorePanel(int level,ScoreSouse scoreSouse) {
		this.setBackground(Color.BLACK);
		setLayout(null);
		this.level=level;
		this.scoreSouse=scoreSouse;
		
		textLabel.setSize(50, 20);
		textLabel.setLocation(50, 10);
		textLabel.setForeground(fontColor);
		add(textLabel);

		
		scoreLabel.setSize(100, 20);
		scoreLabel.setLocation(100, 10);
		scoreLabel.setForeground(fontColor);
		add(scoreLabel);
		
		
		
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
	public void change() {
		score=scoreSouse.score;
		scoreLabel.setText(Integer.toString(score));
		add(scoreLabel);
	}
}

