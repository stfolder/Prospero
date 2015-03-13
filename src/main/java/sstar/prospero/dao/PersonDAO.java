package sstar.prospero.dao;

import sstar.prospero.entity.Person;

import java.util.List;

/**
 * Created by Sergey.Tarasenko on 26.02.2015.
 */
public interface PersonDAO {
    void addPerson(Person person);
    void deleteById(int personId);
    Person getPersonById(int personId);
    public List<Person> getPeople();
    void persistPersonProperties(Person person);
    public void setPersonProperty(int personId, String propertyId, String propertyValue);
    public void loadPersonProperties(Person person);
}
