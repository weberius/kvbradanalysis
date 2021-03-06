package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.jdbc.JndiProperties;
import de.illilli.opendata.service.Facade;

/**
 * <p>
 * Dieser Test hat zur Zeit mangels einer Integrations-Stage nur eine
 * main-Methode, die es ermöglicht, die Funktion manuell zu überprüfen.
 * </p>
 * <p>
 * TODO: Mit Integrations-Stage Test implementieren.
 * </p>
 */
public class RoutingAnalyseFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException {
		JndiProperties.setUpConnectionForJndi();
		Facade facade = new RoutingAnalyseFacade();
		String json = facade.getJson();
		System.out.println(json);
	}

}
