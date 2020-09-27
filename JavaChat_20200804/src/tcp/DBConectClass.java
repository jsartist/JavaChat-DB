package tcp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConectClass {
	private File file;
	private String driver;
	private String info[];
	private String url;
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	
	public Connection getConnection() {
		this.driver = "org.mariadb.jdbc.Driver";
		this.info = new String[4];
		try {
			FileStream();
			setURL();
			Class.forName(driver);
			DriverManager.getConnection(url, info[2], info[3]);
			System.out.println("DB�� ����Ǿ����ϴ�.");
		}
		catch(Exception e) {
			System.out.println("DB ���ῡ �����Ͽ����ϴ�.");
		}

		return con;
	}
	
	private void FileStream() {
		this.file = new File("D:\\DB\\info.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(this.file))){
			String line;
			int k = 0;
			while((line = br.readLine()) != null && k < 4){
				this.info[k] = line;
				k++;
			}
		}
		catch(Exception e) {
			
		}
	}
	
	private void setURL() {
		this.url = "jdbc:mariadb://" + info[0]  + ":" + info[1] + "/usus";
	}
	
	public void dbClose() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet�� �����ϴ� ���� ������ �߻��Ͽ����ϴ�.");
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("PrepareStatement�� �����ϴ� ���� ������ �߻��Ͽ����ϴ�.");
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Connection�� �����ϴ� ���� ������ �߻��Ͽ����ϴ�.");
			}
		}
	}
}
