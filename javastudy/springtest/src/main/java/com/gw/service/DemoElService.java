package com.gw.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:test.properties")
//注入配置文件需使用@PropertySource指定文件地址，若使用@Value注入，则要配置一个PropertySourcesPlaceholderConfigurer的Bean。注意@Value("${book.name}"),使用的是$而不是#。
//注入 Properties 还可以从 Environment 中获得。
public class DemoElService {
    @Value("I LOVE YOU!") //注入普通字符串
    private String normal;

    @Value("#{systemProperties['os.name']}") //注入操作系统属性
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0}") //注入表达式结果
    private String randomNumber;

    @Value("#{demoService.another}") //注入其他Bean属性
    private String fromAnother;

    @Value("classpath:/test.txt") //注入文件资源
    private Resource testFile;

    @Value("http://www.baidu.com") //注入网址资源
    private Resource testUrl;

    @Value("${book.name}") // 注入配置文件
    private String bookName;

    @Autowired // 注入配置文件
    private Environment environment;

    @Bean // 注入配置文件
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public Map<String,Object> outputResource(){
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("normal",normal);
            map.put("osName",osName);
            map.put("randomNumber",randomNumber);
            map.put("fromAnother",fromAnother);
            map.put("txtfile",IOUtils.toString(testFile.getInputStream()));
            map.put("testurl",IOUtils.toString(testUrl.getInputStream()));
            map.put("bookName",bookName);
            map.put("book_author",environment.getProperty("book.author"));
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}