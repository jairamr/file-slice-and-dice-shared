package com.minimalism.shared.service;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import com.minimalism.shared.exceptions.NoSuchPathException;

public class BrokerConfigurationReader {
    private String clientName;
    private String recordTypeName;
    private Properties kafkaProperties;
    
    public BrokerConfigurationReader(String clientName, String recordTypeName) throws NoSuchPathException, IOException, URISyntaxException {
        this.kafkaProperties = new Properties();
        this.clientName = clientName;
        this.recordTypeName = recordTypeName;
        loadKafkaProperties();
    }

    public String getBootsatrapServers() {
        return kafkaProperties.getProperty("bootstrap_servers");
    }

    public String getTopic() {
        return kafkaProperties.getProperty("topic");
    }

    public int getPartitionsCount() {
        return Integer.parseInt(this.kafkaProperties.getProperty("topic_partitions"));
    }

    public long getPublisherBufferMemorySize() {
        return Long.parseLong(this.kafkaProperties.getProperty("publisher.buffer.memory"));
    }

    public int getPublisherRetriesCount() {
        return Integer.parseInt(this.kafkaProperties.getProperty("publisher.retries"));
    }

    public int getPublisherLingerMilliseconds() {
        return Integer.parseInt(this.kafkaProperties.getProperty("publisher.linger.milliseconds"));
    }

    public int getPublisherBatchSize() {
        return Integer.parseInt(this.kafkaProperties.getProperty("publisher.batch.size"));
    }

    public boolean autoRegisterSchemas() {
        return Boolean.parseBoolean(this.kafkaProperties.getProperty("auto.register.schemas"));
    }

    public String getSchemaRegistryUrl() {
        return this.kafkaProperties.getProperty("schema.registry.url");
    }

    public String getKeySerializerName() {
        return this.kafkaProperties.getProperty("key.serializer");
    }

    public String getValueSerializerName() {
        return this.kafkaProperties.getProperty("value.serializer");
    }

    public String getConsumerGroupId() {
        return this.kafkaProperties.getProperty("consumer.group.id");
    }

    public boolean getConsumerAutoCommitFlag() {
        return this.kafkaProperties.getProperty("enable.auto.commit").equalsIgnoreCase("true");
    }

    public String getConsumerDefaultOffsetMode() {
        return this.kafkaProperties.getProperty("consumer.auto.offset.reset");
    }

    public int getConsumerPollingInterval() {
        return Integer.parseInt(this.kafkaProperties.getProperty("consumer.poll.duration.milliseconds"));
    }

    public String getKeyDeserializer() {
        return this.kafkaProperties.getProperty("key.deserializer");
    }

    public String getValueDeserializer() {
        return this.kafkaProperties.getProperty("value.deserializer");
    }

    public BrokerConfiguration getBrokerConfiguration() {
        var returnValue = new BrokerConfiguration();
        returnValue.setBootstrapServers(this.getBootsatrapServers());
        returnValue.setTopic(this.getTopic());
        returnValue.setPartitions(this.getPartitionsCount());
        returnValue.setAutoRegisterSchemas(this.autoRegisterSchemas());
        returnValue.setPublisherBatchSize(this.getPublisherBatchSize());
        returnValue.setPublisherBufferMemory(this.getPublisherBufferMemorySize());
        returnValue.setPublisherLingerMilliseconds(this.getPublisherLingerMilliseconds());
        returnValue.setPublisherRetries(this.getPublisherRetriesCount());
        returnValue.setKeySerializer(this.getKeySerializerName());
        returnValue.setValueSerializer(this.getValueSerializerName());
        returnValue.setSchemaRegistryUrl(this.getSchemaRegistryUrl());
        returnValue.setConsumerAutoCommitFlag(this.getConsumerAutoCommitFlag());
        returnValue.setConsumerDefaultOffsetMode(this.getConsumerDefaultOffsetMode());
        returnValue.setConsumerGroupId(this.getConsumerGroupId());
        returnValue.setConsumerPollingInterval(this.getConsumerPollingInterval());
        returnValue.setKeyDeserializer(this.getKeyDeserializer());
        returnValue.setValueDeserializer(this.getValueDeserializer());

        return returnValue;
    }

    private void loadKafkaProperties() throws NoSuchPathException, IOException, URISyntaxException {
        var kafkaConfigFilePath = FileSystemConfigHelper.getInstance()
                .getServiceOutputDataDefinitionDirectory(this.clientName)
                .resolve("kafka".concat("_")
                            .concat(this.recordTypeName.toLowerCase())
                            .concat(".properties"));
        try(var kafkaPropertiesFile = new FileReader(kafkaConfigFilePath.toString())) {
            kafkaProperties.load(kafkaPropertiesFile);
        }
    }
}
