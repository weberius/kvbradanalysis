package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.geojson.FeatureCollection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectCountNumberofGeomFromAnalysis;

public class GeoJsonFacade implements Facade {

	private FeatureCollection featureCollection;

	public GeoJsonFacade() throws SQLException, NamingException, IOException {
		featureCollection = new RoutingFeatureCollection(
				new SelectCountNumberofGeomFromAnalysis())
				.getFeatureCollection();
	}

	@Override
	public String getJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(featureCollection);
	}

}
