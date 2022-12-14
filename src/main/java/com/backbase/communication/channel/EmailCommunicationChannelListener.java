package com.backbase.communication.channel;

import com.backbase.buildingblocks.commns.listener.CommnsAbstractMessagesStreamListener;
import com.backbase.buildingblocks.commns.service.AbstractPriorityQueueService;
import com.backbase.buildingblocks.commns.service.TrackingService;
import com.backbase.buildingblocks.multitenancy.TenantProvider;
import com.backbase.communication.model.Sendable;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class EmailCommunicationChannelListener extends CommnsAbstractMessagesStreamListener<Sendable> {

    public EmailCommunicationChannelListener(AbstractPriorityQueueService<Sendable> priorityQueueService,
                                             TrackingService trackingService,
                                             ObjectMapper objectMapper,
                                             TenantProvider tenantProvider) {
        super(priorityQueueService, trackingService, objectMapper, tenantProvider);
        log.info("Instantiated CommnsMessageListener");
    }
}


