package ru.com.melt.info.service;

import ru.com.melt.info.model.NotificationMessage;

public interface NotificationTemplateService {

    NotificationMessage createNotificationMessage(String templateName, Object model);
}
