package cn.netkiller.ipo.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SqlUtil {

	final public static class SQL {
		public final static String INSERT = "INSERT";
		public final static String REPLACE = "REPLACE";
		public final static String UPDATE = "UPDATE";
	}

	public final static String QUOTE = "\'";

	public static String join(String method, String table, Map<String, Object> map) {

		Iterator<Entry<String, Object>> entries = map.entrySet().iterator();
		StringBuffer sql = new StringBuffer("");
		StringBuffer fields = new StringBuffer();
		StringBuffer values = new StringBuffer();

		// List<String> function = List.of("now()", "current_date");
		List<String> function = Arrays.asList("now()", "current_date");

		while (entries.hasNext()) {

			Entry<String, Object> entry = entries.next();

			// System.out.println("Key = " + entry.getKey() + ",Value = " + entry.getValue());
			fields.append(String.format("`%s`", entry.getKey()));

			if (entry.getValue() == null) {
				values.append(String.format("%s", "null"));
			} else if (entry.getValue() instanceof Integer) {
				values.append(String.format("%d", entry.getValue()));
			} else if (entry.getValue() instanceof String) {
				if (function.contains(((String) entry.getValue()).toLowerCase())) {
					values.append(String.format("%s", entry.getValue()));
				} else {
					values.append(String.format("'%s'", entry.getValue()));
				}
			} else {
				values.append(String.format("'%s'", entry.getValue()));
			}

			if (entries.hasNext()) {
				fields.append(", ");
				values.append(", ");
			}

		}
		sql.append(method);
		sql.append(" INTO " + table + " (").append(fields.toString()).append(") VALUE(").append(values.toString()).append(")");

		return sql.toString();
	}

	public static String insert(String method, String table, Map<String, Object> map) {
		return join(SQL.INSERT, table, map);

	}

	public static String replace(String method, String table, Map<String, Object> map) {
		return join(SQL.REPLACE, table, map);

	}

	public static String update(String table, Map<String, Object> map, String where) throws Exception {

		if (!map.containsKey(where)) {
			throw new Exception("The field isn't exist!");
		}

		StringBuffer sql = new StringBuffer(SQL.UPDATE);
		sql.append(" " + table + " SET ");

		Iterator<Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, Object> entry = entries.next();
			if (entry.getKey().equals(where)) {
				continue;
			}
			if (entry.getValue() == null) {
				sql.append(String.format("%s = %s", entry.getKey(), "null"));
			} else if (entry.getValue() instanceof Integer) {
				sql.append(String.format("%s = %d", entry.getKey(), entry.getValue()));
			} else if (entry.getValue() instanceof String) {
				sql.append(String.format("%s = \'%s\'", entry.getKey(), entry.getValue()));
			} else {
				sql.append(String.format("%s = \'%s\'", entry.getKey(), entry.getValue()));
			}

		}
		sql.append(String.format(" WHERE %s=\'%s\'", where, map.get(where)));
		return sql.toString();
	}
}
