package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.jdbc.JndiProperties;
import de.illilli.jdbc.Select;

public class SelectCountNumberofGeomFromAnalysisTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException,
			NamingException, IOException {
		JndiProperties.setUpConnectionForJndi();
		Select<CountNumberofGeomDao> select = new SelectCountNumberofGeomFromAnalysis();
		List<CountNumberofGeomDao> objectList = select.getDbObjectList();
		CountNumberofGeomDao firstDao = objectList.get(0);
		System.out.println("numberOf: " + firstDao.getNumberof());
		System.out.println("objectList size: " + objectList.size());
	}

}
