package Client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.Position;

import Model.CODE_KEY;
import Model.ImageSet;
import Model.ImgPosition;
import Model.MyLabel;
import Thread.GameSender;

class MyPanel extends Panel {

	Image img;
	Position p;
	ArrayList<ImageSet> setList;

	public MyPanel(ArrayList<ImageSet> setList) {
		// TODO Auto-generated constructor stub
		this.setList = setList;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		for (ImageSet imgSet : setList) {
			ImgPosition p = imgSet.p;
			g.drawImage(imgSet.img, p.x, p.y, p.width, p.height, this);
		}

	}

	@Override
	public void setLayout(LayoutManager mgr) {
		// TODO Auto-generated method stub
		super.setLayout(mgr);
	}
}


public class ClientGUI extends Frame implements ActionListener {
	String state;
	public int allGameMoney =200;
	public int bettingMoney  =100;
	public int myMoney = 50000;
	public int yourMoney = 50000;
	public int playerNumber;
	public int card1Num=-1;
	public int card2Num=-1;
	
	JLabel label_list;
	JLabel label_combi;
	ImageIcon image_combi;
	
	Panel p_right;
	Panel p_left;

	Panel p_centerLine_1;
	Panel p_centerLine_2;
	Panel p_centerLine_3;

	Button btn_msgSend;
	GameSender thread;

	JButton btn_call;
	JButton btn_double;
	JButton btn_half;
	JButton btn_die;
	JButton btn_GameStart;

	Label label_name;
	Label label_money;

	MyLabel label_AllGameMoney;
	MyLabel label_MyState;
	MyLabel label_YourState;
	Label label_MyMoney;
	Label label_YourMoney;
	MyLabel label_betting;

	MyPanel panel_plyer1;
	MyPanel panel_plyer2;
//	MyPanel panel_YourImg;
//	MyPanel panel_MyImg;
//	MyPanel panel_YourCard1;
//	MyPanel panel_YourCard2;
//	MyPanel panel_MyCard1;
//	MyPanel panel_MyCard2;

	// public ClientGUI() {
	// // ===============Size setting===============
	// setBounds(300, 200, 1300, 700);
	// setResizable(false);
	// // ===============panel_initialize===============
	// panel_initialize();
	//
	// // ===============Chatting Initialize==========
	// tf = new TextField();
	// ta = new TextArea(30, 40);
	// tf.setColumns(30);
	// btn_msgSend = new Button("전송");
	//
	// // ===============btn_initialize===============
	// btn_initialize();
	// btn_listener();
	// // ===============label_initialize===============
	// label_initialize();
	//
	// launchTest();
	// setVisible(true);
	// }
	public ClientGUI() {
		// ===============Size setting===============
		setBounds(300, 200, 1300, 700);
		setResizable(false);
		// ===============panel_initialize===============
		panel_initialize();

		// ===============Chatting Initialize==========
		btn_msgSend = new Button("전송");

		// ===============btn_initialize===============
		btn_initialize();
		btn_listener();
		// ===============label_initialize===============
		label_initialize();

		launchTest();
		setVisible(true);
	}

	public ClientGUI(String name, GameSender thread) {
		this.thread = thread;
		// ===============Size setting===============
		setBounds(300, 200, 1300, 700);
		setResizable(false);
		// ===============panel_initialize===============
		panel_initialize();

		// ===============Chatting Initialize==========
		label_list = new JLabel(new ImageIcon("img_list.png"));
		image_combi = new ImageIcon();
		label_combi = new JLabel();
		btn_msgSend = new Button("전송");

		// ===============btn_initialize===============
		btn_initialize();
		btn_listener();
		updateAllButton(false);
		// ===============label_initialize===============
		label_initialize();

		launchTest();
		setVisible(true);
	}

