package com.jumisz.phonebook.dao;

import java.util.Collection;

import com.jumisz.phonebook.model.PhoneBookEntry;

public interface PhoneBookDAO {
	
	
	Collection<PhoneBookEntry> listEntries();
	
	Collection<PhoneBookEntry> searchEntries(String surname);
	
	void add(PhoneBookEntry phonebook);
	
	void update(PhoneBookEntry phonebook);

	void delete(PhoneBookEntry phonebook);
	
	
	
}
