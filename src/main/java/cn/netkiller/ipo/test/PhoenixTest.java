package cn.netkiller.ipo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhoenixTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		try {
//			Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
		String url = "jdbc:phoenix:123.207.61.225:2181";
		// String url =
		// "jdbc:phoenix:41.byzoro.com,42.byzoro.com,43.byzoro.com:2181";
		Connection conn = DriverManager.getConnection(url);
		
		Statement stmt = conn.createStatement();
		
		stmt.executeUpdate("create table test (mykey integer not null primary key, mycolumn varchar)");
		stmt.executeUpdate("upsert into test values (1,'Hello')");
		stmt.executeUpdate("upsert into test values (2,'World!')");
		conn.commit();
		
		Statement statement = conn.createStatement();
		String sql = "select count(1) from test";
		long time = System.currentTimeMillis();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			int count = rs.getInt("num");
			System.out.println("row count is " + count);
		}
		long timeUsed = System.currentTimeMillis() - time;
		System.out.println("time " + timeUsed + "mm");

		rs.close();
		statement.close();
		conn.close();
	}

}
