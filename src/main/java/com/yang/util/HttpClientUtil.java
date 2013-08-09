package com.yang.util;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: jianyuanyang
 * Date: 13-5-11
 * Time: 下午2:50
 */
public class HttpClientUtil {

    /**
     * 设置 HttpClient 属性 retryHandler,超时时间
     * @return   DefaultHttpClient
     */
    private static DefaultHttpClient getHttpClient(){
        //设置请求超时参数
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 2000);
        HttpConnectionParams.setSoTimeout(params, 3000);

        DefaultHttpClient httpclient = new DefaultHttpClient(params);
        //设置重连3次条件
        httpclient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3,false));
        return httpclient ;
    }

    /**
     * 获取get 请求返回数据
     * @param getUrl   请求url
     * @return   字符串
     */
    public static String httpGet(String getUrl){
       return httpGetMethod(getUrl);
    }


    public static String httpPost(String postUrl,List<NameValuePair> nameValuePairList){
        return httpPostMethod(postUrl, nameValuePairList, false);
    }


    public static String httpPostJson(String postUrl,List<NameValuePair> nameValuePairList){
        return httpPostMethod(postUrl, nameValuePairList, true);
    }



    /**
     * get 方式请求数据
     * @param getUrl  请求url
     * @return 字符串数据
     */
    private static String httpGetMethod(String getUrl){
        String output = null ;
        DefaultHttpClient defaultHttpClient = getHttpClient();
        HttpGet httpget = new HttpGet(getUrl);

        if (getUrl.indexOf("https://")!=-1){
            /*Protocol.registerProtocol("https",
                    new Protocol("https", new MySSLSocketFactory(), 443));*/
        }

        try {
            output = dealResponse(defaultHttpClient.execute(httpget));
        } catch (IOException e) {
            e.printStackTrace();
            return "" ;
        }finally {//释放请求链接
            defaultHttpClient.getConnectionManager().shutdown();
        }
        return output == null ? "" :output ;
    }



    /**
     * post 方式请求数据
     * @param postUrl  请求url
     * @param nameValuePairList  参数
     * @param isJsonType  返回数据是否是json格式
     * @return 字符串数据
     */
    private static String httpPostMethod(String postUrl,List<NameValuePair> nameValuePairList,boolean isJsonType){
        String output = null ;
        DefaultHttpClient defaultHttpClient = getHttpClient();
        HttpPost httppost = new HttpPost(postUrl);

        if(nameValuePairList != null){
            //post json 格式数据
            if(isJsonType){
                StringEntity entity = new StringEntity(JsonUtil.list2JsonStr(nameValuePairList), Consts.UTF_8);
                entity.setContentType("application/json");
                httppost.setEntity(entity);
            }else{//post form 数据
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);
                httppost.setEntity(entity);
            }
        }

        try {
            output = dealResponse(defaultHttpClient.execute(httppost));
        }catch(IOException e){
            e.printStackTrace();
            return "" ;
        } finally{//释放请求链接
            defaultHttpClient.getConnectionManager().shutdown();
        }
        return output == null ? "" :output ;
    }


    /**
     * 处理response 返回数据
     * @param response
     * @return 请求响应数据
     */
    private static String dealResponse(HttpResponse response) throws IOException{
        String output = null;

       //请求成功
       if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            if(null!=httpEntity){
                output = EntityUtils.toString(httpEntity);
            }
        }
        return output == null ? "" :output ;
    }



    public static void main(String[] args) {

       /* String jsonStr =  httpGet("http://tao.stage.bang5mai.com/b5mclist.do") ;
        System.out.println("jsonStr---"+jsonStr);
        Map map =  JsonUtil.JsonStr2Map(jsonStr);
        for(Object obj :map.values()){
            if(obj.getClass().isInstance(List.class)){
                for(Object obj2 :(List)obj){
                    System.out.println("value2---"+obj2);
                }
            }else{
                System.out.println("value1---"+obj);
            }

        }
        System.out.println("json-----"+httpGet("http://tao.stage.bang5mai.com/b5mclist.do"));*/


        //get
        String getUrl = "https://api.weibo.com/2/users/show.json" ;
        String jsonStr1 =  httpGet(getUrl);
        System.out.println("get--json--url--"+jsonStr1);

        //post
        String postUrl = "https://api.weibo.com/oauth2/access_token" ;
        List<NameValuePair> list  = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("client_secret","123244")) ;
        list.add(new BasicNameValuePair("client_id","123244")) ;
        list.add(new BasicNameValuePair("grant_type","123244")) ;
        String jsonStr2 = httpPostJson(postUrl,list);
        System.out.println("post--json--Url--"+jsonStr2);

       list.isEmpty();


    }



}
