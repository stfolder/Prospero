package sstar.prospero.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey.Tarasenko on 26.02.2015.
 */
public class Person {
    private int id;
    private Date created;
    private String label;
    private int puserId;
    private Map<String,String> properties = new HashMap<String, String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPuserId() {
        return puserId;
    }

    public void setPuserId(int puserId) {
        this.puserId = puserId;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
