package cn.netkiller.ipo.util.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.netkiller.ipo.annotation.Column;
import cn.netkiller.ipo.annotation.Table;

public class Sql {

	StringBuffer sql = new StringBuffer();

	public Sql() {
		// TODO Auto-generated constructor stub
	}

	@Deprecated
	public String build() {
		return sql.toString();
	}

	@SuppressWarnings("unused")
	public static String query(Object p) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder str = new StringBuilder();
		// 通过反射获取Class对象，以便获取注解的值
		Class<? extends Object> obj = p.getClass();
		// 判断该对象的类上有没有注解@Table
		boolean isExistsTable = obj.isAnnotationPresent((Class<? extends Annotation>) Table.class);
		if (!isExistsTable) {
			return null;
		}
		// 获取Table注解，并获取注解的值，即表名
		Table table = (Table) obj.getAnnotation(Table.class);
		String tableName = table.value();
		// 拼装sql
		str.append("select * from ").append(tableName).append(" where ");
		// 获取所有的成员变量，并遍历出来成员变量上的注解值
		Field[] fields = obj.getDeclaredFields();
		for (Field field : fields) {
			Boolean isExistColumn = field.isAnnotationPresent(Column.class);
			if (!isExistColumn) {
				continue;
			}
			// 获取成员变量上的注解值
			Column column = field.getAnnotation(Column.class);
			String columnName = column.value();
			// 获取成员变量的get方法名
			String methodName = "get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
			// 获取成员变量的get方法
			Method method = obj.getMethod(methodName);
			// 执行成员变量的get方法，参数为该对象
			Object value = method.invoke(p);
			// 过滤掉成员变量中的null值，以及0
			if (null == value || (value instanceof Integer && (Integer) value == 0)) {
				continue;
			}

			if (value instanceof String) {
				if (((String) value).contains(",")) {
					String[] values = ((String) value).split(",");
					str.append(" in (");
					for (String s : values) {
						str.append("'").append(s).append("'").append(",");
					}
					str.deleteCharAt(str.length() - 1);
					str.append(")");
				} else {
					str.append(columnName).append("=").append("'").append(value).append("'");
				}
			} else {

				str.append(columnName).append("=").append(value);
			}
			str.append(" and ");
		}
		// str.deleteCharAt(str.length() - 5);
		str.delete(str.length() - 5, str.length());

		return str.toString();
	}
}
