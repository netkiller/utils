package cn.netkiller.ipo.process;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NginxAccessProcess implements ProcessInterface {
	private String regex;

	public NginxAccessProcess() {
		this.regex = "([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}) - - \\[(.*)\\] \\\"(.*) HTTP(.+)\\\" ";
	}

	public NginxAccessProcess(String regex) {
		this.regex = regex;
	}

	@Override
	public String run(String line) {
		// throw new UnsupportedOperationException("Not supported yet.");
		Map<String, String> map = new HashMap<String, String>();
		Pattern pattern = Pattern.compile(this.regex);

		// 现在创建 matcher 对象
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {

			// System.out.println(String.format("%s, %s, %s",
			// matcher.group(1),matcher.group(2),matcher.group(3)) );
			// System.out.println("Group Length: " + matcher.group().length());

			if (matcher.group(3) != null && matcher.group(3).contains("?")) {
				// System.out.println("Found value: " + matcher.group(3));
				String[] param = matcher.group(3).split("\\?");
				String[] variables = param[1].split("&");
				for (String variable : variables) {
					String[] var = variable.split("=");
					if (var.length == 2) {
						// System.out.println(var[0] + "=" + var[1]);
						map.put(var[0], var[1]);
					} else {
						// System.out.println(var[0] + "=");
						map.put(var[0], "");
					}

				}

			}
		}
		// System.out.println(map.toString());
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

		return gson.toJson(map);
	}

}
