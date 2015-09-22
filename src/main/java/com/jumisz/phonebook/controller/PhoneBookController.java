package com.jumisz.phonebook.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumisz.phonebook.dao.PhoneBookDAO;
import com.jumisz.phonebook.model.PhoneBookEntry;

import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * Handles requests to the phonebook
 */

public class PhoneBookController {

	private static final String OK = "OK";

	private static final Logger LOG = LoggerFactory.getLogger(PhoneBookController.class);

	// Jackson ObjectMapper is thread safe.
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private final PhoneBookDAO dao;

	public PhoneBookController(PhoneBookDAO dao) {
		this.dao = dao;
		registerAPI();
	}

	private void registerAPI() {

		get("/", (req, res) -> list(req, res));
		post("/", (req, res) -> add(req, res));
		put("/:id", (req, res) -> update(req, res));
		delete("/:id", (req, res) -> remove(req, res));
		get("/search/:term", (req, res) -> search(req, res));

	}

	public Object list(Request request, Response response) throws JsonProcessingException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("listing phonebook entries");
		}
		Collection<PhoneBookEntry> values = dao.listEntries();
		return MAPPER.writeValueAsString(values);
	}

	public Object add(Request request, Response response) throws IOException {
		// TODO Validate input
		// TODO Sanitize input
		// TODO Handle exceptional cases
		PhoneBookEntry phonebook = readPhoneBookEntryFromRequest(request);
		dao.add(phonebook);
		LOG.info("Adding phonebook: " + phonebook);
		Spark.halt(204);
		return OK;
	}

	private static PhoneBookEntry readPhoneBookEntryFromRequest(Request request)
			throws IOException, JsonParseException, JsonMappingException {
		PhoneBookEntry phonebook = MAPPER.readValue(request.body(), PhoneBookEntry.class);
		return phonebook;
	}

	public Object update(Request request, Response response) throws IOException {
		PhoneBookEntry phonebook = readPhoneBookEntryFromRequest(request);
		dao.update(phonebook);
		return OK;
	}

	public Object remove(Request request, Response response) {
		return OK;
	}

	public Object search(Request request, Response response) {
		String surname = request.params("term");
		return dao.searchEntries(surname);
	}

}
