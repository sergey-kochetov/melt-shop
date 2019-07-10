package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;

import javax.annotation.Nonnull;

public interface NotificationManagerService {

    void sendRestoreAccessLink(@Nonnull Profile profile, @Nonnull String restoreLink);

    void sendPasswordChanged(@Nonnull Profile profile);

    void sendPasswordGenerated(@Nonnull Profile profile, @Nonnull String generatedPassword);
}
