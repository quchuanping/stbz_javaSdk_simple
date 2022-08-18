胜天半子－聚合供应链JAVA版本SDK

SDK需要Apache Httpclient4.5.12 和 Gson支持

支持包Maven地址
&lt;!-- apache HttpClient --&gt;

&lt;dependency&gt;

&lt;groupId&gt;org.apache.httpcomponents&lt;/groupId&gt;
  
&lt;artifactId&gt;httpclient&lt;/artifactId&gt;
  
&lt;version&gt;4.5.12&lt;/version&gt;
  
&lt;/dependency&gt;

&lt;!-- https://mvnrepository.com/artifact/com.google.code.gson/gson --&gt;

&lt;dependency&gt;

&lt;groupId&gt;com.google.code.gson&lt;/groupId&gt;

&lt;artifactId&gt;gson&lt;/artifactId&gt;

&lt;/dependency&gt;


meave本地私有包可将源码下载后自行打包


SDK基本用法示例

    	
        String appKey = "";     //appkey
        String appSecret = "";    //app秘钥

        //获取api
        ApiClient apiClient = new ApiClient(appKey, appSecret);
        apiClient.setDebug(true); //设置调试模式

        TreeMap<String,Object> paramsMap = new TreeMap<>();
        paramsMap.put("page",1);
        paramsMap.put("limit",10);
        String result = apiClient.exec(RequestMethod.GET,"/v2/Goods/Lists",paramsMap);
        System.out.print(result);
      
技术支持: quchuanping@qq.com    
