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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JdbcOutput implements OutputInterface {

	private final static Logger logger = LoggerFactory.getLogger(JdbcOutput.class);
	private Connection connection = null;

	private String driver;
	private String url;
	private String username;
	private String password;
	private String table;

	private Map<String, String> map = new LinkedHashMap<String, String>();

	private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

	public JdbcOutput(String table, Map<String, String> map) {
		this.table = table;
		this.map = map;
	}

	protected void finalize() {
		System.out.println("in finalize");
	}

	public void setDriver(String driver) {
		this.driver = driver;
		logger.info("jdbc.driver {}", driver);
	}

	public void setUrl(String url) {
		this.url = url;
		logger.info("jdbc.url {}", url);
	}

	public void setUsername(String username) {
		this.username = username;
		logger.info("jdbc.username {}", username);
	}

	public void setPassword(String password) {
		this.password = password;
		logger.info("jdbc.password {}", "******");
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean open() {
		try {

			Class.forName(this.driver).newInstance();
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return false;

	}

	@Override
	public boolean write(Object tmp) {
		String output = (String) tmp;
		logger.info("Output {}", output);

		Map<String, String> source = gson.fromJson(output, new TypeToken<Map<String, String>>() {
		}.getType());

		logger.info("Output Map {}", source);

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
			logger.info("SQL {}", sql);
			Statement stmt = this.connection.createStatement();
			stmt.execute(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean close() {
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
