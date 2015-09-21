package com.jumisz.phonebook.dao;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

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
		DB db = DBMaker.memoryDB().make();
		BTreeMap<Integer, PhoneBookEntry> map = db.treeMap("phoneBook");
		return new MapDBPhoneBookDAO(map);
	}

}
