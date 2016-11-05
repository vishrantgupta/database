package com.vishrant.database;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

class Columns {

	@XStreamImplicit(itemFieldName = "column")
	private final List<String> columns = new ArrayList<>();

	public List<String> getColumnList() {
		return this.columns;
	}

	@Override
	public String toString() {
		return "Columns [columns=" + columns + "]";
	}

}
