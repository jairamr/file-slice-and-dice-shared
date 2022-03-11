package com.minimalism.shared.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.minimalism.shared.exceptions.NoSuchPathException;

public class BrokerConfigurationReader {
    private String clientName;
    private String recordTypeName;
    private Properties brokerProperties;
    
    public BrokerConfigurationReader(String clientName, String recordTypeName, String brokerTypeName) throws NoSuchPathException, IOException {
        this.brokerProperties = new Properties();
        this.clientName = clientName;
        this.recordTypeName = recordTypeName;
        loadBrokerProperties(brokerTypeName);
    }

    public String getBootsatrapServers() {
        return brokerProperties.getProperty("bootstrap_servers");
    }

    public String getTopic() {
        return brokerProperties.getProperty("topic");
    }

    public String getTopicKey() {
        return brokerProperties.getProperty("topic.key");
    }

    public String getErrorsTopic() {
        return brokerProperties.getProperty("errors.topic");
    }

    public String getErrorsTopicKey() {
        return this.brokerProperties.getProperty("errors.topic.key");
    }

    public int getPartitionsCount() {
        return Integer.parseInt(this.brokerProperties.getProperty("topic_partitions"));
    }

    public long getPublisherBufferMemorySize() {
        return Long.parseLong(this.brokerProperties.getProperty("publisher.buffer.memory"));
    }

    public int getPublisherRetriesCount() {
        return Integer.parseInt(this.brokerProperties.getProperty("publisher.retries"));
    }

    public int getPublisherLingerMilliseconds() {
        return Integer.parseInt(this.brokerProperties.getProperty("publisher.linger.milliseconds"));
    }

    public int getPublisherBatchSize() {
        return Integer.parseInt(this.brokerProperties.getProperty("publisher.batch.size"));
    }

    public boolean autoRegisterSchemas() {
        return Boolean.parseBoolean(this.brokerProperties.getProperty("auto.register.schemas"));
    }

    public String getRecordKey() {
        String key = this.brokerProperties.getProperty("record.key");
        if(key == null || key.isEmpty() || key.isBlank()) {
            return "none";
        } else {
            return key;
        }
    }

    public String getSchemaRegistryUrl() {
        return this.brokerProperties.getProperty("schema.registry.url");
    }

    public String getKeySerializerName() {
        return this.brokerProperties.getProperty("key.serializer");
    }

    public String getValueSerializerName() {
        return this.brokerProperties.getProperty("value.serializer");
    }

    public String getConsumerGroupId() {
        return this.brokerProperties.getProperty("consumer.group.id");
    }

    public boolean getConsumerAutoCommitFlag() {
        return this.brokerProperties.getProperty("consumer.enable.auto.commit").equalsIgnoreCase("true");
    }

    public String getConsumerDefaultOffsetMode() {
        return this.brokerProperties.getProperty("consumer.auto.offset.reset");
    }

    public int getConsumerPollingInterval() {
        return Integer.parseInt(this.brokerProperties.getProperty("consumer.poll.duration.milliseconds"));
    }

    public String getKeyDeserializer() {
        return this.brokerProperties.getProperty("key.deserializer");
    }

    public String getValueDeserializer() {
        return this.brokerProperties.getProperty("value.deserializer");
    }

    public BrokerConfiguration getBrokerConfiguration() {
        var returnValue = new BrokerConfiguration();
        returnValue.setBootstrapServers(this.getBootsatrapServers());
        returnValue.setTopic(this.getTopic());
        returnValue.setErrorsTopic(this.getErrorsTopic());
        returnValue.setTopicKey(this.getTopicKey());
        returnValue.setErrorsTopicKey(this.getErrorsTopicKey());
        returnValue.setPartitions(this.getPartitionsCount());
        returnValue.setAutoRegisterSchemas(this.autoRegisterSchemas());
        returnValue.setRecordKey(this.getRecordKey());
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

    private void loadBrokerProperties(String brokerTypeName) throws NoSuchPathException, IOException {
        var brokerConfigFilePath = FileSystemConfigHelper.getInstance()
                .getServiceOutputDataDefinitionDirectory(this.clientName)
                .resolve(brokerTypeName.toLowerCase().concat("_")
                            .concat(this.recordTypeName.toLowerCase())
                            .concat(".properties"));
        try(var brokerPropertiesFile = new FileReader(brokerConfigFilePath.toString())) {
            brokerProperties.load(brokerPropertiesFile);
        }
    }
}
