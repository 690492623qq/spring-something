package com.yang.controller;

import com.yang.model.TestBean;
import com.yang.service.IndexService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * User: jianyuanyang
 * Date: 13-4-11
 * Time: 下午1:09
 */
@RequestMapping("/index")
@Controller
public class IndexController {


    @Autowired
    public IndexService indexService ;



    public String index(Model model){
        return "index";

    }


    @RequestMapping("test2")
    public String test2(String type ,Model model){
        if(StringUtils.isBlank(type))
            return "index2";

        return "index";

    }


    @RequestMapping("/test")
    public void test(HttpServletResponse response){
        System.out.println("i am coming test");

        System.out.println(TestBean.myName+"---"+TestBean.myPassword);

        PrintWriter out = null  ;
        try{

            out = response.getWriter() ;
            out.write("this is test");

        } catch(Exception e){

        }finally {
            if(null != out){
                out.close();
                out.flush();
            }
        }

    }


    @RequestMapping("/stringTest")
    public @ResponseBody String stringTest(HttpServletResponse response){
        System.out.println("i am coming test");
        return "thisis test";
    }


    @RequestMapping("sendEmail")
    public void sendEmail(){
         this.indexService.sendEmail("thisistest");
    }



}
