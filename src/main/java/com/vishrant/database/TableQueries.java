package com.vishrant.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("queries")
class TableQueries /* implements Table */{

	private class DeleteQueries {
		@XStreamImplicit(itemFieldName = "delete")
		private final List<Delete> deletes = new ArrayList<>();

		public List<Delete> getDeletes() {
			return this.deletes;
		}

		@Override
		public String toString() {
			return "DeleteQueries [deletes=" + this.deletes + "]";
		}
	}

	private class InsertQueries {
		@XStreamImplicit(itemFieldName = "insert")
		private final List<Insert> inserts = new ArrayList<>();

		public List<Insert> getInserts() {
			return this.inserts;
		}

		@Override
		public String toString() {
			return "InsertQueries [inserts=" + this.inserts + "]";
		}
	}

	private class SelectQueries {
		@XStreamImplicit(itemFieldName = "select")
		private final List<Select> selects = new ArrayList<>();

		public List<Select> getSelects() {
			return this.selects;
		}

		@Override
		public String toString() {
			return "SelectQueries [selects=" + this.selects + "]";
		}
	}

	private class UpdateQueries {
		@XStreamImplicit(itemFieldName = "update")
		private final List<Update> updates = new ArrayList<>();

		public List<Update> getUpdates() {
			return this.updates;
		}

		@Override
		public String toString() {
			return "UpdateQueries [updates=" + this.updates + "]";
		}
	}

	@XStreamAlias("table")
	private String table;

	@XStreamAlias("selectQueries")
	private SelectQueries selectQueries;

	@XStreamAlias("insertQueries")
	private InsertQueries insertQueries;

	@XStreamAlias("updateQueries")
	private UpdateQueries updateQueries;

	@XStreamAlias("deleteQueries")
	private DeleteQueries deleteQueries;

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
		final TableQueries other = (TableQueries) obj;
		if (this.deleteQueries == null) {
			if (other.deleteQueries != null) {
				return false;
			}
		} else if (!this.deleteQueries.equals(other.deleteQueries)) {
			return false;
		}
		if (this.insertQueries == null) {
			if (other.insertQueries != null) {
				return false;
			}
		} else if (!this.insertQueries.equals(other.insertQueries)) {
			return false;
		}
		if (this.selectQueries == null) {
			if (other.selectQueries != null) {
				return false;
			}
		} else if (!this.selectQueries.equals(other.selectQueries)) {
			return false;
		}
		if (this.table == null) {
			if (other.table != null) {
				return false;
			}
		} else if (!this.table.equals(other.table)) {
			return false;
		}
		if (this.updateQueries == null) {
			if (other.updateQueries != null) {
				return false;
			}
		} else if (!this.updateQueries.equals(other.updateQueries)) {
			return false;
		}
		return true;
	}

	public Map<String, String> getAllQueries()
			throws DatabaseInitializationException {

		final Map<String, String> map = new HashMap<String, String>();

		final List<Query> list = new ArrayList<Query>();

		final InsertQueries inserts = this.getInsertQueries();
		if (inserts != null) {
			list.addAll(inserts.getInserts());
		}

		final SelectQueries selects = this.getSelectQueries();
		if (selects != null) {
			list.addAll(selects.getSelects());
		}

		final UpdateQueries updates = this.getUpdateQueries();
		if (updates != null) {
			list.addAll(updates.getUpdates());
		}

		final DeleteQueries deletes = this.getDeleteQueries();
		if (deletes != null) {
			list.addAll(deletes.getDeletes());
		}

		if (list.size() > 0) {
			for (final Query query : list) {
				map.put(query.getQueryId(), query.formQuery(this.getTable()));
			}
		}

		return map;
	}

	public DeleteQueries getDeleteQueries() {
		return this.deleteQueries;
	}

	public InsertQueries getInsertQueries() {
		return this.insertQueries;
	}

	public SelectQueries getSelectQueries() {
		return this.selectQueries;
	}

	public String getTable() {
		return this.table;
	}

	public UpdateQueries getUpdateQueries() {
		return this.updateQueries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((this.deleteQueries == null) ? 0 : this.deleteQueries
						.hashCode());
		result = (prime * result)
				+ ((this.insertQueries == null) ? 0 : this.insertQueries
						.hashCode());
		result = (prime * result)
				+ ((this.selectQueries == null) ? 0 : this.selectQueries
						.hashCode());
		result = (prime * result)
				+ ((this.table == null) ? 0 : this.table.hashCode());
		result = (prime * result)
				+ ((this.updateQueries == null) ? 0 : this.updateQueries
						.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "TableQueries [table=" + table + ", selectQueries="
				+ selectQueries + ", insertQueries=" + insertQueries
				+ ", updateQueries=" + updateQueries + ", deleteQueries="
				+ deleteQueries + "]";
	}

}
