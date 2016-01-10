package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectCeilAvgCountFromAnalysisresult;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectCountGeomFromAnalysisresult;

public class GeoJsonFacade implements Facade {

	private FeatureCollection featureCollection;

	public GeoJsonFacade() throws SQLException, NamingException, IOException {

		int minCount = new SelectCeilAvgCountFromAnalysisresult().getDbObject().getNumber();

		featureCollection = new AnalysisFeatureCollection(new SelectCountGeomFromAnalysisresult(minCount))
				.getFeatureCollection();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
