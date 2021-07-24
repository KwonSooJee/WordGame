import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame{
	
	private StoryPanel storyPanel=null;
	private StagePanel sPanel=null;
	private GamePanel gPanel=null;
	private ItemSouse itemSouse =new ItemSouse();
	
	public boolean []stageClear= {false,false,false,false};
	
	public boolean []storyWatch= {false,false,false,false,false};
	
	
	public MainFrame() {
		setTitle("Word Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,700);
		setLayout(new BorderLayout());
		
		story();
		
		setVisible(true);
	}
	
	public void change() {
		getContentPane().removeAll();
		sPanel =new StagePanel(this);
		getContentPane().add(sPanel);
		revalidate();
		repaint();
	}
	
	public void change(int level) {
		getContentPane().removeAll();
		gPanel=new GamePanel(this,level,itemSouse);
		getContentPane().add(gPanel);
		revalidate();
		repaint();	
	}
	
	public void story() {
		getContentPane().removeAll();
		storyPanel=new StoryPanel(this);
		getContentPane().add(storyPanel);
		revalidate();
		repaint();
	}

}
