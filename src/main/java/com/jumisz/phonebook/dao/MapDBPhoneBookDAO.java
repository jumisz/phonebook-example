package com.jumisz.phonebook.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import org.mapdb.BTreeMap;
import org.mapdb.Bind;
import org.mapdb.Fun;

import com.jumisz.phonebook.model.PhoneBookEntry;

public class MapDBPhoneBookDAO implements PhoneBookDAO {
	
	private final BTreeMap<Integer, PhoneBookEntry> map;
	
	private final NavigableSet<Object[]> surnames;
	
	private final ThreadLocalRandom random;
	
	

	public MapDBPhoneBookDAO(final BTreeMap<Integer, PhoneBookEntry> map) {
		this.map = map;
		surnames = new TreeSet<>();
		Bind.secondaryKey(map, surnames, new Fun.Function2<String, Integer, PhoneBookEntry>() {

			@Override
			public String run(Integer a, PhoneBookEntry b) {
				
				return b.getSurname();
			}
			
		});
		random = ThreadLocalRandom.current();
	}

	@Override
	public Collection<PhoneBookEntry> listEntries() {
		// TODO pagination?
		return map.values();
	}

	@Override
	public List<PhoneBookEntry> searchEntries(String surname) {
		List<PhoneBookEntry> result = new ArrayList<>();
		 for (Object[] pair : Fun.filter(surnames, surname)) {
			 Integer key = (Integer) pair[1];
			result.add(map.get(key));
		};
		return result;
		
	}

	@Override
	public void add(PhoneBookEntry phonebook) {
		Integer key = random.nextInt();
		map.put(key, phonebook);

	}

	@Override
	public void update(PhoneBookEntry phonebook) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String surname) {
		// TODO Auto-generated method stub

	}

}
