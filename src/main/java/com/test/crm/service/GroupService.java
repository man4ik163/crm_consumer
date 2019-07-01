package com.test.crm.service;

import com.test.crm.model.Group;

import java.util.List;
import java.util.Map;

public interface GroupService {
    public List<Map<String, Object>> report(List<Group> groups);
}
