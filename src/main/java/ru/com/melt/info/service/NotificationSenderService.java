package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.model.NotificationMessage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface NotificationSenderService {

    void sendNotification(@Nonnull NotificationMessage message);

    @Nullable
    String getDestinationAddress(@Nonnull Profile profile);
}
