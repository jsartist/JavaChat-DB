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
			System.out.println("Server Socket�� �������� �ʾҽ��ϴ�.\n");
		}
	}

	private void userAccept() {
		int trueNumber = 5;
		try {
			while(trueNumber == 5) {
				socket = serversocket.accept();
				System.out.println("����");
				new UserStart(socket, connectuser, jdbc).start();
			}
		}
		catch(Exception e) {
			System.out.println("accept�� �����ϴµ� ������ �߻��Ͽ����ϴ�.");
			trueNumber = 0;
		}
	}
	
	public void startServer() {
		try {
			serverStart();
			userAccept();
		}
		catch(Exception e) {
			System.out.println("TCP������ ���۽�Ű�� ���߽��ϴ�.");
		}
	}
}
