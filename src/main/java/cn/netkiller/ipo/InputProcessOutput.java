package cn.netkiller.ipo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.input.InputInterface;
import cn.netkiller.ipo.output.OutputInterface;
import cn.netkiller.ipo.position.PositionInterface;
import cn.netkiller.ipo.process.ProcessInterface;

/**
 *
 * @author netkiller
 */
public class InputProcessOutput {
	private final static Logger logger = LoggerFactory.getLogger(InputProcessOutput.class);

	private InputInterface input;
	private ProcessInterface process;
	private OutputInterface output;
	private PositionInterface position;

	private boolean exit = false;
	private boolean pipeline = false;
	private String name = "";

	public InputProcessOutput() {
		this.name = this.getClass().getName();
	}

	public InputProcessOutput(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InputProcessOutput setInput(InputInterface input) {
		this.input = input;
		return this;
	}

	public InputInterface getInput() {
		return input;
	}

	public InputProcessOutput setProcess(ProcessInterface process) {
		this.process = process;
		return this;
	}

	public ProcessInterface getProcess() {
		return process;
	}

	public InputProcessOutput setOutput(OutputInterface output) {
		this.output = output;
		return this;
	}

	public OutputInterface getOutput() {
		return output;
	}

	public InputProcessOutput setPosition(PositionInterface position) {
		this.position = position;
		return this;
	}

	public PositionInterface getPosition() {
		return this.position;
	}

	public void setPipeline(boolean pipeline) {
		this.pipeline = pipeline;
	}

	public boolean getPipeline() {
		return this.pipeline;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public void launch() {

		this.input.open();
		this.output.open();
		this.process.open();
		logger.debug("==================== Begin ====================");
		if (this.pipeline) {
			try {
				do {
					if (!this.execute()) {
						Thread.sleep(1000);
					}
					if (exit) {
						this.pipeline = false;
					}
					// logger.debug("shutdown() => {}", exit);
				} while (this.pipeline);
			} catch (InterruptedException e) {
				logger.debug(e.getMessage());
			}
		} else {
			do {
				this.execute();
				logger.debug("---------- " + this.name + " ----------");
				if (exit) {
					break;
				}
			} while (this.input.hasNext());
		}

		logger.debug("==================== End ====================");
		this.process.close();
		this.output.close();
		this.input.close();
	}

	private boolean execute() {
		// Object dataType = input.getDataType();
		// logger.debug(input.getDataType().getClass().getTypeName());
		// if (dataType instanceof String) {
		// this.string();
		// }
		//
		// if (dataType instanceof HashMap || dataType instanceof LinkedHashMap) {
		// this.map();
		// }
		try {
			Object row = input.readLine();
			if (row == null) {
				return false;
			}
			if (this.process != null) {
				row = this.process.run(row);
			}
			if (row != null) {
				boolean outputStatus = this.output.write(row);
				if (this.position != null && outputStatus) {

					this.position.set(row);
				}
			}

			return true;
		} catch (Exception e) {
			// logger.error(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		return false;

	}

	public void shutdown() {
		this.exit = true;
	}

}
