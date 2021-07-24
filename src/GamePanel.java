import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;


public class GamePanel extends JPanel{
	
	private WordGamePanel wordGamePanel;
	private CharPanel charPanel;
	private GameScorePanel scorePanel;
	
	private MainFrame mainFrame;
	
	private ScoreSouse scoreSouse;
	private int level;
	private ItemSouse itemSouse;
	
	public GamePanel(MainFrame mainFrame,int level,ItemSouse itemSouse) {
		
		this.level=level;
		this.itemSouse=itemSouse;
		
		this.mainFrame=mainFrame;
		setLayout(new BorderLayout());
		scoreSouse=new ScoreSouse(level);
		
		wordGamePanel=new WordGamePanel(level,itemSouse);
		charPanel=new CharPanel(mainFrame);
		scorePanel=new GameScorePanel(level,scoreSouse);
		
		splitPane();
	
	}
	
 	private void splitPane() {
		
		JSplitPane hPane = new JSplitPane();
		add(hPane,BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(700);
		hPane.setEnabled(false);
		
		hPane.setLeftComponent(wordGamePanel);
		
		JSplitPane pPane=new JSplitPane();
		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setEnabled(false);
		pPane.setDividerLocation(300);
		pPane.setTopComponent(scorePanel);
		pPane.setBottomComponent(charPanel);
		hPane.setRightComponent(pPane);
		
	}


class WordGamePanel extends JPanel{
	
	private JTextField input = new JTextField(40);
	private JLabel timeText;
	private Vector<JLabel> textVector =new Vector<JLabel>();
	private JLabel text = new JLabel("start"); 
	private Vector<TextThread> textThreadVector=new Vector<TextThread>();
	private TextSource textSource = new TextSource();
	private TextDropThread textDropThread;
	private ItemSouse itemSouse;

	
	private Color backgroundColor =new Color(0Xabff93);
	private Color blueBackgroundColor=new Color(0Xabf0ff);
	private Color inputColor =new Color(0Xf7ff82);
	private GameGroundPanel gameGroundPanel;

	private boolean heart[]= {true,true,true,true,true};
	private int flag=0;
	private int level;
	private int heartIndex=4;
	private boolean success=false;
	private boolean balloonFlag=false;
	
	private String[] itemName= {"bomb","balloon","slingshot","swap"};
	private Color swapColor=new Color(0Xded119);
	private Color slingshotColor=new Color(0Xd29634);
	private Color balloonColor=new Color(0X13a91e);
	private Color bombColor=new Color(0Xff0000);

	private IconPanel iconPanel;
	
	private int delay;
	private int drop;
	private TimeThread timeThread;
	
	private ItemPanel itemPanel;
	
	public WordGamePanel(int level,ItemSouse itemSouse){
		
		this.level=level;
		this.itemSouse=itemSouse;
		timeThread=new TimeThread(level);
		
		textDropThread=new TextDropThread(level);
		
		timeText=new JLabel(timeThread.time+"");
		iconPanel=new IconPanel();
		text.setBackground(backgroundColor);
		setLayout(new BorderLayout());

		add(gameGroundPanel=new GameGroundPanel(), BorderLayout.CENTER);
		add(itemPanel=new ItemPanel(itemSouse),BorderLayout.WEST);
		add(new InputPanel(), BorderLayout.SOUTH);
		add(iconPanel,BorderLayout.NORTH);
		game();
		
	}
	
	public void game() {
		selectdelay();
	
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t=(JTextField)(e.getSource());
				String inWord=t.getText();

				for(int i=0;i<textVector.size();i++) {
					if(textVector.get(i).getText().equals(inWord)){
						success=true;
						itemPanel.checkItem(textVector.get(i));
						textVector.get(i).setText("");
						scoreSouse.increase(level);
						scorePanel.change();
						startGame(level);
						t.setText("");
						break;
					}			
			}
			if(!success) {
				t.setText("");
			}
			
			success=false;
			
			if(text.getText().equals(inWord)&&flag==0) {
				flag=1;
				timeThread.start();
				textDropThread.start();
				t.setText("");
				text.removeAll();
			}
		}
		});
	
	}
	
	public void startGame(int level){
		String newWord;
		newWord = textSource.get(level);
		text =new JLabel(newWord);
		TextThread textThread;
		
		textVector.add(text);
		
	 	int k=textVector.indexOf(text);
		
		textVector.get(k).setBackground(null);
		textVector.get(k).setOpaque(true);

		textThreadVector.add(textThread=new TextThread(textVector.get(k),level));
		
		k=textThreadVector.indexOf(textThread);
		textThreadVector.get(k).start();
		add(new GameGroundPanel(), BorderLayout.CENTER);
	}
	
	public void selectItem(JLabel text) {
		int item=(int)(Math.random()*20);
		if(item<5) {
			text.setForeground(swapColor);
		}
		else if(item<7) {
			text.setForeground(slingshotColor);
		}
		else if(item<9) {
			text.setForeground(balloonColor);
		}
		else if(item<10) {
			text.setForeground(bombColor);
		}
		else {
			text.setForeground(new Color(0X000000));
		}
	}

	public void selectdelay() {
		switch(level) {
		case 0:
			delay=70;
			drop=1;
			break;
		case 1:
			delay=50;
			drop=1;
			break;
		case 2:
			delay=40;
			drop=1;
			break;
		case 3:
			delay=20;
			drop=1;
			break;
		}

	}
	
	public void balloon() {
		delay*=2;
		balloonFlag=true;
		BalloonTimeThread balloon=new BalloonTimeThread();
		balloon.start();
	}
	
	public void slingshot() {
		int k=textVector.size()-1;
		for(int i=0;i<textVector.size();i++) {
			if(textVector.get(i).getText()!="") {
				if(textVector.get(i).getY()>textVector.get(k).getY()){
					k=i;
				}
			}
		}
		textVector.get(k).setText("");
		startGame(level);
	}
	
	public void bomb() {
		for(int i=0;i<textVector.size();i++) {
			textVector.get(i).setText("");
		}
		startGame(level);
	}
	
	public void swap(int level) {
		String newWord;
		newWord = textSource.get(level);
		
		int k=textVector.size()-1;
		for(int i=0;i<textVector.size();i++) {
			if(textVector.get(i).getText()!="") {
				if(textVector.get(i).getY()>textVector.get(k).getY()){
					k=i;
				}
			}
		}
		textVector.get(k).setText(newWord);
	}

	class GameGroundPanel extends JPanel {
		public GameGroundPanel() {
			this.setBackground(backgroundColor);
			if(level==3) {
				this.setBackground(blueBackgroundColor);
			}

			setLayout(null);
			
			if(flag==1) {
			int k=textVector.size()-1;

			textVector.get(k).setBackground(null);
			textVector.get(k).setFont(new Font(null,Font.PLAIN,15));
			textVector.get(k).setSize(100, 20);
			add(textVector.get(k));
			}
			else {
				text.setSize(100,30);
				text.setLocation(100,10);
				text.setBackground(null);
				add(text);
			}
		}
	}
	
	class TextDropThread extends Thread {	
		private int level;
		private int time;
		
		public TextDropThread(int level) {
			this.level=level;
			switch(level) {
			case 0:
				time=12000;
				break;
			case 1:
				time=7500;
				break;
			case 2:
				time=5000;
				break;
			case 3:
				time=4000;
				break;
			}
		}
		@Override
		public void run() {
			
			while(true){
				try {
					startGame(level);
					Thread.sleep(time);
			} catch (InterruptedException e) {
				return;
				}
				}
			}
		}//end TextDropThread
	
	class TimeThread extends Thread{
		private int level;
		private int timeLimit=0;
		public int time=10 ;
		
		public TimeThread(int level) {
			this.level=level;
			switch(level) {
			case 0:
				time=20;
				break;
			case 1:
				time=30;
				break;
			case 2:
				time=40;
				break;
			case 3:
				time=60;
				break;
			}
		}
		@Override
		public void run() {
			while(time!=timeLimit){
				try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}

			timeText.setText(--time+"");
			if(time==0) {
				mainFrame.stageClear[level] = true;
				for(int i=0;i<5;i++) {
					if(heart[i])
						scoreSouse.score+=50;
				}
				
				for(int i=0;i<textVector.size();i++) {
					textVector.get(i).setText("");
				}
				
				for(int i=0;i<textThreadVector.size();i++) {
					textThreadVector.get(i).interrupt();
				}
				textDropThread.interrupt();
				scoreSouse.record();
				JOptionPane.showMessageDialog(null, "Game Clear");
				mainFrame.story();
			}
			else if(heart[0]==false) {
				for(int i=0;i<textVector.size();i++) {
					textVector.get(i).setText("");
				}
				for(int i=0;i<textThreadVector.size();i++) {
					textThreadVector.get(i).interrupt();
				}
				textDropThread.interrupt();
				
				scoreSouse.record();
				JOptionPane.showMessageDialog(null, "Game Over");
				mainFrame.change();
				return;
			}
			}	
		}
	}//end of the class Timethread
		
	class BalloonTimeThread extends Thread{
		public int time=5000;
		public BalloonTimeThread() {

		}
		@Override
		public void run() {
			try {
				Thread.sleep(time);
				delay/=2;
				balloonFlag=false;
			} catch (InterruptedException e) {
				return;
			}
		}
	}//end of the class Timethread
		
	class TextThread extends Thread{
		private JLabel text;
		private int level;
		
		public TextThread(JLabel text,int level) {
			this.text=text;
			this.level=level;
		}	
		@Override
		public void run() {
			
			selectItem(text);
			
			boolean overlap;
			int x;
			do{
				overlap=false;
				x=(int)(Math.random()*(GamePanel.this.getWidth()/2))+20;
				for(int i=0;i<textVector.size()-1;i++) {
					
						if((x<textVector.get(i).getX()+100&&x>textVector.get(i).getX()-100)&&(textVector.get(i).getY()<40)) {
							overlap=true;
							break;
						}
				}
			}while(overlap);
			
			text.setLocation(x, 0);
			
			while(true) {
				int y=text.getY()+drop;
				
				if(y>gameGroundPanel.getHeight()&&text.getText()!=""){
					heart[heartIndex]=false;
					heartIndex--;
					iconPanel.repaint();
					String newWord;
					newWord = textSource.get(level);
						
					text.setText(newWord);
					do{
						overlap=false;
						x=(int)(Math.random()*(GamePanel.this.getWidth()/2))+20;
						for(int i=0;i<textVector.size()-1;i++) {
							
								if((x<textVector.get(i).getX()+100&&x>textVector.get(i).getX()-100)&&(textVector.get(i).getY()<40)) {
									overlap=true;
									break;
								}
						}
					}while(overlap);
					text.setLocation(x,0);
						
				}
				
				else {
					text.setLocation(x,y);
				}
				iconPanel.repaint();
				text.repaint();
				try {
					Thread.sleep(delay);
				}catch(InterruptedException e) {
					return;
				}
			}	
		}//end of class TextThread
	}
		
	class InputPanel extends JPanel {
		public InputPanel() {
			setLayout(new FlowLayout());
			this.setBackground(inputColor);
			add(input);
			

		}
	}
	
	class IconPanel extends JPanel{

		private ImageIcon heartIcon = new ImageIcon("heart.png");
		private ImageIcon brokenHeartIcon = new ImageIcon("brokenHeart.png");

		private JLabel heartsLabel1= new JLabel(heartIcon);
		private JLabel heartsLabel2= new JLabel(heartIcon);
		private JLabel heartsLabel3= new JLabel(heartIcon);
		private JLabel heartsLabel4= new JLabel(heartIcon);
		private JLabel heartsLabel5= new JLabel(heartIcon);
		
	
	public IconPanel() {
		setLayout(new GridLayout(1,6,10,0));
		
		this.setBackground(new Color(0xFFFFFF));
		
		checkHeart();
		add(heartsLabel1);
		add(heartsLabel2);
		add(heartsLabel3);
		add(heartsLabel4);
		add(heartsLabel5);
		
		add(timeText);
		
		setVisible(true);
	}
	
	@Override
	public void repaint() {
		checkHeart();
		super.repaint();
	}
	
	
	private void checkHeart() {
			if(heart[0]==false) {heartsLabel1.setIcon(brokenHeartIcon);}			
			if(heart[1]==false) {heartsLabel2.setIcon(brokenHeartIcon);}			
			if(heart[2]==false) {heartsLabel3.setIcon(brokenHeartIcon);}			
			if(heart[3]==false) {heartsLabel4.setIcon(brokenHeartIcon);}				
			if(heart[4]==false) {heartsLabel5.setIcon(brokenHeartIcon);}
		}
	}
	
	class ItemPanel extends JPanel{
		private ImageIcon bombIcon = new ImageIcon("bomb.png");
		private ImageIcon balloonIcon = new ImageIcon("balloon.png");
		private ImageIcon slingshotIcon = new ImageIcon("slingshot.png");
		private ImageIcon swapIcon = new ImageIcon("swap.png");
		
		private JButton bombBtn = new JButton(bombIcon);
		private JButton balloonBtn = new JButton(balloonIcon);
		private JButton slingshotBtn = new JButton(slingshotIcon);
		private JButton swapBtn = new JButton(swapIcon);
	
	
		
		private ItemSouse itemSouse;
		
		private Color ItemBackgroundColor =new Color(184,218,247);
		
		public ItemPanel(ItemSouse itemSouse) {
			this.itemSouse=itemSouse;
			
			set();
			
			setLayout(new GridLayout(5,1,10,0));
			setBackground(ItemBackgroundColor);
			makeToolItem();
		}
		
		private void set() {	
			bombBtn.setText(itemSouse.item.get(itemName[0])+"");
			balloonBtn.setText(itemSouse.item.get(itemName[1])+"");
			slingshotBtn.setText(itemSouse.item.get(itemName[2])+"");
			swapBtn.setText(itemSouse.item.get(itemName[3])+"");		
		}
		
		
		public void checkItem(JLabel text) {
			if(text.getForeground()==swapColor) {
				itemSouse.increase(itemName[3]);
			}
			else if(text.getForeground()==slingshotColor) {
				itemSouse.increase(itemName[2]);
			}
			else if((text.getForeground()==balloonColor)) {
				itemSouse.increase(itemName[1]);
			}
			else if(text.getForeground()==bombColor) {
				itemSouse.increase(itemName[0]);
				
			}
			
			set();
			
		}
		
		
		private void makeToolItem() {
			bombBtn.setBackground(null);
			bombBtn.setBorderPainted(false);
			balloonBtn.setBackground(null);
			balloonBtn.setBorderPainted(false);
			
			slingshotBtn.setBackground(null);
			slingshotBtn.setBorderPainted(false);
			swapBtn.setBackground(null);
			swapBtn.setBorderPainted(false);
			
			add(bombBtn);
			add(balloonBtn);
			add(slingshotBtn);
			add(swapBtn);
			
			bombBtn.addActionListener(new BoomAction());
			balloonBtn.addActionListener(new SlowAction());
			slingshotBtn.addActionListener(new deleteAction());
			swapBtn.addActionListener(new SwapAction());
		}
		
		private class BoomAction implements ActionListener {

			public BoomAction(){

			}
			public void actionPerformed(ActionEvent e) {
				if(flag==1&&itemSouse.item.get(itemName[0])>0){
					itemSouse.decrease(itemName[0]);
					bomb();
					bombBtn.setText(itemSouse.item.get(itemName[0])+"");
				}
			}
		}
		
		private class SlowAction implements ActionListener{

			public SlowAction(){
				
			}
			public void actionPerformed(ActionEvent e) {
				if(flag==1&&itemSouse.item.get(itemName[1])>0&&(!balloonFlag)){
					itemSouse.decrease(itemName[1]);
					balloon();
					balloonBtn.setText(itemSouse.item.get(itemName[1])+"");
				}
			}
		}
		private class deleteAction implements ActionListener{

			public deleteAction(){

			}
			public void actionPerformed(ActionEvent e) {
				if(flag==1&&itemSouse.item.get(itemName[2])>0){
				itemSouse.decrease(itemName[2]);
				slingshot();
				slingshotBtn.setText(itemSouse.item.get(itemName[2])+"");
			}

			}
		}
		private class SwapAction implements ActionListener{

			public SwapAction(){
				
			}
			public void actionPerformed(ActionEvent e) {
				if(flag==1&&itemSouse.item.get(itemName[3])>0){
					itemSouse.decrease(itemName[3]);
					swap(level);
					swapBtn.setText(itemSouse.item.get(itemName[3])+"");
				}

			}
	}
	}
}
}
	

