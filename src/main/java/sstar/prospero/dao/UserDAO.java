package sstar.prospero.dao;

import sstar.prospero.entity.AppUser;
import sstar.prospero.entity.Person;

import java.util.List;

/**
 * Created by Sergey.Tarasenko on 26.02.2015.
 */
public interface UserDAO {
    Integer getFormsQty(String userName);
    void logAction(String userName, String actionName, String actionData);
    void addUser(AppUser appUser);
    List<AppUser> getAppUsersList();
    void deleteByLogin(String login);
}
