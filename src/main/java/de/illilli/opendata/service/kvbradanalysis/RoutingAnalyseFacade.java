package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.illilli.opendata.service.DbWriter;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradanalysis.jdbc.DeleteFromAnalysisResult;
import de.illilli.opendata.service.kvbradanalysis.jdbc.InsertAnalysisResult;
import de.illilli.opendata.service.kvbradanalysis.jdbc.InsertLastRunToDb;
import de.illilli.opendata.service.kvbradanalysis.jdbc.InsertRoutingFragmentsToDb;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectLastrunFromDb;

public class RoutingAnalyseFacade implements Facade {

	private static final Logger logger = Logger.getLogger(RoutingAnalyseFacade.class);

	private long lastrun;
	private DbWriter lastRunWriter;

	public RoutingAnalyseFacade() throws SQLException, NamingException, IOException {
		// letzten Lauf ermitteln
		this.lastrun = new SelectLastrunFromDb().getTime();
		logger.info("lastrun: '" + new Date(this.lastrun).toString() + "'");
		// Routing Ergebnisse von kvbradrouting erfragen
		AskForKvbradrouting askFor = new AskForKvbradrouting(this.lastrun);
		logger.info("askFor size: '" + askFor.getKvbradroutingList().size() + "'");
		// Ergebnisse in die Datenbank schreiben
		DbWriter insertRoutingFragments = new InsertRoutingFragmentsToDb(askFor.getKvbradroutingList());
		logger.info("InsertRoutingFragmentsToDb: '" + insertRoutingFragments.getNumberOfInserts() + "' inserted");
		// delete analysis-values
		DbWriter delteFromAnalysisResult = new DeleteFromAnalysisResult();
		logger.info("DeleteFromAnalysisResult: '" + delteFromAnalysisResult.getNumberOfInserts() + "' deleted");
		// write analysis-values
		DbWriter insertAnalysisResult = new InsertAnalysisResult();
		logger.info("InsertAnalysisResult: '" + insertAnalysisResult.getNumberOfInserts() + "' inserted");
		// vermerken, dass Daten geschrieben wurde
		this.lastRunWriter = new InsertLastRunToDb(insertRoutingFragments.getNumberOfInserts());
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return "Anzahl eingefügter Routings: " + this.lastRunWriter.getNumberOfInserts();
	}

}
