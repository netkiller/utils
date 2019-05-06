/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.input.InputInterface;
import cn.netkiller.ipo.input.JdbcTemplateInput;
import cn.netkiller.ipo.input.KafkaInput;
import cn.netkiller.ipo.input.StdinInput;

/**
 *
 * @author netkiller
 */
public class Input implements InputInterface {
	private final static Logger logger = LoggerFactory.getLogger(Input.class);
	private final List<InputInterface> inputs = new ArrayList<InputInterface>();
	private boolean hasNext = false;
	private Object dataType = new String();

	public Input() {
	}

	public Input(Object dataType) {
		this.dataType = dataType;
	}

	public boolean open() {
		for (InputInterface input : this.inputs) {
			logger.debug("Open input source: {}", input.getClass().getName());
			input.open();
		}
		return true;
	}

	public boolean close() {
		for (InputInterface input : this.inputs) {
			logger.debug("Close input source: {}", input.getClass().getName());
			input.close();
		}
		return true;
	}

	// public boolean hasNextLine() {
	// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	// // logger.debug(String.valueOf(this.nextLine));
	// return this.nextLine;
	// }

	@Override
	public Object readLine() {
		this.hasNext = false;
		for (InputInterface input : this.inputs) {
			if (input.hasNext()) {
				this.dataType = input.getDataType();
				// logger.warn(this.dataType.getClass().getTypeName());
				if (input.getDataType() instanceof String) {
					String tmp = (String) input.readLine();
					if (tmp != null && !tmp.equals("")) {
						this.hasNext = input.hasNext();
						return tmp;
					}
				}
				if (input.getDataType() instanceof HashMap) {
					this.hasNext = input.hasNext();
					return input.readLine();
				}
			}
		}
		return null;
	}

	public Input add(FileInput input) {
		this.inputs.add(input);
		return this;
	}

	public Input add(StdinInput stdinInput) {
		this.inputs.add(stdinInput);
		return this;
	}

	public Input add(KafkaInput kafkaInput) {
		this.inputs.add(kafkaInput);
		return this;
	}

	public Input add(JdbcTemplateInput jdbcTemplateInput) {
		this.inputs.add(jdbcTemplateInput);
		this.dataType = jdbcTemplateInput.getDataType();
		return this;

	}

	@Override
	public Object getDataType() {
		return this.dataType;
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

}
