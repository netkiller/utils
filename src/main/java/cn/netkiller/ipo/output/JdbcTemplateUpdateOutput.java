package cn.netkiller.ipo.output;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.netkiller.ipo.util.SqlUtil;

public class JdbcTemplateUpdateOutput implements OutputInterface {
	private final static Logger logger = LoggerFactory.getLogger(JdbcTemplateUpdateOutput.class);
	private JdbcTemplate outputJdbcTemplate;
	private String table;
	private String where;

	public JdbcTemplateUpdateOutput(JdbcTemplate outputJdbcTemplate, String table, String where) {
		this.outputJdbcTemplate = outputJdbcTemplate;
		this.table = table;
		this.where = where;
	}

	@Override
	public boolean open() {
		return true;
	}

	@Override
	public boolean write(Object output) {

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) output;
		String sql;
		try {
			sql = SqlUtil.update(this.table, map, this.where);
			int id = this.outputJdbcTemplate.update(sql);
			logger.debug(sql);
			logger.debug("Insert ID {}", id);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean close() {
		return true;
	}

}
