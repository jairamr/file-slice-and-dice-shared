package com.minimalism.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
    }
}
