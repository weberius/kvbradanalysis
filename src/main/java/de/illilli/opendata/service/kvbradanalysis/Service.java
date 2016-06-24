package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	/**
	 * <p>
	 * Beispiel:
	 * <code>curl -X PUT http://localhost:8080/kvbradanalysis/service/put</code>
	 * </p>
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ClassNotFoundException
	 */
	@PUT
	@Path("/put")
	public void putRoutingAnalyse() throws JsonParseException, JsonMappingException, IOException, SQLException,
			NamingException, ClassNotFoundException {
		logger.info("putRoutingAnalyse");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade facade = new RoutingAnalyseFacade();
	}

	/**
	 * <p>
	 * To get geojson - Data use this:
	 * <a href="http://localhost:8080/kvbradanalysis/service/data?geojson">
	 * /kvbradanalysis/service/data?geojson</a>
	 * </p>
	 * 
	 * <p>
	 * To get geojson - Data with threshold use this: <a href=
	 * "http://localhost:8080/kvbradanalysis/service/data?geojson&threshold=3">
	 * /kvbradanalysis/service/data?geojson&threshold=3</a>
	 * </p>
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ClassNotFoundException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/data")
	public String getGeojson() throws JsonParseException, JsonMappingException, IOException, SQLException,
			NamingException, ClassNotFoundException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		boolean geojson = request.getParameter("geojson") != null;
		String threshold = request.getParameter("threshold");

		logger.info("getGeojson; geojson = '" + geojson + "', threshold = '" + threshold + "'");

		Facade facade = new GeoJsonFacade(threshold);

		return facade.getJson();
	}

}
