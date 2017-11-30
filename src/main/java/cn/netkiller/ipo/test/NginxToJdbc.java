package cn.netkiller.ipo.test;

import java.util.Map;
import java.util.LinkedHashMap;

import cn.netkiller.ipo.Config;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputJdbc;
import cn.netkiller.ipo.output.OutputStdout;
import cn.netkiller.ipo.process.DropProcess;
import cn.netkiller.ipo.process.NginxAccessProcess;
import cn.netkiller.ipo.process.Process;

public class NginxToJdbc {
	public static void main(String[] args) {
		// TODO code application logic here
		System.out.println("Starting...");

		Config config = new Config();

		System.out.println(config.get("sss"));

		FileInput fi = new FileInput(config.get("input.file"));
		// System.out.print(fi.readLine());

		Input input = new Input();
		input.add(fi);

		Process process = new Process();
		process.add(new NginxAccessProcess());
		process.add(new DropProcess("\"logType\":\"1\""));

		Output output = new Output();
		output.add(new OutputStdout());

		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("logType", "logType");
		map.put("deviceId", "deviceId");

		OutputJdbc jdbc = new OutputJdbc("stat_app_log", map);
		jdbc.setDriver("com.mysql.cj.jdbc.Driver");
		jdbc.setUrl("jdbc:mysql://123.207.61.25:3306/test?useSSL=false");
		jdbc.setUsername("test");
		jdbc.setPassword("123456");

		output.add(jdbc);

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setBatch(10);
		ipo.launch();
	}
}
