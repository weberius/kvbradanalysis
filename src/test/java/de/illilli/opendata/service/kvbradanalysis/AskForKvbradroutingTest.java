package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AskForKvbradroutingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetKvbradroutingList() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/kvbradrouting.json");
		AskForKvbradrouting askFor = new AskForKvbradrouting(inputStream);
		List<Kvbradrouting> list = askFor.getKvbradroutingList();
		System.out.println(list);
	}

	@Test
	public void testNumberOfKvbradrouting() throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/kvbradrouting.json");
		AskForKvbradrouting askFor = new AskForKvbradrouting(inputStream);
		List<Kvbradrouting> list = askFor.getKvbradroutingList();
		int expected = 11661;
		int actual = list.size();
		Assert.assertEquals(expected, actual);
	}

}
