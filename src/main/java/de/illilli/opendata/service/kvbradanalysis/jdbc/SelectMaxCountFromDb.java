package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.ConnectionFactory;

public class SelectMaxCountFromDb {

	String queryString = "/selectMaxCount.sql";
	private MaxCountDao maxCountDao;

	public SelectMaxCountFromDb() throws SQLException, NamingException,
			IOException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				queryString);
		String sql = IOUtils.toString(inputStream);
		QueryRunner query = new QueryRunner();
		ResultSetHandler<MaxCountDao> handler = new BeanHandler<MaxCountDao>(
				MaxCountDao.class);
		maxCountDao = query.query(conn, sql, handler);

	}

	public int getMaxCount() {
		return maxCountDao.getMaxcount();
	}

}
