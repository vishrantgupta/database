package com.vishrant.database;

import org.junit.Test;

import com.vishrant.database.config.DBConfiguration;

public class DriverManagerTest {

	@Test
	public void testDriverManager() {
		DatabaseManager instance = DatabaseManager.getInstance();

		String selectCheck = instance.getQuery("selectCheck");
		String insertCheck = instance.getQuery("insertCheck");
		String updateCheck = instance.getQuery("updateCheck");
		String deleteCheck = instance.getQuery("deleteCheck");
		String deleteCheck1 = instance.getQuery("deleteCheck1");

		System.out.println("selectCheck: " + selectCheck);
		System.out.println("insertCheck: " + insertCheck);
		System.out.println("updateCheck: " + updateCheck);
		System.out.println("deleteCheck: " + deleteCheck);
		System.out.println("deleteCheck1: " + deleteCheck1);

		DBConfiguration dbConfiguration = instance.getDBConfiguration();

		System.out.println(dbConfiguration);

	}
}
