package com.backbase.communication.azure.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AzureProperties.class)
@EnableConfigurationProperties(AzureProperties.class)
@ActiveProfiles("test")
class AzurePropertiesTest {

    @Autowired
    private AzureProperties azureProperties;

    @Test
    void getEmailConnectionString() {
        assertNotNull(azureProperties.getEmailConnectionString());
    }
}