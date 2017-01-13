package com.example.mapper;

import com.example.model.Users;
import com.github.abel533.mapper.Mapper;
//import org.apache.ibatis.annotations.Mapper;
public interface UserMapper extends Mapper<Users> {
    Users findOne(Integer id);
}
