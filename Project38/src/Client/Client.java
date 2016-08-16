package Client;
import java.io.IOException;
import java.net.Socket;

import Thread.GameReceiver;
import Thread.GameSender;

public class Client {

	private Socket socket;

	String serverIp = "211.183.8.79";
	ClientGUI gui ;
	public static void main(String[] args) {
		String name = "";
		Client client = new Client();
		
//		client.reciverStart();
//		ClientSender sender = new ClientSender(client.serverIp, name);
//		sender.start();
		client.gameThreadStart(client);
	}
	public void gameThreadStart(Client client) {
		try {
			socket = new Socket(serverIp, 7776);
			GameSender gameSender = new GameSender(socket, client.serverIp);
			client.gui = new ClientGUI("",gameSender);
			GameReceiver gameReceiver = new GameReceiver(socket,gui);
			gameReceiver.start();
			gameSender.start();
		} catch (IOException e) {
		}
	}
//	public void reciverStart() {
//		try {
//			socket = new Socket(serverIp, 7777);
//			ClientReceiver clientReceiver = new ClientReceiver(socket,gui);
//			clientReceiver.start();
//		} catch (IOException e) {
//		}
//	}
}



