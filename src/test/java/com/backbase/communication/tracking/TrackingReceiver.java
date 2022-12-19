package com.backbase.communication.tracking;

import com.backbase.buildingblocks.commns.model.ProcessingStatus;
import com.backbase.buildingblocks.commns.model.TrackingResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrackingReceiver {
    private final ObjectMapper objectMapper;

    private TrackingListener trackingListener;

    @Bean
    public Consumer<Message<String>> trackingFunction() {
        return this::processMessage;
    }

    private void processMessage(Message<String> msg) {
        try {
            TrackingResponse trackingResponse = objectMapper.readValue(msg.getPayload(), TrackingResponse.class);
            trackingListener.trackingReceived(trackingResponse.getTrackingId(), trackingResponse.getStatus());
        } catch (JsonProcessingException e) {
            log.error("Cannot deserialize payload to {}.", TrackingResponse.class);
            trackingListener.trackingReceived(null, ProcessingStatus.FAILED);
        }
    }

    public void setTrackingListener(TrackingListener trackingListener) {
        this.trackingListener = trackingListener;
    }
}
