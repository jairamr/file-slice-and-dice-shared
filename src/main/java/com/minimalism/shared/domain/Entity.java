package com.minimalism.shared.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.text.CaseUtils;

public class Entity {
    private String name;
    private String targetDomainName;
    private Map<String, Field> fields;

    public Entity() {
        this.fields = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = hyphensToCamelCase(name);
    }

    public String getTargetDomainName() {
        return targetDomainName;
    }

    public void setTargetDomainName(String targetDomainName) {
        this.targetDomainName = targetDomainName;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public List<Field> getListOfFields() {
        return this.fields.values().stream().collect(Collectors.toList());
    }

    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }

    public void setFields(List<Field> listOfFields) {
        for(Field f : listOfFields) {
            this.fields.put(f.getName(), f);
        }
    }
    
    public void addField(Field field) {
        this.fields.put(field.getName(), field);
    }

    public Field getField(String fieldName) {
        return this.fields.get(fieldName);
    }

    public Object getFieldValue(String fieldName) {
        return this.fields.get(fieldName).getValue();
    }

    public JsonObject asJsonObject() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        
        JsonArrayBuilder fieldsBuilder = Json.createArrayBuilder();
        for(Field f : this.fields.values()) {
            fieldsBuilder.add(f.asJsonObject());
        }
        return builder
            .add("name", this.getName())
            .add("targetDomainName", this.getTargetDomainName())
            .add("fields", fieldsBuilder)
            .build();
    }

    private String hyphensToCamelCase(String input) {
        if(input.contains("-")) {
            return CaseUtils.toCamelCase(input, true, '-');
        } else {
            return CaseUtils.toCamelCase(input, true);
        }
    }
}
