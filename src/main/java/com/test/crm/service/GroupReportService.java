package com.test.crm.service;

import com.test.crm.model.Group;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.List;

public interface GroupReportService {

    void report(List<Group> groups) throws IOException, JRException;
}
