package cn.netkiller.ipo.test;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedHashMap;

import cn.netkiller.ipo.Configuration;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputJdbc;
import cn.netkiller.ipo.process.Process;
import cn.netkiller.ipo.process.nginx.NginxAccessGetParameterProcess;

public class NginxToJdbc {
	private final static Logger logger = LoggerFactory.getLogger(NginxToJdbc.class);

	public static void main(String[] args) {

		Configuration config = new Configuration();

		String filename = String.format("%s%s%s%s%s", config.get("input.file.path"), File.separator, config.get("input.file.prefix"), config.get("input.file.filename"), config.get("input.file.suffix"));
		logger.info(config.get("output.jdbc.url"));
		logger.info(filename);
		FileInput fi = new FileInput(filename);

		Input input = new Input();
		input.add(fi);

		Process process = new Process();
		// process.add(new NginxAccessProcess());
		process.add(new NginxAccessGetParameterProcess());
		// process.add(new ExcludeProcess("\"logType\":\"1\""));

		Output output = new Output();
		// output.add(new OutputStdout());

		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("logType", "logType");
		map.put("deviceId", "deviceId");
		map.put("deviceType", "deviceType");
		map.put("userType", "userType");
		map.put("platformType", "platformType");
		map.put("platformName", "platformName");
		// map.put("accountType", "accountType");
		map.put("account", "account");
		map.put("channel", "channel");
		map.put("businessPlatform", "businessPlatform");
		map.put("carrier", "carrier");
		map.put("model", "model");
		map.put("platformVersion", "platformVersion");
		map.put("deviceResolution", "deviceResolution");
		map.put("netStatus", "netStatus");
		map.put("idfa", "idfa");
		map.put("eventCategory", "eventCategory");
		map.put("eventAction", "eventAction");
		map.put("eventLabel", "eventLabel");
		map.put("eventValue", "eventValue");
		//
		map.put("userIp", "remote_addr");
		map.put("operationTime", "time_local");
		map.put("url", "request_uri");

		OutputJdbc jdbc = new OutputJdbc("stat_app_log", map);
		jdbc.setDriver(config.get("output.jdbc.driver"));
		jdbc.setUrl(config.get("output.jdbc.url"));
		jdbc.setUsername(config.get("output.jdbc.username"));
		jdbc.setPassword(config.get("output.jdbc.password"));

		output.add(jdbc);

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setBatch(10);
		ipo.launch();
	}
}
