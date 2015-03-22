package sstar.prospero.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;


import java.io.*;

/**
 * Created by Sergey.Tarasenko on 11.03.2015.
 */
public class JasperReportGenerator implements ReportGenerator{
    private String templateBase;


    public void generateXLS(String template, String jsonData, OutputStream os) {
        JsonDataSource ds=null;
        byte[] result;
        try {
            ds = new JsonDataSource(new ByteArrayInputStream(jsonData.getBytes()));
            JasperPrint jprint = JasperFillManager.fillReport(new FileInputStream(templateBase+template), null, ds);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(jprint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();

        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePDF(String template, String jsonData, OutputStream os) {
        JsonDataSource ds=null;
        byte[] result;
        try {
            ds = new JsonDataSource(new ByteArrayInputStream(jsonData.getBytes()));
            JasperPrint jprint = JasperFillManager.fillReport(new FileInputStream(templateBase+template), null, ds);
            JasperExportManager.exportReportToPdfStream(jprint,os);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTemplateBase(String templateBase) {
        this.templateBase = templateBase;
    }



    public  static void main(String ... args) throws IOException{
        JasperReportGenerator rg = new JasperReportGenerator();
        rg.setTemplateBase("report_templates/");
        rg.generateXLS("uvedomlenie.jrxml", "{\"firstName\":\"Сергей\", \"lastName\":\"Тарасенко\", \"middleName\":\"Сергеевич\"}", new FileOutputStream("test.xls"));
    }
}
