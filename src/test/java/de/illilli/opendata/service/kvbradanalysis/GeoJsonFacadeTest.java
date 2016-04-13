package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import de.illilli.jdbc.JndiProperties;
import de.illilli.opendata.service.Facade;

public class GeoJsonFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetJson() throws SQLException, NamingException, IOException {
		JndiProperties.setUpConnectionForJndi();
		Facade afc = new GeoJsonFacade();
		String json = afc.getJson();
		System.out.println(json);
	}

}
