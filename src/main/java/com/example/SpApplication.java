package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")//mybatis mapper接口扫描
@EnableTransactionManagement //启注解事务管理.等同于xml配置方式的<tx:annotation-driven/>
public class SpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpApplication.class, args);
	}
}
