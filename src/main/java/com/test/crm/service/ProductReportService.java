package com.test.crm.service;

import com.test.crm.model.Product;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ProductReportService {

    void report(List<Product> products) throws IOException, JRException, ParseException;
}
