package com.example.controller;

import com.example.model.AutoConfigDemo;
import com.example.model.Users;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: zf
 * Date: 2016/12/23  16:03
 * Description:
 */
@RestController
public class HelloController {

    @Autowired
    AutoConfigDemo customer;

    @RequestMapping(value = "/")
    public Object hello(){
        return "hello";
    }

    @RequestMapping(value = "/customer")
    public Object helloCustomer(){
        return customer;
    }
}
