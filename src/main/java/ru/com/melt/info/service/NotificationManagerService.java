package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;

public interface NotificationManagerService {

    void sendRestoreAccessLink(Profile profile, String restoreLink);

    void sendPasswordChanged(Profile profile);
}
