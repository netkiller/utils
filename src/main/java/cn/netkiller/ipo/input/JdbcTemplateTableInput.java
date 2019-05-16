package cn.netkiller.ipo.input;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTableInput implements InputInterface {
	private final static Logger logger = LoggerFactory.getLogger(JdbcTemplateTableInput.class);
	private JdbcTemplate inputJdbcTemplate;

	private String sql = null;
	private Iterator<Map<String, Object>> iterator = null;

	public JdbcTemplateTableInput(JdbcTemplate inputJdbcTemplate, String table) {
		this.inputJdbcTemplate = inputJdbcTemplate;
		this.sql = "SELECT * FROM " + table;
	}

	public JdbcTemplateTableInput(JdbcTemplate inputJdbcTemplate, String table, String fields) {
		this.inputJdbcTemplate = inputJdbcTemplate;
		this.sql = "SELECT " + fields + " FROM " + table;
	}

	public JdbcTemplateTableInput(JdbcTemplate inputJdbcTemplate, String table, String fields, String where) {
		this.inputJdbcTemplate = inputJdbcTemplate;
		this.sql = "SELECT " + fields + " FROM " + table + " WHERE " + where;
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
	public Object readLine() {
		if (this.iterator.hasNext()) {
			Object line = this.iterator.next();
			// logger.debug(line.toString());
			return line;
		}
		return null;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

}
