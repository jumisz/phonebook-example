package com.jumisz.phonebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jumisz.phonebook.controller.PhoneBookController;
import com.jumisz.phonebook.dao.PhoneBookDAO;
import com.jumisz.phonebook.dao.PhoneBookDAOFactory;

public final class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		// Initialise DAO
		PhoneBookDAO dao = PhoneBookDAOFactory.newInstance();
		// Initialise API Services
		new PhoneBookController(dao);
		LOG.info("Phonebook service started");
	}

}
