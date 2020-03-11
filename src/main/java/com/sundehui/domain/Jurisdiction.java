package com.sundehui.domain;

import org.springframework.stereotype.Component;

@Component
public class Jurisdiction {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}