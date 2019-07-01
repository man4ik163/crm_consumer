package com.test.crm.service;

import com.test.crm.model.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    public List<Map<String, Object>> report(List<Product> products) throws ParseException;
}
