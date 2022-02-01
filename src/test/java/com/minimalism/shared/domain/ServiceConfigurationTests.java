package com.minimalism.shared.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.minimalism.shared.common.AllEnums.DataSources;

import org.junit.jupiter.api.Test;

class ServiceConfigurationTests {
    private ServiceConfiguration iut = new ServiceConfiguration("client_1");
    @Test
    void testGetClientName() {
        assertEquals("client_1",iut.getClientName());
    }

    @Test
    void testGetFileReadBufferSize() {
        iut.setFileReadBufferSize("2048");
        assertEquals(2048,iut.getFileReadBufferSize());
    }

    @Test
    void testGetFileReadBufferSize_not_a_number() {
        assertThrows(NumberFormatException.class, () -> {
            iut.setFileReadBufferSize("not a number");
        });
    }

    @Test
    void testGetOperatingMode() {
        iut.setOperatingMode("balanced");
        assertEquals("balanced", iut.getOperatingMode());
    }

    @Test
    void testGetOutputEndpoint() {
        iut.setServiceOutputEndpoint(DataSources.KAFKA.name());
        assertEquals(DataSources.KAFKA, iut.getOutputEndpoint());
    }

    @Test
    void testGetOutputEndPoint_null_input() {
        iut.setServiceOutputEndpoint(null);
        assertEquals(DataSources.NTFS, iut.getOutputEndpoint());
    }
    

    @Test
    void testGetOutputFailEndpoint() {

    }

    @Test
    void testGetThreadsLoadingFactor() {

    }

    @Test
    void testSetFileReadBufferSize() {

    }

    @Test
    void testSetOperatingMode() {

    }

    @Test
    void testSetServiceOutputEndpoint() {

    }

    @Test
    void testSetServiceOutputFailoverEndpoint() {

    }

    @Test
    void testSetThreadsLoadingFactor() {

    }
}
