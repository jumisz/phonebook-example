package com.jumisz.phonebook.dao;

import java.util.Arrays;
import java.util.Collection;

import com.google.common.collect.ListMultimap;
import com.jumisz.phonebook.model.PhoneBookEntry;

public class MapPhoneBookDAO implements PhoneBookDAO {
	
	private final ListMultimap<String, PhoneBookEntry> map;
	
	

	public MapPhoneBookDAO(final ListMultimap<String, PhoneBookEntry> map) {
		this.map = map;
		
	
	}

	@Override
	public Collection<PhoneBookEntry> listEntries() {
		// TODO pagination?
		return map.values();
	}

	@Override
	public Collection<PhoneBookEntry> searchEntries(String surname) {
		return map.get(surname);
		
	}

	@Override
	public void add(PhoneBookEntry phonebook) {
		map.put(phonebook.getSurname(), phonebook);

	}

	@Override
	public void update(PhoneBookEntry phonebook) {
		map.replaceValues(phonebook.getSurname(), Arrays.asList(phonebook));

	}

	@Override
	public void delete(PhoneBookEntry phonebook) {
		map.remove(phonebook.getSurname(), phonebook);

	}

}
