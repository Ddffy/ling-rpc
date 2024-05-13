package com.ling.example.common.model;

import java.io.Serializable;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
