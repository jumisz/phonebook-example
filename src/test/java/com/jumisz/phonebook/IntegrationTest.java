package com.jumisz.phonebook;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumisz.phonebook.model.PhoneBookEntry;

import spark.Spark;
import spark.utils.IOUtils;

public class IntegrationTest {
	
	private List<PhoneBookEntry> fixtures;
	
	private static final String LOCAL_URL_ROOT = "http://localhost:4567/";

	@Before
	public void setup() throws Exception {
		Main.main(null);
		fixtures = new ObjectMapper().readValue(new File("src/test/resources/test-fixtures.json"), new TypeReference<List<PhoneBookEntry>>() {});
		addAllFixtures();
		
	}
	
	@After
	public void stopServer() {
		Spark.stop();
	}
	
	
	
	@Test
	public void listEntries() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(LOCAL_URL_ROOT);
		HttpResponse response = client.execute(get);
		InputStream content = response.getEntity().getContent();
		String contentAsString = IOUtils.toString(content);
		System.err.println("Content: "+contentAsString);
		List<PhoneBookEntry> entries = new ObjectMapper().readValue(contentAsString, new TypeReference<List<PhoneBookEntry>>() {	});
		assertEquals(fixtures.size(), entries.size());
		
	    for (int i = 0; i < fixtures.size(); i++) {
	    	assertEquals(fixtures.get(i), entries.get(i));
	    }
		
		
	}
	
	public void addAllFixtures() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		for (PhoneBookEntry entry: fixtures) {
			HttpPost post = new HttpPost(LOCAL_URL_ROOT);
			post.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(entry)));
			client.execute(post);
			
		}
	}

}
