package com.backbase.communication.service;

import com.backbase.communication.azure.email.AzureEmailClient;
import com.backbase.communication.mapper.EmailV1Mapper;
import com.backbase.communication.model.Email;
import com.backbase.communication.testutils.EmailFactory;
import com.backbase.communication.testutils.EmailV1Factory;
import com.backbase.outbound.integration.communications.rest.spec.v1.model.Content;
import com.backbase.outbound.integration.communications.rest.spec.v1.model.Recipient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class EmailNotificationServiceTest {

    static {
        System.setProperty("SIG_SECRET_KEY", "JWTSecretKeyDontUseInProduction!");
    }

    @Autowired
    EmailServiceV1 emailServiceV1;
    @MockBean
    AzureEmailClient azureEmailClient;
    @MockBean
    EmailV1Mapper emailV1Mapper;

    @Test
    void processRecipient() {
        final Recipient recipient = EmailV1Factory.emailV1().getRecipients().get(0);
        final Content content = EmailV1Factory.emailV1().getContent().get(0);
        when(emailV1Mapper.toEmail(recipient, content)).thenReturn(EmailFactory.createRandomEmail());
        emailServiceV1.sendEmailV1(recipient, content);
        verify(emailV1Mapper, times(1)).toEmail(recipient, content);
        verify(azureEmailClient,times(1)).sendEmail(any());
        verify(azureEmailClient,times(1)).checkEmailDelivery(any());
    }

    @Test
    void processRecipient_withEncoded_body() {
        final Recipient recipient = EmailV1Factory.emailV1().getRecipients().get(0);
        final Content content = EmailV1Factory.emailV1().getContent().get(0);

        final Email email = EmailFactory.createRandomEmail();
        email.setBody("This is your otp: 123456");
        email.setFrom(null);
        when(emailV1Mapper.toEmail(recipient, content)).thenReturn(email);
        emailServiceV1.sendEmailV1(recipient, content);
        verify(emailV1Mapper, times(1)).toEmail(recipient, content);
    }
}