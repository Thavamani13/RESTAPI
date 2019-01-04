package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import junit.framework.Assert;

public class GetAPITest extends TestBase {
	
	public GetAPITest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}



	TestBase testbase;
	
	String serviceeurl;
	
	String apiurl;
	
	String url;
	
	RestClient restclient;
	
	CloseableHttpResponse closeableHttpResponse;
	
	
	
	
	
	@BeforeMethod
	
	public void setup() throws IOException {
		
		 testbase = new  TestBase();
		 
		 serviceeurl = prop.getProperty("URL");
		 
		 apiurl = prop.getProperty("serviceURL");
		
		 url =  serviceeurl+apiurl; 
		
	 }


	
	//Create an object for RestClient
   @Test()
	public void getTest() throws ClientProtocolException, IOException {
		
		restclient = new RestClient();
		
		closeableHttpResponse=restclient.get(url);
		
		
		
		//1.status code
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ----->" + statusCode);
		
		Assert.assertEquals("status code is not 200", RESPONSE_STATUS_CODE_200, statusCode);
		

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonresponse = new JSONObject(responseString);
        System.out.println("JSON Response from API------->" + jsonresponse);
        
        //Single value assertion 
        
		
		//Validate Pagevalue
		String perpagevalue = TestUtil.getValueByJPath(jsonresponse, "/per_page");
		System.out.println("value of perpage----->" +perpagevalue);
		Assert.assertEquals(Integer.parseInt(perpagevalue), 3);		
		
		
		//Validate total value
		String totalvalue = TestUtil.getValueByJPath(jsonresponse, "/total");
		System.out.println("value of total----->" +totalvalue);
		org.testng.Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		
		//***GET THE VALUE FROM JSON ARRAY*****//
		
		  String lastname = TestUtil.getValueByJPath(jsonresponse, "/data[0]/last_name");
		  String id = TestUtil.getValueByJPath(jsonresponse, "/data[0]/id");
		  String avatar = TestUtil.getValueByJPath(jsonresponse, "/data[0]/avatar");
		  String firstName = TestUtil.getValueByJPath(jsonresponse, "/data[0]/first_name");

		  
		  System.out.println(lastname);
		  System.out.println(id);
	      System.out.println(avatar);
	      System.out.println(firstName);


		

		
		

		Header[] headerarray = closeableHttpResponse.getAllHeaders();
		int length = headerarray.length;
        System.out.println("No of headers----->" + length);
        

		HashMap<String, String> allheaders = new HashMap<String, String>();

		for (Header header : headerarray) {

			allheaders.put(header.getName(), header.getValue());

		}

		System.out.println("Headersarray------>" + allheaders);
		
	}
	
   
   @Test(priority=2)
	public void getTestWithHeaders() throws ClientProtocolException, IOException {
		
		restclient = new RestClient();
		
		
		HashMap<String, String> headermap = new HashMap<String, String>();
		
		headermap.put("Content-Type", "application/json"); 
		
		/*headermap.put("username", "test@amazon.com"); 
		headermap.put("password", "test213"); 
		headermap.put("Auth Token", "12345");*/ 

		
		
		closeableHttpResponse=restclient.get(url,headermap);
		
		
		
		//1.status code
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ----->" + statusCode);
		
		Assert.assertEquals("status code is not 200", RESPONSE_STATUS_CODE_200, statusCode);
		

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonresponse = new JSONObject(responseString);
       System.out.println("JSON Response from API------->" + jsonresponse);
       
       //Single value assertion 
       
		
		//Validate Pagevalue
		String perpagevalue = TestUtil.getValueByJPath(jsonresponse, "/per_page");
		System.out.println("value of perpage----->" +perpagevalue);
		Assert.assertEquals(Integer.parseInt(perpagevalue), 3);		
		
		
		//Validate total value
		String totalvalue = TestUtil.getValueByJPath(jsonresponse, "/total");
		System.out.println("value of total----->" +totalvalue);
		org.testng.Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		
		//***GET THE VALUE FROM JSON ARRAY*****//
		
		  String lastname = TestUtil.getValueByJPath(jsonresponse, "/data[0]/last_name");
		  String id = TestUtil.getValueByJPath(jsonresponse, "/data[0]/id");
		  String avatar = TestUtil.getValueByJPath(jsonresponse, "/data[0]/avatar");
		  String firstName = TestUtil.getValueByJPath(jsonresponse, "/data[0]/first_name");

		  
		  System.out.println(lastname);
		  System.out.println(id);
	      System.out.println(avatar);
	      System.out.println(firstName);


		

		
		

		Header[] headerarray = closeableHttpResponse.getAllHeaders();
		int length = headerarray.length;
       System.out.println("No of headers----->" + length);
       

		HashMap<String, String> allheaders = new HashMap<String, String>();

		for (Header header : headerarray) {

			allheaders.put(header.getName(), header.getValue());

		}

		System.out.println("Headersarray------>" + allheaders);
		
	
	
	 
	
	
	
	

	

	

}
}