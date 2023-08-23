package com.backbase.communication;

import com.backbase.buildingblocks.commns.model.ProcessingStatus;
import com.backbase.buildingblocks.testutils.TestTokenUtil;
import com.backbase.communication.tracking.TrackingListener;
import com.backbase.communication.tracking.TrackingReceiver;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@Slf4j
@Testcontainers
@ActiveProfiles({"default", "it"})
@SpringBootTest(classes = {AzureCSConnectorApplication.class})
@ContextConfiguration(classes = AzureCSConnectorApplication.class)
class SendEmailIT implements TrackingListener {

    private final RestTemplate template = new RestTemplate();

    @Autowired
    private TrackingReceiver trackingReceiver;

    @Container
    private final static DockerComposeContainer environment = new DockerComposeContainer(
            new File("src/test/resources/docker-compose.yml"))
            .withLogConsumer("message-broker", new Slf4jLogConsumer(log))
            .withLogConsumer("communication", new Slf4jLogConsumer(log))
            .withExposedService("message-broker", 61616, Wait.forListeningPort())
            .withExposedService("communication", 8080, Wait.forHttp("/actuator/health")
                    .forStatusCode(200))
            .withLocalCompose(true);

    private final ArrayList<String> trackingCodes = new ArrayList<>();

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        System.setProperty("SIG_SECRET_KEY", "JWTSecretKeyDontUseInProduction!");
        System.setProperty("TESTCONTAINERS_RYUK_DISABLED", "true");
        registry.add("spring.activemq.broker-url", () -> "tcp://%s:%s"
                .formatted(environment.getServiceHost("message-broker", 61616),
                        environment.getServicePort("message-broker", 61616)));
    }

    @BeforeEach
    void init() {
        trackingCodes.clear();
        trackingReceiver.setTrackingListener(this);
    }

    @ParameterizedTest
    @CsvSource({
            "Important Email Subject, This is the email body for john, john@domain.com"
    })
    void sendEmail(String subject, String body, String to) {
        String host = environment.getServiceHost("communication", 8080);
        int port = environment.getServicePort("communication", 8080);

        String uuid = UUID.randomUUID().toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + TestTokenUtil.encode(TestTokenUtil.serviceClaimSet()));

        RequestEntity<String> request = RequestEntity.post("http://%s:%s/service-api/v1/messages".formatted(host, port))
                .headers(headers)
                .body("""
                        {
                          "messages": [
                            {
                              "deliveryChannel": "email",
                              "priority": 1,
                              "tag": "%s",
                              "payload": {
                                "subject": "%s",
                                "body": "%s",
                                "to": ["%s"],
                                "replyTo": "%s"
                              }
                            }
                          ]
                        }
                        """.formatted(uuid, subject, body, to, to));

        ResponseEntity<Void> exchange = template.exchange(request, Void.TYPE);
        Assertions.assertThat(exchange.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        await().atMost(2, TimeUnit.MINUTES).until(() -> !trackingCodes.isEmpty());

        assertThat(trackingCodes).isNotEmpty();
    }

    public void trackingReceived(String trackingId, ProcessingStatus status) {
        trackingCodes.add(trackingId);
        log.debug("Tracking received for TrackId:{}, status:{}", trackingId, status);
        assertTrue(!Objects.isNull(trackingId) || ProcessingStatus.SUCCESS.equals(status));
    }
}