import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CharPanel extends JPanel{
	private ImageIcon charIcon1=new ImageIcon("egg.jpg");
	private ImageIcon charIcon2=new ImageIcon("baby.jpg");
	private ImageIcon charIcon3=new ImageIcon("kid.jpg");
	private ImageIcon charIconFinal=new ImageIcon("상상부기.jpg");

	private JLabel charLabel=new JLabel(charIcon1);
	
	private MainFrame mainFrame;
	
	public CharPanel(MainFrame mainFrame) {
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout());
		
		this.mainFrame=mainFrame;
		
		checkAndChange();
		
		charLabel.setSize(this.getWidth(),this.getHeight());
		add(charLabel);
	}
	
	public void checkAndChange() {
		if(mainFrame.stageClear[0]==true) {
			charLabel.setIcon(charIcon2);
			}
		if(mainFrame.stageClear[1]==true) {
			charLabel.setIcon(charIcon3);
		}
		if(mainFrame.stageClear[2]==true) {
			charLabel.setIcon(charIconFinal);
		}
	}
}
