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
	private ImageIcon adultIcon=new ImageIcon("상상부기.png");
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
		case 0://첫번째
			add(new FirstStoryPanel());
			mainFrame.storyWatch[0]=true;
			break;
		case 1://두번째
			add(new SecondStoryPanel());
			mainFrame.storyWatch[1]=true;
			break;
		case 2://세번째
			add(new ThirdStoryPanel());
			mainFrame.storyWatch[2]=true;
			break;
		case 3://네번째
			add(new FourthStoryPanel());
			mainFrame.storyWatch[3]=true;
			break;
		case 4://다섯번째
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
				letter.setLetter("<html>이 알은 뭐지? 이상하게 생겼는데...<br>(집에 가져가볼까?)<br><br><br></html>",greenFontColor);
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
				letter.setLetter("<html>여러가지 위험(떨어지는 단어)으로 부터 알을 지켜 알이 성장할 수 있게 해주세요.<br>알에서 깨어나면 새끼가 바다를 찾으러 갈 수 있도록<br>알에서 깨면 바로 해변가에 풀어주세요<br><br></html>",new Color(0XFFFFFF));
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
				letter1.setLetter("<html>이것은 폭탄입니다<br>모든 단어를 삭제합니다<br>이 폰트와 같은 색의 단어를 맞추면<br>하나씩 늘어납니다</html>",bombColor);
				add(letter1);
				add(balloon);
				letter2.setLetter("<html>이것은 풍선입니다<br>모든 단어를 느리게 떨어지도록 합니다<br>이 폰트와 같은 색의 단어를 맞추면<br>하나씩 늘어납니다</html>",balloonColor);
				add(letter2);
				add(slingshot);
				letter3.setLetter("<html>이것은 새총입니다<br>가장 아래있는 단어를 삭제합니다<br>이 폰트와 같은 색의 단어를 맞추면<br>하나씩 늘어납니다</html>",slingshotColor);
				add(letter3);
				add(swap);
				letter4.setLetter("<html>이것은 swap입니다<br>가장 아래있는 단어를 랜덤하게 바꿉니다<br>이 폰트와 같은 색의 단어를 맞추면<br>하나씩 늘어납니다</html>",swapColor);
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
				letter.setLetter("<html>(!!! 알이 깨졌다!)<br><br><br><br></html>",greenFontColor);
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
				letter.setLetter("<html>...? 사람?은 아닌 것 같고<br>(제 2의 박혁거세인가?)<br>그보다 이 포대기는 알에서부터 있었던거야?<br><br></html>",greenFontColor);
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
				letter.setLetter("<html>누가 해변가에 바로 놓으라고 했지만 너무 작으니 당분간은 데리고 있어야겠다<br>(그보다 누가 말해줬더라?)<br><br><br></html>",greenFontColor);
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
				letter.setLetter("<html>오늘도 또 키가 컸어요!<br>이젠 혼자서도 멀리 놀러갈 수 있어요<br><br><br></html>",blueFontColor);
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
				letter.setLetter("<html>(어느새 그 알이 부쩍 많이 자랐다 이제는 해변가에 데려가야 하나<br>하지만 그러기엔 아직 너무 작은데...<br>조금만 더 크면 데리고 가자)<br><br></html>",greenFontColor);
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
				letter.setLetter("<html>이런, 괜찮을까?<br>...바다에서 못 하는 일들을 많이 해보고있으니 괜찮겠지<br><br><br></html>",new Color(0XFFFFFF));
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
				letter.setLetter("<html>(어느새 저렇게 컸지...<br>제 슬슬 바다로 갈 수 있도록<br>해변가에 데려다줘야겠다)<br><br></html>",greenFontColor);
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
				letter.setLetter("<html>(결국 해변가에 데리고 갔다<br>이제 보내줘야 시간이다)<br><br><br></html>",greenFontColor);
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
				letter.setLetter("<html>(이제 스스로 해야될 때가 됐구나<br>아무것도 모르는데 나 혼자서 어떡하지)<br><br><br></html>",blueFontColor);
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
				letter.setLetter("<html>가끔 힘들 때도 있지만 처음보다는 바닷속 생활이 힘들지는 않아<br>처음 길을 갈 때 도와준 사람도 있었고<br>이제는 위험한게 있어도 잘 대처할 수 있어<br><br></html>",blueFontColor);
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
				letter.setLetter("<html>아직 실수할 때도 많지만<br>그 다음의 나는 더 잘 해낼거야<br><br><br></html>",blueFontColor);
				
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
