package com.backbase.communication.channel;

import com.backbase.communication.service.EmailNotificationService;
import com.backbase.communication.testutils.EmailV1Factory;
import com.backbase.communication.testutils.EmailV2Factory;
import com.backbase.communication.validator.EmailV1Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class EmailCommunicationChannelConsumerTest {

    @Autowired
    EmailCommunicationChannelConsumer emailCommunicationChannelConsumer;

    @Autowired
    EmailV1Validator emailV1Validator;

    @MockBean
    EmailNotificationService emailNotificationService;

    @Test
    void acceptEmailV1Test() {
        emailCommunicationChannelConsumer.accept(EmailV1Factory.emailV1());
        verify(emailNotificationService, times(1)).sendEmail(any());
    }

    @Test
    void acceptEmailV2Test() {
        emailCommunicationChannelConsumer.accept(EmailV2Factory.createRandomEmailV2());
        verify(emailNotificationService, times(1)).sendEmail(any());
    }
}
