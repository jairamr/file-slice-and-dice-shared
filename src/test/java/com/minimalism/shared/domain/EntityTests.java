package com.minimalism.shared.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.minimalism.shared.common.AllEnums.DataTypes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(Lifecycle.PER_CLASS)
class EntityTests {
    Entity iut = new Entity();

    @BeforeAll
    void init() {
        iut.setName("testEntity");
        iut.setTargetDomainName("com.minimalism.entity");
        List<Field> fields = new ArrayList<>();
        Field field_1 = new Field("field_one", DataTypes.BIG_DECIMAL, new BigDecimal("22.22"));
        Field field_2 = new Field("field_two", DataTypes.BOOLEAN, Boolean.TRUE);
        Field field_3 = new Field("field_three", DataTypes.DOUBLE, Double.parseDouble("33.33"));
        Field field_4 = new Field("field_four", DataTypes.FLOAT, Float.parseFloat("44.44"));
        Field field_5 = new Field("field_five", DataTypes.INTEGER, Integer.parseInt("22"));
        Field field_6 = new Field("field_six", DataTypes.LOCAL_DATE, LocalDate.parse("2022-02-04"));
        Field field_7 = new Field("field_seven", DataTypes.LOCAL_TIME, LocalTime.parse("12:12:12"));
        Field field_8 = new Field("field_eight", DataTypes.LONG, Long.parseLong("33"));
        Field field_9 = new Field("field_nine", DataTypes.STRING, "test string");
        fields.add(field_1);
        fields.add(field_2);
        fields.add(field_3);
        fields.add(field_4);
        fields.add(field_5);
        fields.add(field_6);
        fields.add(field_7);
        fields.add(field_8);
        fields.add(field_9);
        iut.setFields(fields);
    }
    @Test
    void testAddField() {
        Field field_ten = new Field("field_ten", DataTypes.STRING, "additional field");
        iut.addField(field_ten);
        assertEquals(10, iut.getFields().size());
        iut.getFields().remove("field_ten");
    }

    @Test
    void testAsJson() {
        try {
            System.out.println(iut.asJson());
            assertEquals("{\"name\":\"testEntity\",\"targetDomainName\":\"com.minimalism.entity\"," + 
            "\"fields\":{\"field_four\":{\"name\":\"field_four\",\"dataType\":\"Float\",\"value\":44.44}," + 
            "\"field_six\":{\"name\":\"field_six\",\"dataType\":\"LocalDate\",\"value\":\"2022-02-04\"}," + 
            "\"field_eight\":{\"name\":\"field_eight\",\"dataType\":\"Long\",\"value\":33}," + 
            "\"field_three\":{\"name\":\"field_three\",\"dataType\":\"Double\",\"value\":33.33}," + 
            "\"field_seven\":{\"name\":\"field_seven\",\"dataType\":\"LocalTime\",\"value\":\"12:12:12\"}," + 
            "\"field_nine\":{\"name\":\"field_nine\",\"dataType\":\"String\",\"value\":\"test string\"}," + 
            "\"field_two\":{\"name\":\"field_two\",\"dataType\":\"Boolean\",\"value\":true}," + 
            "\"field_five\":{\"name\":\"field_five\",\"dataType\":\"Integer\",\"value\":22}," + 
            "\"field_one\":{\"name\":\"field_one\",\"dataType\":\"BigDecimal\",\"value\":22.22}}}", iut.asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_one() {
        try {
            assertEquals("{\"name\":\"field_one\",\"dataType\":\"BigDecimal\",\"value\":22.22}", iut.getField("field_one").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_two() {
        try {
            assertEquals("{\"name\":\"field_two\",\"dataType\":\"Boolean\",\"value\":true}", iut.getField("field_two").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_three() {
        try {
            assertEquals("{\"name\":\"field_three\",\"dataType\":\"Double\",\"value\":33.33}", iut.getField("field_three").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_four() {
        try {
            assertEquals("{\"name\":\"field_four\",\"dataType\":\"Float\",\"value\":44.44}", iut.getField("field_four").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_five() {
        try {
            assertEquals("{\"name\":\"field_five\",\"dataType\":\"Integer\",\"value\":22}", iut.getField("field_five").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_six() {
        try {
            assertEquals("{\"name\":\"field_six\",\"dataType\":\"LocalDate\",\"value\":\"2022-02-04\"}", iut.getField("field_six").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_seven() {
        try {
            assertEquals("{\"name\":\"field_seven\",\"dataType\":\"LocalTime\",\"value\":\"12:12:12\"}", iut.getField("field_seven").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_eight() {
        try {
            assertEquals("{\"name\":\"field_eight\",\"dataType\":\"Long\",\"value\":33}", iut.getField("field_eight").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetField_field_nine() {
        try {
            assertEquals("{\"name\":\"field_nine\",\"dataType\":\"String\",\"value\":\"test string\"}", iut.getField("field_nine").asJson());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetFields() {
        Map<String, Field> fields = iut.getFields();
        assertEquals(9, fields.size());
    }

    @Test
    void testGetListOfFields() {
        List<Field> fieldsList = iut.getListOfFields();
        assertEquals(9, fieldsList.size());
    }

    @Test
    void testGetName() {
        assertEquals("testEntity", iut.getName());
    }

    static Stream<String> blankStrings() {
        return Stream.of("", "   ", null);
    }

    @ParameterizedTest
    @MethodSource("blankStrings")
    void testGetName_is_null() {
        Entity nullNamed = new Entity();
        assertThrows(IllegalArgumentException.class, () -> {
            nullNamed.setName(null);
        });
    }

    @Test
    void testGetTargetDomainName() {
        assertEquals("com.minimalism.entity", iut.getTargetDomainName());
    }
}
