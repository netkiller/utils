package cn.netkiller.ipo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private Properties properties = new Properties();
	private String filename = "ipo.properties";

	public Configuration() {
		this.load();
	}

	public Configuration(String filename) {
		this.filename = filename;
		this.load();
	}

	public void load() {

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));

		try {
			properties.load(new FileInputStream(this.filename));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public String get(String key) {
		return properties.getProperty(key);
	}

	public boolean contains(String key) {
		return properties.contains(key);
	}
}
