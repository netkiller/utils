/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.test;

import java.util.HashMap;
import java.util.Map;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.string.Replace;
import cn.netkiller.ipo.util.SqlUtil;

/**
 *
 * @author neoch
 */
public class Test {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		System.out.println("Starting...");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 10);
		map.put("name", "neo");
		map.put("age", null);

		String s = SqlUtil.join("insert", "test", map);
		System.out.println(s);

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
