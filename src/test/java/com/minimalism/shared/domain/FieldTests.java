package com.minimalism.shared.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minimalism.shared.common.AllEnums.DataTypes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class FieldTests {
    @Test
    void testAsJson_boolean() {
        Field iut = new Field("field_1", DataTypes.BOOLEAN, Boolean.TRUE);
        try {
            assertEquals("{\"name\":\"field_1\",\"dataType\":\"Boolean\",\"value\":true}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_bigDecimal() {
        Field iut = new Field("field_2", DataTypes.BIG_DECIMAL, "22.22");
        try {
            assertEquals("{\"name\":\"field_2\",\"dataType\":\"BigDecimal\",\"value\":22.22}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_double() {
        Field iut = new Field("field_3", DataTypes.DOUBLE, Double.valueOf(22.22d));
        try {
            assertEquals("{\"name\":\"field_3\",\"dataType\":\"Double\",\"value\":22.22}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_float() {
        Field iut = new Field("field_4", DataTypes.FLOAT, Float.valueOf(22.22f));
        try {
            assertEquals("{\"name\":\"field_4\",\"dataType\":\"Float\",\"value\":22.22}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_inetger() {
        Field iut = new Field("field_5", DataTypes.INTEGER, 7);
        try {
            assertEquals("{\"name\":\"field_5\",\"dataType\":\"Integer\",\"value\":7}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_local_date() {
        Field iut = new Field("field_6", DataTypes.LOCAL_DATE, LocalDate.parse("2022-02-03"));
        try {
            assertEquals("{\"name\":\"field_6\",\"dataType\":\"LocalDate\",\"value\":\"2022-02-03\"}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_local_time() {
        Field iut = new Field("field_7", DataTypes.LOCAL_TIME, LocalTime.parse("12:12:12"));
        try {
            assertEquals("{\"name\":\"field_7\",\"dataType\":\"LocalTime\",\"value\":\"12:12:12\"}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_long() {
        Field iut = new Field("field_8", DataTypes.LONG, Long.valueOf(22l));
        try {
            assertEquals("{\"name\":\"field_8\",\"dataType\":\"Long\",\"value\":22}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testAsJson_string() {
        Field iut = new Field("field_9", DataTypes.STRING, "this is a string");
        try {
            assertEquals("{\"name\":\"field_9\",\"dataType\":\"String\",\"value\":\"this is a string\"}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    }

    @Test
    void testEquals_same_name_and_data_type_are_equal() {
        Field iut1 = new Field("name", DataTypes.STRING, "first name");
        Field iut2 = new Field("name", DataTypes.STRING, "second name");
        assertEquals(Boolean.TRUE, iut1.equals(iut2));
    }

    @Test
    void testEquals_other_is_null_are_not_equal() {
        Field iut1 = new Field("name", DataTypes.STRING, "first name");
        Field iut2 = null;
        assertEquals(Boolean.FALSE, iut1.equals(iut2));
    }

    @Test
    void testEquals_other_is_different_type_type_are_not_equal() {
        Field iut1 = new Field("name", DataTypes.STRING, "first name");
        Object iut2 = "Other"; 
        assertEquals(Boolean.FALSE, iut1.equals(iut2));
    }

    @ParameterizedTest
    @EnumSource(DataTypes.class)
    void testGetDataType(DataTypes dataType) {
        Field iut = new Field();
        iut.setDataType(dataType);
        assertEquals(dataType, iut.getDataType());
    }

    @Test
    void testGetDataTypeClassName_boolean() {
        Field iut = new Field();
        iut.setDataType(DataTypes.BOOLEAN);
        assertEquals("Boolean", iut.dataTypeClassName());
    }

    @Test
    void testGetDataTypeClassName_bigDecimal() {
        Field iut = new Field();
        iut.setDataType(DataTypes.BIG_DECIMAL);
        assertEquals("BigDecimal", iut.dataTypeClassName());
    }
    @Test
    void testGetDataTypeClassName_double() {
        Field iut = new Field();
        iut.setDataType(DataTypes.DOUBLE);
        assertEquals("Double", iut.dataTypeClassName());
    }

    @Test
    void testGetDataTypeClassName_float() {
        Field iut = new Field();
        iut.setDataType(DataTypes.FLOAT);
        assertEquals("Float", iut.dataTypeClassName());
    }

    @Test
    void testGetDataTypeClassName_integer() {
        Field iut = new Field();
        iut.setDataType(DataTypes.INTEGER);
        assertEquals("Integer", iut.dataTypeClassName());
    }

    @Test
    void testGetDataTypeClassName_localDate() {
        Field iut = new Field();
        iut.setDataType(DataTypes.LOCAL_DATE);
        assertEquals("LocalDate", iut.dataTypeClassName());
    }

    @Test
    void testGetDataTypeClassName_localTime() {
        Field iut = new Field();
        iut.setDataType(DataTypes.LOCAL_TIME);
        assertEquals("LocalTime", iut.dataTypeClassName());
    }

    @Test
    void testGetDataTypeClassName_string() {
        Field iut = new Field();
        iut.setDataType(DataTypes.STRING);
        assertEquals("String", iut.dataTypeClassName());
    }

    @Test
    void testGetName() {
        Field iut = new Field();
        iut.setName("someField");
        assertEquals("someField", iut.getName());
    }

    @Test
    void testGetValue_boolean_true() {
        Field iut = new Field("isTrue", DataTypes.BOOLEAN, Boolean.TRUE);
        assertEquals(Boolean.TRUE, iut.getValue());
    }

    @Test
    void testGetValue_boolean_false() {
        Field iut = new Field("isTrue", DataTypes.BOOLEAN, Boolean.FALSE);
        assertEquals(Boolean.FALSE, iut.getValue());
    }

    @Test
    void testGetValue_boolean_input_as_string_true() {
        Field iut = new Field("isTrue", DataTypes.BOOLEAN, "true");
        assertEquals(Boolean.TRUE, iut.getValue());
    }

    @Test
    void testGetValue_boolean_input_as_string_false() {
        Field iut = new Field("isTrue", DataTypes.BOOLEAN, "false");
        assertEquals(Boolean.FALSE, iut.getValue());
    }
    @Test
    void testGetValue_boolean_input_as_string_random() {
        Field iut = new Field("isTrue", DataTypes.BOOLEAN, "untrue");
        assertEquals(Boolean.FALSE, iut.getValue());
    }

    @Test
    void testGetValue_bigDecimal() {
        Field iut = new Field("isTrue", DataTypes.BIG_DECIMAL, BigDecimal.valueOf(22.22d));
        assertEquals(BigDecimal.valueOf(22.22d), iut.getValue());
    }

    @Test
    void testGetValue_bigDecimal_value_as_String() {
        Field iut = new Field();
        iut.setDataType(DataTypes.BIG_DECIMAL);
        iut.setValue("22.22");
        assertEquals(BigDecimal.valueOf(22.22d), iut.getValue());
    }

    @Test
    void testGetValue_integer() {
        Field iut = new Field("intValue", DataTypes.INTEGER, 33);
        assertEquals(33, iut.getValue());
    }

    @Test
    void testGetValue_integer_as_string() {
        Field iut = new Field("intValue", DataTypes.INTEGER, "33");
        assertEquals(33, iut.getValue());
    }

    @Test
    void testGetValue_local_date() {
        Field iut = new Field("date", DataTypes.LOCAL_DATE, LocalDate.now());
        assertEquals(LocalDate.now(), iut.getValue());
    }

    @Test
    void testGetValue_local_date_as_string() {
        Field iut = new Field("date", DataTypes.LOCAL_DATE, "2022-02-03");
        assertEquals(LocalDate.parse("2022-02-03"), iut.getValue());
    }

    @Test
    void testGetValue_local_time() {
        Field iut = new Field("time", DataTypes.LOCAL_TIME, LocalTime.MIDNIGHT);
        assertEquals(LocalTime.MIDNIGHT, iut.getValue());
    }

    @Test
    void testGetValue_local_time_as_string() {
        Field iut = new Field("time", DataTypes.LOCAL_TIME, "12:12:12");
        assertEquals(LocalTime.parse("12:12:12"), iut.getValue());
    }

    @Test
    void testHashCode() {
        Field iut = new Field("name", DataTypes.STRING, "test");
        assertEquals(Objects.hash(iut.getName(), iut.getDataType()), iut.hashCode());
    }

    @Test
    void testAvroSchema() {
        Field iut = new Field("name", DataTypes.STRING, "test");
        System.out.println(iut.getAvroSchemaJson());
    }
}
