package de.illilli.opendata.service.kvbradanalysis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.illilli.opendata.service.Config;

public class AskForKvbradrouting {

	private String url = Config.getProperty("kvbradrouting.json.url");
	private InputStream inputStream;
	private List<Kvbradrouting> kvbradroutingList;

	public AskForKvbradrouting() throws MalformedURLException, IOException {
		this.inputStream = new URL(url).openStream();
		deserialize();
	}

	public AskForKvbradrouting(long lastrun) throws MalformedURLException, IOException {
		url = url + "/" + lastrun;
		this.inputStream = new URL(url).openStream();
		deserialize();
	}

	public AskForKvbradrouting(InputStream inputStream) throws IOException {
		this.inputStream = inputStream;
		deserialize();
	}

	void deserialize() throws IOException {
		Gson gson = new Gson();
		Type type = new TypeToken<List<Kvbradrouting>>() {
		}.getType();
		String json = IOUtils.toString(inputStream);
		kvbradroutingList = gson.fromJson(json, type);
	}

	public List<Kvbradrouting> getKvbradroutingList() {
		return kvbradroutingList;
	}

}
