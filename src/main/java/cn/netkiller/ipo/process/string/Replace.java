/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.process.string;

import cn.netkiller.ipo.process.ProcessInterface;

/**
 *
 * @author netkiller
 */
public class Replace implements ProcessInterface {

	private final String oldString;
	private final String newString;
	private String tmp;

	public Replace(String oldString, String newString) {
		this.oldString = oldString;
		this.newString = newString;
	}

	@Override
	public Object run(Object data) {
		String tmp = (String) data;
		if (tmp == null) {
			return "";
		}
		this.tmp = tmp;
		if (tmp.contains(this.oldString)) {
			return tmp.replace(this.oldString, this.newString);
		} else {
			return tmp;
		}

	}

	@Override
	public String toString() {
		return (String.format("%s: %s -> %s \n", this.tmp, this.oldString, this.newString));
	}

}
