//package com.hww.es.util;
//
//import java.util.Properties;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.github.pagehelper.PageHelper;
//
//@Configuration
//public class PageHelperConfig {
//
//    /**
//     * 注入pagehelper配置
//     * 
//     * @return
//     */
//    @Bean
//    public PageHelper getPageHelper() {
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("offsetAsPageNum","true");
//        properties.setProperty("rowBoundsWithCount","true");
//        properties.setProperty("reasonable","true");
//        properties.setProperty("dialect","mysql");  
//        pageHelper.setProperties(properties);
//        return pageHelper;
//    }
//
//}
