package cn.netkiller.ipo.output;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OutputJdbc implements OutputInterface {

	private Connection connection = null;
	private Statement stmt = null;

	private String driver;
	private String url;
	private String username;
	private String password;
	private String table;

	private Map<String, String> map = new LinkedHashMap<String, String>();

	private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

	public OutputJdbc(String table, Map<String, String> map) {
		this.table = table;
		this.map = map;
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

			Class.forName(this.driver).newInstance();
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	@Override
	public void write(String output) {
		// System.out.println(output);

		@SuppressWarnings("unchecked")
		Map<String, String> source = gson.fromJson(output, LinkedHashMap.class);

		try {
			List<String> valuesList = new ArrayList<String>();
			for (String value : map.values()) {
				if (source.containsKey(value)) {
					valuesList.add(source.get(value));
				} else {
					valuesList.add("");
				}
			}
			String fields = StringUtils.join(map.keySet(), "`,`");
			String values = StringUtils.join(valuesList, "\",\"");

			String sql = String.format("INSERT INTO `%s`(`%s`) value(\"%s\")", this.table, fields, values);
			System.out.println(sql);
			this.stmt = this.connection.createStatement();
			this.stmt.execute(sql);
			this.stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() {
		try {

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