	public void panel_initialize() {
		// Chatting panel
		p_right = new Panel();

		// Game panel
		p_left = new Panel();

		// Game panel centerline
		p_centerLine_1 = new Panel();
		p_centerLine_2 = new Panel();
		p_centerLine_3 = new Panel();

	}

	public void label_initialize() {
		label_name = new Label();

		label_money = new Label();
		label_MyState = new MyLabel(80);
		label_YourState = new MyLabel(80);
		label_AllGameMoney = new MyLabel(40);
		// lable_BattingMoney = new Label();
	}

	public void btn_initialize() {
		btn_call = new JButton(new ImageIcon("btn_call.png"));
		btn_call.setMargin(new Insets(3, 3, 3, 3));
		btn_call.setToolTipText("Launch nuclear missiles");
		btn_double = new JButton(new ImageIcon("btn_double.png"));
		btn_double.setMargin(new Insets(3, 3, 3, 3));
		btn_double.setToolTipText("Launch nuclear missiles");
		btn_half = new JButton(new ImageIcon("btn_half.png"));
		btn_half.setMargin(new Insets(3, 3, 3, 3));
		btn_half.setToolTipText("Launch nuclear missiles");
		btn_die = new JButton(new ImageIcon("btn_die.png"));
		btn_die.setMargin(new Insets(3, 3, 3, 3));
		btn_die.setToolTipText("Launch nuclear missiles");
		btn_GameStart = new JButton(new ImageIcon("btn_start_on.png"));
		btn_GameStart.setMargin(new Insets(3, 3, 3, 3));
		btn_GameStart.setToolTipText("Launch nuclear missiles");
	}

	public void btn_listener() {
		btn_call.addActionListener(this);
		btn_double.addActionListener(this);
		btn_half.addActionListener(this);
		btn_die.addActionListener(this);
		btn_GameStart.addActionListener(this);
	}

	public void updateYourMoney(String money) {
		label_YourMoney.setText(money);
		
	}

	public void updateMyMoney(String money) {
		label_MyMoney.setText(money);
		
	}

	public void updateBettingMoney(String money) {
		label_betting.setText(money);
	}

	public void updateGameMoney(String money) {
		label_AllGameMoney.setText(money);
		
	}

	public void updateYourState(String state) {
		if(state.equals(CODE_KEY.CODE_CALL)){
			label_YourState.setText("코르콜");
		}
		else if(state.equals(CODE_KEY.CODE_DIE)){
			label_YourState.setText("암 다이");
		}
		else if(state.equals(CODE_KEY.CODE_DOUBLE)){
			label_YourState.setText("따아블");
		}
		else if(state.equals(CODE_KEY.CODE_HALF)){
			label_YourState.setText("하아프");
		}
		else{
			label_YourState.setText(state);
		}
		
	}

