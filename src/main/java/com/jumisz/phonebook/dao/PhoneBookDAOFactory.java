package com.jumisz.phonebook.dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.jumisz.phonebook.model.PhoneBookEntry;

/**
 * 
 * This class could potentially read DAO configuration, including implementation class, etc. from a configuration file.
 * 
 * At the moment it only initialises the default DAO.
 *
 */
public class PhoneBookDAOFactory {
	
	
	public static PhoneBookDAO newInstance() {
		ListMultimap<String, PhoneBookEntry> map = ArrayListMultimap.create();
		return new MapPhoneBookDAO(map);
	}

}
