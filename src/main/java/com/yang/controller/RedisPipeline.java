package com.yang.controller;

import com.yang.model.RedisObjectTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jianyuanyang
 * Date: 13-8-9
 * Time: 下午3:11
 */
@Controller
@RequestMapping("redisPipeline")
public class RedisPipeline {

    @Autowired
    private RedisTemplate redisTemplate ;




}
