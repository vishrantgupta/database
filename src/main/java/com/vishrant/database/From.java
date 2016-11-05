package com.vishrant.database;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

class From {

	public class Table {

		@XStreamAlias("name")
		private String name;

		@XStreamAlias("alias")
		private String alias;

		public String getAlias() {
			return this.alias;
		}

		public String getName() {
			return this.name;
		}

		@Override
		public String toString() {
			return "Table [name=" + this.name + ", alias=" + this.alias + "]";
		}
	}

	@XStreamImplicit(itemFieldName = "table")
	private final List<Table> tableList = new ArrayList<>();

	public List<Table> getTableList() {
		return this.tableList;
	}

	@Override
	public String toString() {
		return "From [tableList=" + this.tableList + "]";
	}

}
