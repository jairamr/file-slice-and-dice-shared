package com.minimalism.shared.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.json.Json;
import javax.json.JsonObject;

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

    public String dataTypeClassName() {
        return CaseUtils.toCamelCase(this.getDataType().name(), true, '_');
    }

    public void setDataType(DataTypes dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value.toString();
    }

    public void setValue(Object value) {
        setValueAsTargetType(value);
    }

    public JsonObject asJson() {
        return Json.createObjectBuilder()
            .add("name", this.getName())
            .add("dataType", CaseUtils.toCamelCase(this.getDataType().name(), true, '_'))
            .add("value", this.value.toString())
            .build();
    }

    // public String getAvroSchemaJson() {
    //     org.apache.avro.Schema avroField = org.apache.avro.SchemaBuilder
    //     .record("Field").namespace("com.minimalism.shared.domain")
    //         .fields()//.requiredLong("requestTime")
    //             .name("name").type("string").noDefault()
    //             .name("dataType").type(this.dataType.getTypeName()).withDefault(DataTypes.STRING.name())
    //             .name("value").type(this.dataType.getTypeName().toLowerCase())
    //             .noDefault()
    //         .endRecord();
            
    //     return avroField.toString();
    // }

    private void setValueAsTargetType(Object value) {
        if(value == null) {
            this.value = null;
            return;
        }
        String temp = String.valueOf(value);
        switch(this.getDataType()) {
            case BOOLEAN:
            TypeConverter<Boolean> tcb = new TypeConverter<>(Boolean.class);
            this.value = tcb.convert(value);
            break;
            case BIG_DECIMAL:
            TypeConverter<BigDecimal> tcbd = new TypeConverter<>(BigDecimal.class);
            this.value = tcbd.convert(value);
            break;
            case DOUBLE:
            TypeConverter<Double> tcd = new TypeConverter<>(Double.class);
            this.value = tcd.convert(value);
            break;
            case FLOAT:
            TypeConverter<Float> tcf = new TypeConverter<>(Float.class);
            this.value = tcf.convert(value);
            break;
            case INTEGER:
            TypeConverter<Integer> tci = new TypeConverter<>(Integer.class);
            this.value = tci.convert(value);
            break;
            case LOCAL_DATE:
            TypeConverter<LocalDate> tcld = new TypeConverter<>(LocalDate.class);
            this.value = tcld.convert(value);
            break;
            case LOCAL_TIME:
            TypeConverter<LocalTime> tclt = new TypeConverter<>(LocalTime.class);
            this.value = tclt.convert(value);
            break;
            case LONG:
            TypeConverter<Long> tcl = new TypeConverter<>(Long.class);
            this.value = tcl.convert(value);
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
        return this.asJson().toString();
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

    protected class TypeConverter<T> {
        private Class<T> type;

        public TypeConverter(Class<T> type) {
            this.type = type;
        }

        private BigDecimal toBigDecimal(String value) {
            return new BigDecimal(value);
        }

        protected T convert(Object value) {
            T returnValue = null;
            if(type.isInstance(value)) {
                returnValue = type.cast(value);
            } else {
                returnValue = type.cast(assignFromString(value.toString()));
            }
            return returnValue;
        }

        private Object fromStaticMethod(Method m, String value) {
            Object returnValue = null;
            try {
                returnValue = m.invoke(null, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return returnValue;
        }

        private Object fromPublicMethod(Method m, String value) {
            Object returnValue = null;
            try {
                Class<?>[] paramTypes = null;
                returnValue = type.cast(m.invoke(type.getDeclaredConstructor(paramTypes)
                    .newInstance((Object[])paramTypes), value));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                        | InstantiationException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
            }
            return returnValue;
        }

        private Object assignFromString(String value) {
            if(type.isAssignableFrom(java.math.BigDecimal.class)) {
                return this.toBigDecimal(value);
            }
            Method[] methods = type.getDeclaredMethods();
            for(Method m : methods) {
                if(m.getName().contains("parse")) {
                    if(Modifier.isStatic(m.getModifiers()) && m.getParameterCount() == 1) {
                        return fromStaticMethod(m, value);
                    } else if(Modifier.isPublic(m.getModifiers()) && m.getParameterCount() == 1) {
                        return fromPublicMethod(m, value);
                    }
                }
            }
            return value;
        }
    }
    
}
