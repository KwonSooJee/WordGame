import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StoryPanel extends JPanel{
	private MainFrame mainFrame;
	private int flag=6;
	private ImageIcon eggIcon=new ImageIcon("egg.png");
	private ImageIcon brokenEggIcon=new ImageIcon("brokenEgg.png");
	private ImageIcon babyIcon=new ImageIcon("baby.png");
	private ImageIcon kidIcon=new ImageIcon("kid.png");
	private ImageIcon adultIcon=new ImageIcon("���α�.png");
	private ImageIcon bombIcon = new ImageIcon("bomb.png");
	private ImageIcon balloonIcon = new ImageIcon("balloon.png");
	private ImageIcon slingshotIcon = new ImageIcon("slingshot.png");
	private ImageIcon swapIcon = new ImageIcon("swap.png");
	
	private JLabel kidLabel=new JLabel(kidIcon);
	private JLabel adultLabel=new JLabel(adultIcon);
	private JLabel swap=new JLabel(swapIcon);
	private JLabel bomb=new JLabel(bombIcon);
	private JLabel balloon=new JLabel(balloonIcon);
	private JLabel slingshot=new JLabel(slingshotIcon);
	
	private Color greenBackgroundColor =new Color(0Xabff93);
	private Color yellowBackgroundColor =new Color(0Xefea9f);
	private Color blueBackgroundColor =new Color(0X2a71ff);
	private Color greenFontColor=new Color(0X2eff82);
	private Color blueFontColor=new Color(0X8bb1c4);
	
	private Color swapColor=new Color(0Xded119);
	private Color slingshotColor=new Color(0Xd29634);
	private Color balloonColor=new Color(0X13a91e);
	private Color bombColor=new Color(0Xff0000);
	
	private Vector<JPanel> story=new Vector<JPanel>();
	
	public StoryPanel(MainFrame mainFrame) {
		this.mainFrame=mainFrame;
		this.setBackground(greenBackgroundColor);
		this.setLayout(new BorderLayout());
		check();
		watch();
	}
	
	public void check() {
		if(!mainFrame.storyWatch[0]) {
			flag=0;
		}
		
		for(int i=1;i<5;i++) {
			if((!mainFrame.storyWatch[i])&&mainFrame.stageClear[i-1]) {
				flag=i;
			}
		}
		if(mainFrame.storyWatch[4]) {
			flag=5;
		}
	}
	
	public void watch() {
		switch(flag) {
		case 0://ù��°
			add(new FirstStoryPanel());
			mainFrame.storyWatch[0]=true;
			break;
		case 1://�ι�°
			add(new SecondStoryPanel());
			mainFrame.storyWatch[1]=true;
			break;
		case 2://����°
			add(new ThirdStoryPanel());
			mainFrame.storyWatch[2]=true;
			break;
		case 3://�׹�°
			add(new FourthStoryPanel());
			mainFrame.storyWatch[3]=true;
			break;
		case 4://�ټ���°
			add(new FifthStoryPanel());
			mainFrame.storyWatch[4]=true;
			break;
		default:
			mainFrame.change();
		}
	}
	
	class FirstStoryPanel extends JPanel{
		private int index=0;
		private JPanel action;
		
		public FirstStoryPanel() {
			this.setBackground(Color.black);
			this.setLayout(new BorderLayout());

			setPanel();
			changePanel();
			
		}
		
		public void setPanel() {	
			story.add(new First());
			story.add(new Second());
			story.add(new Third());
		}
		
		public void changePanel() {
			removeAll();
			if(index>2) {
				mainFrame.change();
				return;
			}
			action=story.get(index++);
			add(action);
			revalidate();
			repaint();
			action.addMouseListener(new MyMouseListener());
			
		}

		class First extends JPanel{
			private LetterPanel letter =new LetterPanel();
			private JLabel eggLabel=new JLabel(eggIcon);
			
			public First() {
				this.setBackground(Color.black);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(eggLabel,BorderLayout.CENTER);
				letter.setLetter("<html>�� ���� ����? �̻��ϰ� ����µ�...<br>(���� ����������?)<br><br><br></html>",greenFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}
		class Second extends JPanel{
			
			private LetterPanel letter =new LetterPanel();
			private JLabel eggLabel=new JLabel(eggIcon);
			
			public Second() {
				this.setBackground(Color.black);
				this.setLayout(new BorderLayout());
				
				set();
			}
			public void set() {
				add(eggLabel,BorderLayout.CENTER);
				letter.setLetter("<html>�������� ����(�������� �ܾ�)���� ���� ���� ���� ���� ������ �� �ְ� ���ּ���.<br>�˿��� ����� ������ �ٴٸ� ã���� �� �� �ֵ���<br>�˿��� ���� �ٷ� �غ����� Ǯ���ּ���<br><br></html>",new Color(0XFFFFFF));
				add(letter,BorderLayout.SOUTH);
			}
			
		}
		class Third extends JPanel{
			private LetterPanel letter1=new LetterPanel();
			private LetterPanel letter2=new LetterPanel();
			private LetterPanel letter3=new LetterPanel();
			private LetterPanel letter4=new LetterPanel();
			
			public Third() {
				this.setBackground(Color.white);
				this.setLayout(new GridLayout(4,2,10,10));
				
				set();
			}
			
			public void set() {
				add(bomb);
				letter1.setLetter("<html>�̰��� ��ź�Դϴ�<br>��� �ܾ �����մϴ�<br>�� ��Ʈ�� ���� ���� �ܾ ���߸�<br>�ϳ��� �þ�ϴ�</html>",bombColor);
				add(letter1);
				add(balloon);
				letter2.setLetter("<html>�̰��� ǳ���Դϴ�<br>��� �ܾ ������ ���������� �մϴ�<br>�� ��Ʈ�� ���� ���� �ܾ ���߸�<br>�ϳ��� �þ�ϴ�</html>",balloonColor);
				add(letter2);
				add(slingshot);
				letter3.setLetter("<html>�̰��� �����Դϴ�<br>���� �Ʒ��ִ� �ܾ �����մϴ�<br>�� ��Ʈ�� ���� ���� �ܾ ���߸�<br>�ϳ��� �þ�ϴ�</html>",slingshotColor);
				add(letter3);
				add(swap);
				letter4.setLetter("<html>�̰��� swap�Դϴ�<br>���� �Ʒ��ִ� �ܾ �����ϰ� �ٲߴϴ�<br>�� ��Ʈ�� ���� ���� �ܾ ���߸�<br>�ϳ��� �þ�ϴ�</html>",swapColor);
				add(letter4);
			}
			
			
		}

		class MyMouseListener implements MouseListener{
		   @Override
		    public void mouseClicked(MouseEvent e) {
		    	changePanel();
		    }
		    @Override
		    public void mousePressed(MouseEvent e) {}
		    @Override
		    public void mouseReleased(MouseEvent e) {}
		    @Override
		    public void mouseEntered(MouseEvent e) {}
		    @Override
		    public void mouseExited(MouseEvent e) {}   
		}
	}
	
	class SecondStoryPanel extends JPanel{
		private int index=0;
		private JPanel action;
		
		public SecondStoryPanel() {
			this.setLayout(new BorderLayout());
			setPanel();
				changePanel();
		}
		
		public void setPanel() {
			
			story.add(new First());
			story.add(new Second());
			story.add(new Third());
		}
		
		public void changePanel() {
			removeAll();
			if(index>2) {
				mainFrame.change();
				return;
			}
			action=story.get(index++);
			add(action);
			revalidate();
			repaint();
			action.addMouseListener(new MyMouseListener());
			
		}

		class First extends JPanel{
			private JLabel brokenEggLabel=new JLabel(brokenEggIcon);
			private LetterPanel letter =new LetterPanel();
			
			public First() {
				this.setBackground(greenBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(brokenEggLabel);
				letter.setLetter("<html>(!!! ���� ������!)<br><br><br><br></html>",greenFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}

		class Second extends JPanel{
			private JLabel babyLabel=new JLabel(babyIcon);
			private LetterPanel letter=new LetterPanel();
			public Second() {
				this.setBackground(greenBackgroundColor);
				this.setLayout(new BorderLayout());
				set();
			}
			
			public void set() {
				add(babyLabel);
				letter.setLetter("<html>...? ���?�� �ƴ� �� ����<br>(�� 2�� �����ż��ΰ�?)<br>�׺��� �� ������ �˿������� �־����ž�?<br><br></html>",greenFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}

		class Third extends JPanel{
			private JLabel babyLabel=new JLabel(babyIcon);
			private LetterPanel letter=new LetterPanel();
			
			public Third() {
				this.setBackground(greenBackgroundColor);
				this.setLayout(new BorderLayout());
				set();
			}
			
			public void set() {
				add(babyLabel);
				letter.setLetter("<html>���� �غ����� �ٷ� ������� ������ �ʹ� ������ ��а��� ������ �־�߰ڴ�<br>(�׺��� ���� ���������?)<br><br><br></html>",greenFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}

		class MyMouseListener implements MouseListener{
		   @Override
		    public void mouseClicked(MouseEvent e) {
		    	changePanel();
		    }
		    @Override
		    public void mousePressed(MouseEvent e) {}
		    @Override
		    public void mouseReleased(MouseEvent e) {}
		    @Override
		    public void mouseEntered(MouseEvent e) {}
		    @Override
		    public void mouseExited(MouseEvent e) {}   
		}
	}
	
	class ThirdStoryPanel extends JPanel{
		private int index=0;
		private JPanel action;
		
		public ThirdStoryPanel() {
			this.setLayout(new BorderLayout());
			setPanel();
			changePanel();
		}
		
		public void setPanel() {
			
			story.add(new First());
			story.add(new Second());
			story.add(new Third());
		}
		
		public void changePanel() {
			removeAll();
			if(index>2) {
				mainFrame.change();
				return;
			}
			
			action=story.get(index++);
			add(action);
			revalidate();
			repaint();
			action.addMouseListener(new MyMouseListener());
			
		}


		class First extends JPanel{
			private JLabel kidLabel=new JLabel(kidIcon);
			private LetterPanel letter=new LetterPanel();
			
			public First() {
				this.setBackground(greenBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(kidLabel);
				letter.setLetter("<html>���õ� �� Ű�� �Ǿ��!<br>���� ȥ�ڼ��� �ָ� ��� �� �־��<br><br><br></html>",blueFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}
			
		class Second extends JPanel{
			private JLabel kidLabel=new JLabel(kidIcon);
			private LetterPanel letter=new LetterPanel();
			
			public Second() {
				this.setBackground(greenBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(kidLabel);
				letter.setLetter("<html>(����� �� ���� ��½ ���� �ڶ��� ������ �غ����� �������� �ϳ�<br>������ �׷��⿣ ���� �ʹ� ������...<br>���ݸ� �� ũ�� ������ ����)<br><br></html>",greenFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}

		class Third extends JPanel{
			private JLabel kidLabel=new JLabel(kidIcon);
			private LetterPanel letter=new LetterPanel();
			
			public Third() {
				this.setBackground(greenBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(kidLabel);
				letter.setLetter("<html>�̷�, ��������?<br>...�ٴٿ��� �� �ϴ� �ϵ��� ���� �غ��������� ��������<br><br><br></html>",new Color(0XFFFFFF));
				add(letter,BorderLayout.SOUTH);
			}
		}

		class MyMouseListener implements MouseListener{
		   @Override
		    public void mouseClicked(MouseEvent e) {
		    	changePanel();
		    }
		    @Override
		    public void mousePressed(MouseEvent e) {}
		    @Override
		    public void mouseReleased(MouseEvent e) {}
		    @Override
		    public void mouseEntered(MouseEvent e) {}
		    @Override
		    public void mouseExited(MouseEvent e) {}   
		}
	}
	
	class FourthStoryPanel extends JPanel{
		private int index=0;
		private JPanel action;
		
		public FourthStoryPanel() {
			this.setLayout(new BorderLayout());
			setPanel();
			changePanel();
		}
		
		public void setPanel() {
			
			story.add(new First());
			story.add(new Second());
			story.add(new Third());
		}
		
		public void changePanel() {
			removeAll();
			if(index>2) {
				mainFrame.change();
				return;
			}
			
			action=story.get(index++);
			add(action);
			revalidate();
			repaint();
			action.addMouseListener(new MyMouseListener());
			
		}
			
		class First extends JPanel{
			private JLabel adultLabel=new JLabel(adultIcon);
			private LetterPanel letter=new LetterPanel();
			
			public First() {
			this.setBackground(greenBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(adultLabel);
				letter.setLetter("<html>(����� ������ ����...<br>�� ���� �ٴٷ� �� �� �ֵ���<br>�غ����� ��������߰ڴ�)<br><br></html>",greenFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}
		
		class Second extends JPanel{
			private JLabel adultLabel=new JLabel(adultIcon);
			private LetterPanel letter=new LetterPanel();
			
			public Second() {
				this.setBackground(yellowBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(adultLabel);
				letter.setLetter("<html>(�ᱹ �غ����� ������ ����<br>���� ������� �ð��̴�)<br><br><br></html>",greenFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}
		
		class Third extends JPanel{
			private JLabel adultLabel=new JLabel(adultIcon);
			private LetterPanel letter=new LetterPanel();
			
			public Third() {
				this.setBackground(yellowBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(adultLabel);
				letter.setLetter("<html>(���� ������ �ؾߵ� ���� �Ʊ���<br>�ƹ��͵� �𸣴µ� �� ȥ�ڼ� �����)<br><br><br></html>",blueFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}

		class MyMouseListener implements MouseListener{
		   @Override
		    public void mouseClicked(MouseEvent e) {
		    	changePanel();
		    }
		    @Override
		    public void mousePressed(MouseEvent e) {}
		    @Override
		    public void mouseReleased(MouseEvent e) {}
		    @Override
		    public void mouseEntered(MouseEvent e) {}
		    @Override
		    public void mouseExited(MouseEvent e) {}   
		}
	}

	class FifthStoryPanel extends JPanel{
		private int index=0;
		private JPanel action;
		
		public FifthStoryPanel() {
			this.setLayout(new BorderLayout());
			setPanel();
			changePanel();
		}
		
		public void setPanel() {
			story.add(new First());
			story.add(new Second());
			story.add(new Third());
		}
		
		public void changePanel() {
			removeAll();
			if(index>2) {
				mainFrame.change();
				return;
			}
			
			action=story.get(index++);
			add(action);
			revalidate();
			repaint();
			action.addMouseListener(new MyMouseListener());
			
		}
		
		class First extends JPanel{
			private JLabel adultLabel=new JLabel(adultIcon);
			private LetterPanel letter=new LetterPanel();
			
			public First() {
			this.setBackground(blueBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(adultLabel);
				letter.setLetter("<html>���� ���� ���� ������ ó�����ٴ� �ٴ�� ��Ȱ�� �������� �ʾ�<br>ó�� ���� �� �� ������ ����� �־���<br>������ �����Ѱ� �־ �� ��ó�� �� �־�<br><br></html>",blueFontColor);
				add(letter,BorderLayout.SOUTH);
			}
		}

		class Second extends JPanel{
			private JLabel adultLabel=new JLabel(adultIcon);
			private LetterPanel letter=new LetterPanel();
			
			public Second() {
				this.setBackground(blueBackgroundColor);
				this.setLayout(new BorderLayout());
				
				set();
			}
			
			public void set() {
				add(adultLabel);
				letter.setLetter("<html>���� �Ǽ��� ���� ������<br>�� ������ ���� �� �� �س��ž�<br><br><br></html>",blueFontColor);
				
				add(letter,BorderLayout.SOUTH);
			}
		}

		class Third extends JPanel{
			private JLabel letter=new JLabel();
			public Third() {
				this.setBackground(Color.BLACK);
				this.setLayout(new BorderLayout());
				
				set();
			}
			public void set() {
				letter.setText("-END-");
				letter.setForeground(new Color(0XFFFFFF));
				letter.setFont(new Font(null,Font.PLAIN,50));
				letter.setHorizontalAlignment(JLabel.CENTER);
				add(letter);
			}
		}
	
		class MyMouseListener implements MouseListener{
		   @Override
		    public void mouseClicked(MouseEvent e) {
		    	changePanel();
		    }
		    @Override
		    public void mousePressed(MouseEvent e) {}
		    @Override
		    public void mouseReleased(MouseEvent e) {}
		    @Override
		    public void mouseEntered(MouseEvent e) {}
		    @Override
		    public void mouseExited(MouseEvent e) {}   
		}
	}
	
	class LetterPanel extends JPanel{
		
		private JLabel letterBuf=new JLabel();
		
		public LetterPanel() {
			this.setBackground(new Color(0X404040));
			letterBuf.setFont(new Font(null,Font.PLAIN,20));
		}
		
		public void setLetter(String text) {
			letterBuf.setText(text);
			letterBuf.setHorizontalAlignment(JLabel.CENTER);
			add(letterBuf);
		}
		public void setLetter(String text,Color color) {
			letterBuf.setForeground(color);
			setLetter(text);
		}
	}
}
