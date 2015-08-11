package sstar.prospero.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import sstar.prospero.entity.AppUser;
import sstar.prospero.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Sergey.Tarasenko on 26.02.2015.
 */
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {


    @Override
    public Integer getFormsQty(String userName) {
        List<Integer> count = getJdbcTemplate().query("select count(distinct action_data) from APPUSER_LOG " +
                "where action_name='form.generate' and user_login=?"
                , new Object[]{ userName}, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt(1);
            }
        });
        if(count.size()>0) {
            return count.get(0);
        }
        return null;
    }

    @Override
    public void logAction(String userName, String actionName, String actionData) {
        getJdbcTemplate().update("INSERT INTO APPUSER_LOG VALUES(?,?,?,?)", userName, new Date(),actionName, actionData);
    }

    @Override
    public void addUser(AppUser appUser) {
        getJdbcTemplate().update("INSERT INTO APPUSER(user_login, fio, password) VALUES (?, ?, ?)",
                appUser.getUserLogin(), appUser.getFio(), appUser.getPassword());
    }

    @Override
    public List<AppUser> getAppUsersList() {
        List<AppUser> users = getJdbcTemplate().query("SELECT user_login, fio, password FROM appuser order by user_login", new RowMapper<AppUser>() {
            @Override
            public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
                AppUser appUser = new AppUser();
                appUser.setUserLogin(rs.getString(1));
                appUser.setFio(rs.getString(2));
                appUser.setPassword(rs.getString(3));
                return appUser;
            }
        });
        return users;
    }

    @Override
    public void deleteByLogin(String login) {
        getJdbcTemplate().update("DELETE FROM APPUSER WHERE user_login=?", login);
    }


}
