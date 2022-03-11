package com.minimalism.shared.service;

public class BrokerConfiguration {
    private String bootstrapServers;
    private String topic;
    private String topicKey;
    private String errorsTopic;
    private String errorsTopicKey;
    private int partitions;
    private long publisherBufferMemory;
    private int publisherRetries;
    private int publisherLingerMilliseconds;
    private int publisherBatchSize;
    private boolean autoRegisterSchemas;
    private String recordKey;
    private String schemaRegistryUrl;
    private String keySerializer;
    private String valueSerializer;
    private String consumerGroupId;
    private boolean consumerAutoCommitFlag;
    private String consumerDefaultOffsetMode;
    private int consumerPollingInterval;
    private String keyDeserializer;
    private String valueDeserializer;
    
    public String getBootstrapServers() {
        return this.bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopiKey() {
        return topicKey;
    }

    public void setTopicKey(String topicKey) {
        this.topicKey = topicKey;
    }

    public String getErrorsTopic() {
        return errorsTopic;
    }
    public String getErrorsTopicKey() {
        return errorsTopicKey;
    }

    public void setErrorsTopicKey(String errorsTopicKey) {
        this.errorsTopicKey = errorsTopicKey;
    }
    public void setErrorsTopic(String errorsTopic) {
        this.errorsTopic = errorsTopic;
    }



    public int getPartitions() {
        return partitions;
    }
    
    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public long getPublisherBufferMemory() {
        return publisherBufferMemory;
    }

    public void setPublisherBufferMemory(long publisherBufferMemory) {
        this.publisherBufferMemory = publisherBufferMemory;
    }

    public int getPublisherRetries() {
        return publisherRetries;
    }

    public void setPublisherRetries(int publisherRetries) {
        this.publisherRetries = publisherRetries;
    }

    public int getPublisherLingerMilliseconds() {
        return publisherLingerMilliseconds;
    }

    public void setPublisherLingerMilliseconds(int publisherLingerMilliseconds) {
        this.publisherLingerMilliseconds = publisherLingerMilliseconds;
    }

    public int getPublisherBatchSize() {
        return publisherBatchSize;
    }

    public void setPublisherBatchSize(int publisherBatchSize) {
        this.publisherBatchSize = publisherBatchSize;
    }

    public boolean getAutoRegisterSchemasFlag() {
        return autoRegisterSchemas;
    }

    public void setAutoRegisterSchemas(boolean autoRegisterSchemas) {
        this.autoRegisterSchemas = autoRegisterSchemas;
    }

    public String getRecordKey() {
        return this.recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    public void setSchemaRegistryUrl(String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    public String getKeySerializer() {
        return this.keySerializer;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public String getValueSerializer() {
        return this.valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public String getConsumerGroupId(){
        return this.consumerGroupId;
    }

    public void setConsumerGroupId(String groupId) {
        this.consumerGroupId = groupId;
    }

    public boolean getConsumerAutoCommitFlag() {
        return this.consumerAutoCommitFlag;
    }

    public void setConsumerAutoCommitFlag(boolean flag) {
        this.consumerAutoCommitFlag = flag;
    }

    public String getConsumerDefaultOffsetMode() {
        return this.consumerDefaultOffsetMode;
    }

    public void setConsumerDefaultOffsetMode(String offsetMode) {
        this.consumerDefaultOffsetMode = offsetMode;
    }

    public int getConsumerPollingInterval() {
        return this.consumerPollingInterval;
    }

    public void setConsumerPollingInterval(int pollingInterval) {
        this.consumerPollingInterval = pollingInterval;
    }

    public String getKeyDeserializer() {
        return this.keyDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValueDeserializer() {
        return this.valueDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }
    

}
