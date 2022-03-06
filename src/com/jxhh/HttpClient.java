package com.jxhh;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;


/**
 * apache httpclient 4.5.12
 * HttpClient
 * @author Administrator
 *
 */
public class HttpClient{

	
	public static final String get(String url,Map<String, String> headers) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();		
		HttpGet oHttpGet = new HttpGet(url);
		for(String key : headers.keySet()) {
			oHttpGet.setHeader(key,headers.get(key));
		}
	    String responseString = "";
		HttpResponse httpResponse = httpClient.execute(oHttpGet);
	    responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	    httpClient.close();
		return responseString;
		
	}



	public static final String get(String url,String jsonBody,Map<String, String> headers) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGetWithEntity oHttpGet = new HttpGetWithEntity(url);
		headers = pushHeader(headers);
		for(String key : headers.keySet()) {
			oHttpGet.setHeader(key,headers.get(key));
		}
		HttpEntity httpEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
		oHttpGet.setEntity(httpEntity);

		String responseString = "";
		HttpResponse httpResponse = httpClient.execute(oHttpGet);
		responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		httpClient.close();
		return responseString;

	}



	public static final String post(String url, String jsonBody, Map<String,String> headers) throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		headers = pushHeader(headers);
		for(String key : headers.keySet()) {
			httpPost.setHeader(key,headers.get(key));
		}
		HttpEntity httpEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
		httpPost.setEntity(httpEntity);
		String responseString = "";
		HttpResponse httpResponse = httpClient.execute(httpPost);
		responseString = responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		httpClient.close();
		return responseString;
	}


	public static final String patch(String url, String jsonBody, Map<String,String> headers) throws IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPatch httpPatch = new HttpPatch(url);
		headers = pushHeader(headers);
		for(String key : headers.keySet()) {
			httpPatch.setHeader(key,headers.get(key));
		}
		HttpEntity httpEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
		httpPatch.setEntity(httpEntity);

		String responseString = "";
		HttpResponse httpResponse = httpClient.execute(httpPatch);
		responseString = responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		httpClient.close();
		return responseString;
	}


	public static final String put(String url, String jsonBody, Map<String,String> headers) throws IOException {


		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPut httpPut = new HttpPut(url);
		headers = pushHeader(headers);
		for(String key : headers.keySet()) {
			httpPut.setHeader(key,headers.get(key));
		}
		HttpEntity httpEntity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
		httpPut.setEntity(httpEntity);

		String responseString = "";
		HttpResponse httpResponse = httpClient.execute(httpPut);
		responseString = responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		httpClient.close();
		return responseString;

	}



	public static Map<String,String> pushHeader(Map<String,String> headers){

		headers.put("Content-type", "application/json");
		headers.put("Charset", "UTF-8");
		headers.put("Accept", "application/json");
		headers.put("Accept-Charset", "UTF-8");
		return headers;
	}

}
