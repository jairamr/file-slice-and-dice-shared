package com.minimalism.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import com.minimalism.shared.domain.ServiceConfiguration;
import com.minimalism.shared.exceptions.NoSuchPathException;
import com.minimalism.shared.service.ClientConfigHelper;

import org.junit.jupiter.api.Test;

class ClientConfigHelperTests {
    @Test
    void testGetServiceConfiguration() {
        ClientConfigHelper iut;
        try {
            iut = new ClientConfigHelper("client_1");
            ServiceConfiguration result = iut.getServiceConfiguration("client_1");
            assertEquals("client_1", result.getClientName());
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        } 
    }

    @Test
    void testGetServiceConfiguration_non_existent_client_throws_exception() {
        try {
            ClientConfigHelper iut = new ClientConfigHelper("client_4");
            assertThrows(NoSuchPathException.class, () -> {
                iut.getServiceConfiguration("client_4");
            });
        } catch (IOException | NoSuchPathException e) {
            e.printStackTrace();
        }
    }
}
