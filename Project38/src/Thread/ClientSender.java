//package Thread;
//
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//import Client.ClientGUI;
//
//public class ClientSender extends Thread {
//	Socket socket;
//	DataOutputStream output;
//	String msg = "";
//	String name;
//
//	public ClientSender(String serverIp, String name) {
//		try {
//			this.name = name;
//			socket = new Socket(serverIp, 7776);
//			output = new DataOutputStream(socket.getOutputStream());
//			output.writeUTF(name);
//			System.out.println("대화방에 입장하였습니다.");
//		} catch (Exception e) {
//		}
//	}
//
//	public void send(String msg) {
//		this.msg = msg;
//	}
//
//	@Override
//	public void run() {
//		while (output != null) {
//			try {
//				synchronized (this) {
//					this.wait();
//				}
////				if (msg.equals(ClientGUI.CALL)) {
////					output.writeUTF(ClientGUI.CALL);
////				} else if (msg.equals(ClientGUI.DOUBLE)) {
////					output.writeUTF(ClientGUI.DOUBLE);
////				} else if (msg.equals(ClientGUI.MAX)) {
////					output.writeUTF(ClientGUI.MAX);
////				} else if (msg.equals(ClientGUI.DIE)) {
////					output.writeUTF(ClientGUI.DIE);
////				} else {
//					System.out.println(msg);
//					output.writeUTF("[" + name + "]" + msg);
////				}
//
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//			}
//		}
//	}
//}