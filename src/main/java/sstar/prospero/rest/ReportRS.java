package sstar.prospero.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import sstar.prospero.dao.OperationDAO;
import sstar.prospero.dao.PersonDAO;
import sstar.prospero.entity.Form;
import sstar.prospero.entity.Person;
import sstar.prospero.report.ReportGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.*;
import java.util.Map;

/**
 * Created by Sergey.Tarasenko on 11.03.2015.
 */

@Path("report")
public class ReportRS extends SpringResource{
    
    @Autowired 
    ReportGenerator reportGenerator;
    @Autowired
    OperationDAO operationDAO;
    @Autowired
    PersonDAO personDAO;
    

    @GET
    @Path("xls")
    @Produces("application/vnd.ms-excel")
    public Response getReport(@QueryParam("formid") String formId, @QueryParam("personid") String personId){
        String temporaryFileName="cache/"+System.currentTimeMillis()+".xls";
        Form form = operationDAO.getFormById(Integer.parseInt(formId));
        Person person = personDAO.getPersonById(Integer.parseInt(personId));
        personDAO.loadPersonProperties(person);
        try {
            String jsonData = (new ObjectMapper()).writeValueAsString(person.getProperties());
            OutputStream os = new FileOutputStream(temporaryFileName);
            reportGenerator.generateXLS(form.getTemplate(), jsonData, os);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseBuilder rb = Response.ok(new File(temporaryFileName));
        rb.header("Content-Disposition",
                    "inline;filename=report.xls");

        return rb.build();
    }

    @GET
    @Produces("application/pdf")
    public Response getPdfReport(@QueryParam("formid") String formId, @QueryParam("personid") String personId){
        String temporaryFileName="cache/"+System.currentTimeMillis()+".pdf";
        Form form = operationDAO.getFormById(Integer.parseInt(formId));
        Person person = personDAO.getPersonById(Integer.parseInt(personId));
        personDAO.loadPersonProperties(person);
        try {
            String jsonData = (new ObjectMapper()).writeValueAsString(person.getProperties());
            OutputStream os = new FileOutputStream(temporaryFileName);
            reportGenerator.generatePDF(form.getTemplate(), jsonData, os);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseBuilder rb = Response.ok(new File(temporaryFileName));
        rb.header("Content-Disposition",
                "inline;filename=report.pdf");

        return rb.build();
    }

    @Path("json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("personid") String personId){
        Person person = personDAO.getPersonById(Integer.parseInt(personId));
        personDAO.loadPersonProperties(person);
        String jsonData = "{}";
        try {
            jsonData=(new ObjectMapper()).writeValueAsString(person.getProperties());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    
}
