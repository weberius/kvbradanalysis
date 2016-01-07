package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.Before;

import de.illilli.jdbc.JndiProperties;
import de.illilli.opendata.service.DbWriter;

/**
 * <p>
 * Dieser Test hat zur Zeit mangels einer Integrations-Stage nur eine
 * main-Methode, die es ermöglicht, das Löschen der Werte in der Tabelle
 * <code>analysisresult</code> zu überprüfen.
 * </p>
 * <p>
 * TODO: Mit Integrations-Stage Test implementieren.
 * </p>
 */
public class DeleteFromAnalysisResultTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws SQLException, NamingException, IOException {
		JndiProperties.setUpConnectionForJndi();
		DbWriter delete = new DeleteFromAnalysisResult();
		int numberOfDeletions = delete.getNumberOfInserts();
		System.out.println("numberOfDeletions = '" + numberOfDeletions + "'");
	}

}
