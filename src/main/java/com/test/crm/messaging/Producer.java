package com.test.crm.messaging;

import com.test.crm.model.Group;
import com.test.crm.model.GroupStorage;
import com.test.crm.model.Product;
import com.test.crm.model.ProductStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.group.queue}")
    private String groupQueue;

    @Value("${spring.activemq.product.queue}")
    private String productQueue;

    @Value("${spring.activemq.group.storage.queue}")
    private String groupStorageQueue;

    @Value("${spring.activemq.product.storage.queue}")
    private String productStorageQueue;

    public void send(Group group){
        jmsTemplate.convertAndSend(groupQueue, group);
    }

    public void send(Product product){
        jmsTemplate.convertAndSend(productQueue, product);
    }

    public void send(GroupStorage groupStorage){
        jmsTemplate.convertAndSend(groupStorageQueue, groupStorage);
    }

    public void send(ProductStorage productStorage){
        jmsTemplate.convertAndSend(productStorageQueue, productStorage);
    }
}