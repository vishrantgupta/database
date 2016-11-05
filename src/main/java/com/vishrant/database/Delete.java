package com.vishrant.database;

import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

class Delete implements Query {

	@XStreamAlias("id")
	private String id;

	@XStreamAlias("table")
	private String table;

	@XStreamAlias("where")
	private String whereClause;

	@Override
	public String formQuery(final String tableName)
			throws DatabaseInitializationException {

		if (Objects.isNull(tableName)) {
			throw new DatabaseInitializationException(
					"Syntax error while forming query " + this);
		}

		final StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM " + tableName);

		if(Objects.nonNull(this.getWhereClause())) {
			sb.append(" \nWHERE " + this.getWhereClause());
		}

		return sb.toString();

	}

	public String getId() {
		return this.id;
	}

	@Override
	public String getQueryId() {
		return this.getId();
	}

	public String getTable() {
		return this.table;
	}

	public String getWhereClause() {
		return this.whereClause;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setTable(final String table) {
		this.table = table;
	}

	public void setWhereClause(final String whereClause) {
		this.whereClause = whereClause;
	}

	@Override
	public String toString() {
		return "Delete [id=" + id + ", table=" + table + ", whereClause="
				+ whereClause + "]";
	}

}
