package cn.netkiller.ipo.process.nginx;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.netkiller.ipo.process.ProcessInterface;

public class NginxAccessProcess implements ProcessInterface {
	private String regex;

	public NginxAccessProcess() {
		this.regex = "([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}) - - \\[(.*)\\] \\\"(.*) HTTP/(.+)\\\" ([0-9]{3}+) ([0-9]+) \\\"(.+)\\\" \\\"(.+)\\\"";
	}

	public NginxAccessProcess(String regex) {
		this.regex = regex;
	}

	@Override
	public String run(String line) {
		// throw new UnsupportedOperationException("Not supported yet.");
		Map<String, String> map = new LinkedHashMap<String, String>();
		Pattern pattern = Pattern.compile(this.regex);

		// 现在创建 matcher 对象
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			// log_format main '$remote_addr - $remote_user [$time_local] "$request"
			// ''$status $body_bytes_sent "$http_referer" ' '"$http_user_agent"
			// "$http_x_forwarded_for"';

			map.put("remote_addr", matcher.group(1));
			map.put("user", "");
			map.put("time_local", matcher.group(2));
			map.put("request", matcher.group(3));
			map.put("status", matcher.group(5));
			map.put("body_bytes_sent", matcher.group(6));
			map.put("http_referer", matcher.group(7));
			map.put("http_user_agent", matcher.group(8));
			// map.put("http_x_forwarded_for", matcher.group(7));

		}
		// System.out.println(map.toString());
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

		return gson.toJson(map);
	}

}
