package cn.netkiller.demo;

import java.lang.reflect.InvocationTargetException;

import cn.netkiller.ipo.annotation.Column;
import cn.netkiller.ipo.annotation.Table;
import cn.netkiller.ipo.util.sql.Sql;

public class SQLTable {

	public SQLTable() {
		// TODO Auto-generated constructor stub
	}

	@Table("person")
	public static class Person {

		@Column("id")
		private int id;

		@Column("name")
		private String name;

		@Column("sex")
		private String sex;

		@Column("age")
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLTable.Person p = new SQLTable.Person();
		p.setId(10);
		p.setName("neo");
		p.setAge(35);
		String sql = null;
		try {
			sql = Sql.query(p);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(sql);
	}

}
