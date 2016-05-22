package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AskForKvbradroutingTest {

	private static final Logger logger = Logger.getLogger(AskForKvbradroutingTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetKvbradrouting21489() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/kvbradrouting.json");
		AskForKvbradrouting askFor = new AskForKvbradrouting(inputStream);
		List<Kvbradrouting> list = askFor.getKvbradroutingList();
		Kvbradrouting krr = list.get(0);
		int actual = krr.getNumber();
		int expected = 21489;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testNumberOfKvbradrouting() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/kvbradrouting.json");
		AskForKvbradrouting askFor = new AskForKvbradrouting(inputStream);
		List<Kvbradrouting> list = askFor.getKvbradroutingList();
		int expected = 11661;
		int actual = list.size();
		logger.info("expected: " + expected + "; actual: " + actual);
		Assert.assertEquals(expected, actual);
	}

}
