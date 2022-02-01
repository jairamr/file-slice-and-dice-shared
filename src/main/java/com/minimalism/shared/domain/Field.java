package com.minimalism.shared.domain;

import java.util.Objects;

import org.apache.commons.text.CaseUtils;

public class Field {
    private String name;
    private String dataType;
    private Object value;

    public Field() {}

    public Field(String name, String dataType, Object value) {
        this.setName(name);
        this.setDataType(dataType);
        this.setValue(value);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        //hyphenate words to camelCase
        this.name = hyphensToCamelCase(name);
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        //to simple class names for supported types... Strings for rest
        toSimpleClassNames(dataType);
        this.dataType = dataType;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    public JsonObject asJsonObject() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder
        .add("name", this.getName())
        .add("type", this.getDataType());

        switch(this.getDataType()) {
            case "String":
                builder.add("value", this.getValue().toString());
            break;
            case "Integer":
                builder.add("value", ((Integer)this.getValue()));
            break;
            case "Long":
                builder.add("value", ((Long)this.getValue()));
            break;
            case "Double":
                builder.add("value", ((Double)this.getValue()));
            break;
            case "Float":
                builder.add("value", ((Float)this.getValue()));
            break;
            case "Boolean":
                builder.add("value", ((Boolean)this.getValue()));
            break;
            default:
                builder.add("value", this.getValue().toString());
            break;
        }
        return builder.build();
    }

    private String hyphensToCamelCase(String input) {
        if(input.contains("-")) {
            return CaseUtils.toCamelCase(input, false, new char[]{'-'});
        } else {
            return input;
        }
    }

    private String toSimpleClassNames(String input) {
        var indexOflastDot = input.lastIndexOf(".");
        String temp = null;
        if(indexOflastDot > 0) {
            temp = input.substring(indexOflastDot);
        } else {
            temp = input;
        }
        if(!(temp.equalsIgnoreCase("string") 
            || temp.equalsIgnoreCase("integer") || temp.equalsIgnoreCase("int") 
            || temp.equalsIgnoreCase("long")
            || temp.equalsIgnoreCase("double")
            || temp.equalsIgnoreCase("float")
            || temp.equalsIgnoreCase("boolean"))) {
                temp = "string";
        }
        if(temp.equalsIgnoreCase("int")) temp = "Integer";
        return CaseUtils.toCamelCase(temp.toLowerCase(), true);
    }

    private void setValueAsTargetType(Object value) {
        try{
            switch(this.getDataType()) {
                case "String":
                this.value = value.toString();
                break;
                case "Inetger":
                if(value instanceof java.lang.Integer) {
                    this.value = value;
                } else {
                    this.value = Integer.parseInt(value.toString());
                }
                break;
                case "Long":
                if(value instanceof java.lang.Long) {
                    this.value = value;
                } else {
                    this.value = Long.parseLong(value.toString());
                }
                break;
                case "Float":
                if(value instanceof java.lang.Float) {
                    this.value = value;
                } else {
                    this.value = Float.parseFloat(value.toString());
                }
                break;
                case "Double":
                if(value instanceof java.lang.Double) {
                    this.value = value;
                } else {
                    this.value = Double.parseDouble(value.toString());
                }
                break;
                case "Boolean":
                if(value instanceof java.lang.Boolean) {
                    this.value = value;
                } else {
                    this.value = Boolean.parseBoolean(value.toString());
                }
                break;
                default:
                this.value = value.toString();
                this.setDataType("string");
                break;
            }
        } catch (NumberFormatException e) {
            // log error
        }
    }

    @Override
    public int hashCode() {
        // each field name must be unique...
        return Objects.hash(this.getName(), this.getDataType());
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) return false;
        if(!(other instanceof Field)) return false;
        return this.hashCode() == other.hashCode();
    }
    
}
