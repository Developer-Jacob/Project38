//package Thread;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//import Client.ClientGUI;
//import Model.CODE_KEY;
//
//public class ClientReceiver extends Thread {
//	Socket socket;
//	DataInputStream input;
//	String msg;
//	ClientGUI gui;
//	int money = 0;
//	public ClientReceiver(Socket socket,ClientGUI gui) {
//		this.socket = socket;
//		this.gui = gui;
//		try {
//			input = new DataInputStream(socket.getInputStream());
//		} catch (IOException e) {
//		}
//	}
//
//	@Override
//	public void run() {
//		while (input != null) {
//			try {
//				String str = input.readUTF();
//				System.out.println("Clinent msg : "+str);
//				String[] input = str.split("_");
//				money = Integer.valueOf(input[1]);
//				if (input[0].equals(CODE_KEY.CODE_CALL)) {
//					
//				} else if (input[0].equals(CODE_KEY.CODE_CALL)) {
//
//				} else if (input[0].equals(CODE_KEY.CODE_DOUBLE)) {
//
//				} else if (input[0].equals(CODE_KEY.CODE_MAX)) {
//
//				} else if (input[0].equals(CODE_KEY.CODE_DIE)) {
//
//				}
//				System.out.println();
//
//			} catch (IOException e) {
//			}
//		}
//	}
//}