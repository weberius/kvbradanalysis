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
		Select<CountGeomDao> select = new SelectCountGeomFromAnalysisresult(0);
		List<CountGeomDao> objectList = select.getDbObjectList();
		CountGeomDao firstDao = objectList.get(0);
		System.out.println("numberOf: " + firstDao.getCount());
		System.out.println("objectList size: " + objectList.size());
	}

}
