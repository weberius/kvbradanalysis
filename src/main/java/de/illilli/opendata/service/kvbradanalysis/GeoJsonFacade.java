package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectCeilAvgCountFromAnalysisresult;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectCountGeomFromAnalysisresult;

public class GeoJsonFacade implements Facade {

	private static final Logger logger = Logger.getLogger(GeoJsonFacade.class);

	private FeatureCollection featureCollection;

	/**
	 * Der Wert für threshold gibt den unteren Schwellwert an. Er hängt an der
	 * Anzahl der gezählten Line-String Stücke. Es wird ein Integer erwartet.
	 * Bei Übergabe von 'null' oder einer beliebigen Zeichenkette, wird ein
	 * Durchschnittswert aus den Datenbankeinträgen ermittelt.
	 * 
	 * @param threshold
	 *            eine ganze Zahl; wenn nicht gewünscht 'null'
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	public GeoJsonFacade(String threshold) throws SQLException, NamingException, IOException {

		int minCount = 0;
		try {
			minCount = Integer.parseInt(threshold);
			logger.info("threshold = " + minCount);
		} catch (NumberFormatException e) {
			minCount = new SelectCeilAvgCountFromAnalysisresult().getDbObject().getNumber();
			logger.error("no value for threshold; threshold = " + minCount);
		}

		logger.info("minCount: " + minCount);
		featureCollection = new AnalysisFeatureMultiLinesCollection(new SelectCountGeomFromAnalysisresult(minCount))
				.getFeatureCollection();
	}

	/**
	 * Der Threshold ermittelt sich mit Hilfe von
	 * SelectCeilAvgCountFromAnalysisresult aus dem Mittelwert der Einträge.
	 * 
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @see SelectCeilAvgCountFromAnalysisresult
	 */
	public GeoJsonFacade() throws SQLException, NamingException, IOException {

		int minCount = new SelectCeilAvgCountFromAnalysisresult().getDbObject().getNumber();
		logger.info("minCount: " + minCount);
		featureCollection = new AnalysisFeatureMultiLinesCollection(new SelectCountGeomFromAnalysisresult(minCount))
				.getFeatureCollection();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
