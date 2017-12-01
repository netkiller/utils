package cn.netkiller.ipo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private Properties properties = new Properties();

	public Configuration() {

//		System.out.println("Working Directory = " + System.getProperty("user.dir"));

		try {
			properties.load(new FileInputStream("ipo.properties"));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public String get(String key) {
		return properties.getProperty(key);
	}

}
