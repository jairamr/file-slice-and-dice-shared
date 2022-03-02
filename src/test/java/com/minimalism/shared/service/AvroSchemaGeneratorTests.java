package com.minimalism.shared.service;

import com.minimalism.shared.domain.Field;

import org.apache.avro.reflect.ReflectData;
import org.junit.jupiter.api.Test;

class AvroSchemaGeneratorTests {
    @Test
    void testCreateAvroSchemaForField() {
        System.out.println(AvroSchemaGenerator.createAvroSchemaForField());
        System.out.println("________________");
        System.out.println(ReflectData.get().getSchema(Field.class));
        
    }

    @Test
    void testCreateAvroSchemaForEntity() {
        System.out.println(AvroSchemaGenerator.createAvroSchemaForEntity().toString());
    }
}
