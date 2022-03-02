package com.minimalism.shared.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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
        if(name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Entity name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getTargetDomainName() {
        return targetDomainName;
    }

    public void setTargetDomainName(String targetDomainName) {
        if(targetDomainName == null || targetDomainName.isEmpty() || targetDomainName.isBlank()) {
            targetDomainName = "target.domain.name";
        }
        this.targetDomainName = targetDomainName;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public List<Field> getListOfFields() {
        return this.fields.values().stream().toList();
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

    @Override
    public String toString() {
        String returnValue = null;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        fields.values().forEach(f -> arrayBuilder.add(f.asJson()));
        JsonObjectBuilder fieldsBuilder = Json.createObjectBuilder();
        this.fields.entrySet().stream().map((Map.Entry<String, Field> e) ->
            Json.createObjectBuilder()
                .add(e.getKey(), e.getValue().asJson())).forEach(fieldsBuilder::addAll);
        
        JsonObject jsonObject = Json.createObjectBuilder()
            .add("name", this.getName())
            .add("targetDomainName", this.getTargetDomainName())
            .add("fields", fieldsBuilder.build())
            .build();
        returnValue = jsonObject.toString();
        return returnValue;
    }
}
