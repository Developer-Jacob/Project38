import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import DB.DAO;
import Model.User;

class Login extends Frame { // Frame = 기본 Container

	private static final long serialVersionUID = 1L;
	// Components
	Label lb_ID; // ID 레이블
	Label lb_PSW; // PW 레이블
	TextField tf_ID; // ID 필드
	TextField tf_PSW; // PW 필드
	Button bt_OK; // 로그인 버튼
	Button bt_SIGNUP;

	Dialog dlg_msg; // 팝업창
	Label msgLabel;

	// 초기화(Initialization)
	// components 인스턴스(객체) 생성 / 속성 할당 / container에 장착
	Login(String title) {
		super(title); // Frame(String title)을 호출한다.

		// Components 생성
		lb_ID = new Label("ID :", Label.RIGHT); // Label의 text정렬을 오른쪽으로.
		lb_PSW = new Label("Password :", Label.RIGHT); // 약 12개의 글자를 입력할 수 있는
														// TextField 생성.
		tf_ID = new TextField(12);
		tf_PSW = new TextField(12);
		tf_PSW.setEchoChar('*'); // 입력한 값 대신 '*'이 보이게 한다.

		tf_ID.setBackground(Color.white); // 배경색 지정
		tf_PSW.setBackground(Color.white); // 배경색 지정

		bt_OK = new Button("LOGIN"); // ok = new Button("OK");
		bt_SIGNUP = new Button("SIGNUP");
		
		dlg_msg = new Dialog(this, "로그인 메시지", false);
		dlg_msg.setSize(500, 70);
		dlg_msg.setLocation(50, 50);
		dlg_msg.setLayout(new FlowLayout());
		dlg_msg.setVisible(false);

		msgLabel = new Label("");
		dlg_msg.add(msgLabel);

		Button btnOk = new Button("OK");
		dlg_msg.add(btnOk);

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlg_msg.setVisible(false);
				dlg_msg.dispose();
				tf_ID.setText("");
				tf_PSW.setText("");
				tf_ID.requestFocus();
			}
		});

		// OK버튼과 TextField에 이벤트처리를 위한 Listener를 추가해준다.
		tf_ID.addActionListener(new EventHandler()); // ID field
		tf_PSW.addActionListener(new EventHandler()); // PW field
		bt_OK.addActionListener(new EventHandler()); // 로그인 버튼
		addWindowListener(new EventHandler());//
		// super.addWindowListener(new EventHandler()); //

		// component들을 container에 장착(add)
		// 레이아웃(배치) 방식을 설정
		setLayout(new FlowLayout()); // LayoutManager를 FlowLayout으로
		add(lb_ID); // ID Label // 생성한 Component들을 Frame에 포함시킨다.
		add(tf_ID); // ID field
		add(lb_PSW); // PW Label
		add(tf_PSW); // PW field
		add(bt_OK); // 로그인 버튼
		add(bt_SIGNUP);
		setSize(500, 200); // 크기설정
		setVisible(true); // Frame이 화면에 보이게 한다.
	}



	public static void main(String args[]) {
		Login f = new Login("Login");
	}

	class EventHandler implements ActionListener, WindowListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(bt_OK)){
				
			
			// TODO Auto-generated method stub
			String id = tf_ID.getText(); // tfId에 입력되어있는 text를 얻어온다.
			String password = tf_PSW.getText();
			
			DAO dao = new DAO();
			User u = dao.select(id, password);
			if(u== null){
				//id가 존재 하지 않습니다.
				msgLabel.setText("Invalid ID. Check your ID or PSW.");
				dlg_msg.setVisible(true);
			}else{
				msgLabel.setText("Welcom "+u.getNickName()+" Yours Money is :"+u.getMoney());
				dlg_msg.setVisible(true);
			}
			}
		}
//
//		public void actionPerformed(ActionEvent e) {
//			String id = tfId.getText(); // tfId에 입력되어있는 text를 얻어온다.
//			String password = tfPwd.getText();
//
//			// DB P/G'n 시작
//			HashMap<String, String> map = new HashMap<String, String>();
//
//			MemberDAO dao = new MemberDAO();
//			ArrayList<Member> members = new ArrayList<Member>();
//
//			try {
//				members = dao.getMembers();
//
//			} catch (SQLException se) {
//				System.out.println("예외처리 5: " + se.getMessage());
//				se.printStackTrace();
//			} catch (Exception ex) {
//				System.out.println("예외처리 6: " + ex.getMessage());
//				ex.printStackTrace();
//			}
//
//			for (int i = 0; i < members.size(); i++)
//				map.put(members.get(i).getId(), members.get(i).getPw());
//
//			if (checkId(id)) { // ID 유효성(validation) 점검 = OK !
//
//				if (!map.containsKey(id)) {
//					msgLabel.setText("입력하신 id는 존재하지 않습니다. 다시 입력해주세요.");
//					msgDlg.setVisible(true);
//					// System.out.println("입력하신 id는 존재하지 않습니다. 다시 입력해주세요.");
//
//				} else {
//					if (!(map.get(id)).equals(password)) {
//						msgLabel.setText("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
//						msgDlg.setVisible(true);
//						// System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
//					} else {
//						msgLabel.setText("id와 비밀번호가 일치합니다.");
//						msgDlg.setVisible(true);
//						// System.out.println("id와 비밀번호가 일치합니다.");
//					} // if
//				} // if
//			}
//			// DB P/G'n 끝
//
//			/*
//			 * if (!id.equals("javachobo")){ System.out.println(
//			 * "입력하신 id가 유효하지 않습니다. 다시 입력해 주세요."); // id를 다시 입력할 수 있도록, focus를
//			 * tfId로 옮긴다. tfId.requestFocus(); tfId.selectAll(); // tfId에 입력된
//			 * text가 선택되게 한다. } else if (!password.equals("asdf")) {
//			 * System.out.println("입력하신 비밀번호가 틀렸습니다. 다시 입력해 주시기 바랍니다.");
//			 * 
//			 * // id를 다시 입력할 수 있도록 focus를 tfId로 옮긴다. tfPwd.requestFocus();
//			 * tfPwd.selectAll(); } else { System.out.println( id +
//			 * "님, 성공적으로 로그인 되었습니다."); }
//			 */
//
//		}
//
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			e.getWindow().setVisible(false); // 창(윈도우 : Frme)을 닫다; 메모리상에서는 여전히
			e.getWindow().dispose(); // 메모리에서 제거
			System.exit(0); // 프로그램 정상 종료
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		
//	}
	}
}