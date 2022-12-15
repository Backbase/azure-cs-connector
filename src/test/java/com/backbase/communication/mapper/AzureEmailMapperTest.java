package com.backbase.communication.mapper;

import com.backbase.communication.config.DefaultMailSenderProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = AzureEmailMapper.class)
@EnableConfigurationProperties(value = DefaultMailSenderProperties.class)
@ActiveProfiles({"test"})
class AzureEmailMapperTest {

    @Autowired
    private AzureEmailMapper azureEmailMapper;

    @Test
    void toEmailMessage() {
//        Email randomEmail = EmailFactory.createRandomEmail();
//        EmailMessage emailMessage = azureEmailMapper.toEmailMessage(randomEmail);
//        assertThat(randomEmail.getFrom()).isEqualTo(emailMessage.getSender());
//        assertThat(randomEmail.getBody()).isEqualTo(emailMessage.getContent().getHtml());
//        assertThat(randomEmail.getSubject()).isEqualTo(emailMessage.getContent().getSubject());
//        assertThat(randomEmail.getTo()).containsExactlyElementsOf(
//                Streams.stream(emailMessage.getRecipients().getTo())
//                        .map(EmailAddress::getEmail).toList());
//        assertThat(randomEmail.getCc()).containsExactlyElementsOf(
//                Streams.stream(emailMessage.getRecipients().getCc())
//                        .map(EmailAddress::getEmail).toList());
//        assertThat(randomEmail.getBcc()).containsExactlyElementsOf(
//                Streams.stream(emailMessage.getRecipients().getBcc())
//                        .map(EmailAddress::getEmail).toList());
//        assertThat(randomEmail.getReplyTo()).isEqualTo(emailMessage.getReplyTo().iterator().next().getEmail());
//        assertThat(randomEmail.getImportant()).isEqualTo(emailMessage.getImportance().equals(EmailImportance.HIGH));
//        assertThat(randomEmail.getAttachments().stream().map(Attachment::getFileName).toList())
//                .containsSequence(Streams.stream(emailMessage.getAttachments())
//                        .map(EmailAttachment::getName).toList());
//        assertThat(randomEmail.getAttachments().stream().map(Attachment::getContent).toList())
//                .containsSequence(Streams.stream(emailMessage.getAttachments())
//                        .map(EmailAttachment::getContentBytesBase64).toList());
    }
}