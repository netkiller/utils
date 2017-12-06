package cn.netkiller.ipo.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StdinToStdout {

	public StdinToStdout() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		String s = "";
		try {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			while ((s = stdIn.readLine()) != null) {
				System.out.println(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
