package cn.netkiller.component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Position;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.JdbcTemplateInput;
import cn.netkiller.ipo.output.JdbcTemplateOutput;
import cn.netkiller.ipo.position.RedisPosition;
import cn.netkiller.ipo.process.map.MapLeft;
import cn.netkiller.ipo.process.map.MapPut;
import cn.netkiller.ipo.process.map.MapRemove;
import cn.netkiller.ipo.process.map.MapReplace;
import cn.netkiller.ipo.process.map.MapTrim;
import cn.netkiller.process.AddressProcess;
import cn.netkiller.process.PartnerAProcess;
import cn.netkiller.ipo.util.SqlUtil.SQL;

@Component
@Order(1)
// @Order(50)
public class Project implements ApplicationRunner {
	private final static Logger logger = LoggerFactory.getLogger(Project.class);

	@Qualifier("inputJdbcTemplate")
	@Autowired
	private JdbcTemplate inputJdbcTemplate;

	@Qualifier("outputJdbcTemplate")
	@Autowired
	private JdbcTemplate outputJdbcTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// this.crm();
		// this.account();
		// this.project();
		this.contract();
		System.exit(0);
	}

	private void crm() {
		logger.debug("==================================================");
		logger.debug("==================== CRM ====================");
		logger.debug("==================================================");

		outputJdbcTemplate.execute("ALTER TABLE lz_cloud_om_dev.om_crm_customer AUTO_INCREMENT=100000");
		outputJdbcTemplate.execute("delete from lz_cloud_om_dev.om_crm_customer where ipo = 'import'");

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "CRM"), "id");

		String id = position.get();
		String sql = "select * from import_crm";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_cloud_om_dev.om_crm_customer", SQL.REPLACE));

		process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new MapRemove("accept_type"));
		process.add(new MapPut("ipo", "import"));
		// process.add(new MapPut("company_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	private void account() {
		logger.debug("==================================================");
		logger.debug("==================== Account ====================");
		logger.debug("==================================================");

		// outputJdbcTemplate.execute("ALTER TABLE lz_cloud_om_dev.om_finance_account AUTO_INCREMENT=100000");
		outputJdbcTemplate.execute("delete from lz_cloud_om_dev.om_finance_account where ipo = 'import'");

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Account"), "id");

		String id = position.get();
		String sql = "select * from import_account";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_cloud_om_dev.om_finance_account", SQL.INSERT));

		process.add(new MapReplace("created_time", null, "now()"));
		process.add(new MapRemove("id"));
		process.add(new MapPut("ipo", "import"));
		// process.add(new MapPut("company_id", "1"));

		InputProcessOutput ipo = new InputProcessOutput();

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	private void project() {
		// outputJdbcTemplate.execute("delete from lz_cloud_om_dev.om_project where ipo = 'import'");

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

		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Project"), "id");
		// position.reset();

		String id = position.get();
		String sql = "select * from import_projects";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_cloud_om_dev.om_project", SQL.REPLACE));

		process.add(new MapReplace("created_time", null, "now()"));
		process.add(new AddressProcess(province, city, district, address));
		process.add(new PartnerAProcess(inputJdbcTemplate));
		process.add(new MapPut("ipo", "import"));
		process.add(new MapPut("company_id", "1"));
		// process.add(new MapPut("building_type", 1));
		process.add(new MapLeft("addr_detail", 128));
		process.add(new MapTrim("part_a_name"));

		process.add(new MapPut("part_b_json", StringEscapeUtils.escapeJson("{\"linkPhone\":\"18310358098\",\"districtId\":440305,\"linkPost\":\"扫地僧\",\"companyTel\":\"53165186518561\",\"projectAddr\":[\"44\",\"4403\",\"440305\"],\"addrDetail\":\"南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"projectAddress\":\"广东省深圳市南山区南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"cityId\":4403,\"linkMan\":\"张三\",\"provinceId\":44}")));

		InputProcessOutput ipo = new InputProcessOutput(this.getClass().getName());

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}

	private void contract() {

		logger.debug("==================================================");
		logger.debug("==================== Contract ====================");
		logger.debug("==================================================");

		outputJdbcTemplate.execute("delete from lz_cloud_om_dev.om_project_contract where ipo = 'import'");
		
		Input input = new Input(new LinkedHashMap<Object, Object>());
		Process process = new Process();
		Output output = new Output();
		Position position = new Position(new RedisPosition(stringRedisTemplate, "Contract"), "id");
		// position.reset();

		String id = position.get();
		String sql = "select * from import_contract";
		if (id != null) {
			sql += " where id > " + id;
		}

		input.add(new JdbcTemplateInput(inputJdbcTemplate, sql));

		output.add(new JdbcTemplateOutput(outputJdbcTemplate, "lz_cloud_om_dev.om_project_contract", SQL.REPLACE));

		process.add(new MapReplace("created_by", null, ""));
		process.add(new MapReplace("created_time", null, "now()"));
		// process.add(new AddressProcess(province, city, district, address));
//		process.add(new PartnerAProcess(inputJdbcTemplate));
		process.add(new MapPut("ipo", "import"));
		process.add(new MapPut("company_id", "1"));
		// process.add(new MapPut("building_type", 1));
		process.add(new MapLeft("addr_detail", 128));
		process.add(new MapTrim("part_a_name"));

		process.add(new MapPut("part_b_json", StringEscapeUtils.escapeJson("{\"linkPhone\":\"18310358098\",\"districtId\":440305,\"linkPost\":\"扫地僧\",\"companyTel\":\"53165186518561\",\"projectAddr\":[\"44\",\"4403\",\"440305\"],\"addrDetail\":\"南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"projectAddress\":\"广东省深圳市南山区南头街道马家龙工业区19栋(鼎元宏易大厦)4楼401-405\",\"cityId\":4403,\"linkMan\":\"张三\",\"provinceId\":44}")));

		InputProcessOutput ipo = new InputProcessOutput(this.getClass().getName());

		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.setPosition(position);
		ipo.launch();
	}
}
