package sstar.prospero.dao;

import sstar.prospero.entity.Form;
import sstar.prospero.entity.FormProperty;
import sstar.prospero.entity.Operation;
import sstar.prospero.entity.Person;

import java.util.List;

/**
 * Created by Sergey.Tarasenko on 01.03.2015.
 */
public interface OperationDAO {
    public List<Operation> getOperations(Integer parentId);
    public Form getFormById(int formId);
    public List<Form> getForms(int operationId);
    public List<FormProperty> getFProperties(Form form, Person person);
}
