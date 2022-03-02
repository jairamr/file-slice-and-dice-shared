package com.minimalism.shared.service;

import com.minimalism.shared.domain.Entity;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class AvroSchemaGenerator {
    private AvroSchemaGenerator() {}
    
    public static Schema forInputField() {
        return SchemaBuilder.record("InputField").namespace("com.minimalism.files.domain.entities")
                                .fields()
                                .requiredString("name")
                                .requiredString("dataType")
                                .requiredString("value")
                                .endRecord();
    }

    public static Schema forInputEntity() {
        return SchemaBuilder.record("InputEntity").namespace("com.minimalisim.files.domain.entities")
                            .fields()
                            .requiredString("name")
                            .requiredString("targetDomainClassName")
                            .name("inputFields").type().array().items(forInputField()).noDefault()
                            .endRecord();
    }

    public static Schema createAvroSchemaForField() {
        return SchemaBuilder.record("Field").namespace("com.minimalism.shared.domain")
                            .fields().requiredString("name")
                                    .requiredString("dataType")
                                    // enum does not work since avro 1.8.1
                                    //.name("dataType").type().enumeration("DataTypes")
                                            //.namespace("com.minimalism.shared.common.AllEnums")
                                            //.symbols("BOOLEAN", "BIG_DECIMAL", "CHARACTER", "DOUBLE", "FLOAT", "INTEGER", "LOCAL_DATE", "LOCAL_TIME", "LONG", "STRING").enumDefault("STRING")
                                    .requiredString("value")
                            .endRecord();
        // ReflectData.get().getSchema(Field.class)
    }

    public static Schema createAvroSchemaForEntity() {
        return SchemaBuilder.record("Entity").namespace("com.minimalism.shared.domain")
                            .fields()
                            .requiredString("name")
                            .requiredString("targetDomainClassName")
                            .name("fields").type().map()
                            .values().unionOf().stringType().and().type(createAvroSchemaForField()).endUnion().noDefault()//(createAvroSchemaForField()).noDefault()
                            .endRecord();
        
        //{"type":"record","name":"Entity","namespace":"com.minimalism.shared.domain",
        //"fields":[{"name":"name","type":"string"},
        //{"name":"targetDomainName","type":"string"},
        //{"name":"fields","type":{"type":"map","values":
        //{"type":"record","name":"Field",
        //"fields":[{"name":"name","type":"string"},
        //{"name":"dataType",
        //"type":{"type":"enum","name":"DataTypes",
        //"namespace":"com.minimalism.shared.common.AllEnums$",
        //"symbols":["BOOLEAN","BIG_DECIMAL","CHARACTER","DOUBLE","FLOAT","INTEGER","LOCAL_DATE","LOCAL_TIME","LONG","STRING"]}},
        //{"name":"value","type":{"type":"record","name":"Object","namespace":"java.lang","fields":[]}}]}}}]}
        //return ReflectData.get().getSchema(Entity.class);
    }
}
 