package com.vishrant.database;

import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

class Insert implements Query {

	@XStreamAlias("id")
	private String id;

	@XStreamAlias("values")
	private Columns columns;

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Insert other = (Insert) obj;
		if (this.columns == null) {
			if (other.columns != null) {
				return false;
			}
		} else if (!this.columns.equals(other.columns)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String formQuery(final String tableName)
			throws DatabaseInitializationException {

		if (Objects.isNull(tableName)
				|| (Objects.nonNull(this.getColumns()) && (Objects
						.isNull(this.getColumns().getColumnList()) || (this.getColumns()
								.getColumnList().size() < 1)))) {

			throw new DatabaseInitializationException(
					"Syntax error while forming query " + this);
		}

		final StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + tableName + " VALUES (");

		if(Objects.nonNull(this.getColumns()) && Objects.nonNull(this.getColumns().getColumnList())) {
			for (final String column : this.getColumns().getColumnList()) {
				sb.append(column + ", ");
			}
			
			sb.setLength(Math.max(sb.length() - 2, 0));

			sb.append(" ) VALUES ( ");
			for (int i = 0; i < this.getColumns().getColumnList().size(); i++) {
				sb.append("?, ");
			}
			sb.setLength(Math.max(sb.length() - 2, 0));

			sb.append(")");
		} else {
			throw new DatabaseInitializationException(
					"Syntax error while forming query " + this);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.columns == null) ? 0 : this.columns.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Insert [id=" + id + ", columns=" + columns + "]";
	}

}
