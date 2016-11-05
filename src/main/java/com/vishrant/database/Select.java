package com.vishrant.database;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vishrant.database.From.Table;

class Select implements Query {

	@XStreamAlias("id")
	private String id;

	@XStreamAlias("columns")
	private Columns columns;

	@XStreamAlias("from")
	private From from;

	@XStreamAlias("where")
	private String whereClause;

	@XStreamAlias("groupBy")
	private String groupBy;

	@XStreamAlias("orderBy")
	private String orderBy;

	@XStreamAlias("limit")
	private String limit;

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
		final Select other = (Select) obj;
		if (this.columns == null) {
			if (other.columns != null) {
				return false;
			}
		} else if (!this.columns.equals(other.columns)) {
			return false;
		}
		if (this.from == null) {
			if (other.from != null) {
				return false;
			}
		} else if (!this.from.equals(other.from)) {
			return false;
		}
		if (this.groupBy == null) {
			if (other.groupBy != null) {
				return false;
			}
		} else if (!this.groupBy.equals(other.groupBy)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.limit == null) {
			if (other.limit != null) {
				return false;
			}
		} else if (!this.limit.equals(other.limit)) {
			return false;
		}
		if (this.orderBy == null) {
			if (other.orderBy != null) {
				return false;
			}
		} else if (!this.orderBy.equals(other.orderBy)) {
			return false;
		}
		if (this.whereClause == null) {
			if (other.whereClause != null) {
				return false;
			}
		} else if (!this.whereClause.equals(other.whereClause)) {
			return false;
		}
		return true;
	}

	@Override
	public String formQuery(final String tableName)
			throws DatabaseInitializationException {

		final List<String> cols = this.columns.getColumnList();
		final StringBuilder sb = new StringBuilder();

		sb.append("SELECT ");
		if ((Objects.nonNull(cols)) && (cols.size() > 0) && !cols.contains("*")) {
			for (String column : cols) {
				sb.append(column + ", ");
			}
			sb.setLength(Math.max(sb.length() - 2, 0));
		} else if ((cols.size() == 1) && cols.get(0).equals("*")) {
			sb.append("* ");
		} else {
			throw new DatabaseInitializationException(
					"Syntax error while forming query " + this);
		}
		sb.append("\n");

		if (Objects.nonNull(this.getFrom())
				&& Objects.nonNull(this.getFrom().getTableList())
				&& (this.getFrom().getTableList().size() > 0)) {
			sb.append(" FROM ");

			final Iterator<Table> iterator = this.getFrom().getTableList().iterator();
			while (iterator.hasNext()) {
				final Table table = iterator.next();
				sb.append(table.getName() + " " + (table.getAlias() != null ? table
						.getAlias() : ""));
				sb.append(", ");
			}

			sb.setLength(Math.max(sb.length() - 2, 0));
			sb.append("\n");
		} else {
			throw new DatabaseInitializationException(
					"Syntax error while forming query " + this);
		}

		if (Objects.nonNull(this.getWhereClause()) && !this.getWhereClause().isEmpty()) {
			sb.append(" WHERE " + this.getWhereClause().trim() + " \n");
		}

		if (Objects.nonNull(this.getGroupBy()) && !this.getGroupBy().isEmpty()) {
			sb.append(" GROUP BY " + this.getGroupBy() + " \n");
		}

		if (Objects.nonNull(this.getOrderBy()) && !this.getOrderBy().isEmpty()) {
			sb.append(" ORDER BY " + this.getOrderBy() + " \n");
		}

		if (Objects.nonNull(this.getLimit()) && !this.getLimit().isEmpty()) {
			sb.append(" LIMIT BY " + this.getLimit() + " \n");
		}

		return sb.toString();
	}

	public Columns getColumns() {
		return this.columns;
	}

	public From getFrom() {
		return this.from;
	}

	public String getGroupBy() {
		return this.groupBy;
	}

	public String getId() {
		return this.id;
	}

	public String getLimit() {
		return this.limit;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	@Override
	public String getQueryId() {
		return this.getId();
	}

	public String getWhereClause() {
		return this.whereClause;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.columns == null) ? 0 : this.columns.hashCode());
		result = (prime * result) + ((this.from == null) ? 0 : this.from.hashCode());
		result = (prime * result) + ((this.groupBy == null) ? 0 : this.groupBy.hashCode());
		result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
		result = (prime * result) + ((this.limit == null) ? 0 : this.limit.hashCode());
		result = (prime * result) + ((this.orderBy == null) ? 0 : this.orderBy.hashCode());
		result = (prime * result)
				+ ((this.whereClause == null) ? 0 : this.whereClause.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Select [id=" + id + ", columns=" + columns + ", from=" + from
				+ ", whereClause=" + whereClause + ", groupBy=" + groupBy
				+ ", orderBy=" + orderBy + ", limit=" + limit + "]";
	}

}
