package sstar.prospero.rest;

import java.util.List;

/**
 * Created by Sergey.Tarasenko on 10.03.2015.
 */
public class DForm {
    private String method = "post";
    private String action = "";
    private List<Field> html;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Field> getHtml() {
        return html;
    }

    public void setHtml(List<Field> html) {
        this.html = html;
    }

    public static class Field {
        private String name;
        private String type;
        private String caption;
        private String value;
        
        public Field(){};
        public Field(String name, String value, String type){            
            this.name = name;
            this.value = value;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

/*
{
    "action":"index.html",
    "method":"post",
    "html":[
        {
            "type":"fieldset",
            "caption":"User information",
            "html":[
                {
                    "name":"email",
                    "caption":"Email address",
                    "type":"text",
                    "placeholder":"E.g. user@example.com",
                    "validate":{
                        "email":true
                    }
                },
                {
                    "name":"password",
                    "caption":"Password",
                    "type":"password",
                    "id":"registration-password",
                    "validate":{
                        "required":true,
                        "minlength":5,
                        "messages":{
                            "required":"Please enter a password",
                            "minlength":"At least {0} characters long"
                        }
                    }
                },
                {
                    "name":"password-repeat",
                    "caption":"Repeat password",
                    "type":"password",
                    "validate":{
                        "equalTo":"#registration-password",
                        "messages":{
                            "equalTo":"Please repeat your password"
                        }
                    }
                },
                {
                    "type":"radiobuttons",
                    "caption":"Sex",
                    "name":"sex",
                    "class":"labellist",
                    "options":{
                        "f":"Female",
                        "m":"Male"
                    }
                },
                {
                    "type":"checkboxes",
                    "name":"test",
                    "caption":"Receive newsletter about",
                    "class":"labellist",
                    "options":{
                        "updates":"Product updates",
                        "errors":{
                            "value":"security",
                            "caption":"Security warnings",
                            "checked":"checked"
                        }
                    }
                }
            ]
        },
        {
            "type":"fieldset",
            "caption":"Address information",
            "html":[
                {
                    "name":"name",
                    "caption":"Your name",
                    "type":"text",
                    "placeholder":"E.g. John Doe"
                },
                {
                    "name":"address",
                    "caption":"Address",
                    "type":"text",
                    "validate":{ "required":true }
                },
                {
                    "name":"zip",
                    "caption":"ZIP code",
                    "type":"text",
                    "size":5,
                    "validate":{ "required":true }
                },
                {
                    "name":"city",
                    "caption":"City",
                    "type":"text",
                    "validate":{ "required":true }
                },
                {
                    "type":"select",
                    "name":"continent",
                    "caption":"Choose a continent",
                    "options":{
                        "america":"America",
                        "europe":{
                            "selected":"true",
                            "id":"europe-option",
                            "value":"europe",
                            "html":"Europe"
                        },
                        "asia":"Asia",
                        "africa":"Africa",
                        "australia":"Australia"
                    }
                }
            ]
        },
        {
            "type":"submit",
            "value":"Signup"
        }
    ]
}*/