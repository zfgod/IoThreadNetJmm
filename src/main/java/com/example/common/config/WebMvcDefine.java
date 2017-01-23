package com.example.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * author: zf
 * Date: 2016/12/27  14:14
 * Description:
 */
@Configuration
public class WebMvcDefine extends WebMvcConfigurerAdapter{
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
        //设置不忽略后缀,使用后缀,设置为false后情况如下：
        //@RequestMapping(value="/one/{id}")  路径只能是 contentPath/one/1 ，contentPath/one/1.xx 不行
        //@RequestMapping(value="/one/{id}.do")  路径只能是 contentPath/one/1.do，其他都不行
        configurer.setUseSuffixPatternMatch(false);
		//取消默认配置的忽略点号后面内容 避免在前后分离开发时,页面导航位置和接口位置重合
	}

}
