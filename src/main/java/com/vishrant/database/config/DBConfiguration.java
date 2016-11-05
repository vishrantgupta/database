package com.vishrant.database.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("databaseManagement")
public class DBConfiguration {

	public class Connection {

		@XStreamAlias("connectionPool")
		private String connectionPool;

		public String getConnectionPool() {
			return this.connectionPool;
		}

		@Override
		public String toString() {
			return "Connection [connectionPool=" + this.connectionPool + "]";
		}

	}

	public class Database {

		@XStreamAlias("url")
		private String url;

		@XStreamAlias("name")
		private String name;

		@XStreamAlias("username")
		private String username;

		@XStreamAlias("password")
		private String password;

		public String getName() {
			return this.name;
		}

		public String getPassword() {
			return this.password;
		}

		public String getUrl() {
			return this.url;
		}

		public String getUsername() {
			return this.username;
		}

		@Override
		public String toString() {
			return "Database [url=" + this.url + ", name=" + this.name
					+ ", username=" + this.username + ", password="
					+ this.password + "]";
		}

	}

	public class DataSource {

		@XStreamAlias("jndi")
		private String jndi;

		public String getJndi() {
			return this.jndi;
		}

		@Override
		public String toString() {
			return "DataSource [jndi=" + this.jndi + "]";
		}

	}

	@XStreamAlias("datasource")
	private DataSource datasource;

	@XStreamAlias("database")
	private Database database;

	@XStreamAlias("connection")
	private Connection connection;

	public Connection getConnection() {
		return this.connection;
	}

	public Database getDatabase() {
		return this.database;
	}

	public DataSource getDatasource() {
		return this.datasource;
	}

	@Override
	public String toString() {
		return "DBConfiguration [datasource=" + this.datasource + ", database="
				+ this.database + ", connection=" + this.connection + "]";
	}

}
