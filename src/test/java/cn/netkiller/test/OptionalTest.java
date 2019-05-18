package cn.netkiller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionalTest {

	public OptionalTest() {
		// TODO Auto-generated constructor stub
	}

	// public static class ValueAbsentException extends Throwable {
	//
	// private static final long serialVersionUID = -1758502952187236809L;
	//
	// public ValueAbsentException() {
	// super();
	// }
	//
	// public ValueAbsentException(String msg) {
	// super(msg);
	// }
	//
	// @Override
	// public String getMessage() {
	// return "No value present in the Optional instance";
	// }
	// }

	public static class Person {
		public int id;
		public String username;
		public String password;
		public String name;

	}

	public static void main(String[] args) {

		Optional<Map<String, Object>> name = Optional.of(new HashMap<String, Object>() {
			{
				put("id", 1);
				put("name", "Neo");
				put("age", 30);
			}
		});

		System.out.println(name.toString());
		name.map((m) -> m.put("count", 1));
		System.out.println(name.get());
		name.map((m) -> m.put("nickname", "netkiller"));
		name.map((m) -> m.remove("id"));
		System.out.println(name.get());
		Optional<Map<String, Object>> tmp = name.filter((m) -> ((Integer) m.get("age")) == 30);
		System.out.println("filter: " + tmp.get());

		// Optional<String> empty = Optional.ofNullable(null);

		

		// for (String item : List.of("Neo", "Jerry", "Netkiller", "Tom", "Anni", "Lisa", "Leo")) {
		// Optional<String> username = Optional.of(item).filter((value) -> value.length() > 6);
		// System.out.println("name is: " + username.orElse("The name is less than 6 characters"));
		// }
		List.of("Neo", "Jerry", "Netkiller", "Tom", "Anni", "Lisa", "Leo").forEach(item -> {
			Optional.of(item).filter((value) -> value.length() > 2).filter((value) -> value.contains("o")).ifPresent((n) -> {
				System.out.println(n);
			});
		});
	}

}
