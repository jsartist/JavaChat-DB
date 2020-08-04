package tcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConectClass {
	private String driver;
	private String ip;
	private int port;
	private String id;
	private String pw;
	private String url;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	public Connection getCon() {
		this.driver = "org.mariadb.jdbc.Driver";
		this.ip = new Scanner(System.in).nextLine();
		this.port = 3306;
		this.url = "jdbc:mariadb://" + ip  + ":" + port + "/usus";
		this.id = new Scanner(System.in).nextLine();
		this.pw = new Scanner(System.in).nextLine();
		try {
			Class.forName(driver);
			DriverManager.getConnection(url, id, pw);
			System.out.println("DB가 연결되었습니다.");
		}
		catch(Exception e) {
			System.out.println("DB 연결에 실패하였습니다.");
		}

		return con;
	}
	public void dbClose() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet을 종료하는 동안 오류가 발생하였습니다.");
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("PrepareStatement를 종료하는 동안 오류가 발생하였습니다.");
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Connection을 종료하는 동안 오류가 발생하였습니다.");
			}
		}
	}
}
