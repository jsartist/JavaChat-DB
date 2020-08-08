package tcp;

public class DBInsert {
	String infomation;
	DBConectClass jdbc;
	
	public DBInsert(String infomation, DBConectClass jdbc) {
		this.infomation = infomation;
		this.jdbc = jdbc;
	}
	
}