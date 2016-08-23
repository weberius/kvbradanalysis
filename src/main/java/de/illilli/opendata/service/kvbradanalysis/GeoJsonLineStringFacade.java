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

public class GeoJsonLineStringFacade implements Facade {

	private static final Logger logger = Logger.getLogger(GeoJsonFacade.class);

	private FeatureCollection featureCollection;

	public GeoJsonLineStringFacade(String threshold) throws SQLException, NamingException, IOException {

		int minCount = 0;
		try {
			minCount = Integer.parseInt(threshold);
			logger.info("threshold = " + minCount);
		} catch (NumberFormatException e) {
			minCount = new SelectCeilAvgCountFromAnalysisresult().getDbObject().getNumber();
			logger.error("no value for threshold; threshold = " + minCount);
		}

		logger.info("minCount: " + minCount);
		featureCollection = new AnalysisFeatureCollection(new SelectCountGeomFromAnalysisresult(minCount))
				.getFeatureCollection();

	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
