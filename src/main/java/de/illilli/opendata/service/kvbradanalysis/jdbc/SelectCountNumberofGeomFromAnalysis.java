package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import de.illilli.jdbc.Select;

public class SelectCountNumberofGeomFromAnalysis extends
		Select<CountNumberofGeomDao> {

	public SelectCountNumberofGeomFromAnalysis() throws SQLException,
			NamingException, IOException {
		setQueryString("/selectCountNumberofGeomFromAnalysis.sql");
		Object[] params = new Object[0];
		runSelect(new BeanListHandler<CountNumberofGeomDao>(
				CountNumberofGeomDao.class), params);
	}

}
