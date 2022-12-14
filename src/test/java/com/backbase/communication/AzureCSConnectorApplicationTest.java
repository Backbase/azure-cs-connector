package com.backbase.communication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
public class AzureCSConnectorApplicationTest {

    static {
        System.setProperty("SIG_SECRET_KEY", "JWTSecretKeyDontUseInProduction!");
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldLoadContext() {
        Assertions.assertNotNull(applicationContext);
    }

    @Test
    public void shouldLoadContextWithArgs() {
        AzureCSConnectorApplication.main(new String[]{"--spring.profiles.active=test"});
        Assertions.assertNotNull(applicationContext);
    }
}