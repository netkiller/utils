package cn.netkiller.ipo.input;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateInput implements InputInterface {
	private final static Logger logger = LoggerFactory.getLogger(JdbcTemplateInput.class);

	private JdbcTemplate inputJdbcTemplate;

	private String sql = null;
	private Iterator<Map<String, Object>> iterator = null;

	public JdbcTemplateInput(JdbcTemplate inputJdbcTemplate, String sql) {
		this.inputJdbcTemplate = inputJdbcTemplate;
		this.sql = sql;

	}

	public JdbcTemplateInput(JdbcTemplate inputJdbcTemplate, String sql, String where) {
		this.inputJdbcTemplate = inputJdbcTemplate;
		this.sql = sql + " WHERE " + where;
	}

	@Override
	public boolean open() {
		logger.debug("Input SQL {};", this.sql);
		this.iterator = inputJdbcTemplate.queryForList(this.sql).iterator();
		if (this.iterator != null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> readLine() {

		if (this.iterator.hasNext()) {
			Map<String, Object> line = this.iterator.next();
			// logger.debug(line.toString());
			return line;
		}
		return null;
	}

	@Override
	public boolean close() {
		return false;
	}

	public boolean hasNext() {
		return this.iterator.hasNext();
	}
}