	public void updateMyState(String state) {
		if(state.equals(CODE_KEY.CODE_CALL)){
			label_MyState.setText("코르콜");
		}
		else if(state.equals(CODE_KEY.CODE_DIE)){
			label_MyState.setText("암 다이");
		}
		else if(state.equals(CODE_KEY.CODE_DOUBLE)){
			label_MyState.setText("따아블");
		}
		else if(state.equals(CODE_KEY.CODE_HALF)){
			label_MyState.setText("하아프");
		}else{
			label_MyState.setText(state);
		}
	}
	public void updateStartButton(boolean flag){
		btn_GameStart.setEnabled(flag);
		if(flag){
			btn_GameStart.setIcon(new ImageIcon("btn_start_on.png"));
		}else{
			btn_GameStart.setIcon(new ImageIcon("btn_start_off.png"));
		}
		repaint();
	}
	public void updateAllButton(boolean flag){
			btn_call.setEnabled(flag);
			btn_double.setEnabled(flag);
			btn_half.setEnabled(flag);
			btn_die.setEnabled(flag);
	}
	public void updateYourCardImage(int cardNum1, int cardNum2) {
		MyPanel myPanel;
		String imgURL1 = "img_card_" + cardNum1 + ".png";
		String imgURL2 = "img_card_" + cardNum2 + ".png";
		Image img1 = Toolkit.getDefaultToolkit().getImage(imgURL1);
		Image img2 = Toolkit.getDefaultToolkit().getImage(imgURL2);
		
			ArrayList<ImageSet> list = panel_plyer1.setList;
			ImageSet set = list.get(1);
			set.img = img1;
			list.set(1, set);
			
			set = list.get(2);
			set.img = img2;
			list.set(2, set);
			panel_plyer1.repaint();
		repaint();
	}
	public void updateMyCardImage( int cardNum1, int cardNum2) {
		MyPanel myPanel;
		String imgURL1 = "img_card_" + cardNum1 + ".png";
		String imgURL2 = "img_card_" + cardNum2 + ".png";
		Image img1 = Toolkit.getDefaultToolkit().getImage(imgURL1);
		Image img2 = Toolkit.getDefaultToolkit().getImage(imgURL2);
//		if(playerNum==0){
//			ArrayList<ImageSet> list = panel_plyer1.setList;
//			ImageSet set = list.get(1);
//			set.img = img1;
//			list.set(1, set);
//			set = list.get(2);
//			set.img = img1;
//			list.set(2, set);
//			panel_plyer1.repaint();
//		}else{
			System.out.println("else");
			ArrayList<ImageSet> list = panel_plyer2.setList;
			ImageSet set = list.get(1);
			set.img = img1;
			list.set(1, set);
			
			set = list.get(2);
			set.img = img2;
			list.set(2, set);
			panel_plyer2.repaint();
//		}
		repaint();
		
	}

	public void launchTest() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		label_list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
				ImageIcon im = new ImageIcon("img_listlist.png");
				JLabel l = new JLabel(im);
				JFrame f =new JFrame();
				f.setBounds(950,30,600,1000);
				f.add(l);
				f.setVisible(true);
				 
				
			}
		});
