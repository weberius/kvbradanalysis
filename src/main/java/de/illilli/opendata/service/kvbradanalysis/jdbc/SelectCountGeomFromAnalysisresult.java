package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import de.illilli.jdbc.Select;

public class SelectCountGeomFromAnalysisresult extends Select<CountGeomDao> {

	public SelectCountGeomFromAnalysisresult(int minCount) throws SQLException,
			NamingException, IOException {
		setQueryString("/selectCountGeomFromAnalysisresult.sql");
		Object[] params = new Object[] { minCount };
		runSelect(new BeanListHandler<CountGeomDao>(CountGeomDao.class), params);
	}
}
