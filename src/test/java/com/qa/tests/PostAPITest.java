package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

import junit.framework.Assert;

public class PostAPITest  extends TestBase{
	
	
TestBase testbase;
	
	String serviceeurl;
	
	String apiurl;
	
	String url;
	
	RestClient restclient;
	
	CloseableHttpResponse closeableHttpResponse;
	
	
	
	
  public PostAPITest() throws IOException {
		super();
	}

  @BeforeMethod
	
	public void setup() throws IOException {
		
		 testbase = new  TestBase();
		 
		 serviceeurl = prop.getProperty("URL");
		 
		 apiurl = prop.getProperty("serviceURL");
		
		 url =  serviceeurl+apiurl; 
		
	 }
  
  
  @Test
  
  public void PostAPITest() throws JsonGenerationException, JsonMappingException, IOException {
	  
	  restclient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader"); //expected users obejct
		
		
		//object to JSON file
		mapper.writeValue(new File("C:\\TestLeaf\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		
		//Object to json in string 
		
		String writeValueAsString = mapper.writeValueAsString(users);
		System.out.println( writeValueAsString  );
		
		
		
		closeableHttpResponse = restclient.post(url	, writeValueAsString, headerMap);
		
		//Check status.code
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, testbase.RESPONSE_STATUS_CODE_201);
		
		//JsonSTring
		
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject responsejson = new JSONObject(responsestring);
		System.out.println("API RESPONSE---->" +responsejson);
		
		//Validate weather data created or nt
		
		//Json to jaava object - marshlling 
		
		Users readValue = mapper.readValue(responsestring, Users.class);//actual user Object 
		
		Assert.assertTrue(users.getName().equals(readValue.getName()));
		Assert.assertTrue(users.getJob().equals(readValue.getJob()));
		
		
		
		
		
	  
	  
  }
  
  
  
  
  
}
