package cn.netkiller.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.InputProcessOutputGroup;
import cn.netkiller.ipo.InputProcessOutputMatchChain;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Position;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.JdbcTemplateInput;
import cn.netkiller.ipo.output.JdbcTemplateOutput;
import cn.netkiller.ipo.output.JdbcTemplateUpdateOutput;
import cn.netkiller.ipo.position.RedisPosition;
import cn.netkiller.ipo.process.map.MapCopy;
import cn.netkiller.ipo.process.map.MapKeyInclude;
import cn.netkiller.ipo.process.map.MapLeft;
import cn.netkiller.ipo.process.map.MapPut;
import cn.netkiller.ipo.process.map.MapRemove;
import cn.netkiller.ipo.process.map.MapReplace;
import cn.netkiller.ipo.process.map.MapTrim;
import cn.netkiller.ipo.service.AliyunOssService;
import cn.netkiller.ipo.util.SqlUtil.SQL;
import cn.netkiller.process.AddressProcess;
import cn.netkiller.process.AttachmentProcess;
import cn.netkiller.process.BizPostId;
import cn.netkiller.process.CreateProjectProcess;
import cn.netkiller.process.PartnerAProcess;

@Service
public class DataMigration {
	private final static Logger logger = LoggerFactory.getLogger(DataMigration.class);

	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private AliyunOssService aliyunOssService;

	private String omdb = "lz_cloud_om_temp";

	public DataMigration() {
		// TODO Auto-generated constructor stub
	}

	public void users(boolean reset) {
		logger.debug("==================== User ====================");

		// String query = "SELECT id, title, content from article where id = ";

		// jdbcTemplate.queryForObject(query, (resultSet, i) -> {
		// System.out.println(resultSet.getLong(1)+","+ resultSet.getString(2)+","+ resultSet.getString(3));
		// });

		// String jdbc = jdbcTemplate.queryForObject("select name from saas.tbl_eos_contracts limit 1", String.class);
		// logger.warn(jdbc);

		// var rows = inputJdbcTemplate.queryForList("select * from saas.tbl_eos_contracts");
		// for (Map<String, Object> row : rows) {
		// logger.warn(row.toString());
		// }

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		RedisPosition redis = new RedisPosition(stringRedisTemplate, "import_users", "user_id");
		Position position = new Position(redis);

		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("ALTER TABLE lz_cloud_dev.lz_users AUTO_INCREMENT=1000000000000000000");
			outputJdbcTemplate.execute("delete from lz_users where ipo = 'import'");
		}

