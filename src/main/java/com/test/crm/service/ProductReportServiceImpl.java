package com.test.crm.service;

import com.test.crm.model.Product;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductReportServiceImpl implements ProductReportService {

    @Autowired
    private final ProductService productService;

    @Value("${product.report.path}")
    private String productReportPath;

    @Value("${product.report.output.path}")
    private String productReportOutputPath;

    @Value("${file.extension.xlsx}")
    private String fileExtensionXlsx;

    public ProductReportServiceImpl(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public void report(List<Product> groups) throws IOException, JRException, ParseException {
        JasperReport jasperReport;

        InputStream inputStream = JRLoader.getResourceInputStream(productReportPath);
        jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(inputStream));

        Map<String, Object> params = new HashMap<>();
        JRMapArrayDataSource dataSource = new JRMapArrayDataSource(productService.report(groups).toArray());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);
        StringBuffer sb = new StringBuffer(30);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        File outputFile = new File(sb.append(productReportOutputPath).append(format.format(new Date())).append(fileExtensionXlsx).toString());
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
