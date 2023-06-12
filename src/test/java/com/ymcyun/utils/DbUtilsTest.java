package com.ymcyun.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class DbUtilsTest {

	DbUtils utils = DbUtils.getInstance();


	@Test
	public void testGetConnection() {
		assertTrue(utils.getConnection() instanceof Connection);
		
	}

	@Test
	public void testGetStatement() {
		assertTrue(utils.getStatement() instanceof Statement);
	}
	
}
