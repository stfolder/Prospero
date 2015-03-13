package sstar.prospero.report;

import java.io.OutputStream;

/**
 * Created by Sergey.Tarasenko on 11.03.2015.
 */
public interface ReportGenerator {
    public void generate(String template, String jsonData, OutputStream os);
}
