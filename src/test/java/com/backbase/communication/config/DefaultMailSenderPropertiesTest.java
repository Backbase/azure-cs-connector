package com.backbase.communication.config;

import org.junit.Assert;
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
        Assert.assertEquals("test", defaultMailSenderProperties.getFromName());
        Assert.assertEquals("test@backbase.com", defaultMailSenderProperties.getFromAddress());
    }

}