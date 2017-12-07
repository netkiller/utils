package cn.netkiller.ipo.process.json;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.netkiller.ipo.process.ProcessInterface;

public class JsonValueLength implements ProcessInterface {
	private final static Logger logger = LoggerFactory.getLogger(JsonValueLength.class);
	private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	private int maxLength = 0;

	public JsonValueLength(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public String run(String line) {

		Map<String, String> map = gson.fromJson(line, new TypeToken<Map<String, String>>() {
		}.getType());
		for (String key : map.keySet()) {
			if (map.get(key).length() > this.maxLength) {
				map.put(key, map.get(key).substring(0, this.maxLength));
			}
		}
		logger.debug("{}", map);
		return gson.toJson(map);
	}

}
