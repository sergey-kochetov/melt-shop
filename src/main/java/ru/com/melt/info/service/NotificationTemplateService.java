package ru.com.melt.info.service;

import ru.com.melt.info.model.NotificationMessage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface NotificationTemplateService {

    @Nonnull
    NotificationMessage createNotificationMessage(@Nonnull String templateName, @Nullable Object model);
}
