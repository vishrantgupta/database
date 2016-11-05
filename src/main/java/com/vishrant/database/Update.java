package com.vishrant.database;

import java.util.List;
import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

class Update implements Query {

	@XStreamAlias("id")
	private String id;

	@XStreamAlias("values")
	private Columns columns;

	@XStreamAlias("where")
	private String where;

	@Override
	public String formQuery(final String tableName)
			throws DatabaseInitializationException {

		final StringBuilder sb = new StringBuilder();
		if (Objects.isNull(tableName)) {
			throw new DatabaseInitializationException(
					"Syntax error while forming query " + this);
		}

		sb.append("UPDATE " + tableName.toLowerCase() + " ");

		final List<String> columnList = this.getColumns().getColumnList();
		if(Objects.nonNull(columnList) && columnList.size() > 0) {
			sb.append("SET ");
			for (final String column : columnList) {
				sb.append(column + " = ? ");
			}
		} else {
			throw new DatabaseInitializationException(
					"Syntax error while forming query " + this);			
		}

		if (Objects.nonNull(this.getWhere())) {
			sb.append(this.getWhere());
		}

		return sb.toString();
	}

	public Columns getColumns() {
		return this.columns;
	}

	public String getId() {
		return this.id;
	}

	@Override
	public String getQueryId() {
		return this.getId();
	}

	public String getWhere() {
		return this.where;
	}

	@Override
	public String toString() {
		return "Update [id=" + this.id + ", columns=" + this.columns
				+ ", where=" + this.where + "]";
	}
}
