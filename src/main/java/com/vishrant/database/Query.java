package com.vishrant.database;

interface Query {

	String formQuery(String tableName) throws DatabaseInitializationException;

	String getQueryId();

}
