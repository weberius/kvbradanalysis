package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import de.illilli.jdbc.Select;
import de.illilli.opendata.service.kvbradanalysis.jdbc.CountGeomDao;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectMaxCountFromDb;

public class AnalysisFeatureCollection {

	private FeatureCollection featureCollection;

	public AnalysisFeatureCollection(Select<CountGeomDao> select) throws SQLException, NamingException, IOException {
		featureCollection = new FeatureCollection();

		List<CountGeomDao> objectList = select.getDbObjectList();
		int maxCount = new SelectMaxCountFromDb().getDbObject().getNumber();

		for (CountGeomDao obj : objectList) {

			Double index = new BigDecimal(obj.getCount()).divide(new BigDecimal(maxCount), 2, RoundingMode.HALF_UP)
					.doubleValue();

			Feature feature = new Feature();
			LineString lineString = new LineString();
			org.postgis.LineString pgLineString = (org.postgis.LineString) obj.getGeom().getGeometry();
			org.postgis.Point[] points = pgLineString.getPoints();
			for (org.postgis.Point point : points) {
				LngLatAlt element = new LngLatAlt(point.getX(), point.getY());
				lineString.add(element);
			}
			feature.setGeometry(lineString);
			Map<String, Object> properties = new Hashtable<String, Object>();
			properties.put("index", index);
			feature.setProperties(properties);
			featureCollection.add(feature);
		}

	}

	public FeatureCollection getFeatureCollection() {
		return featureCollection;
	}

	public void setFeatureCollection(FeatureCollection featureCollection) {
		this.featureCollection = featureCollection;
	}

}
