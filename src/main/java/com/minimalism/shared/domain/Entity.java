package com.minimalism.shared.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

    @JsonIgnore
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
        JsonMapper mapper = new JsonMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            returnValue = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
