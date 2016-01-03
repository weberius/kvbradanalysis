package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.opendata.service.DbWriter;

public class InsertRoutingFragmentsToDb implements DbWriter {

	private String queryString = "/insertRoutingFragments.sql";
	private int numberOfInserts;

	public InsertRoutingFragmentsToDb(List<Kvbradrouting> kvbradroutingList)
			throws SQLException, IOException, NamingException {
		Connection conn = ConnectionFactory.getConnection();
		InputStream inputStream = this.getClass().getResourceAsStream(
				this.queryString);
		String sql = IOUtils.toString(inputStream);

		for (Kvbradrouting kvbradrouting : kvbradroutingList) {
			int number = kvbradrouting.getNumber();
			String value = kvbradrouting.getValue();

			List<Double[]> points = null;

			Point[] pointArray = new Point[points.size()];
			for (int i = 0; i < points.size(); i++) {
				Double[] point = points.get(i);
				pointArray[i] = new Point(point[0], point[1]);
			}

			LineString lineString = new LineString(pointArray);
			lineString.srid = kvbradrouting.getSrid();

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			preparedStatement.setObject(4, new PGgeometry(lineString));

			numberOfInserts = preparedStatement.executeUpdate();

		}
		try {
			conn.close();
		} catch (SQLException e) {

		} finally {

		}
	}

	@Override
	public int getNumberOfInserts() {
		return numberOfInserts;
	}

}
