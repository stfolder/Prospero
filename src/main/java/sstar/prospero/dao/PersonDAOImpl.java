package sstar.prospero.dao;

import org.springframework.jdbc.core.RowCallbackHandler;
import sstar.prospero.entity.Person;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey.Tarasenko on 26.02.2015.
 */
public class PersonDAOImpl  extends JdbcDaoSupport implements PersonDAO {
    @Override
    public void addPerson(final Person person) {
        
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO PERSON(LABEL,CREATE_DATE) VALUES(?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"PERSON_ID"});
                stmt.setString(1,person.getLabel());
                stmt.setDate(2, new java.sql.Date(person.getCreated().getTime()));
                return stmt;
            }
        }, keyHolder);
        person.setId(keyHolder.getKey().intValue());
        persistPersonProperties(person);
    }

    @Override
    public void deleteById(int personId) {
        getJdbcTemplate().update("DELETE FROM PERSON WHERE PERSON_ID=?", personId);
        getJdbcTemplate().update("DELETE FROM P_PROPERTY WHERE PERSON_PERSON_ID=?", personId);
    }

    @Override
    public Person getPersonById(int personId) {
        List<Person> people = getJdbcTemplate().query("select person_id,LABEL,CREATE_DATE from person where person_id="+
                personId, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person person = new Person();
                person.setId(resultSet.getInt(1));
                person.setLabel(resultSet.getString(2));
                person.setCreated(resultSet.getDate(3));
                return person;
            }
        });
        if(people.size()>0) {
            return people.get(0);
        }
        return null;
    }

    @Override
    public List<Person> getPeople() {
        List<Person> people = getJdbcTemplate().query("select person_id,LABEL,CREATE_DATE from person order by create_date desc",
                new RowMapper<Person>() {
                    @Override
                    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                        Person person = new Person();
                        person.setId(resultSet.getInt(1));
                        person.setLabel(resultSet.getString(2));
                        person.setCreated(resultSet.getDate(3));
                        return person;
                    }
                });

        return people;
    }

    @Override
    public void persistPersonProperties(Person person) {
        Map<String,String> properties = person.getProperties();
        if(properties == null) return;
        
        for(Iterator<String> iter = properties.keySet().iterator();iter.hasNext();) {
            String propertyId = iter.next();
            String propertyValue = properties.get(propertyId);
            setPersonProperty(person.getId(),propertyId,propertyValue);
        }
    }
    
    public void setPersonProperty(int personId, String propertyId, String propertyValue) {
        int updated = getJdbcTemplate().update("UPDATE P_PROPERTY SET P_VALUE=? WHERE " +
                "PERSON_PERSON_ID=? AND PROPERTY_PROPERTY_ID=?",propertyValue,personId,propertyId);  
        if(updated==0) {
            getJdbcTemplate().update("INSERT INTO P_PROPERTY VALUES(?,?,?)",personId,propertyId,propertyValue);
        }
    }

    @Override
    public void loadPersonProperties(Person person) {
        final Map<String,String> properties = new HashMap<>();
        getJdbcTemplate().query("select property_property_id, p_value from p_property where person_person_id="+
                person.getId(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                properties.put(resultSet.getString(1),resultSet.getString(2));
            }
        });
        person.setProperties(properties);
    }

}
