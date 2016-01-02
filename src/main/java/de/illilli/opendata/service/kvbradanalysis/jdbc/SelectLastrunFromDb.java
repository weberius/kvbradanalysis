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
import de.illilli.opendata.service.Lastrun;

public class SelectLastrunFromDb implements Lastrun {

	String queryString = "/selectLastRun.sql";
	private LastRunDao lastRunDao;

	public SelectLastrunFromDb() throws SQLException, NamingException,
			IOException {
		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				queryString);
		String sql = IOUtils.toString(inputStream);
		QueryRunner query = new QueryRunner();
		ResultSetHandler<LastRunDao> handler = new BeanHandler<LastRunDao>(
				LastRunDao.class);
		lastRunDao = query.query(conn, sql, handler);
	}

	@Override
	public long getTime() {
		if (lastRunDao == null) {
			return 0;

		} else {
			return lastRunDao.getModtime().getTime();
		}
	}

}
