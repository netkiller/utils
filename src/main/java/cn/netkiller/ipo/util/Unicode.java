package cn.netkiller.ipo.util;

public class Unicode {
	public Unicode() {
		// TODO Auto-generated constructor stub
	}

	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);

			// 追加成string
			string.append((char) data);
		}

		return string.toString();
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("file.encoding"));
		// 方法二：中文操作系统中打印GBK
		// String string = "佛山市南海区123华泰ABC精密abc机械有限公司消防维保􁵪􁻠􁴹􄲀􀞜􀨨,";
		
		String string = "􁵪􁻠􁴹􄲀􀞜􀨨";
		System.out.println(string2Unicode("􁵪"));
		System.out.println(string2Unicode(string));

		// String regex = "[\u4e00-\u9fa5]";
		// String regex = "[\ud0000000-\udfffffff]";
		// String regex = "􁵪􁻠􁴹􄲀􀞜􀨨";
		String regex = "\udbc7\udd6a";
		String str = string.replaceAll(regex, "");
		System.out.println(str);
		System.out.println(string2Unicode(str));
	}

}
