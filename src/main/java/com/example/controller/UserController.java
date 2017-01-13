package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.Users;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: zf
 * Date: 2016/12/26  17:21
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;


    @RequestMapping(value = "/{id}")
    public Object get(@PathVariable("id") Integer id){
        return userService.findOne(id);
    }

    @RequestMapping(value = "/update")
    public Object updateUser(Users user){
        JSONObject result = new JSONObject();
        try {
            int i = userService.updateUser(user);
            if(i == 1){
                logger.info("更新成功！");
                result.put("code",200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新失败！");
            result.put("code",200);
        }
        return result;
    }

    @RequestMapping(value = "/listAll")
    public Object updateUser(){
        JSONObject result = new JSONObject();
        try {
            List<Users> list = userService.list();
            result.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
