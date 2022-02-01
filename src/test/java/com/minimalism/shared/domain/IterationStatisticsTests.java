package com.minimalism.shared.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class IterationStatisticsTests {
    public IterationStatistics iut = new IterationStatistics();
    
    @Test
    void testAddResidualProcessedBytes() {
        iut.setProcessedBytes(35000);
        iut.addResidualProcessedBytes(5000);
        assertEquals(40000,iut.getProcessedBytes());
    }

    @Test
    void testAddResidualProcessedRecords() {
        iut.setProcessedRecords(2500);
        iut.addResidualProcessedRecords(300);
        assertEquals(2800, iut.getProcessedRecords());
    }

    @Test
    void testGetIterationDuration() {
        iut.setIterationStartTime(123456789L);
        iut.setIterationEndTime(234567890L);
        assertEquals((234567890L - 123456789L), iut.getIterationDuration());
    }

    @Test
    void testGetParsingDuration() {
        iut.setParsingStartTime(345678901L);
        iut.setParsingEndTime(456789012L);
        assertEquals((456789012 - 345678901), iut.getParsingDuration());
    }

    @Test
    void testGetPublishingDuration() {
        iut.setParsingStartTime(123456789L);
        iut.setParsingEndTime(234567890L);
        assertEquals((234567890 - 123456789), iut.getParsingDuration());
    }

    @Test
    void testHashCode() {
        iut.setThreadName("thread_one");
        iut.setWorkerId(1L);
        iut.setIterationNumber(0);
        assertEquals(Objects.hash("thread_one", 1L, 0), iut.hashCode());
    }

    @Test
    void testSetBufferSize() {
        iut.setBufferSize(1024*1024);
        assertEquals(1024*1024, iut.getBufferSize());
    }

    @Test
    void testSetBufferSize_with_negative_value() {
        iut.setBufferSize(-1024*1024);
        assertEquals(0, iut.getBufferSize());
    }

    @Test
    void testSetByteOffsetForBuffer() {
        iut.setByteOffsetForBuffer(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, iut.getByteOffsetForBuffer());
    }

    @Test
    void testsetByteOffsetForBuffer_negative_value() {
        iut.setByteOffsetForBuffer(-10241024L);
        assertEquals(0, iut.getByteOffsetForBuffer());
    }

    @Test
    void testSetByteOffsetInFile() {
        iut.setByteOffsetInFile(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, iut.getByteOffsetInFile());
    }

    @Test
    void testSetByteOffsetInFile_negative_value() {
        iut.setByteOffsetInFile(-Long.MAX_VALUE);
        assertEquals(0,iut.getByteOffsetInFile());
    }

    @Test
    void testSetInvalidRecords() {
        iut.setInvalidRecords(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, iut.getInvalidRecords());
    }

    @Test
    void testSetInvalidRecords_negative_value() {
        iut.setInvalidRecords(-5);
        assertEquals(0, iut.getInvalidRecords());
    }

    @Test
    void testSetIterationNumber() {
        iut.setIterationNumber(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, iut.getIterationNumber());
    }

    @Test
    void testSetIterationNumber_negative_value() {
        iut.setIterationNumber(-23);
        assertEquals(0, iut.getIterationNumber());
    }

    @Test
    void testSetMissingInformationRecords() {
        iut.setMissingInformationRecords(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE,iut.getMissingInformationRecords());
    }

    @Test
    void testMissingInformationRecords_negative_value() {
        iut.setMissingInformationRecords( -8);
        assertEquals(0, iut.getMissingInformationRecords());
    }

    @Test
    void testSetProcessedBytes() {
        iut.setProcessedBytes(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, iut.getProcessedBytes());
    }

    @Test
    void testSetProcessedBytes_negative_value() {
        iut.setProcessedBytes(-Long.MAX_VALUE);
        assertEquals(0, iut.getProcessedBytes());
    }

    @Test
    void testSetProcessedRecords() {
        iut.setProcessedRecords(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, iut.getProcessedRecords());
    }

    @Test
    void testSetProcessedRecords_negative_value() {
        iut.setProcessedRecords(-Integer.MAX_VALUE);
        assertEquals(0, iut.getProcessedRecords());
    }

    @Test
    void testSetThreadName() {
        iut.setThreadName("thread_one");
        assertEquals("thread_one", iut.getThreadName());
    }

    @Test
    void testSetThreadName_null_value() {
        iut.setThreadName(null);
        assertEquals("no_name", iut.getThreadName());
    }

    @Test
    void testSetThreadName_empty_value() {
        iut.setThreadName("");
        assertEquals("no_name", iut.getThreadName());
    }

    @Test
    void testSetThreadName_blank_value() {
        iut.setThreadName("  ");
        assertEquals("no_name", iut.getThreadName());
    }

    @Test
    void testSetValidRecords() {
        iut.setValidRecords(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE,iut.getValidRecords());
    }

    @Test
    void testSetValidRecords_negative_value() {
        iut.setValidRecords(-Integer.MAX_VALUE);
        assertEquals(0, iut.getValidRecords());
    }

    @Test
    void testSetWorkerId() {
        iut.setWorkerId(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, iut.getWorkerId());
    }

    @Test
    void testSetWorkerId_negative_value() {
        iut.setWorkerId(-Long.MAX_VALUE);
        assertEquals(0,iut.getWorkerId());
    }

    @Test
    void testToString() {
        iut.setBufferSize(1024*1024);
        iut.setByteOffsetForBuffer(Long.MAX_VALUE);
        iut.setByteOffsetInFile(Long.MAX_VALUE);
        iut.setInvalidRecords(Integer.MAX_VALUE);
        iut.setIterationNumber(Integer.MAX_VALUE);
        iut.setMissingInformationRecords(Integer.MAX_VALUE);
        iut.setProcessedBytes(Long.MAX_VALUE);
        iut.setProcessedRecords(Integer.MAX_VALUE);
        iut.setThreadName("thread_one");
        iut.setValidRecords(Integer.MAX_VALUE);
        iut.setWorkerId(Long.MAX_VALUE);
        assertEquals("{\"iterationNumber\":2147483647,\"workerId\":9223372036854775807,"+
        "\"threadName\":\"thread_one\",\"bufferSize\":1048576,\"byteOffsetInFile\":9223372036854775807,"+
        "\"byteOffsetForBuffer\":9223372036854775807,\"processedBytes\":9223372036854775807,"+
        "\"processedRecords\":2147483647,\"validRecords\":2147483647,\"invalidRecords\":2147483647,"+
        "\"missingInformationRecords\":2147483647,\"iterationDuration\":0,\"parsingDuration\":0,"+
        "\"publishingDuration\":0}",iut.toString());
    }
}
