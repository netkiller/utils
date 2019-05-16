package cn.netkiller.test;

import java.util.LinkedList;
import java.util.Queue;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.input.QueueInput;
import cn.netkiller.ipo.output.QueueOutput;
import cn.netkiller.ipo.process.string.StringReplace;
import cn.netkiller.ipo.Process;

public class Test {

	public static void main(String[] args) {

		// System.out.println(StringEscapeUtils.escapeJson("{\"name\":\"Neo\"}"));
		//
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("id", 10);
		// map.put("name", "neo");
		// map.put("age", null);
		// map.put("json", "{\"name\":\"Neo\"}".replace("\\", "\\\\"));
		// // URLEncoder.encode(address,"UTF-8")
		// map.put("test", StringEscapeUtils.escapeJson("{\"name\":\"Neo\"}"));

		// map.put("obj", new Object() {
		// String name = "Chen";
		// });
		//
		// String s = SqlUtil.join("insert", "test", map);
		// System.out.println(s);

		// String test="INSERT INTO `lz_departments`(`dept_id`,`dept_code`,`dept_name`,`company_id`,`created_by`,`created_time`) value(\"22\",\"001\",\"广州分公司\",\"1\",\"import\",\"now()\");";
		// System.out.println(test.replace("now()", "aaa"));
		// System.out.println(test.replace("\"now()\"", "now()"));

	}

}
