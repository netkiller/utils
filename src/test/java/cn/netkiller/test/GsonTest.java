package cn.netkiller.test;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		String line = "{\"@timestampGMT8\":\"2017-12-04T00:00:02+08:00\",\"@reqstatus\":\"200\",\"@clientip\":\"180.76.181.191\",\"@url\":\"/1.gif\",\"@serverip\":\"192.168.8.177\",\"@body_bytes_sent\":35,\"@bytes_sent\":266,\"@refer\r\n" + "		er\": \"https://www.gwfx.com/lp56_a_cg_011zq_v3.html?utm_source=360daohang&utm_medium=ad&utm_campaign=DH-LP56V3&utm_content=sgz&utm_term=w\",\"@params\": \"prevUrl=https%3A%2F%2Fhao.360.cn%2F%3Fsrc%3Dlm%26ls%3Dn4514002b9a&sessionId=6c7b2b48-2a15-4aad-9b18-2e1\r\n" + "		0f4365194&businessPlatform=1&userId=C7C85C8B76E0000197739E701E4B2A00&platformVersion=V2.1.1&deviceId=&behaviorType=1&platformName=%E5%A4%96%E6%B1%87%E7%BD%91%E7%AB%99&platformType=0&logType=1&advisoryType=&utmctr=(direct)&utmccn=(direct)&utmcct=&utmcmd=\r\n" + "		(none)&utmcsr=(direct)&utmctr2=(direct)&utmccn2=(direct)&utmcct2=&utmcmd2=(none)&utmcsr2=(direct)&eventCategory=&eventAction=&eventLabel=&behaviorDetail=&dates=1512316802699&\",\"@browser\": \"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like G\r\n" + "		ecko\"}";
		Map<String, String> map = gson.fromJson(line, LinkedHashMap.class);
		System.out.println(line);
		System.out.println(map.toString());
	}
}
