package de.illilli.opendata.service.kvbradanalysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;

import de.illilli.jdbc.Select;
import de.illilli.opendata.service.kvbradanalysis.jdbc.CountNumberofGeomDao;

public class RoutingFeatureCollection {

	private FeatureCollection featureCollection;

	public RoutingFeatureCollection(Select<CountNumberofGeomDao> select) {
		featureCollection = new FeatureCollection();

		List<CountNumberofGeomDao> objectList = select.getDbObjectList();
		int maxNumberOf = objectList.get(0).getNumberof();

		for (CountNumberofGeomDao obj : objectList) {
			Feature feature = new Feature();
			LineString lineString = new LineString();
			org.postgis.LineString pgLineString = (org.postgis.LineString) obj
					.getGeom().getGeometry();
			org.postgis.Point[] points = pgLineString.getPoints();
			for (org.postgis.Point point : points) {
				LngLatAlt element = new LngLatAlt(point.getX(), point.getY());
				lineString.add(element);
			}
			feature.setGeometry(lineString);
			Map<String, Object> properties = new Hashtable<String, Object>();
			Double index = new BigDecimal(obj.getNumberof()).divide(
					new BigDecimal(maxNumberOf), 2, RoundingMode.HALF_UP)
					.doubleValue();
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
