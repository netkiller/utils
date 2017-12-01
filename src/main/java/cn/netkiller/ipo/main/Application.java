package cn.netkiller.ipo.main;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.Configuration;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.input.Input;
import cn.netkiller.ipo.input.NginxAccessInput;
import cn.netkiller.ipo.output.Output;
import cn.netkiller.ipo.output.OutputJdbc;
import cn.netkiller.ipo.output.OutputStdout;
import cn.netkiller.ipo.process.IncludeProcess;
import cn.netkiller.ipo.process.Process;
import cn.netkiller.ipo.process.nginx.NginxAccessGetParameterProcess;

public class Application {

	private final static Logger logger = LoggerFactory.getLogger(Application.class);

	public Application() {
	}

	public static void main(String[] args) {

		Configuration config = new Configuration();

		String filename = String.format("%s%s%s%s%s", config.get("input.file.path"), File.separator, config.get("input.file.prefix"), config.get("input.file.filename"), config.get("input.file.suffix"));		
		NginxAccessInput nginxAccessInput = new NginxAccessInput(filename);

		Input input = new Input();
		input.add(nginxAccessInput);

		Process process = new Process();
		// process.add(new IncludeProcess("\"logType\":\"3\"")).add(new
		// IncludeProcess("\"businessPlatform\":\"3\""));
		process.add(new NginxAccessGetParameterProcess());

		Output output = new Output();
		// output.add(new OutputStdout());

		Map<String, String> map = new LinkedHashMap<String, String>();

		map.put("logType", "logType");
		map.put("deviceId", "deviceId");
		map.put("deviceType", "deviceType");
		map.put("userType", "userType");
		map.put("platformType", "platformType");
		map.put("platformName", "platformName");
		map.put("accountType", "accountType");
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
		// map.put("serverIp", ""); // 不需要
		// map.put("bodyBytesSent", ""); // 不需要
		// map.put("bytesSent", ""); // 不需要
		map.put("browser", "http_user_agent"); // user-agent
		// map.put("ctime","");
		// map.put("mtime","");

		// ALTER TABLE `stat_app_log` CHANGE COLUMN `accountType` `accountType` VARCHAR(255) NULL DEFAULT NULL COMMENT '账号类型：0迷你，1标准，2:VIP' AFTER `platformName`;
		// ALTER TABLE `stat_app_log` CHANGE COLUMN `browser` `browser` VARCHAR(255) NULL DEFAULT NULL COMMENT '浏览器信息' AFTER `bytesSent`;

		/*
		 * 
		 * "@timestampGMT8":"2017-10-01T00:00:05+08:00", "@reqstatus":"200", "@clientip":"117.136.88.94", "@url":"/1.gif", "@serverip":"192.168.8.177", "@body_bytes_sent":35, "@bytes_sent":266, "@referer": "-", "@params": "logType=3 &deviceId=44:c3:46:11:49:38 &deviceType=Android &platformType=GTS2 &accountType= &model=MHA-AL00 &userIp=null &deviceResolution=360*604 &platformVersion=1.5.1 &carrier=HUAWEI &eventAction=ClickTabbar &eventCategory=Main &eventLabel=broadcast &eventValue=broadcast &userType=3 &account=86133808 &netStatus=mobile &channel=fx0 &businessPlatform=1 &platformName=%E9%87%91%E9%81%93%E5%A4%96%E6%B1%87%E6%8A%95%E8%B5%84com.gwtsz .gts2 &idfa=864678032385641 &dates=1506787203858 &", "@browser": "okhttp/3.2.0"}
		 */

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
