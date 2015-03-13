package sstar.prospero.entity;

/**
 * Created by Sergey.Tarasenko on 01.03.2015.
 */
public class Form {
    private int formId;
    private String caption;
    private String template;

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
    
    @Override
    public String toString() {
        return "->"+caption;
    }
}
