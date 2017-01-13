package com.example.service;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.UserMapper;
import com.example.model.Users;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: zf
 * Date: 2016/12/26  13:33
 * Description:
 */
@Service
public class UserService {

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;

    public Users findOne(Integer id) {
        Users one = userMapper.findOne(id);
        logger.info("查询用户："+ JSONObject.toJSONString(one));
        return one;
    }

    @Transactional
    public int updateUser(Users u){
        int i = userMapper.updateByPrimaryKeySelective(u);
        return i;
    }

    public List<Users> list() {
        return userMapper.select(null);
    }
}
