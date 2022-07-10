package com.jeff.testdb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Priority(10)
@Repository
public class DBService {

	private static final Logger log = LogManager.getLogger(DBService.class);
	private final String sql = "select * from Jeff1_Table1";

	@Autowired
	private JdbcTemplate queryTemplate;

	@PostConstruct
	private void init() {
		log.info("Starting up. Do something here is we need to.");
		
	}

	public List<Row> getData() {

		return queryTemplate.query(sql, new ResponseMapper());
				
	}


}
