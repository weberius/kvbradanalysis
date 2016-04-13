package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.NamingException;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.LngLatAlt;
import org.geojson.MultiLineString;

import de.illilli.jdbc.Select;
import de.illilli.opendata.service.kvbradanalysis.jdbc.CountGeomDao;
import de.illilli.opendata.service.kvbradanalysis.jdbc.SelectMaxCountFromDb;

/**
 * Diese Klasse
 */
public class AnalysisFeatureMultiLinesCollection {

	private FeatureCollection featureCollection;

	public AnalysisFeatureMultiLinesCollection(Select<CountGeomDao> select)
			throws SQLException, NamingException, IOException {

		featureCollection = new FeatureCollection();

		List<CountGeomDao> objectList = select.getDbObjectList();
		int maxCount = new SelectMaxCountFromDb().getDbObject().getNumber();

		Collection<Feature> collectionOfFeatures = new ArrayList<Feature>();

		List<LngLatAlt> elements = new ArrayList<LngLatAlt>();

		MultiLineString mls0 = new MultiLineString();
		MultiLineString mls1 = new MultiLineString();
		MultiLineString mls2 = new MultiLineString();
		MultiLineString mls3 = new MultiLineString();

		for (CountGeomDao obj : objectList) {

			elements = new ArrayList<LngLatAlt>();

			Double index = new BigDecimal(obj.getCount()).divide(new BigDecimal(maxCount), 2, RoundingMode.HALF_UP)
					.doubleValue();

			org.postgis.LineString pgLineString = (org.postgis.LineString) obj.getGeom().getGeometry();
			org.postgis.Point[] points = pgLineString.getPoints();
			for (org.postgis.Point point : points) {
				LngLatAlt element = new LngLatAlt(point.getX(), point.getY());
				elements.add(element);
			}
			if (index < 0.25) {
				mls0.add(elements);
			} else if (index < 0.375) {
				mls1.add(elements);
			} else if (index < 0.5) {
				mls2.add(elements);
			} else {
				mls3.add(elements);
			}

		}

		Feature feature0 = new Feature();
		feature0.setGeometry(mls0);
		feature0.setProperty("color", "#a6d96a");
		feature0.setProperty("index", "< 0.25");
		collectionOfFeatures.add(feature0);

		Feature feature1 = new Feature();
		feature1.setGeometry(mls1);
		feature1.setProperty("color", "#ffffbf");
		feature1.setProperty("index", "< 0.375");
		collectionOfFeatures.add(feature1);

		Feature feature2 = new Feature();
		feature2.setGeometry(mls2);
		feature2.setProperty("color", "#fdae61");
		feature2.setProperty("index", "< 0.5");
		collectionOfFeatures.add(feature2);

		Feature feature3 = new Feature();
		feature3.setGeometry(mls3);
		feature3.setProperty("color", "#d7191c");
		feature3.setProperty("index", "< 1");
		collectionOfFeatures.add(feature3);

		featureCollection.addAll(collectionOfFeatures);

	}

	public FeatureCollection getFeatureCollection() {
		return featureCollection;
	}

	public void setFeatureCollection(FeatureCollection featureCollection) {
		this.featureCollection = featureCollection;
	}

}
