package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.output.OutputInterface;
import cn.netkiller.ipo.process.ProcessInterface;

public class InputProcessOutputMatchChain extends InputProcessOutput {
	private final static Logger logger = LoggerFactory.getLogger(InputProcessOutputMatchChain.class);
	private Map<ProcessInterface, OutputInterface> chains = new LinkedHashMap<ProcessInterface, OutputInterface>();

	public InputProcessOutputMatchChain() {
		this.setName(this.getClass().getName());
	}

	public InputProcessOutputMatchChain(String name) {
		this.setName(name);
	}

	public InputProcessOutputMatchChain match(Process process, Output output) {
		this.chains.put(process, output);
		return this;
	}

	@Override
	public void run() {
		logger.debug("==================== Begin {} ====================", this.getName());
		this.getInput().open();

		if (this.getPipeline()) {
			try {
				do {
					if (!this.execute()) {
						Thread.sleep(1000);
					}
					if (this.isExit()) {
						this.setPipeline(false);
					}
					// logger.debug("shutdown() => {}", this.isExit());
				} while (this.getPipeline());
			} catch (InterruptedException e) {
				logger.debug(e.getMessage());
			}
		} else {
			do {
				this.execute();
				// logger.info("==================== Batch Done ====================");
				if (this.isExit()) {
					break;
				}
			} while (this.getInput().hasNext());
		}
		this.getInput().close();
		logger.debug("==================== End {} ====================", this.getName());
	}

	private boolean execute() {
		boolean isNextBatch = false;
		List<String> inputLines = new ArrayList<String>();

		String line = (String) this.getInput().readLine();
		if (line != null) {
			inputLines.add(line);
			isNextBatch = true;
		}

		for (Entry<ProcessInterface, OutputInterface> entry : chains.entrySet()) {
			entry.getValue().open();

			List<String> processLines = new ArrayList<String>();

			for (String proc : inputLines) {
				line = entry.getKey().run(proc);
				if (line != null) {
					processLines.add(line);
				}
			}

			for (String out : processLines) {
				entry.getValue().write(out);
			}

			entry.getValue().close();
		}
		return isNextBatch;

	}
}