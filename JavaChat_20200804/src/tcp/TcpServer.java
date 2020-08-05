package tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer {
	private int port;
	private ServerSocket serversocket;
	private Socket socket;
	private ConnectUser connectuser;
	private DBConectClass jdbc;
	
	public TcpServer() {
		this.port = new Scanner(System.in).nextInt();
		this.serversocket = null;
		socket = new Socket();
		connectuser = new ConnectUser();
		connectuser.start();
	}

	private void serverStart() {
		try {
			serversocket = new ServerSocket(port);
		}
		catch(Exception e) {
			System.out.println("Server Socket이 생성되지 않았습니다.\n");
		}
	}

	private void userAccept() {
		int trueNumber = 5;
		try {
			while(trueNumber == 5) {
				socket = serversocket.accept();
				System.out.println("접속");
				new UserStart(socket, connectuser, jdbc).start();
			}
		}
		catch(Exception e) {
			System.out.println("accept를 실행하는데 문제가 발생하였습니다.");
			trueNumber = 0;
		}
	}
	
	public void startServer() {
		try {
			serverStart();
			userAccept();
		}
		catch(Exception e) {
			System.out.println("TCP서버를 동작시키지 못했습니다.");
		}
	}
}
