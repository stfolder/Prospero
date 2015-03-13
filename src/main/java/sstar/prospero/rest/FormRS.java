package sstar.prospero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import sstar.prospero.dao.OperationDAO;
import sstar.prospero.dao.PersonDAO;
import sstar.prospero.entity.Form;
import sstar.prospero.entity.FormProperty;
import sstar.prospero.entity.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Created by Sergey.Tarasenko on 09.03.2015.
 */

@Path("form")
public class FormRS extends SpringResource{
    
    @Autowired
    OperationDAO operationDAO;
    @Autowired
    PersonDAO personDAO;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DForm getForm(@QueryParam("formid") String formId, @QueryParam("personid") String personId) {
        DForm dForm = new DForm();
        dForm.setAction("/service/form/save");
        Form form = new Form();
        form.setFormId(Integer.parseInt(formId));
        Person person = new Person();
        person.setId(Integer.parseInt(personId));
        List<FormProperty> fps = operationDAO.getFProperties(form, person);
        List<DForm.Field> fields = new ArrayList<DForm.Field>();
        for(FormProperty fp:fps) {
            fields.add(buildProperty(fp));
        }
        fields.add(new DForm.Field("formid",formId,"hidden"));
        fields.add(new DForm.Field("personid",personId,"hidden"));
        //fields.add(new DForm.Field("","Далее","submit"));
        dForm.setHtml(fields);
        return dForm;
    }

    @POST
    @Path("save")
    @Consumes("application/x-www-form-urlencoded")
    public void saveForm(MultivaluedMap<String, String> formParams) {
        Person person = new Person();
        person.setId(Integer.parseInt(formParams.get("personid").get(0)));
        Map<String,String> properties = new HashMap<>();
        for(Iterator<String> it = formParams.keySet().iterator();it.hasNext();) {
            String key = it.next();
            if(key.equals("formid")||key.equals("personid")) continue;
            FormProperty fp = new FormProperty();
            properties.put(key,formParams.get(key).get(0));
        }
        person.setProperties(properties);
        personDAO.persistPersonProperties(person);
    }
    
    private DForm.Field buildProperty(FormProperty fp){
        DForm.Field dField =  new DForm.Field();
        dField.setCaption(fp.getCaption());
        dField.setName(fp.getPropertyId());
        dField.setType(fp.getPtype());
        dField.setValue(fp.getValue()==null?"":fp.getValue());
        return dField;
    }
}