		String sql = "select * from import_users";
		// String id = position.get();
		// if (id != null) {
		// sql += " where user_id > " + id;
		// }
		sql += redis.getSqlWhere();

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));
		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_users", SQL.REPLACE));

		process.add(new MapReplace("parent_id", null, "NULL"));
		process.add(new MapPut("ipo", "import"));
		process.add(new MapReplace("user_pwd", null, "123456"));

		InputProcessOutput ipo = new InputProcessOutput("User");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		// ipo.launch();

		// Position positionUpdate = new Position();
		// String where = "";
		// String updated_time = positionUpdate.get();
		// if(updated_time == null) {
		// where = "";
		// }else {
		// where = "";
		// position.set(new RedisPosition(stringRedisTemplate, "UserUpdate", "updated_time"));
		// }

		InputProcessOutputMatchChain ipomc = new InputProcessOutputMatchChain("User update");
		ipomc.setInput(new Input(new JdbcTemplateInput(inputJdbcTemplate, "select * from import_users where user_id in (select param from import where table in ('res_users','hr_employee'))")));
		ipomc.match(process, output);
		ipomc.match(new Process(new MapKeyInclude(Arrays.asList("id"))), new Output(new JdbcTemplateOutput(inputJdbcTemplate, "import")));

		InputProcessOutputGroup inputProcessOutputGroup = new InputProcessOutputGroup();
		inputProcessOutputGroup.add(ipo);
		// inputProcessOutputGroup.add(ipomc);
		inputProcessOutputGroup.launch();

		// int inputCount = inputJdbcTemplate.queryForObject("select count(*) from import_users", Integer.class);
		// int outputCount = outputJdbcTemplate.queryForObject("select count(*) from lz_users where ipo='import'", Integer.class);
		// if (inputCount == outputCount) {
		// logger.info("---------- Check OK source {}, target {} ----------", inputCount, outputCount);
		// } else {
		// logger.warn("---------- Check ERROR source {}, target {} ----------", inputCount, outputCount);
		// }

	}

	public void department(boolean reset) {

		logger.debug("==================== Department ====================");

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "import_departments", "dept_id"));

		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("ALTER TABLE lz_departments AUTO_INCREMENT=1000000000000000000");
			outputJdbcTemplate.execute("delete from lz_departments where ipo = 'import'");
		}

		String dept_id = position.get();
		String sql = "select * from import_departments";
		if (dept_id != null) {
			sql += " where dept_id > " + dept_id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_departments"));

		process.add(new MapReplace("dept_code", null, "001"));
		process.add(new MapReplace("created_by", null, "admin"));
		process.add(new MapReplace("updated_by", null, "admin"));
		process.add(new MapReplace("created_time", null, "now()"));
		process.add(new MapReplace("updated_time", null, "now()"));
		process.add(new MapPut("ipo", "import"));
		process.add(new MapPut("company_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput("Department");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	public void departmentsHasUser(boolean reset) {

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "import_departments_has_user", "id"));

		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("ALTER TABLE lz_auth AUTO_INCREMENT=1000000000000000000");
			outputJdbcTemplate.execute("delete from lz_auth where ipo = 'import'");
		}

		String id = position.get();
		String sql = "select * from import_departments_has_user";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_auth", SQL.REPLACE));

		process.add(new MapReplace("created_time", null, "now()"));
		process.add(new BizPostId());
		process.add(new MapPut("ipo", "import"));
		// process.add(new MapPut("company_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput("DepartmentsHasUser");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();

	}

	public void business_manager(boolean reset) {
		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		RedisPosition redis = new RedisPosition(stringRedisTemplate, "import_business_manager", "id");
		Position position = new Position(redis);

		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("ALTER TABLE lz_cloud_dev.lz_companys AUTO_INCREMENT=1000000000000000000");
			outputJdbcTemplate.execute("delete from lz_companys where ipo = 'import'");
		}

		// String id = position.get();
		String sql = "select * from import_business_manager";
		// if (id != null) {
		// sql += " where id > " + id;
		// }
		sql += redis.getSqlWhere();
		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_companys", SQL.REPLACE));

		// process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new MapRemove("accept_type"));

		process.add(new MapPut("created_by", "import"));
		// process.add(new MapPut("company_id", "1"));
		// process.add(new MapPut("biz_post_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput("Company");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	public void crm(boolean reset) {
		logger.debug("==================================================");
		logger.debug("==================== CRM ====================");
		logger.debug("==================================================");

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "CRM", "id"));

		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("ALTER TABLE " + this.omdb + ".om_crm_customer AUTO_INCREMENT=100000000000000000");
			outputJdbcTemplate.execute("delete from " + this.omdb + ".om_crm_customer where ipo = 'import'");
		}

		String id = position.get();
		String sql = "select * from import_crm";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, this.omdb + ".om_crm_customer", SQL.REPLACE));

		process.add(new MapReplace("created_time", null, "now()"));
		process.add(new MapPut("ipo", "import"));
		process.add(new MapLeft("customer_company_name", 64));

		InputProcessOutput ipo = new InputProcessOutput("Customer");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	public void account(boolean reset) {
		logger.debug("==================================================");
		logger.debug("==================== Account ====================");
		logger.debug("==================================================");

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Account", "id"));

		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("ALTER TABLE " + this.omdb + ".om_finance_account AUTO_INCREMENT=1000000000");
			outputJdbcTemplate.execute("delete from " + this.omdb + ".om_finance_account where ipo = 'import'");
		}

		String id = position.get();
		String sql = "select * from import_account";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, this.omdb + ".om_finance_account", SQL.REPLACE));

		process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new MapRemove("id"));
		process.add(new MapPut("ipo", "import"));
		// process.add(new MapPut("company_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput("Account");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	public void project(boolean reset) {
		logger.debug("==================================================");
		logger.debug("==================== Project ====================");
		logger.debug("==================================================");

		Map<Integer, String> province = new HashMap<Integer, String>();
		Map<Integer, String> city = new HashMap<Integer, String>();
		Map<Integer, String> district = new HashMap<Integer, String>();
		Map<String, Integer> address = new HashMap<String, Integer>();

		List<Map<String, Object>> rows = inputJdbcTemplate.queryForList("select id, name from res_country_state");
		for (Map<String, Object> row : rows) {
			province.put((Integer) row.get("id"), (String) row.get("name"));
		}

		List<Map<String, Object>> citys = inputJdbcTemplate.queryForList("select id, name from res_country_city");
		for (Map<String, Object> row : citys) {
			city.put((Integer) row.get("id"), (String) row.get("name"));
		}

		List<Map<String, Object>> districts = inputJdbcTemplate.queryForList("select id, name from res_city_district");
		for (Map<String, Object> row : districts) {
			district.put((Integer) row.get("id"), (String) row.get("name"));
		}

		List<Map<String, Object>> addresses = outputJdbcTemplate.queryForList("select id, addr_name as name from lz_address");
		for (Map<String, Object> row : addresses) {
			address.put((String) row.get("name"), (Integer) row.get("id"));
		}

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Project", "id"));
		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("delete from " + this.omdb + ".om_project where ipo = 'import'");
		}
		String id = position.get();
		String sql = "select * from import_projects";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, this.omdb + ".om_project", SQL.REPLACE));

		process.add(new MapReplace("created_time", null, "now()"));
		process.add(new AddressProcess(province, city, district, address));
		process.add(new PartnerAProcess(inputJdbcTemplate));
		process.add(new MapPut("ipo", "import"));
		process.add(new MapPut("company_id", "1"));
		process.add(new MapLeft("remark", 512));
		process.add(new MapLeft("addr_detail", 128));
		process.add(new MapTrim("part_a_name"));

		process.add(new MapPut("part_b_json", "{\"linkPhone\":\"18310358098\",\"districtId\":440305,\"linkPost\":\"扫地僧\",\"companyTel\":\"53165186518561\",\"projectAddr\":[\"44\",\"4403\",\"440305\"],\"addrDetail\":\"南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"projectAddress\":\"广东省深圳市南山区南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"cityId\":4403,\"linkMan\":\"张三\",\"provinceId\":44}"));

		InputProcessOutput ipo = new InputProcessOutput("Project");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	public void contract(boolean reset) {

		logger.debug("==================================================");
		logger.debug("==================== Contract ====================");
		logger.debug("==================================================");

		// Map<Integer, String> province = new HashMap<Integer, String>();
		// Map<Integer, String> city = new HashMap<Integer, String>();
		// Map<Integer, String> district = new HashMap<Integer, String>();
		// Map<String, Integer> address = new HashMap<String, Integer>();
		//
		// List<Map<String, Object>> rows = inputJdbcTemplate.queryForList("select id, name from res_country_state");
		// for (Map<String, Object> row : rows) {
		// province.put((Integer) row.get("id"), (String) row.get("name"));
		// }
		//
		// List<Map<String, Object>> citys = inputJdbcTemplate.queryForList("select id, name from res_country_city");
		// for (Map<String, Object> row : citys) {
		// city.put((Integer) row.get("id"), (String) row.get("name"));
		// }
		//
		// List<Map<String, Object>> districts = inputJdbcTemplate.queryForList("select id, name from res_city_district");
		// for (Map<String, Object> row : districts) {
		// district.put((Integer) row.get("id"), (String) row.get("name"));
		// }
		//
		// List<Map<String, Object>> addresses = outputJdbcTemplate.queryForList("select id, addr_name as name from lz_address");
		// for (Map<String, Object> row : addresses) {
		// address.put((String) row.get("name"), (Integer) row.get("id"));
		// }

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Contract", "id"));
		if (reset) {
			position.reset();
			outputJdbcTemplate.execute("delete from " + this.omdb + ".om_project_contract where ipo = 'import'");
		}
		String id = position.get();
		String sql = "select * from import_contract";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, this.omdb + ".om_project_contract", SQL.REPLACE));

		process.add(new MapReplace("created_by", null, ""));
		process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new AddressProcess(province, city, district, address));
		process.add(new PartnerAProcess(inputJdbcTemplate));
		process.add(new MapPut("ipo", "import"));
		process.add(new MapPut("company_id", "1"));
		process.add(new MapLeft("addr_detail", 128));
		process.add(new MapCopy("part_b_id", "manager_company_id")); // 商务经理的公司ID
		process.add(new MapTrim("part_a_name"));

		process.add(new MapPut("part_b_json", "{\"linkPhone\":\"18310358098\",\"districtId\":440305,\"linkPost\":\"扫地僧\",\"companyTel\":\"53165186518561\",\"projectAddr\":[\"44\",\"4403\",\"440305\"],\"addrDetail\":\"南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"projectAddress\":\"广东省深圳市南山区南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"cityId\":4403,\"linkMan\":\"张三\",\"provinceId\":44}"));

		InputProcessOutput ipo = new InputProcessOutput("Contract");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	public void split_project() {
		inputJdbcTemplate.queryForList("select * from public.import_repeat_project").forEach(item -> {

			logger.debug(item.toString());
			int projectId = (Integer) item.get("id");
			String sql = "select * from import_contract where proj_id = " + projectId;

			Iterator<Map<String, Object>> it = inputJdbcTemplate.queryForList(sql).iterator();
			boolean first = true;
			while (it.hasNext()) {
				Map<String, Object> contract = (Map<String, Object>) it.next();
				if (first) {
					first = false;
					continue;
				}
				Input input = new Input();
				Process process = new Process();
				Output output = new Output();

				String sqlProject = "select * from " + this.omdb + ".om_project where id = " + projectId;

				input.add(new JdbcTemplateInput(outputJdbcTemplate, sqlProject));

				process.add(new MapRemove("id"));
				process.add(new MapPut("proj_no", contract.get("contract_no")));
				process.add(new MapPut("proj_name", contract.get("contract_name")));
				process.add(new CreateProjectProcess(outputJdbcTemplate, this.omdb + ".om_project"));

				process.add(new MapKeyInclude(Arrays.asList("proj_id")));
				process.add(new MapPut("id", contract.get("id")));

				output.add(new JdbcTemplateUpdateOutput(outputJdbcTemplate, this.omdb + ".om_project_contract", "id"));

				InputProcessOutput ipo = new InputProcessOutput("Contract split");

				ipo.setInput(input);
				ipo.setProcess(process);
				ipo.setOutput(output);
				// ipo.setExit(true);
				ipo.launch();
			}

		});

	}

	public void promission() {

	}

	public void attachment(boolean reset) {

		Input input = new Input();
		Process process = new Process();
		Output output = new Output();
		RedisPosition redis = new RedisPosition(stringRedisTemplate, "Attachment", "id");
		Position position = new Position(redis);
		if (reset) {
			outputJdbcTemplate.update("update " + this.omdb + ".om_project_contract set contract_img_url=''");
			position.reset();
			redis.set(new HashMap<String, Object>() {

				private static final long serialVersionUID = -6337266195218581531L;

				{
					put("id", "3835");
				}
			});
		}
		String sql = "select id, contract_img_url from " + this.omdb + ".om_project_contract where ipo='import' ";
		// sql += redis.getSqlWhere();

		String id = position.get();
		if (id != null) {
			sql += " and id > " + id;
		}

		input.add(new JdbcTemplateInput(outputJdbcTemplate, sql));
		process.add(new AttachmentProcess(aliyunOssService, inputJdbcTemplate));
		output.add(new JdbcTemplateUpdateOutput(outputJdbcTemplate, this.omdb + ".om_project_contract", "id"));

		InputProcessOutput ipo = new InputProcessOutput("Attachment");

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();

	}
}
