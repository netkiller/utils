package cn.netkiller.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.input.QueueInput;
import cn.netkiller.ipo.output.QueueOutput;
import cn.netkiller.ipo.process.string.StringReplace;
import cn.netkiller.ipo.util.SqlUtil;
import cn.netkiller.ipo.Process;

public class Test {

	public static void main(String[] args) {

		// System.out.println(StringEscapeUtils.escapeJson("{\"name\":\"Neo\"}"));
		//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 10);
		map.put("name", "neo's");
		// map.put("age", null);
		// map.put("json", "{\"name\":\"Neo\"}".replace("\\", "\\\\"));
		// // URLEncoder.encode(address,"UTF-8")
		// map.put("test", StringEscapeUtils.escapeJson("{\"name\":\"Neo\"}"));

		// map.put("obj", new Object() {
		// String name = "Chen";
		// });
		//
		String s = SqlUtil.join("insert", "test", map);
		System.out.println(s);

		String test = "永旺华南商业有限公司yong'wang东湖店";
		System.out.println(test.replace("\'", "\\'"));
		// System.out.println(test.replace("\"now()\"", "now()"));

	}

}
