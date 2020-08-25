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
			System.out.println("��Ʈ ������ �Ϸ�Ǿ����ϴ�. ���� ������ ��Ʈ�� " + this.port + "�Դϴ�.");
		}
		catch(Exception e) {
			System.out.println("������ �ҷ����� ���Ͽ����ϴ�.");
			System.exit(1);
		}
	}

	private void serverStart() {
		try {
			serversocket = new ServerSocket(port);
			System.out.println("Server Socket�� �����Ǿ����ϴ�.");
		}
		catch(Exception e) {
			System.out.println("Socket ������ �߻��Ͽ� Server Socket�� �������� ���Ͽ����ϴ�.\n");
			System.exit(1);
		}
	}

	private void countConnectUser() {
		connectuser = new ConnectUser();
		connectuser.start();
		System.out.println("countConnectUser �����带 �����մϴ�.");
	}
	
	private void userAccept() {
		int trueNumber = 5;
		try {
			while(trueNumber == 5) {
				System.out.println("������ ��ٸ��� �ֽ��ϴ�.");
				socket = serversocket.accept();
				System.out.println("������ �����Ͽ����ϴ�. �����带 �����մϴ�.");
				new UserStart(socket, connectuser, jdbc).start();
			}
		}
		catch(Exception e) {
			System.out.println("accept�� �����ϴµ� ������ �߻��Ͽ����ϴ�.");
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
