package com.jumisz.phonebook.dao;

import java.util.Collection;
import java.util.List;

import com.jumisz.phonebook.model.PhoneBookEntry;

public interface PhoneBookDAO {
	
	
	Collection<PhoneBookEntry> listEntries();
	
	List<PhoneBookEntry> searchEntries(String surname);
	
	void add(PhoneBookEntry phonebook);
	
	void update(PhoneBookEntry phonebook);
	
	void delete(String surname);
	
	
	
}
