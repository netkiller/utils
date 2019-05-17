package cn.netkiller.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.netkiller.service.DataMigration;

@Component
@Order(30)
public class DataInsert implements ApplicationRunner {

	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(DataInsert.class);

	@Autowired
	private DataMigration dataMigration;

	public void run(ApplicationArguments args) throws Exception {

		if (args.containsOption("table")) {
			if (!args.getOptionValues("table").equals("user")) {
				return;
			}
			if (!args.getOptionValues("table").equals("department")) {
				return;
			}
		}
		boolean reset = false;

		// dataMigration.users(reset);
		// dataMigration.department(reset);
		// dataMigration.departmentsHasUser(reset);
		// dataMigration.business_manager(reset);
		// dataMigration.crm(reset);
		// dataMigration.account(reset);
		dataMigration.project(reset);
		dataMigration.contract(reset);
		dataMigration.split_project();
		// dataMigration.attachment(reset);
		System.exit(0);
	}

}
