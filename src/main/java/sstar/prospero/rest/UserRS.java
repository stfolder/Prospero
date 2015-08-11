package sstar.prospero.rest;

import org.springframework.beans.factory.annotation.Autowired;
import sstar.prospero.dao.PersonDAO;
import sstar.prospero.dao.UserDAO;
import sstar.prospero.entity.AppUser;
import sstar.prospero.entity.Person;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey.Tarasenko on 07.03.2015.
 */

@Path("user")
public class UserRS extends SpringResource{

    @Autowired
    UserDAO userDAO;
   
    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStats(@Context HttpServletRequest request) {
        String userName = request.getRemoteUser();
        int qty = userDAO.getFormsQty(userName);

        return "{\"username\":\""+userName+"\", \"todayQty\": \""+ qty +"\"}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(AppUser appUser) {
        userDAO.addUser(appUser);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppUser> listUsers() {
        return userDAO.getAppUsersList();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public Response deletePerson(@PathParam("id") String userLogin) {
        userDAO.deleteByLogin(userLogin);
        return Response.status(200).entity(userLogin).build();
    }
}
