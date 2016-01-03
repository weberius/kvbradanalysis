package de.illilli.opendata.service.kvbradanalysis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.postgis.Point;

public class Value2PointArrayListConverter {

	private static final String COLON = ",";
	private List<Point[]> pointArrayList = new ArrayList<Point[]>();

	public Value2PointArrayListConverter(String value) throws SQLException {
		// cut away leading and ending brackets
		value = value.substring(1, value.length() - 1);
		// tokenize by colon
		StringTokenizer st = new StringTokenizer(value, COLON);
		Point[] pointArray = new Point[2];
		boolean firstRun = true;
		for (int i = 0; st.hasMoreElements(); i++) {
			String token = st.nextToken();
			if (firstRun) {
				// bei jedem graden i
				pointArray[0] = new Point(token);
				firstRun = false;
			} else {
				// initialize array
				pointArray[1] = new Point(token);
				pointArrayList.add(pointArray);
				pointArray = new Point[2];
				pointArray[0] = new Point(token);
			}
		}
	}

	public List<Point[]> getPointArrayList() {
		return pointArrayList;
	}

}
