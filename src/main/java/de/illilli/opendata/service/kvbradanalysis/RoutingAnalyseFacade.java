package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.DbWriter;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradanalysis.jdbc.LastRunWriterToDb;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectLastrunFromDb;

public class RoutingAnalyseFacade implements Facade {

	private static final Logger logger = Logger
			.getLogger(RoutingAnalyseFacade.class);

	private long lastrun;
	private DbWriter lastRunWriter;

	public RoutingAnalyseFacade() throws SQLException, NamingException,
			IOException {
		// letzten Lauf ermitteln
		this.lastrun = new SelectLastrunFromDb().getTime();
		// Routing Ergebnisse von kvbradrouting erfragen
		AskForKvbradrouting askFor = new AskForKvbradrouting();
		// Routing Ergebnisse aufsplitten

		// Aufgesplittete Ergebnisse in die Datenbank schreiben
		DbWriter dbWriter = new InsertRoutingFragmentsToDb(
				askFor.getKvbradroutingList());
		// vermerken, dass Daten geschrieben wurde
		this.lastRunWriter = new LastRunWriterToDb(0);

	}

	@Override
	public String getJson() throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

}
