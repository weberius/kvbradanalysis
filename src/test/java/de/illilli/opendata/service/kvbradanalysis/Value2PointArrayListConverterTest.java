package de.illilli.opendata.service.kvbradanalysis;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.postgis.Point;

public class Value2PointArrayListConverterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testThreePointListArray() throws SQLException {

		String value = "(6.95764 50.905,6.95707 50.90436,6.95722 50.90433)";

		List<Point[]> pointListArray = new Value2PointArrayListConverter(value)
				.getPointArrayList();

		int expected = 2;
		int actual = pointListArray.size();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void test31PointListArray() throws SQLException {

		String value = "(6.95764 50.905,6.95707 50.90436,6.95722 50.90433,6.95592 50.90282,6.95641 50.90265,6.9563 50.90245,6.95633 50.90201,6.9563 50.90191,6.95619 50.90169,6.9559 50.90131,6.95584 50.90119,6.95539 50.89952,6.95488 50.89786,6.95478 50.89767,6.95459 50.89742,6.95375 50.8966,6.95334 50.89604,6.95328 50.89589,6.9532 50.89553,6.95318 50.89537,6.95307 50.89539,6.95256 50.89539,6.95134 50.89544,6.95075 50.8955,6.95072 50.89541,6.95068 50.89538,6.95061 50.89536,6.94974 50.89546,6.94946 50.89547,6.94916 50.89513,6.94908 50.89515,6.94849 50.89447)";

		List<Point[]> pointListArray = new Value2PointArrayListConverter(value)
				.getPointArrayList();

		int expected = 31;
		int actual = pointListArray.size();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testFirstValue() throws SQLException {

		String value = "(6.95764 50.905,6.95707 50.90436,6.95722 50.90433)";

		List<Point[]> pointListArray = new Value2PointArrayListConverter(value)
				.getPointArrayList();

		Double expected = 6.95764;
		Double actual = pointListArray.get(0)[0].x;

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testLastValue() throws SQLException {

		String value = "(6.95764 50.905,6.95707 50.90436,6.95722 50.90433,6.95592 50.90282,6.95641 50.90265,6.9563 50.90245,6.95633 50.90201,6.9563 50.90191,6.95619 50.90169,6.9559 50.90131,6.95584 50.90119,6.95539 50.89952,6.95488 50.89786,6.95478 50.89767,6.95459 50.89742,6.95375 50.8966,6.95334 50.89604,6.95328 50.89589,6.9532 50.89553,6.95318 50.89537,6.95307 50.89539,6.95256 50.89539,6.95134 50.89544,6.95075 50.8955,6.95072 50.89541,6.95068 50.89538,6.95061 50.89536,6.94974 50.89546,6.94946 50.89547,6.94916 50.89513,6.94908 50.89515,6.94849 50.89447)";

		List<Point[]> pointListArray = new Value2PointArrayListConverter(value)
				.getPointArrayList();

		Double expected = 50.89447;
		Double actual = pointListArray.get(pointListArray.size() - 1)[1].y;

		Assert.assertEquals(expected, actual);

	}

}
