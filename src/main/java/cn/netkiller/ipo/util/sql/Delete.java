package cn.netkiller.ipo.util.sql;

public class Delete extends Sql {

	public Delete() {
		// TODO Auto-generated constructor stub
	}

	public Delete truncate(String table) {
		sql.append(String.format("TRUNCATE FROM %s ", table));
		return this;
	}

	public Sql delete(String table) {
		sql.append(String.format("DELETE FROM %s ", table));
		return this;
	}

	public Sql where(String where) {
		sql.append(String.format(" WHERE %s ", where));
		return this;
	}

}
