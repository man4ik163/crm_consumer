package com.test.crm.messaging;

import com.test.crm.service.GroupReportService;
import com.test.crm.model.Group;
import com.test.crm.model.GroupStorage;
import com.test.crm.model.Product;
import com.test.crm.model.ProductStorage;
import com.test.crm.service.ProductReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
public class Consumer {

    @Autowired
    private final GroupReportService groupReportService;

    @Autowired
    private final ProductReportService productReportService;

    public Consumer(GroupReportService groupReportService, ProductReportService productReportService) {
        this.groupReportService = groupReportService;
        this.productReportService = productReportService;
    }

    @JmsListener(destination = "${spring.activemq.group.queue}", containerFactory="jsaFactory")
    public void receiveGroup(Group group){
        System.out.println("Recieved Message: " + group);
    }

    @JmsListener(destination = "${spring.activemq.product.queue}", containerFactory="jsaFactory")
    public void receiveProduct(Product product){
        System.out.println("Recieved Message: " + product);
    }

    @JmsListener(destination = "${spring.activemq.group.storage.queue}", containerFactory="jsaFactory")
    public void receiveGroupStorage(GroupStorage groupStorage) throws IOException, JRException {
        groupReportService.report(groupStorage.getAll());
        System.out.println("Recieved Message: " + groupStorage);
    }

    @JmsListener(destination = "${spring.activemq.product.storage.queue}", containerFactory="jsaFactory")
    public void receiveProductStorage(ProductStorage productStorage) throws IOException, JRException, ParseException {
        productReportService.report(productStorage.getAll());
        System.out.println("Recieved Message: " + productStorage);
    }
}
