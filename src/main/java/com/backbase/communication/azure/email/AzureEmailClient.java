package com.backbase.communication.azure.email;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.SendEmailResult;
import com.backbase.communication.azure.config.AzureProperties;
import com.backbase.communication.mapper.AzureEmailMapper;
import com.backbase.communication.model.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class AzureEmailClient {
    private EmailClient emailClient;
    private final AzureProperties azureProperties;
    private final AzureEmailMapper azureEmailMapper;

    @PostConstruct
    public void init() {
        emailClient = new EmailClientBuilder()
                .connectionString(azureProperties.getEmailConnectionString())
                .buildClient();
    }

    public String sendEmail(Email email) {
        EmailMessage emailMessage = azureEmailMapper.toEmailMessage(email);
        log.debug("Start sending email to azure. {}", emailMessage);
        SendEmailResult sendEmailResult = emailClient.send(emailMessage);
        log.debug("Azure email sent result. {}", sendEmailResult);
        return sendEmailResult.getMessageId();
    }

    public String checkEmailDelivery(String messageId) {
        return emailClient.getSendStatus(messageId).getStatus().toString();
    }
}