//		// // frame은 기본적으로 BorderLayout이다.
//		btn_msgSend.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				sendMsg();
//			}
//		});


		p_right.setLayout(new BorderLayout());
		p_right.add(BorderLayout.NORTH, label_list);
		image_combi = new ImageIcon("img_main.png");
		label_combi.setIcon(image_combi);
		p_right.add(BorderLayout.CENTER, label_combi);

		p_left.setLayout(new GridLayout(5, 0));

		
		new ImgPosition();
		ArrayList<ImageSet> setList = new ArrayList<>();
		setList.add(new ImageSet(Toolkit.getDefaultToolkit().getImage("img_player.png"), ImgPosition.position_Yourimg));
		setList.add(new ImageSet(Toolkit.getDefaultToolkit().getImage("img_card_-1.png"), ImgPosition.position_Yourcard1));
		setList.add(new ImageSet(Toolkit.getDefaultToolkit().getImage("img_card_-1.png"), ImgPosition.position_Yourcard2));
		panel_plyer1 = new MyPanel(setList);
		ArrayList<ImageSet> setList2 = new ArrayList<>();
		setList2.add(new ImageSet(Toolkit.getDefaultToolkit().getImage("img_player.png"), ImgPosition.position_Myimg));
		setList2.add(new ImageSet(Toolkit.getDefaultToolkit().getImage("img_card_-1.png"), ImgPosition.position_Mycard1));
		setList2.add(new ImageSet(Toolkit.getDefaultToolkit().getImage("img_card_-1.png"), ImgPosition.position_Mycard2));
		panel_plyer2 = new MyPanel(setList2);
		
		Panel panel_betting = new Panel();
		Panel panel_button = new Panel();
		Label label_MyNickName = new Label();
		Label label_YourNickName = new Label();
		
		label_betting = new MyLabel(30);
		label_betting.setAlignment(Label.CENTER);
		label_betting.setText("100 $");
		
		label_MyMoney = new Label();
		Label label_MyMoney_1 = new Label();
		label_MyMoney_1.setPreferredSize(new Dimension(50, 100));
		label_YourMoney = new Label();
		Label label_YourMoney_1 = new Label();
		Label label_YourMoney_2 = new Label();
		label_YourMoney_1.setPreferredSize(new Dimension(100, 100));
		label_YourMoney_2.setText("Player1's");
		label_YourMoney_2.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		
		panel_button.add(btn_call);
		panel_button.add(btn_double);
		panel_button.add(btn_half);
		panel_button.add(btn_die);
		panel_button.add(btn_GameStart);

		panel_betting.setLayout(new BorderLayout());
		panel_betting.add(BorderLayout.NORTH, panel_button);
		panel_betting.add(BorderLayout.SOUTH, label_betting);

		panel_plyer1.setBackground(Color.lightGray);
		panel_plyer2.setBackground(Color.lightGray);

		label_YourMoney.setPreferredSize(new Dimension(350, 100));
		label_YourMoney.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		label_YourMoney.setText("50000$");

		label_MyMoney.setPreferredSize(new Dimension(200, 100));
		label_MyMoney.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		label_MyMoney.setText("50000$");
		
		label_YourNickName.setBackground(Color.white);
		label_YourNickName.setText("    Your Name  ");

		label_MyNickName.setBackground(Color.white);
		label_MyNickName.setBounds(200, 2, 100, 100);
		label_MyNickName.setText("      My Name     ");


		panel_plyer1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_plyer1.add(label_YourNickName);
		panel_plyer1.add(label_YourMoney_1);
		panel_plyer1.add(label_YourMoney_2);
		panel_plyer1.add(label_YourMoney);

		panel_plyer2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_plyer2.add(panel_betting);
		panel_plyer2.add(label_MyNickName);
		panel_plyer2.add(label_MyMoney_1);
		panel_plyer2.add(label_MyMoney);
		JLabel jjj = new JLabel("         ");
		jjj.setFont(new Font("궁서", Font.PLAIN, 50));;
		jjj.setBackground(Color.black);
		jjj.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				updateMyCardImage( card1Num, card2Num);
			}
		});
		panel_plyer2.add(jjj);
		
		
		p_centerLine_1.setBackground(new Color(00, 64, 00));
		p_centerLine_2.setBackground(new Color(00, 64, 00));
		p_centerLine_3.setBackground(new Color(00, 64, 00));
		
		//중앙 라인 글씨
		label_MyState.setPreferredSize(new Dimension(300, 100));
		label_MyState.setForeground(Color.yellow);
		label_MyState.setAlignment(Label.CENTER);
		label_MyState.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		label_YourState.setPreferredSize(new Dimension(300, 100));
		label_YourState.setAlignment(Label.CENTER);
		label_YourState.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		label_AllGameMoney.setPreferredSize(new Dimension(600, 200));
		label_AllGameMoney.setAlignment(Label.CENTER);
		label_AllGameMoney.setText("준비해 준비해");
		label_AllGameMoney.setFont(new Font("맑은 고딕", Font.PLAIN,50));

		p_centerLine_1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_centerLine_1.add(label_YourState);
		p_centerLine_2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_centerLine_2.add(label_AllGameMoney);
		p_centerLine_3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p_centerLine_3.add(label_MyState);
		
		

		p_left.add(panel_plyer1);
		p_left.add(p_centerLine_1);
		p_left.add(p_centerLine_2);
		p_left.add(p_centerLine_3);
		p_left.add(panel_plyer2);
		add(BorderLayout.EAST, p_right);
		add(BorderLayout.CENTER, p_left);

	}
	public void setCombi(int card1, int card2){
		int c1;
		int c2;
		
		if(card1<card2){
			c1 = card1;
			c2 = card2;
		}else{
			c1 = card2;
			c2 = card1;
		}
		
		if(c1==13&& c2==18){
			image_combi = new ImageIcon("img_38.png");
			label_combi.setIcon(image_combi);
		}else if(c1==11 && c2==13){
			//광땡
			image_combi = new ImageIcon("img_18.png");
			label_combi.setIcon(image_combi);
		}else if(c1==11 && c2==18){
			//광땡
			image_combi = new ImageIcon("img_18.png");
			label_combi.setIcon(image_combi);
		}else if(c1==c2){
			image_combi = new ImageIcon("img_1010.png");
			label_combi.setIcon(image_combi);
		}
		
		//알리
		else if((c1%10)==1&&(c2%10)==2){
			image_combi = new ImageIcon("img_12.png");
			label_combi.setIcon(image_combi);
		}else if((c1%10)==2&&(c2%10)==1){
			image_combi = new ImageIcon("img_12.png");
			label_combi.setIcon(image_combi);
		}
		
		//독사
		else if((c1%10)==1&&(c2%10)==4){
			image_combi = new ImageIcon("img_14.png");
			label_combi.setIcon(image_combi);
		}else if((c1%10)==4&&(c2%10)==1){
			image_combi = new ImageIcon("img_14.png");
			label_combi.setIcon(image_combi);
		}
		
		//구삥
		else if((c1%10)==1&&(c2%10)==9){
			image_combi = new ImageIcon("img_91.png");
			label_combi.setIcon(image_combi);
		}else if((c1%10)==9&&(c2%10)==1){
			image_combi = new ImageIcon("img_91.png");
			label_combi.setIcon(image_combi);
		}
		
		//장삥
		else if((c1%10)==1&&(c1%10)==10){
			image_combi = new ImageIcon("img_101.png");
			label_combi.setIcon(image_combi);
		}else if((c1%10)==10&&(c2%10)==1){
			image_combi = new ImageIcon("img_101.png");
			label_combi.setIcon(image_combi);
		}
		
		//장사
		else if((c1%10)==10&&(c2%10)==4){
			image_combi = new ImageIcon("img_104.png");
			label_combi.setIcon(image_combi);
		}else if((c1%10)==4&&(c2%10)==10){
			image_combi = new ImageIcon("img_104.png");
			label_combi.setIcon(image_combi);
		}
		//세륙
		else if((c1%10)==4&&(c2%10)==6){
			image_combi = new ImageIcon("img_46.png");
			label_combi.setIcon(image_combi);
		}else if((c1%10)==6&&(c2%10)==4){
			image_combi = new ImageIcon("img_46.png");
			label_combi.setIcon(image_combi);
		}
		//끗
		else {
			image_combi = new ImageIcon("img_00.png");
			label_combi.setIcon(image_combi);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(btn_call)) {
			
			
			allGameMoney = allGameMoney+bettingMoney;
			myMoney = myMoney-bettingMoney;
			
			StringBuilder strbuilder = new StringBuilder();
			strbuilder.append(CODE_KEY.CODE_GAME);
			strbuilder.append("_");
			strbuilder.append(CODE_KEY.CODE_CALL);
			strbuilder.append("_");
			strbuilder.append(bettingMoney);
			strbuilder.append("_");
			strbuilder.append(allGameMoney);
			strbuilder.append("_");
			strbuilder.append(myMoney);
			strbuilder.append("_");
			strbuilder.append(playerNumber);
			updateYourState("내턴 내턴");
			//1. 버튼 비활성화
			setEnableButton(false);
			//2. 판돈 업뎃
			updateGameMoney("판돈 "+allGameMoney);
			//3. 내돈 업뎃
			updateMyMoney(myMoney+"");
//			updateMyCardImage(1, 1, 1);
			updateMyState("코오올");
			updateBettingMoney(bettingMoney+"");
			sendCode(strbuilder.toString());
		} else if (e.getSource().equals(btn_die)) {
			updateMyState("다이");
			StringBuilder strbuilder = new StringBuilder();
			strbuilder.append(CODE_KEY.CODE_GAME);
			strbuilder.append("_");
			strbuilder.append(CODE_KEY.CODE_DIE);
			strbuilder.append("_");
			strbuilder.append("0");
			strbuilder.append("_");
			strbuilder.append((allGameMoney));
			strbuilder.append("_");
			strbuilder.append(myMoney);
			strbuilder.append("_");
			strbuilder.append(playerNumber);
			sendCode(strbuilder.toString());
		} else if (e.getSource().equals(btn_double)) {
			bettingMoney = bettingMoney*2;
			allGameMoney = allGameMoney+bettingMoney;
			myMoney = myMoney-bettingMoney;
			StringBuilder strbuilder = new StringBuilder();
			strbuilder.append(CODE_KEY.CODE_GAME);
			strbuilder.append("_");
			strbuilder.append(CODE_KEY.CODE_DOUBLE);
			strbuilder.append("_");
			strbuilder.append(bettingMoney);
			strbuilder.append("_");
			strbuilder.append((allGameMoney));
			strbuilder.append("_");
			strbuilder.append(myMoney);
			strbuilder.append("_");
			strbuilder.append(playerNumber);
			updateYourState("내턴 내턴");
			//1. 버튼 비활성화
			setEnableButton(false);
			//2. 판돈 업뎃
			updateGameMoney("판돈 "+allGameMoney+"");
			//3. 내돈 업뎃
			updateMyMoney(myMoney+"");
//			updateMyCardImage(1, 1, 1);
			updateMyState("따아당");
			updateBettingMoney(bettingMoney+"");
			sendCode(strbuilder.toString());
		} else if (e.getSource().equals(btn_half)) {
			bettingMoney = allGameMoney/2;
			allGameMoney = allGameMoney+bettingMoney;
			myMoney = myMoney-bettingMoney;
			StringBuilder strbuilder = new StringBuilder();
			strbuilder.append(CODE_KEY.CODE_GAME);
			strbuilder.append("_");
			strbuilder.append(CODE_KEY.CODE_DOUBLE);
			strbuilder.append("_");
			strbuilder.append(bettingMoney);
			strbuilder.append("_");
			strbuilder.append((allGameMoney));
			strbuilder.append("_");
			strbuilder.append(myMoney);
			strbuilder.append("_");
			strbuilder.append(playerNumber);
			updateYourState("내턴 내턴");
			//1. 버튼 비활성화
			setEnableButton(false);
			//2. 판돈 업뎃
			updateGameMoney("판돈 "+allGameMoney+"");
			//3. 내돈 업뎃
			updateMyMoney(myMoney+"");
//			updateMyCardImage(1, 1, 1);
			updateMyState("하알프");
			updateBettingMoney(bettingMoney+"");
			sendCode(strbuilder.toString());
		} else if (e.getSource().equals(btn_GameStart)) {
			allGameMoney=200;
			bettingMoney=0;
			
					
			label_AllGameMoney.setText("드루와 드루와");
			btn_GameStart.setEnabled(false);
			label_MyState.setText("암 레디");
			updateStartButton(false);
			sendCode(CODE_KEY.CODE_GAMESTART+"_"+playerNumber+"_"+myMoney);
		}
	}

	public void sendCode(String code) {
		thread.send(code);
		synchronized (thread) {
			thread.notify();
		}
	}
	
	public void setEnableButton(boolean enable) {
		btn_call.setEnabled(enable);
		btn_double.setEnabled(enable);
		btn_half.setEnabled(enable);
		btn_die.setEnabled(enable);
	}

//	public void sendMsg() {
//		if (!tf.getText().equals("")) {
//			String str = tf.getText();
//			thread.send(tf.getText());
//			setMessage(str);
//		}
//		tf.setText("");
//		tf.requestFocus();
//	}

//	public void setMessage(String message) {
//		ta.append(message);
//		ta.append("\n");
//	}

}
