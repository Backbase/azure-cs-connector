package com.backbase.communication.mapper;

import com.azure.communication.email.models.*;
import com.backbase.communication.config.DefaultMailSenderProperties;
import com.backbase.communication.model.Attachment;
import com.backbase.communication.model.Email;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AzureEmailMapper {

    private final DefaultMailSenderProperties defaultMailSenderProperties;

    public EmailMessage toEmailMessage(Email email) {
        EmailContent emailContent = new EmailContent(email.getSubject());
        emailContent.setHtml(email.getBody());
        EmailMessage emailMessage = new EmailMessage(getFromAddress(email.getFrom()), emailContent);
        emailMessage.setImportance(toEmailImportance(email.getImportant()));
        emailMessage.setReplyTo(toEmailAddress(Optional.ofNullable(email.getReplyTo()).orElse(email.getFrom())));
        emailMessage.setRecipients(
                toEmailRecipients(email.getTo())
                        .setCc(toEmailAddress(email.getCc()))
                        .setBcc(toEmailAddress(email.getBcc())));
        emailMessage.setAttachments(toAttachments(email.getAttachments()));
        return emailMessage;
    }

    private String getFromAddress(String from) {
        return Optional.ofNullable(from).orElse(defaultMailSenderProperties.getFromAddress());
    }

    private List<EmailAttachment> toAttachments(List<Attachment> attachments) {
        return attachments.stream().map(attachment -> new EmailAttachment(attachment.getFileName(),
                EmailAttachmentType.fromString(FileNameUtils.getExtension(attachment.getFileName())),
                attachment.getContent())).toList();
    }

    private EmailRecipients toEmailRecipients(List<String> to) {
        return new EmailRecipients(toEmailAddress(to));
    }

    private Iterable<EmailAddress> toEmailAddress(List<String> to) {
        return to.stream().map(EmailAddress::new).toList();
    }

    private Iterable<EmailAddress> toEmailAddress(String email) {
        return List.of(new EmailAddress(email));
    }

    private EmailImportance toEmailImportance(Boolean important) {
        return Optional.ofNullable(important).orElse(false) ? EmailImportance.HIGH : EmailImportance.NORMAL;
    }
}
