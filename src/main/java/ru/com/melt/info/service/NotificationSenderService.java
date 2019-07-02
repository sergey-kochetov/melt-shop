package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.model.NotificationMessage;

public interface NotificationSenderService {

    void sendNotification(NotificationMessage message);

    String getDestinationAddress(Profile profile);
}
