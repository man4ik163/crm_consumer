package com.test.crm.service;

import com.test.crm.model.Group;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Override
    public List<Map<String, Object>> report(List<Group> groups) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Group group : groups) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", group.getId());
            item.put("name", group.getName());
            item.put("createdAt", format.format(group.getCreatedAt()));
            result.add(item);
        }
        return result;
    }

//    @Override
//    public Map<String, Object> report(List<Group> groups) {
//        Map<String, Object> item = new HashMap<String, Object>();
//        for (Group group : groups) {
//            item.put("id", group.getId());
//            item.put("name", group.getName());
//            item.put("createdAt", group.getCreatedAt());
//        }
//        return item;
//    }
}
