package com.backbase.communication.tracking;

import com.backbase.buildingblocks.commns.model.ProcessingStatus;

public interface TrackingListener {
    void trackingReceived(String trackingId, ProcessingStatus status);
}
