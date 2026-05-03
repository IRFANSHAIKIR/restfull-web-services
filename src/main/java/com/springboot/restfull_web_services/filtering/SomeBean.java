package com.springboot.restfull_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonView;

public class SomeBean {

    @JsonView(Views.Basic.class)
    private String field1;

    @JsonView(Views.Basic.class)
    private String field2;

    @JsonView(Views.Details.class)
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
