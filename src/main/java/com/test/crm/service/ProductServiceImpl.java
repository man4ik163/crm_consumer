package com.test.crm.service;

import com.test.crm.model.Product;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Map<String, Object>> report(List<Product> products) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Product product : products) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", product.getId());
            item.put("name", product.getName());
            item.put("createdAt", format.format(product.getCreatedAt()));
            item.put("article", product.getArticle());
            item.put("groupId", product.getGroupId().getId());
            result.add(item);
        }
        return result;
    }
}
