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


    @RequestMapping("/add")
    public  @ResponseBody
    Long add(){
        List<RedisObjectTest> list = new ArrayList<RedisObjectTest>();
        for(int i= 1;i<11;i++){
            long id = i ;
            String name = "name-"+i ;
            String email = "email-"+i;
            list.add(new RedisObjectTest(id,name,email));
        }
        addPipeline(list);

        return null;
    }


    private void addPipeline(final List<RedisObjectTest> list){
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (RedisObjectTest user : list) {
                    byte[] key  = serializer.serialize(user.getId());
                    byte[] name = serializer.serialize(user.getName());
                    byte[] email = serializer.serialize(user.getEmail());
                    connection.setNX(key, name);
                }
                return true;
            }
        }, false, true);
        return result;
    }

}
