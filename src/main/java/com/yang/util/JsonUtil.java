package com.yang.util;


import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jackson 简单使用
 * User: jianyuanyang
 * Date: 13-5-11
 * Time: 下午6:15
 */
public class JsonUtil {


    /**
     * Map 转化为 jsonStr
     * @param map
     * @return  字符串
     */
    public static String Map2JsonStr(Map map){
        return Object2JsonStr(map) ;
    }

    /**
     * List 转化为 jsonStr
     * @param list
     * @return 字符串
     */
    public static String list2JsonStr(List list){
        return Object2JsonStr(list) ;
    }


    /**
     * json 格式字符串 转化为  Map
     * @param jsonStr  json 格式字符串
     * @return  Map
     */
    public static Map JsonStr2Map(String jsonStr){
        return JsonStr2Object(jsonStr,Map.class) ;
    }


    /**
     * json 格式字符串 转化为 java 泛型
     * @param jsonStr  json 格式字符串
     * @param valueType java 泛型
     * @return java 对象
     */
    private static <T> T JsonStr2Object(String jsonStr,Class<T> valueType ){

        if (StringUtils.isBlank(jsonStr)){
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            return objectMapper.readValue(jsonStr,valueType);
        }catch (Exception e){
            e.printStackTrace();
            return null ;
        }
    }


    /**
     * java对象 转化为 String
     * @param obj java 对象
     * @return   字符串
     */
    private static String Object2JsonStr(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        Map map = new HashMap();
        //map.put("key","key_123");
        //map.put("value","123");
        //map.put("value","123");
        System.out.println(Map2JsonStr(null));
        System.out.println(JsonStr2Map(null));
    }

}
