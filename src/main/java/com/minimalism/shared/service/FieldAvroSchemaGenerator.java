package com.minimalism.shared.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.minimalism.shared.domain.Field;

public class FieldAvroSchemaGenerator extends StdSerializer<Field> {
    public FieldAvroSchemaGenerator() {
        this(null);
    }

    public FieldAvroSchemaGenerator(Class<Field> t) {
        super(t);
    }

    @Override
    public void serialize(Field value, JsonGenerator gen, SerializerProvider provider) throws IOException {
       gen.writeStartObject();
       gen.writeString(value.getAvroSchemaJson());
       gen.writeEndObject();

    }
}
 