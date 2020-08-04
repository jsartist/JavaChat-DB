package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class UserStart extends Thread {
	private Socket socket;
	private ConnectUser connectuser;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public UserStart(Socket socket, ConnectUser connectuser) {
		this.socket = socket;
		this.connectuser = connectuser;
	}
	
	private void inputOutputStream() {
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		}
		catch(Exception e) {
			System.out.println("��Ʈ�� ��ü�� �������� ���߽��ϴ�.");
		}
	}
	
	private void loginUser() {	
	}
}
