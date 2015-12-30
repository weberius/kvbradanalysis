package de.illilli.opendata.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Facade {

	String getJson() throws JsonProcessingException;
}
