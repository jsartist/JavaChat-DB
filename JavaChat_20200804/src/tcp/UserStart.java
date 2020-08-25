package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;

public class UserStart extends Thread {
	private Socket socket;
	private ConnectUser connectuser;
	private DataInputStream dis;
	private DataOutputStream dos;
	private DBConectClass jdbc;
	private DBInsert insert;
	private DBSelect select;

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
		try {
			String check = dis.readUTF();
			if( check.equals("Sign Up")) {
				SignUp();
			}
			else {

			}
		}
		catch(Exception e) {

		}

	}

	private void SignUp() {
		try {
			int checkNumber = 5;
			while(checkNumber == 5) {
				String infomation = dis.readUTF();
				if(infomation.split("/")[1].equals("duplicate")) {
					duplicateCheck(infomation.split("/")[0]);
				}
				insert = new DBInsert(infomation, jdbc);
				
			}

		}
		catch(Exception e) {

		}
	}
	
	private void duplicateCheck(String infomation) {
		select = new DBSelect(jdbc);
		
	}

}
