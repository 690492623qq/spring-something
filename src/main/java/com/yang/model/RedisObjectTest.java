package com.yang.model;

/**
 * User: jianyuanyang
 * Date: 13-8-9
 * Time: 下午3:14
 */
public class RedisObjectTest {

    private long id ;

    private String name ;

    private String email ;


    public RedisObjectTest(){};

    public RedisObjectTest(long id,String name,String email){
        this.id = id ;
        this.name = name ;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
