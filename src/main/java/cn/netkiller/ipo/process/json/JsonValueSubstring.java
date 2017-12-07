package cn.netkiller.ipo.process.json;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.netkiller.ipo.process.ProcessInterface;

public class JsonValueSubstring implements ProcessInterface {
	private final static Logger logger = LoggerFactory.getLogger(JsonValueSubstring.class);
	private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	Map<String, String> mapping = new LinkedHashMap<String, String>();

	public JsonValueSubstring(Map<String, String> mapping) {
		this.mapping = mapping;
	}

	@Override
	public String run(String line) {

		Map<String, String> map = gson.fromJson(line, new TypeToken<Map<String, String>>() {
		}.getType());
		for (String key : this.mapping.keySet()) {
			if (map.containsKey(key)) {
				int len = Integer.valueOf(this.mapping.get(key));
				if (map.get(key).length() > len) {
					map.put(key, map.get(key).substring(0, len));
				}

			}
		}
		logger.debug("{}", map);
		return gson.toJson(map);
	}

}
