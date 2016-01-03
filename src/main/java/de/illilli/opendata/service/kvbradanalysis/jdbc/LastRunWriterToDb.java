package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.opendata.service.DbWriter;

public class LastRunWriterToDb implements DbWriter {

	private static final Logger logger = Logger
			.getLogger(LastRunWriterToDb.class);
	private int inserts;

	String queryString = "/insertLastRun.sql";
	String queryStringWithTimestamp = "/insertLastRunWithTimestamp.sql";

	public LastRunWriterToDb(int numberOfInserts) throws SQLException,
			NamingException, IOException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				this.queryString);
		String sql = IOUtils.toString(inputStream);

		QueryRunner run = new QueryRunner();
		inserts = run.update(conn, sql, numberOfInserts);

		conn.close();

		logger.info(inserts + " inserted");
	}

	public LastRunWriterToDb(int numberOfInserts, long modtime)
			throws SQLException, NamingException, IOException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				this.queryStringWithTimestamp);
		String sql = IOUtils.toString(inputStream);

		QueryRunner run = new QueryRunner();
		inserts = run
				.update(conn, sql, numberOfInserts, new Timestamp(modtime));

		conn.close();

		logger.info(inserts + " inserted");
	}

	@Override
	public int getNumberOfInserts() {
		return inserts;
	}
}
