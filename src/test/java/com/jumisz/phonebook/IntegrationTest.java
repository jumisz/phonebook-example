package com.jumisz.phonebook;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumisz.phonebook.model.PhoneBookEntry;

public class IntegrationTest {
	
	private List<PhoneBookEntry> fixtures;
	
	private static final Integer PORT = 4567;

	@Before
	public void setup() throws Exception {
		Main.main(null);
		fixtures = new ObjectMapper().readValue(new File("src/test/resources/test-fixtures.json"), new TypeReference<List<PhoneBookEntry>>() {});
		
		
	}
	
	
	
	@Test
	public void listEntries() {
		
	}
	
	public void addAllFixtures() {
		for (PhoneBookEntry entry: fixtures) {
			
		}
	}

}
