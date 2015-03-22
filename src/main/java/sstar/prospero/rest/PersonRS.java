package sstar.prospero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import sstar.prospero.dao.PersonDAO;
import sstar.prospero.entity.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by Sergey.Tarasenko on 07.03.2015.
 */

@Path("person")
public class PersonRS extends SpringResource{
    
    @Autowired
    public PersonDAO personDAO;
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getPeople() {
        List<Person> people = personDAO.getPeople();
        return people;
    }

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        person.setCreated(new Date());
        if(person.getLabel() != null) {
            String[] fio = person.getLabel().split(" ");
            Map properties = new HashMap<String,String>();
            properties.put("lastName",fio[0]);
            if(fio.length>1)
                properties.put("firstName",fio[1]);
            if(fio.length>2)
                properties.put("middleName",fio[2]);
            person.setProperties(properties);
        }
        personDAO.addPerson(person);
        return Response.status(200).entity(person).build();
    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int personId) {
        personDAO.deleteById(personId);
        return Response.status(200).entity(personId).build();
    }
}
