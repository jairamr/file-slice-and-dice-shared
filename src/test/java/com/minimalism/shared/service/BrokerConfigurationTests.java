package com.minimalism.shared.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import com.minimalism.shared.exceptions.NoSuchPathException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BrokerConfigurationTests {

    BrokerConfiguration iut = BrokerConfigurationTests.init();

    @BeforeAll
    static BrokerConfiguration init() {
        BrokerConfiguration brokerConfiguration = null;
        try {
            BrokerConfigurationReader reader = new BrokerConfigurationReader("client_1", "hrdata");
            brokerConfiguration = reader.getBrokerConfiguration();
        } catch (NoSuchPathException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return brokerConfiguration;
    }

    @Test
    void testGetAutoRegisterSchemasFlag() {
        assertEquals(Boolean.TRUE, iut.getAutoRegisterSchemasFlag());
    }

    @Test
    void testGetBootstrapServers() {
        assertEquals("localhost:9092", iut.getBootstrapServers());
    }

    @Test
    void testGetConsumerAutoCommitFlag() {
        
    }

    @Test
    void testGetConsumerDefaultOffsetMode() {

    }

    @Test
    void testGetConsumerGroupId() {

    }

    @Test
    void testGetConsumerPollingInterval() {

    }

    @Test
    void testGetKeyDeserializer() {

    }

    @Test
    void testGetKeySerializer() {

    }

    @Test
    void testGetPartitions() {

    }

    @Test
    void testGetPublisherBatchSize() {

    }

    @Test
    void testGetPublisherBufferMemory() {

    }

    @Test
    void testGetPublisherLingerMilliseconds() {

    }

    @Test
    void testGetPublisherRetries() {

    }

    @Test
    void testGetSchemaRegistryUrl() {

    }

    @Test
    void testGetTopic() {

    }

    @Test
    void testGetValueDeserializer() {

    }

    @Test
    void testGetValueSerializer() {

    }

    @Test
    void testSetAutoRegisterSchemas() {

    }

    @Test
    void testSetBootstrapServers() {

    }

    @Test
    void testSetConsumerAutoCommitFlag() {

    }

    @Test
    void testSetConsumerDefaultOffsetMode() {

    }

    @Test
    void testSetConsumerGroupId() {

    }

    @Test
    void testSetConsumerPollingInterval() {

    }

    @Test
    void testSetKeyDeserializer() {

    }

    @Test
    void testSetKeySerializer() {

    }

    @Test
    void testSetPartitions() {

    }

    @Test
    void testSetPublisherBatchSize() {

    }

    @Test
    void testSetPublisherBufferMemory() {

    }

    @Test
    void testSetPublisherLingerMilliseconds() {

    }

    @Test
    void testSetPublisherRetries() {

    }

    @Test
    void testSetSchemaRegistryUrl() {

    }

    @Test
    void testSetTopic() {

    }

    @Test
    void testSetValueDeserializer() {

    }

    @Test
    void testSetValueSerializer() {

    }
}
