package com.backbase.communication.service;

import com.backbase.communication.azure.email.AzureEmailClient;
import com.backbase.communication.model.Email;
import com.backbase.communication.model.Status;
import com.backbase.communication.util.DeliveryCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {
    private final AzureEmailClient azureEmailClient;

    public Status sendEmail(Email email) {
        log.debug("Email: '{}'", email.toString());
        Status resultStatus = new Status(DeliveryCodes.SENT);
        try {
            String messageId = azureEmailClient.sendEmail(email);
            String status = azureEmailClient.checkEmailDelivery(messageId);
            log.debug("Email with message id: {} sent with send status: {}", messageId, status);
        } catch (Exception e) {
            log.error("Failed to send email with code.", e);
            resultStatus.setState(DeliveryCodes.FAILED);
        }
        return resultStatus;
    }

}
