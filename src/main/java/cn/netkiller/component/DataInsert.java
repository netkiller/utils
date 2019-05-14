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

	private final static Logger logger = LoggerFactory.getLogger(DataInsert.class);

	@Autowired
	private DataMigration dataMigration;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if (args.containsOption("table")) {
			if (!args.getOptionValues("table").equals("user")) {
				return;
			}
			if (!args.getOptionValues("table").equals("department")) {
				return;
			}
		}
		dataMigration.users(false, false);
		// dataMigration.department(false);
		// dataMigration.departmentsHasUser(false);
		// dataMigration.crm(false);
		// dataMigration.account(false);
		// dataMigration.project(false);
		// dataMigration.contract(false);
	}

}
