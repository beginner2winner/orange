package com.beginner2winner.orange.app.network.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by richard, © copyright Beginner2Winner Ltd
 */

public class ItemAttribute {

    public enum AttributeBaseType {
        TEXT("text"),
        FLOAT("float"),
        INT("int"),
        LIST("list"),
        ASSET("asset");

        private String name;

        AttributeBaseType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static AttributeBaseType findByname(String name) {
            if (name == null) {
                return null;
            }
            for (AttributeBaseType each : AttributeBaseType.values()) {
                if (each.getName().equalsIgnoreCase(name)) {
                    return each;
                }
            }
            return null;
        }
    }

    private String isNull;
    private String selected;
    private String name;
    private AttributeBaseType baseType;
    private List<AttributeValue> values = new ArrayList<>();

    public ItemAttribute(String isNull, String selected, String name, AttributeBaseType baseType) {
        this.isNull = isNull;
        this.selected = selected;
        this.name = name;
        this.baseType = baseType;
    }

    public Boolean getIsNull() {
        return XmlParserUtil.stringToBoolean(this.isNull);
    }

    public Boolean getSelected() {
        return XmlParserUtil.stringToBoolean(this.selected);
    }

    public String getName() {
        return this.name;
    }

    public AttributeBaseType getBaseType() {
        return this.baseType;
    }

    public List<AttributeValue> getValuesCopy() {
        return new ArrayList<>(this.values);
    }

    public void addValue(AttributeValue value) {
        this.values.add(value);
    }

    @Override
    public String toString() {
        if (this.values.size() == 0) {
            return "";
        } else {
            return this.values.get(0).toString();
        }
    }
}
