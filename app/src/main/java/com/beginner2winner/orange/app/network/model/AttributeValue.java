package com.beginner2winner.orange.app.network.model;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class AttributeValue {

    private String nonML;
    private String value;

    public AttributeValue(String nonML, String value) {
        this.nonML = nonML;
        this.value = value;
    }

    public String getNonML() {
        return this.nonML;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
