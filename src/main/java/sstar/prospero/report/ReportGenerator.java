package sstar.prospero.report;

import java.io.OutputStream;

/**
 * Created by Sergey.Tarasenko on 11.03.2015.
 */
public interface ReportGenerator {
    public void generateXLS(String template, String jsonData, OutputStream os);
    public void generatePDF(String template, String jsonData, OutputStream os);
}
