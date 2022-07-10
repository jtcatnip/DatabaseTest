package com.jeff.testdb;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResponseMapper implements RowMapper<Row> {

	public Row mapRow(ResultSet row, int rowNum) throws SQLException {
		Row jRow = new Row();
		jRow.setName(row.getString("name"));
		jRow.setDate(row.getDate("date"));
		jRow.setInfo(row.getString("info"));
		return jRow;
	}

}
