package cn.netkiller.ipo.output;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OutputJdbc implements OutputInterface {

	private Connection connection = null;
	private Statement stmt = null;

	private String driver;
	private String url;
	private String username;
	private String password;

	public OutputJdbc() {

	}

	protected void finalize() {
		System.out.println("in finalize");
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void open() {
		try {
			Class.forName(this.driver);
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

		}

	}

	@Override
	public void write(String output) {
		try {
			this.stmt = this.connection.createStatement();
			this.stmt.execute("INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,STAT_CD) VALUES (1,'Lokesh','Gupta',5)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
