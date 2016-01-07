package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.opendata.service.DbWriter;

public class DeleteFromAnalysisResult implements DbWriter {

	private String queryString = "/deleteFromAnalysisresult.sql";
	private int numberOfDeletions;

	public DeleteFromAnalysisResult() throws SQLException, NamingException, IOException {

		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(this.queryString);
		String sql = IOUtils.toString(inputStream);

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		numberOfDeletions = preparedStatement.executeUpdate();
		preparedStatement.close();
		conn.close();

	}

	@Override
	public int getNumberOfInserts() {
		return numberOfDeletions;
	}
}
