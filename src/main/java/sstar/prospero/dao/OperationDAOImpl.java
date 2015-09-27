package sstar.prospero.dao;

import sstar.prospero.entity.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergey.Tarasenko on 01.03.2015.
 */
public class OperationDAOImpl extends JdbcDaoSupport implements OperationDAO {
    
    @Override
    public List<Operation> getOperations(Integer parentId) {
        String sql = "select operation_id,caption from operation";
        if(parentId!=null) {
            sql+=" where parent_id="+parentId.toString();
        } else {
            sql+=" where parent_id is null";
        }
        List<Operation> operations = getJdbcTemplate().query(sql, new RowMapper<Operation>() {
            @Override
            public Operation mapRow(ResultSet resultSet, int i) throws SQLException {
                Operation op = new Operation();
                op.setOperationId(resultSet.getInt(1));
                op.setCaption(resultSet.getString(2));
                return op;
            }
        });  
        
        return operations;
    }

    @Override
    public Form getFormById(int formId) {
        List<Form> forms = getJdbcTemplate().query("select * from form a "+
                "where a.form_id=" + formId, new RowMapper<Form>() {

            @Override
            public Form mapRow(ResultSet resultSet, int i) throws SQLException {
                Form form = new Form();
                form.setFormId(resultSet.getInt(1));
                form.setCaption(resultSet.getString(2));
                form.setTemplate(resultSet.getString(3));
                return form;
            }
        });
        
        return forms.size()>0 ? forms.get(0) : null;
    }

    @Override
    public List<Form> getForms(int operationId) {
        List<Form> forms = getJdbcTemplate().query("select * from form a inner join form2operation b " +
                "on a.form_id=b.form_form_id  " +
                "where b.operation_operation_id="+operationId, new RowMapper<Form>() {

            @Override
            public Form mapRow(ResultSet resultSet, int i) throws SQLException {
                Form form = new Form();
                form.setFormId(resultSet.getInt(1));
                form.setCaption(resultSet.getString(2));
                form.setTemplate(resultSet.getString(3));
                return  form;
            }
        });
        return forms;
    }
    public List<FormProperty> getFProperties(Form form, Person person) {
        String sql = "SELECT prop.property_id, prop.caption, prop.multiple, f.required " +
                ", prop.p_type, f.presentation, f.position, p.p_value, prop.extra" +
                " FROM (F_PROPERTY f INNER JOIN PROPERTY prop ON f.property_property_id = prop.property_id) " +
                "LEFT JOIN (SELECT * FROM P_PROPERTY WHERE person_person_id = "+person.getId()+") p ON prop.property_id = p.property_property_id " +
                "WHERE f.form_form_id = "+form.getFormId()+
                " ORDER BY f.position";

        List<FormProperty> formProperties = getJdbcTemplate().query(sql, new RowMapper<FormProperty>() {
            @Override
            public FormProperty mapRow(ResultSet resultSet, int i) throws SQLException {
                FormProperty fp = new FormProperty();
                fp.setPropertyId(resultSet.getString(1));
                fp.setCaption(resultSet.getString(2));
                fp.setMultiple(resultSet.getInt(3)>0);
                fp.setRequired(resultSet.getInt(4)>0);
                fp.setPtype(resultSet.getString(5));
                fp.setPresentation(resultSet.getString(6));
                fp.setPosition(resultSet.getInt(7));
                fp.setValue(resultSet.getString(8));
                fp.setExtra(resultSet.getString(9));
                return fp;
            }
        });


        for(FormProperty property : formProperties) {
            if(! "table".equals(property.getPtype())) {
                continue;
            }

            sql = "select tab_field_name, caption, p_type, multiple,extra, position from TAB_PROPERTY_FIELD where tab_name = '" + property.getPropertyId() + "' order by position";

            List<FormPropertyTabField> fields = getJdbcTemplate().query(sql, new RowMapper<FormPropertyTabField>() {
                @Override
                public FormPropertyTabField mapRow(ResultSet rs, int rowNum) throws SQLException {
                    FormPropertyTabField fpTabField = new FormPropertyTabField();
                    fpTabField.setFieldId(rs.getString(1));
                    fpTabField.setCaption(rs.getString(2));
                    fpTabField.setPtype(rs.getString(3));
                    fpTabField.setMultiple(rs.getInt(4) == 1 ? true : false);
                    fpTabField.setExtra(rs.getString(5));
                    fpTabField.setPosition(rs.getInt(6));
                    return fpTabField;
                }
            });
            property.setTabFields(fields);

        }

        return formProperties;
        
    }
}
