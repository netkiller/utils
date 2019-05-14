package cn.netkiller.ipo.util.sql;

public class Select extends Sql {

	public Select() {

	}

	public Sql select(String field) {
		sql.append(String.format("SELECT %s ", field));
		return this;
	}

	public Sql from(String table) {
		sql.append(String.format(" FROM %s ", table));
		return this;
	}

	public Sql where(String where) {
		sql.append(String.format(" WHERE %s ", where));
		return this;
	}

	public Sql where(String field, String sort) {
		sql.append(String.format(" ORDER BY %s ", field, sort));
		return this;
	}
}
