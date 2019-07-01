package com.test.crm.config;

import com.test.crm.model.GroupStorage;
import com.test.crm.model.ProductStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCofiguration {

    @Bean
    public GroupStorage groupStorage(){
        return new GroupStorage();
    }

    @Bean
    public ProductStorage productStorage(){
        return new ProductStorage();
    }

}
