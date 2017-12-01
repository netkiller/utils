/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo.input;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author netkiller
 */
public class Input {
	private final static Logger logger = LoggerFactory.getLogger(Input.class);
	private final List<InputInterface> inputs = new ArrayList<InputInterface>();
	private boolean nextLine = false;

	public Input() {

	}

	public Input open() {
		for (InputInterface input : this.inputs) {
			input.open();
		}
		return this;
	}

	public Input close() {
		for (InputInterface input : this.inputs) {
			input.close();
		}
		return this;
	}

	public Input add(FileInput input) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		this.inputs.add(input);
		return this;
	}

	public boolean hasNextLine() {
//		logger.debug(String.valueOf(this.nextLine));
		return this.nextLine;
	}

	public List<String> reads() {
		List<String> lines = new ArrayList<String>();
		this.nextLine = false;
		for (InputInterface input : this.inputs) {
			String tmp = input.readLine();
			if (tmp != null && !tmp.equals("")) {
				lines.add(tmp);
				this.nextLine = true;
//				logger.debug(tmp);
			}
		}
		return lines;
	}
}
