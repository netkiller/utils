package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputProcessOutputGroup {
	private final static Logger logger = LoggerFactory.getLogger(InputProcessOutputGroup.class);
	private final List<InputProcessOutput> inputProcessOutputGroups = new ArrayList<InputProcessOutput>();

	public InputProcessOutputGroup() {
		super();
	}

	public InputProcessOutputGroup(InputProcessOutput inputProcessOutput) {
		this.inputProcessOutputGroups.add(inputProcessOutput);
	}

	public InputProcessOutputGroup add(InputProcessOutput inputProcessOutput) {
		this.inputProcessOutputGroups.add(inputProcessOutput);
		return this;
	}

	public void launch() {
		for (InputProcessOutput ipo : this.inputProcessOutputGroups) {

			// new Thread(new Runnable() {
			// @Override
			// public void run() {
			ipo.launch();

			// }
			// }).start();
			logger.debug("==================== Done {} ====================", ipo.getName());
		}

	}

}
