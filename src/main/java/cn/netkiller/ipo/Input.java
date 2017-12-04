/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.InputInterface;
import cn.netkiller.ipo.input.StdinInput;

/**
 *
 * @author netkiller
 */
public class Input implements InputInterface {
	// private final static Logger logger = LoggerFactory.getLogger(Input.class);
	private final List<InputInterface> inputs = new ArrayList<InputInterface>();
	private boolean nextLine = false;

	public Input() {

	}

	public boolean open() {
		for (InputInterface input : this.inputs) {
			input.open();
		}
		return true;
	}

	public boolean close() {
		for (InputInterface input : this.inputs) {
			input.close();
		}
		return true;
	}

	public boolean hasNextLine() {
		// logger.debug(String.valueOf(this.nextLine));
		return this.nextLine;
	}

	public List<String> readLines() {
		List<String> lines = new ArrayList<String>();
		this.nextLine = false;
		for (InputInterface input : this.inputs) {
			String tmp = input.readLine();
			if (tmp != null && !tmp.equals("")) {
				lines.add(tmp);
				this.nextLine = true;
				// logger.debug(tmp);
			}
		}
		return lines;
	}

	public Input add(FileInput input) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		this.inputs.add(input);
		return this;
	}

	public Input add(StdinInput stdinInput) {
		this.inputs.add(stdinInput);
		return this;
	}

	@Override
	public String readLine() {
		// TODO Auto-generated method stub
		return null;
	}

}
