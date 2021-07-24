import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class StagePanel extends JPanel{
	

	private CharPanel charPanel;
	private ScorePanel scorePanel;
	private StageCenterPanel centerPanel;
	private MainFrame mainFrame;
	
	public StagePanel(MainFrame mainFrame) {
		setLayout(new BorderLayout());
		this.mainFrame=mainFrame;
		
		centerPanel=new StageCenterPanel(mainFrame);
		charPanel= new CharPanel(mainFrame);
		scorePanel =new ScorePanel(0);
		

		splitPane();
	}
	
	private void splitPane() {
		
		JSplitPane hPane = new JSplitPane();
		add(hPane,BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(700);
		hPane.setEnabled(false);
		
		hPane.setLeftComponent(centerPanel);
		
		JSplitPane pPane=new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(300);
		pPane.setTopComponent(scorePanel);
		pPane.setBottomComponent(charPanel);
		pPane.setEnabled(false);
		hPane.setRightComponent(pPane);
		
	}

	
	class StageCenterPanel extends JPanel{	
		
		private Color backgroundColor =new Color(0Xabff93);
		
		private ImageIcon stageIcon1 = new ImageIcon("stageUnLock1.png");
		private ImageIcon stageIcon2Lock = new ImageIcon("stageLock2.png");
		private ImageIcon stageIcon2 = new ImageIcon("stageUnlock2.png");
		private ImageIcon stageIcon3Lock = new ImageIcon("stageLock3.png");
		private ImageIcon stageIcon3 = new ImageIcon("stageUnLock3.png");
		private ImageIcon stageIconFinalLock = new ImageIcon("stageLockFinal.png");
		private ImageIcon stageIconFinal = new ImageIcon("stageUnLockFinal.png");
		
		private JButton stageButton1 =new JButton(stageIcon1);
		private JButton stageButton2 =new JButton(stageIcon2Lock);
		private JButton stageButton3=new JButton(stageIcon3Lock);
		private JButton stageButton4 =new JButton(stageIconFinalLock);
		
		private String[] stageSubject= {"sea","food","job","travel"};
		
		private MainFrame mainFrame;
		
		public StageCenterPanel(MainFrame mainFrame) {		
			this.setBackground(backgroundColor);
			setLayout(null);
			this.mainFrame=mainFrame;
			checkAndChange();
			
			setPanel();
			
			setVisible(true);
		}
		
		public void setPanel() {
						
			stageButton1.setBackground(null);
			stageButton1.setText(stageSubject[0]);
			stageButton1.setFont(new Font(null,Font.PLAIN,18));
			stageButton1.setBounds(10,this.getHeight()+380,300,300);
			stageButton1.setBorderPainted(false);
			add(stageButton1);

			stageButton1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					stageButton1.setBackground(null);
					mainFrame.change(0);//level 1
				}
				@Override
				public void mouseReleased(MouseEvent e) {	}
				@Override
				public void mousePressed(MouseEvent e) {	}
				@Override
				public void mouseEntered(MouseEvent e) {	
					scorePanel.change(0);
				}
				@Override
				public void mouseExited(MouseEvent e) {		}
			});
			
			stageButton2.setBackground(null);
			stageButton2.setText(stageSubject[1]);
			stageButton2.setFont(new Font(null,Font.PLAIN,18));
			stageButton2.setBounds(370,this.getHeight()+300,300,300);
			stageButton2.setBorderPainted(false);
			add(stageButton2);
			stageButton2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					stageButton2.setBackground(null);
					if(mainFrame.stageClear[0]){//level 2
						mainFrame.change(1);
					}
					else {
						JOptionPane.showMessageDialog(null,"이전 스테이지를 먼저 클리어 해주세요");
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {	}
				@Override
				public void mouseEntered(MouseEvent e) {
					scorePanel.change(1);	
					}
				@Override
				public void mouseExited(MouseEvent e) {		}
			});
				
			stageButton3.setBackground(null);
			stageButton3.setText(stageSubject[2]);
			stageButton3.setFont(new Font(null,Font.PLAIN,18));
			stageButton3.setBounds(10,this.getHeight()+100,300,300);
			stageButton3.setBorderPainted(false);
			add(stageButton3);
			stageButton3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					stageButton3.setBackground(null);
					if(mainFrame.stageClear[1]){//level 3
						mainFrame.change(2);
					}
					else {
						JOptionPane.showMessageDialog(null,"이전 스테이지를 먼저 클리어 해주세요");
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {}
				@Override
				public void mousePressed(MouseEvent e) {	}
				@Override
				public void mouseEntered(MouseEvent e) {
					scorePanel.change(2);	
				}
				@Override
				public void mouseExited(MouseEvent e) {		}
			});
				
			stageButton4.setBackground(null);
			stageButton4.setText(stageSubject[3]);
			stageButton4.setFont(new Font(null,Font.PLAIN,18));
			stageButton4.setBounds(370,this.getHeight()+10,300,300);
			stageButton4.setBorderPainted(false);
			add(stageButton4);
			
			stageButton4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					stageButton4.setBackground(null);
					if(mainFrame.stageClear[2]){//level 4
						mainFrame.change(3);
					}
					else {
						JOptionPane.showMessageDialog(null,"이전 스테이지를 먼저 클리어 해주세요");
					}
				}
				@Override
				public void mouseReleased(MouseEvent e) {	}
				@Override
				public void mousePressed(MouseEvent e) {	}
				@Override
				public void mouseEntered(MouseEvent e) {
					scorePanel.change(3);
				}
				@Override
				public void mouseExited(MouseEvent e) {		}
			});
		}
		
		public void checkAndChange() {
			if(mainFrame.stageClear[0]==true) {
				stageButton2.setIcon(stageIcon2);
				}
			if(mainFrame.stageClear[1]==true) {
				stageButton3.setIcon(stageIcon3);
			}
			if(mainFrame.stageClear[2]==true) {
				stageButton4.setIcon(stageIconFinal);
			}
		}
	}
	
	
}
