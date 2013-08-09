package com.yang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: jianyuanyang
 * Date: 13-8-9
 * Time: 上午10:44
 */
@Controller
@RequestMapping("redisList")
public class RedisListTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * left 压stack
     * @param key
     * @param value
     */
    @RequestMapping(value="/setListLeft", method= RequestMethod.GET)
    public @ResponseBody
    Long setListLeft(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * right 压stack
     * @param key
     * @param value
     */
    @RequestMapping(value="/setListRight", method= RequestMethod.GET)
    public @ResponseBody Long setListRight(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * left 出stack
     * @param key
     * @return
     */
    @RequestMapping(value="/getListLeft", method= RequestMethod.GET)
    public @ResponseBody String getListLeft(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * right 出stack
     * @param key
     * @return
     */
    @RequestMapping(value="/getListRight", method= RequestMethod.GET)
    public @ResponseBody String getListRight(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取list
     * @param key
     * @return
     */
    @RequestMapping(value="/rangeList", method= RequestMethod.GET)
    public @ResponseBody
    List<String> range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除 key index value
     *
     * @param key
     * @param index
     * @param value
     */
    @RequestMapping(value="/remove", method= RequestMethod.GET)
    public @ResponseBody Long removeValueByKeyAndIndex(String key, long index, String value) {
       return redisTemplate.opsForList().remove(key, index, value);
    }

    /**
     * 检索 key index 获取value
     *
     * @param key
     * @param index
     * @return
     */
    @RequestMapping(value="/searchIndex", method= RequestMethod.GET)
    public  @ResponseBody String searchIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 根据key index value 置值
     *
     * @param key
     * @param index
     * @param value
     */
    @RequestMapping(value="/reSet", method= RequestMethod.GET)
    public  void reSet(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 获取size
     *
     * @param key

     */
    @RequestMapping(value="/size", method= RequestMethod.GET)
    public  @ResponseBody Long size(String key) {
        return  redisTemplate.opsForList().size(key);
    }
}
