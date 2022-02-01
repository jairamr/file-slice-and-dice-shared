package com.minimalism.shared.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IngestServiceSummaryTests {
    private IngestServiceSummary iut = new IngestServiceSummary();
    @Test
    void testAddStat() {
        IterationStatistics iStat = new IterationStatistics();
        iStat.setBufferSize(1024*1024);
        iStat.setByteOffsetForBuffer(Long.MAX_VALUE);
        iStat.setByteOffsetInFile(Long.MAX_VALUE);
        iStat.setInvalidRecords(Integer.MAX_VALUE);
        iStat.setIterationNumber(Integer.MAX_VALUE);
        iStat.setMissingInformationRecords(Integer.MAX_VALUE);
        iStat.setProcessedBytes(Long.MAX_VALUE);
        iStat.setProcessedRecords(Integer.MAX_VALUE);
        iStat.setThreadName("thread_one");
        iStat.setValidRecords(Integer.MAX_VALUE);
        iStat.setWorkerId(Long.MAX_VALUE);
        iut.addStat(iStat);
        assertEquals(1, iut.getStats().size());
    }

    @Test
    void testInputFileHadInvalidRecords_true() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setInvalidRecords(3);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setInvalidRecords(0);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(Boolean.TRUE, iut.inputFileHadInvalidRecords());
    }

    @Test
    void testInputFileHadInvalidRecords_false() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setInvalidRecords(0);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setInvalidRecords(0);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(Boolean.FALSE, iut.inputFileHadInvalidRecords());
    }

    @Test
    void testInputFileHadMissingInformation_true() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setMissingInformationRecords(2);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setMissingInformationRecords(0);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(Boolean.TRUE, iut.inputFileHadMissingInformation()); 
    }

    @Test
    void testInputFileHadMissingInformation_false() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setMissingInformationRecords(0);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setMissingInformationRecords(0);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(Boolean.FALSE, iut.inputFileHadMissingInformation()); 
    }

    @Test
    void testIterationHadNoErrors_true() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setInvalidRecords(0);
        iStat1.setMissingInformationRecords(0);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setInvalidRecords(0);
        iStat2.setMissingInformationRecords(0);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(Boolean.TRUE,iut.iterationHadNoErrors());
    }

    @ParameterizedTest
    @CsvSource({
        "0", "0", "3", "0",
        "0", "0", "0", "5",
        "3", "0", "0", "5", 
    })
    void testIterationHadNoErrors_false_with_invalidRecords(
            int invalidRecords_1, int missingInformationRecords_1,
            int invalidRecords_2, int missingInformationRecords_2) {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setThreadName("thread_1");
        iStat1.setWorkerId(0L);
        iStat1.setIterationNumber(0);
        iStat1.setInvalidRecords(invalidRecords_1);
        iStat1.setMissingInformationRecords(missingInformationRecords_1);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setThreadName("thread_2");
        iStat2.setWorkerId(0L);
        iStat2.setIterationNumber(0);
        iStat2.setInvalidRecords(invalidRecords_2);
        iStat2.setMissingInformationRecords(missingInformationRecords_2);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(Boolean.FALSE, iut.iterationHadNoErrors());
    }

    @Test
    void testNumberOfIterations() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setThreadName("thread_1");
        iStat1.setWorkerId(0L);
        iStat1.setIterationNumber(0);
        iStat1.setInvalidRecords(0);
        iStat1.setMissingInformationRecords(0);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setThreadName("thread_2");
        iStat2.setWorkerId(0L);
        iStat2.setIterationNumber(0);
        iStat2.setInvalidRecords(0);
        iStat2.setMissingInformationRecords(0);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(2, iut.numberOfIterations());
    }

    @Test
    void testProcessingTime() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setThreadName("thread_1");
        iStat1.setWorkerId(0L);
        iStat1.setIterationNumber(0);
        iStat1.setInvalidRecords(0);
        iStat1.setMissingInformationRecords(0);
        iStat1.setIterationStartTime(1);
        iStat1.setIterationEndTime(21);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setThreadName("thread_2");
        iStat2.setWorkerId(0L);
        iStat2.setIterationNumber(0);
        iStat2.setInvalidRecords(0);
        iStat2.setMissingInformationRecords(0);
        iStat2.setIterationStartTime(0);
        iStat2.setIterationEndTime(22);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(22L, iut.processingTime());
    }

    @Test
    void testSetFileName() {
        iut.setFileName("hrData_10m.csv");
        assertEquals("hrData_10m.csv", iut.getFileName());
    }

    @Test
    void testSetFileName_null_value() {
        iut.setFileName(null);
        assertEquals("no_name_available", iut.getFileName());
    }

    @Test
    void testSetFileName_empty_value() {
        iut.setFileName("");
        assertEquals("no_name_available", iut.getFileName());
    }

    @Test
    void testSetFileName_blank_value() {
        iut.setFileName("  ");
        assertEquals("no_name_available",iut.getFileName());
    }

    @Test
    void testSetProcessingDate() {
        iut.setProcessingDate(LocalDate.now());
        assertEquals(LocalDate.now(), iut.getProcessingDate());
    }

    @Test
    void testSetRecordName() {
        iut.setRecordName("hrData");
        assertEquals("hrData", iut.getRecordName());
    }

    @Test
    void testSetRecordName_null_value() {
        iut.setRecordName(null);
        assertEquals("no_name_available", iut.getRecordName());
    }

    @Test
    void testSetrecordName_empty_value() {
        iut.setRecordName("");
        assertEquals("no_name_available", iut.getRecordName());
    }

    @Test
    void testSetRecordName_blank_value() {
        iut.setRecordName("  ");
        assertEquals("no_name_available", iut.getRecordName());
    }

    @Test
    void testSetStats() {
        IterationStatistics iStat = new IterationStatistics();
        iStat.setBufferSize(1024*1024);
        iStat.setByteOffsetForBuffer(Long.MAX_VALUE);
        iStat.setByteOffsetInFile(Long.MAX_VALUE);
        iStat.setInvalidRecords(Integer.MAX_VALUE);
        iStat.setIterationNumber(Integer.MAX_VALUE);
        iStat.setMissingInformationRecords(Integer.MAX_VALUE);
        iStat.setProcessedBytes(Long.MAX_VALUE);
        iStat.setProcessedRecords(Integer.MAX_VALUE);
        iStat.setThreadName("thread_one");
        iStat.setValidRecords(Integer.MAX_VALUE);
        iStat.setWorkerId(Long.MAX_VALUE);
        Set<IterationStatistics> stats = new HashSet<>();
        stats.add(iStat);
        iut.setStats(stats);
        assertEquals(1, iut.getStats().size());
    }
    
    @Test
    void testToString() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setThreadName("thread_1");
        iStat1.setWorkerId(0L);
        iStat1.setIterationNumber(0);
        iStat1.setInvalidRecords(0);
        iStat1.setMissingInformationRecords(0);
        iStat1.setIterationStartTime(1);
        iStat1.setIterationEndTime(21);
        iStat1.setProcessedBytes(245678L);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setThreadName("thread_2");
        iStat2.setWorkerId(0L);
        iStat2.setIterationNumber(0);
        iStat2.setInvalidRecords(0);
        iStat2.setMissingInformationRecords(0);
        iStat2.setIterationStartTime(0);
        iStat2.setIterationEndTime(22);
        iStat2.setProcessedBytes(345678L);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals("{\"fileName\":null,\"recordName\":null,\"processingDate\":null,\"numberOfIterations\":2,\"totalRecordsProcessed\":0," +
        "\"totalBytesProcessed\":591356,\"processingTime\":22,\"iterationStatistics\":[{\"iterationNumber\":0,\"workerId\":0," + 
        "\"threadName\":\"thread_1\",\"bufferSize\":0,\"byteOffsetInFile\":0,\"byteOffsetForBuffer\":0,\"processedBytes\":245678," + 
        "\"processedRecords\":0,\"validRecords\":0,\"invalidRecords\":0,\"missingInformationRecords\":0,\"iterationDuration\":20," + 
        "\"parsingDuration\":-1,\"publishingDuration\":21},{\"iterationNumber\":0,\"workerId\":0,\"threadName\":\"thread_2\"," +
        "\"bufferSize\":0,\"byteOffsetInFile\":0,\"byteOffsetForBuffer\":0,\"processedBytes\":345678,\"processedRecords\":0," + 
        "\"validRecords\":0,\"invalidRecords\":0,\"missingInformationRecords\":0,\"iterationDuration\":22,\"parsingDuration\":0," + 
        "\"publishingDuration\":22}]}", iut.toString());
        
    }

    @Test
    void testTotalBytesProcessed() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setThreadName("thread_1");
        iStat1.setWorkerId(0L);
        iStat1.setIterationNumber(0);
        iStat1.setInvalidRecords(0);
        iStat1.setMissingInformationRecords(0);
        iStat1.setIterationStartTime(1);
        iStat1.setIterationEndTime(21);
        iStat1.setProcessedBytes(245678L);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setThreadName("thread_2");
        iStat2.setWorkerId(0L);
        iStat2.setIterationNumber(0);
        iStat2.setInvalidRecords(0);
        iStat2.setMissingInformationRecords(0);
        iStat2.setIterationStartTime(0);
        iStat2.setIterationEndTime(22);
        iStat2.setProcessedBytes(345678L);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals((2456782L + 345678L), iut.totalBytesProcessed());
    }

    @Test
    void testTotalRecordsProcessed() {
        IterationStatistics iStat1 = new IterationStatistics();
        iStat1.setThreadName("thread_1");
        iStat1.setWorkerId(0L);
        iStat1.setIterationNumber(0);
        iStat1.setInvalidRecords(0);
        iStat1.setMissingInformationRecords(0);
        iStat1.setIterationStartTime(1);
        iStat1.setIterationEndTime(21);
        iStat1.setProcessedRecords(2345);
        IterationStatistics iStat2 = new IterationStatistics();
        iStat2.setThreadName("thread_2");
        iStat2.setWorkerId(0L);
        iStat2.setIterationNumber(0);
        iStat2.setInvalidRecords(0);
        iStat2.setMissingInformationRecords(0);
        iStat2.setIterationStartTime(0);
        iStat2.setIterationEndTime(22);
        iStat2.setProcessedRecords(3456);
        iut.addStat(iStat1);
        iut.addStat(iStat2);
        assertEquals(2345+3456, iut.totalRecordsProcessed());
    }
}
