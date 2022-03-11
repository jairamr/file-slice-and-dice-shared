package com.minimalism.shared.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;


import org.junit.jupiter.api.Test;

class SetupTests {
    @Test
    void testRegister() {
        Setup iut = new Setup();
        try {
            iut.register("test_client");
            assertTrue(Files.exists(Path.of("C:\\Users\\jaira\\FileSliceAndDice\\clients\\test_client"), LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
