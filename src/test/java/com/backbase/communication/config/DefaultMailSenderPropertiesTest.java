package com.backbase.communication.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = DefaultMailSenderProperties.class)
@EnableConfigurationProperties(value = DefaultMailSenderProperties.class)
@ActiveProfiles({"test"})
public class DefaultMailSenderPropertiesTest {

    @Autowired
    DefaultMailSenderProperties defaultMailSenderProperties;

    @Test
    public void defaultMailSenderPropertiesShouldNotBeNull(){
        Assertions.assertEquals("test", defaultMailSenderProperties.getFromName());
        Assertions.assertEquals("test@backbase.com", defaultMailSenderProperties.getFromAddress());
    }

}