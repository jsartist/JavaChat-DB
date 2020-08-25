package tcp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class TcpServer {
	private int port;
	private ServerSocket serversocket;
	private Socket socket;
	private ConnectUser connectuser;
	private DBConectClass jdbc;
	
	public TcpServer() {
		this.serversocket = null;
		socket = new Socket();
		jdbc = new DBConectClass();
		Connection con = jdbc.getConnection();
	}
	
	private void setPort() {
		try {
			File file = new File("D:\\DB\\port.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			this.port = Integer.parseInt(br.readLine());
			System.out.println("포트 설정이 완료되었습니다. 현재 설정된 포트는 " + this.port + "입니다.");
		}
		catch(Exception e) {
			System.out.println("파일을 불러오지 못하였습니다.");
			System.exit(1);
		}
	}

	private void serverStart() {
		try {
			serversocket = new ServerSocket(port);
			System.out.println("Server Socket이 생성되었습니다.");
		}
		catch(Exception e) {
			System.out.println("Socket 오류가 발생하여 Server Socket을 생성하지 못하였습니다.\n");
			System.exit(1);
		}
	}

	private void countConnectUser() {
		connectuser = new ConnectUser();
		connectuser.start();
		System.out.println("countConnectUser 스레드를 실행합니다.");
	}
	
	private void userAccept() {
		int trueNumber = 5;
		try {
			while(trueNumber == 5) {
				System.out.println("유저를 기다리고 있습니다.");
				socket = serversocket.accept();
				System.out.println("유저가 접속하였습니다. 스레드를 실행합니다.");
				new UserStart(socket, connectuser, jdbc).start();
			}
		}
		catch(Exception e) {
			System.out.println("accept를 실행하는데 문제가 발생하였습니다.");
			trueNumber = 0;
			System.exit(1);
		}
	}
	
	public void startServer() {
			setPort();
			serverStart();
			countConnectUser();
			userAccept();
	}
}
