package com.minimalism.shared.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({
    "iterationNumber","workerId","threadName",
    "bufferSize","byteOffsetInFile","byteOffsetForBuffer",
    "processedBytes","processedRecords","validRecords",
    "invalidRecords","missingInformationRecords","iterationDuration",
    "parsingDuration","publishingDuration"
})
public class IterationStatistics {
    private int iterationNumber;
    private long workerId;
    private String threadName;
    private int bufferSize;
    private long byteOffsetInFile;
    private long byteOffsetForBuffer;
    private long iterationStartTime;
    private long iterationEndTime;
    private long parsingStartTime;
    private long parsingEndTime;
    private long publishingStartTime;
    private long publishingEndTime;
    private long processedBytes;
    private int processedRecords;
    private int validRecords;
    private int invalidRecords;
    private int missingInformationRecords;

    @JsonGetter("workerId")
    public long getWorkerId() {
        return workerId;
    }

    @JsonSetter("workerId")
    public void setWorkerId(long workerId) {
        if(workerId < 0) {
            workerId = 0;
        }
        this.workerId = workerId;
    }

    @JsonGetter("threadName")
    public String getThreadName() {
        return threadName;
    }

    @JsonSetter("threadName")
    public void setThreadName(String threadName) {
        if(threadName == null || threadName.isEmpty() || threadName.isBlank()) {
            threadName = "no_name";
        }
        this.threadName = threadName;
    }

    @JsonGetter("iterationNumber")
    public int getIterationNumber() {
        return this.iterationNumber;
    }

    @JsonSetter("iterationNumber")
    public void setIterationNumber(int iterationNumber) {
        if(iterationNumber < 0) {
            iterationNumber = 0;
        }
        this.iterationNumber = iterationNumber;
    }

    @JsonGetter("bufferSize")
    public int getBufferSize() {
        return bufferSize;
    }
    @JsonSetter("bufferSize")
    public void setBufferSize(int bufferSize) {
        //if negative, set as zero
        if(bufferSize < 0) {
            bufferSize = 0;
        }
        this.bufferSize = bufferSize;
    }

    @JsonGetter("byteOffsetInFile")
    public long getByteOffsetInFile() {
        return this.byteOffsetInFile;
    }

    @JsonSetter("byteOffsetInFile")
    public void setByteOffsetInFile(long offsetInFile) {
        if(offsetInFile < 0) {
            offsetInFile = 0;
        }
        this.byteOffsetInFile = offsetInFile;
    }

    @JsonGetter("byteOffsetForBuffer")
    public long getByteOffsetForBuffer() {
        return this.byteOffsetForBuffer;
    }

    @JsonSetter("byteOffsetForBuffer")
    public void setByteOffsetForBuffer(long offsetForBuffer) {
        if(offsetForBuffer < 0) {
            offsetForBuffer = 0;
        }
        this.byteOffsetForBuffer = offsetForBuffer;
    }

    @JsonSetter("parsingStartTime")
    public void setParsingStartTime(long parsingStartTime) {
        this.parsingStartTime = parsingStartTime;
    }

    @JsonSetter("publishingStartTime")
    public void setPublishingStartTime(long publishingStartTime) {
        this.publishingStartTime = publishingStartTime;
    }

    @JsonSetter("parsingEndTime")
    public void setParsingEndTime(long parsingEndTime) {
        this.parsingEndTime = parsingEndTime;
        this.publishingStartTime = parsingEndTime;
    }

    @JsonSetter("publishingEndTime")
    public void setPublishingEndTime(long publishingEndTime) {
        this.publishingEndTime = publishingEndTime;
    }

    @JsonGetter("parsingDuration")
    public long getParsingDuration() {
        return this.parsingEndTime - this.parsingStartTime;
    }

    @JsonGetter("publishingDuration")
    public long getPublishingDuration() {
        return this.publishingEndTime - this.publishingStartTime;
    }

    @JsonSetter("iterationStartTime")
    public void setIterationStartTime(long iterationStartTime) {
        this.iterationStartTime = iterationStartTime;
        this.parsingStartTime = iterationStartTime;
    }

    @JsonSetter("iterationEndTime")
    public void setIterationEndTime(long iterationEndTime) {
        this.iterationEndTime = iterationEndTime;
        this.publishingEndTime = iterationEndTime;
    }

    @JsonGetter("iterationDuration")
    public long getIterationDuration() {
        return this.iterationEndTime - this.iterationStartTime;
    }

    @JsonGetter("processedBytes")
    public long getProcessedBytes() {
        return processedBytes;
    }

    @JsonSetter("processedBytes")
    public void setProcessedBytes(long processedBytes) {
        if(processedBytes < 0) {
            processedBytes = 0;
        }
        this.processedBytes = processedBytes;
    }

    @JsonGetter("processedRecords")
    public int getProcessedRecords() {
        return processedRecords;
    }

    @JsonSetter("processedRecords")
    public void setProcessedRecords(int processedRecords) {
        if(processedRecords < 0) {
            processedRecords = 0;
        }
        this.processedRecords = processedRecords;
    }

    @JsonGetter("validRecords")
    public int getValidRecords() {
        return validRecords;
    }

    @JsonSetter("validRecords")
    public void setValidRecords(int validRecords) {
        if(validRecords < 0) {
            validRecords = 0;
        }
        this.validRecords = validRecords;
    }

    @JsonGetter("invalidRecords")
    public int getInvalidRecords() {
        return invalidRecords;
    }

    @JsonSetter("invalidRecords")
    public void setInvalidRecords(int invalidRecords) {
        if(invalidRecords < 0) {
            invalidRecords = 0;
        }
        this.invalidRecords = invalidRecords;
    }

    @JsonGetter("missingInformationRecords")
    public int getMissingInformationRecords() {
        return missingInformationRecords;
    }

    @JsonSetter("missingInformationRecords")
    public void setMissingInformationRecords(int missingInformationRecords) {
        if(missingInformationRecords < 0) {
            missingInformationRecords = 0;
        }
        this.missingInformationRecords = missingInformationRecords;
    }

    @Override
    @JsonIgnore
    public int hashCode() {
        return Objects.hash(this.threadName, this.workerId, this.iterationNumber);
    }

    @Override
    @JsonIgnore
    public boolean equals(Object other) {
        if(other == null)
            return false;
        if(!(other instanceof IterationStatistics))
            return false;
        return other.hashCode() == this.hashCode();
    }

    @Override
    @JsonIgnore
    public String toString() {
        String returnValue = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            returnValue = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public void addResidualProcessedBytes(int residualBytes) {
        this.setProcessedBytes(this.processedBytes + residualBytes);
    }
    
    public void addResidualProcessedRecords(int residualRecords) {
        this.setProcessedRecords(this.processedRecords + residualRecords);
    }
    
}
