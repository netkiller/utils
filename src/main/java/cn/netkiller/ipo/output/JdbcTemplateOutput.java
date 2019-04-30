package cn.netkiller.ipo.output;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateOutput implements OutputInterface {

	private final static Logger logger = LoggerFactory.getLogger(JdbcTemplateOutput.class);
	private String table;

	private Map<String, String> map = new LinkedHashMap<String, String>();

	private JdbcTemplate outputJdbcTemplate;

	public JdbcTemplateOutput(JdbcTemplate outputJdbcTemplate, String table) {
		this.outputJdbcTemplate = outputJdbcTemplate;
		this.table = table;

		logger.debug("Output table {}", table);
	}

	public JdbcTemplateOutput(String table, Map<String, String> map) {
		this.table = table;
		this.map = map;
	}

	@Override
	public void open() {

	}

	@Override
	public void write(Object output) {

		@SuppressWarnings("unchecked")
		Map<String, String> tmp = (Map<String, String>) output;
		logger.info("Output Map {}", tmp.toString());
		String sql = "";
		try {

			if (map != null && !map.isEmpty()) {
				List<String> valuesList = new ArrayList<String>();
				for (String value : map.values()) {
					if (tmp.containsKey(value)) {
						valuesList.add((String) tmp.get(value));
					} else {
						valuesList.add("");
					}
				}
				String fields = StringUtils.join(map.keySet(), "`,`");
				String values = StringUtils.join(valuesList, "\",\"");
				sql = String.format("INSERT INTO `%s`(`%s`) value(\"%s\")", this.table, fields, values);
			} else {

				String fields = StringUtils.join(tmp.keySet(), "`,`");
				String values = StringUtils.join(tmp.values(), "\",\"");

				logger.info("Output Key {}", tmp.keySet().toString());
				logger.info("Output Value {}", tmp.values().toString());

				sql = String.format("INSERT INTO `%s`(`%s`) value(\"%s\")", this.table, fields, values);
			}

			logger.debug("SQL {}", sql);
			outputJdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	@Override
	public void close() {

	}

}
