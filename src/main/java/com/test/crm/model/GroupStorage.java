package com.test.crm.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class GroupStorage implements Serializable {

    private List<Group> groups = new ArrayList<>();

    public void add(Group group) {
        groups.add(group);
    }

    public void clear() {
        groups.clear();
    }

    public List<Group> getAll() {
        return groups;
    }

    public List<Group> addAll(List<Group> groupList) {
        if (groupList != null) groups.addAll(groupList);
        return groups;
    }
}
