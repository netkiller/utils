package cn.netkiller.ipo.process;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonValueSubstring implements ProcessInterface {
	private final static Logger logger = LoggerFactory.getLogger(JsonValueSubstring.class);
	private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	Map<String, String> mapping = new LinkedHashMap<String, String>();
	
	public JsonValueSubstring(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
	@Override
	public String run(String line) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = gson.fromJson(line, LinkedHashMap.class);
		for(String key : this.mapping.keySet()) {
			if(map.containsKey(key)) {
				map.put(key, map.get(key).substring(0, Integer.valueOf(this.mapping.get(key))));
			}
		}
		logger.debug("{}", map);
		return gson.toJson(map);
	}

}
