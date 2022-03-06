package com.jxhh;

import com.google.gson.Gson;
import com.jxhh.util.MD5Utils;
import com.jxhh.util.Sha1Utils;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


public class ApiClient {

	public static enum RequestMethod {
		GET,GETBODY,HEAD, POST,PUT, PATCH, DELETE, OPTIONS, TRACE
	}

	private String appKey;
	
	private String appSecret;
	
	private static final String apiUrl = "https://api.jxhh.com";
	
	private boolean debug = false;
	
	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public ApiClient(String appKey,String appSecret) throws Exception {

		if(appKey == null || appKey.equals("")) throw new Exception("对象ApiClient: appKey不能为空");
		if(appSecret == null || appSecret.equals("")) throw new Exception("对象ApiClient: appSecret不能为空");
		this.appKey = appKey;
		this.appSecret = appSecret;
		
	}
	
	private String sign(TreeMap<String, Object> params,String json){
		
		String signStr = new String();
		for(String key : params.keySet()) {
			signStr = signStr + key + params.get(key);
		}

		signStr = signStr + appSecret + json;
		signStr = signStr.replaceAll("\\s*", "");
//		System.out.println(signStr);
		String sha1Str = Sha1Utils.getSha1(signStr);
		String md5Str = MD5Utils.stringToMD5(sha1Str);
		return md5Str.toUpperCase();
		
	}
	
	
	private TreeMap<String, Object> getMustParams(){
		
		TreeMap<String, Object> params = new TreeMap<String,Object>();
		params.put("Api-App-Key", appKey);
		params.put("Api-Time-Stamp", System.currentTimeMillis()+"");
		final double d = Math.random();
		final long i = (long)(System.currentTimeMillis()*d);
		params.put("Api-Nonce", MD5Utils.stringToMD5(appKey+i));
		return params;
		
	}
	
	private String getUrlParams(Map<String, Object> mapUrl) {

		if(0 == mapUrl.size()) return "";
		String urlParams = "?";
		for (String key : mapUrl.keySet()) {
			urlParams += key + "=" + mapUrl.get(key) + "&";
		}
		if(urlParams.length() > 2)urlParams = urlParams.substring(0, urlParams.length()-1);
		return urlParams;
	}
	 	
	
	private TreeMap<String, String> getHeaders(TreeMap<String, Object>mustParams){
		
		TreeMap<String, String> headerMap = new TreeMap<String, String>();
        for (String key : mustParams.keySet()) {
        	String value = (String)mustParams.get(key);
        	headerMap.put(key, value);
		}
        
		return headerMap;
		
	}
	
	
	
	private TreeMap<String, Object> trimParams(TreeMap<String, Object> params){
		
	    for (String key : params.keySet()) {
	    	if(params.get(key) instanceof String) {
	    		params.put(key, ((String)params.get(key)).trim());
	    	}
		}
		return params;
	} 


	public String exec(RequestMethod method,String Url,TreeMap<String,Object> params) throws IOException {

		params = trimParams(params);
		String url = apiUrl + Url;
		Gson gson = new Gson();
		String jsonBody = gson.toJson(params);
		if (this.debug) System.err.println("接口请求地址: " + url);

		if (this.debug) System.err.println("接口请求参数: " + jsonBody);
		TreeMap<String, Object> mustParams = getMustParams();

		String result = "";
		switch (method) {
			case GET:
				url += getUrlParams(params);
				params.putAll(mustParams);
				mustParams.put("Api-Sign", sign(params, ""));
				if (this.debug) System.err.println("接口请求Header: " + gson.toJson(getHeaders(mustParams)));
				result = HttpClient.get(url, getHeaders(mustParams));
				break;
			case GETBODY:
				mustParams.put("Api-Sign", sign(mustParams, jsonBody));
				if (this.debug) System.err.println("接口请求Header: " + gson.toJson(getHeaders(mustParams)));
				result = HttpClient.get(url, jsonBody, getHeaders(mustParams));
				break;
			case POST:
				mustParams.put("Api-Sign", sign(mustParams, jsonBody));
				if (this.debug) System.err.println("接口请求Header: " + gson.toJson(getHeaders(mustParams)));
				result = HttpClient.post(url, jsonBody, getHeaders(mustParams));
				break;
			case PATCH:
				mustParams.put("Api-Sign", sign(mustParams, jsonBody));
				if (this.debug) System.err.println("接口请求Header: " + gson.toJson(getHeaders(mustParams)));
				result = HttpClient.patch(url, jsonBody, getHeaders(mustParams));
				break;
			default:
				break;
		}

		if (this.debug) System.err.println("接口返回数据： " + result);

		return result;

	}


}
