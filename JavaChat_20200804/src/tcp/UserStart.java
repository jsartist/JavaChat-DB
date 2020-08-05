package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class UserStart extends Thread {
	private Socket socket;
	private ConnectUser connectuser;
	private DataInputStream dis;
	private DataOutputStream dos;
	private DBConectClass jdbc;
	
	public UserStart(Socket socket, ConnectUser connectuser, DBConectClass jdbc) {
		this.socket = socket;
		this.connectuser = connectuser;
		this.jdbc = jdbc;
		inputOutputStream();
	}
	
	private void inputOutputStream() {
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		}
		catch(Exception e) {
			System.out.println("스트림 객체를 생성하지 못했습니다.");
		}
	}
	
	private void loginUser() {	
		
	}
}
