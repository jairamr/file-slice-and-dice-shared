package com.minimalism.shared.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.minimalism.shared.common.AllEnums.DataTypes;

import org.apache.commons.text.CaseUtils;

public class Field {
    private String name;
    private DataTypes dataType;
    private Object value;

    public Field() {}

    public Field(String name, DataTypes dataType, Object value) {
        this.setName(name);
        this.setDataType(dataType);
        this.setValue(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataTypes getDataType() {
        return dataType;
    }

    @JsonGetter("dataType")
    public String dataTypeClassName() {
        return CaseUtils.toCamelCase(this.getDataType().name(), true, '_');
    }

    public void setDataType(DataTypes dataType) {
        this.dataType = dataType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        setValueAsTargetType(value);
    }

    public String asJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(this);
    }

    private void setValueAsTargetType(Object value) {
        if(value == null) {
            this.value = null;
            return;
        }
        String temp = String.valueOf(value);
        switch(this.getDataType()) {
            case BOOLEAN:
            this.value = Boolean.parseBoolean(temp);
            break;
            case BIG_DECIMAL:
            this.value = new BigDecimal(temp);
            break;
            case DOUBLE:
            this.value = Double.parseDouble(temp);
            break;
            case FLOAT:
            this.value = Float.parseFloat(temp);
            break;
            case INTEGER:
            this.value= Integer.parseInt(temp);
            break;
            case LOCAL_DATE:
            this.value = LocalDate.parse(temp);
            break;
            case LOCAL_TIME:
            this.value = LocalTime.parse(temp);
            break;
            case LONG:
            this.value = Long.parseLong(temp);
            break;
            case STRING:
            this.value = temp;
            break;
            default:
            this.value = temp;
        }
    }

    @Override
    public String toString() {
        String returnValue = null;
        try {
            returnValue = this.asJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnValue;
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
