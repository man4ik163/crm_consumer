package com.test.crm.service;

import com.test.crm.model.Group;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupReportServiceImpl implements GroupReportService {

    @Autowired
    private final GroupService groupService;

    @Value("${group.report.path}")
    private String groupReportPath;

    @Value("${group.report.output.path}")
    private String groupReportOutputPath;

    public GroupReportServiceImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void report(List<Group> groups) throws IOException, JRException {
        JasperReport jasperReport;

        InputStream inputStream = JRLoader.getResourceInputStream(groupReportPath);
        jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream));

        Map<String, Object> params = new HashMap<>();
        JRMapArrayDataSource dataSource = new JRMapArrayDataSource(groupService.report(groups).toArray());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);

        File outputFile = new File(groupReportOutputPath);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            Exporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            byteArrayOutputStream.writeTo(fileOutputStream);
        }
    }
}
