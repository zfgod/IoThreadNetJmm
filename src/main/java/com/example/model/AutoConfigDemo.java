package com.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.beans.Encoder;
import java.io.Serializable;

/**
 * author: zf
 * Date: 2016/12/26  18:25
 * Description:
 */
@Component
@ConfigurationProperties(prefix = "customer",
        locations = {"classpath:config/customer.properties"},
        ignoreInvalidFields = false,
        ignoreUnknownFields = false
)
public class AutoConfigDemo implements Serializable{

    private static final long serialVersionUID = -2963105458703422497L;
    private Logger logger = LoggerFactory.getLogger(AutoConfigDemo.class);
    private String name;

    private Integer age;

    private String[] hobbies;

    public AutoConfigDemo() {
//       logger.info("初始化自定义配置bean。。。");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
