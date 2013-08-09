package com.yang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jianyuanyang
 * Date: 13-8-9
 * Time: 下午4:13
 */
@Controller
@RequestMapping("redisHash")
public class RedisHashTestController {
    @Autowired
    private RedisTemplate redisTemplate ;

    /**
     * Hash update 木有插入，有更新
     * @param id
     * @param name
     * @param email
     */
    @RequestMapping("/putAll")
    public void putAll(String id,String name,String email){

        final String key = String.format("redisObjectTest:%s", id);

        final Map<String, Object> properties = new HashMap<String, Object>();

        properties.put("id", id);
        properties.put("name",name);
        properties.put("email",email);

        redisTemplate.opsForHash().putAll(key, properties);
    }

    /**
     * 删除整个Hash
     * @param id
     */
    @RequestMapping("/delete")
    public void delete(String id){
        final String key = String.format("redisObjectTest:%s", id);
        redisTemplate.delete(key);
    }

    /**
     * 删除Hash中某个field
     * @param id
     */
    @RequestMapping("/remove")
    public void remove(String id){
        final String key = String.format("redisObjectTest:%s", id);
        redisTemplate.opsForHash().delete(key,"id");
    }

    @RequestMapping("/put")
    public void put(String id){
        final String key = String.format("redisObjectTest:%s", id);
        redisTemplate.opsForHash().put(key,id,"mytest2");
    }

    /**
     * 根据key 获取 Map
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public @ResponseBody Map get(String id){
        String key = String.format("redisObjectTest:%s", id);
        return redisTemplate.opsForHash().entries(key);
    }

}

