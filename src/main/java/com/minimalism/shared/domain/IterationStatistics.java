package com.minimalism.shared.domain;

import java.util.Objects;

import javax.json.Json;
import javax.json.JsonObject;

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

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        if(workerId < 0) {
            workerId = 0;
        }
        this.workerId = workerId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        if(threadName == null || threadName.isEmpty() || threadName.isBlank()) {
            threadName = "no_name";
        }
        this.threadName = threadName;
    }

    public int getIterationNumber() {
        return this.iterationNumber;
    }

    public void setIterationNumber(int iterationNumber) {
        if(iterationNumber < 0) {
            iterationNumber = 0;
        }
        this.iterationNumber = iterationNumber;
    }

    public int getBufferSize() {
        return bufferSize;
    }
    
    public void setBufferSize(int bufferSize) {
        //if negative, set as zero
        if(bufferSize < 0) {
            bufferSize = 0;
        }
        this.bufferSize = bufferSize;
    }

    public long getByteOffsetInFile() {
        return this.byteOffsetInFile;
    }

    public void setByteOffsetInFile(long offsetInFile) {
        if(offsetInFile < 0) {
            offsetInFile = 0;
        }
        this.byteOffsetInFile = offsetInFile;
    }

    public long getByteOffsetForBuffer() {
        return this.byteOffsetForBuffer;
    }

    public void setByteOffsetForBuffer(long offsetForBuffer) {
        if(offsetForBuffer < 0) {
            offsetForBuffer = 0;
        }
        this.byteOffsetForBuffer = offsetForBuffer;
    }

    public void setParsingStartTime(long parsingStartTime) {
        this.parsingStartTime = parsingStartTime;
    }

    public void setPublishingStartTime(long publishingStartTime) {
        this.publishingStartTime = publishingStartTime;
    }

    public void setParsingEndTime(long parsingEndTime) {
        this.parsingEndTime = parsingEndTime;
        this.publishingStartTime = parsingEndTime;
    }

    public void setPublishingEndTime(long publishingEndTime) {
        this.publishingEndTime = publishingEndTime;
    }

    public long getParsingDuration() {
        return this.parsingEndTime - this.parsingStartTime;
    }

    public long getPublishingDuration() {
        return this.publishingEndTime - this.publishingStartTime;
    }

    public void setIterationStartTime(long iterationStartTime) {
        this.iterationStartTime = iterationStartTime;
        this.parsingStartTime = iterationStartTime;
    }

    public void setIterationEndTime(long iterationEndTime) {
        this.iterationEndTime = iterationEndTime;
        this.publishingEndTime = iterationEndTime;
    }

    public long getIterationDuration() {
        return this.iterationEndTime - this.iterationStartTime;
    }

    public long getProcessedBytes() {
        return processedBytes;
    }

    public void setProcessedBytes(long processedBytes) {
        if(processedBytes < 0) {
            processedBytes = 0;
        }
        this.processedBytes = processedBytes;
    }

    public void addProcessedBytes(int additionalBytes) {
        this.processedBytes += additionalBytes;
    }

    public int getProcessedRecords() {
        return processedRecords;
    }

    public void setProcessedRecords(int processedRecords) {
        if(processedRecords < 0) {
            processedRecords = 0;
        }
        this.processedRecords = processedRecords;
    }

    public int getValidRecords() {
        return validRecords;
    }

    public void setValidRecords(int validRecords) {
        if(validRecords < 0) {
            validRecords = 0;
        }
        this.validRecords = validRecords;
    }

    public int getInvalidRecords() {
        return invalidRecords;
    }

    public void setInvalidRecords(int invalidRecords) {
        if(invalidRecords < 0) {
            invalidRecords = 0;
        }
        this.invalidRecords = invalidRecords;
    }

    public int getMissingInformationRecords() {
        return missingInformationRecords;
    }

    public void setMissingInformationRecords(int missingInformationRecords) {
        if(missingInformationRecords < 0) {
            missingInformationRecords = 0;
        }
        this.missingInformationRecords = missingInformationRecords;
    }

    public JsonObject asJson() {
        return Json.createObjectBuilder()
                    .add("iterationNumber", this.getIterationNumber())
                    .add("workerId", this.getWorkerId())
                    .add("threadName", this.getThreadName())
                    .add("bufferSize", this.getBufferSize())
                    .add("byteOffsetInFile", this.getByteOffsetInFile())
                    .add("byteOffsetForBuffer", this.getByteOffsetForBuffer())
                    .add("processedBytes", this.getProcessedBytes())
                    .add("processedRecords", this.getProcessedRecords())
                    .add("validRecords", this.getValidRecords())
                    .add("invalidRecords", this.getInvalidRecords())
                    .add("missingInformationRecords", this.getMissingInformationRecords())
                    .add("iterationDuration", this.getIterationDuration())
                    .add("parsingDuration", this.getParsingDuration())
                    .add("publishingDuration", this.getPublishingDuration())
                    .build();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.threadName, this.workerId, this.iterationNumber);
    }

    @Override
    public boolean equals(Object other) {
        if(other == null)
            return false;
        if(!(other instanceof IterationStatistics))
            return false;
        return other.hashCode() == this.hashCode();
    }

    @Override
    public String toString() {
        return this.asJson().toString();
    }

    public void addResidualProcessedBytes(int residualBytes) {
        this.setProcessedBytes(this.processedBytes + residualBytes);
    }
    
    public void addResidualProcessedRecords(int residualRecords) {
        this.setProcessedRecords(this.processedRecords + residualRecords);
    }
    
}
