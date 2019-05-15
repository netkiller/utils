package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.input.InputInterface;

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

	public Input(InputInterface inputInterface) {
		this.inputs.add(inputInterface);
	}

	public Input add(InputInterface inputInterface) {
		this.inputs.add(inputInterface);
		return this;
	}

	public boolean open() {
		for (InputInterface input : this.inputs) {
			logger.debug("Open {}", input.getClass().getName());
			input.open();
		}
		return true;
	}

	public boolean close() {
		for (InputInterface input : this.inputs) {
			logger.debug("Close {}", input.getClass().getName());
			input.close();
		}
		return true;
	}

	@Override
	public Object readLine() {
		this.hasNext = false;
		for (InputInterface input : this.inputs) {
			this.hasNext = input.hasNext();
			if (this.hasNext) {

				Object tmp = input.readLine();
				logger.debug("{} {}", input.getClass().getName(), tmp.toString());
				return tmp;

			}
		}
		return null;
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
