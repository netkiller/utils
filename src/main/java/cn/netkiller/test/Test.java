package cn.netkiller.test;

public class Test {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here

		String string = new String("http://www.netkiller.cn");

		System.out.println("查找字符 . 第一次出现的位置: " + string.indexOf('.'));
		System.out.println("从第15个字符位置查找字符 . 出现的位置:" + string.indexOf('.', 15));

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

		// FileInput fi = new FileInput("/tmp/adobegc.log");
		//// System.out.print(fi.readLine());
		//
		// Input input = new Input();
		// input.add(fi);
		//// input.read();
		//
		// Output output = new Output();
		// output.add(new StdoutOutput());
		//
		// Process process = new Process();
		// process.add(new Replace("Hello","Netkiller "));
		// process.add(new Replace("Neo","<Neo>"));
		// process.add(new Replace("Tom","[Tom]"));
		//
		// InputProcessOutput ipo = new InputProcessOutput();
		// ipo.setInput(input);
		// ipo.setProcess(process);
		// ipo.setOutput(output);
		// ipo.launch();
	}

}
