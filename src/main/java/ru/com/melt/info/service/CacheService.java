package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface CacheService {

    @Nullable
    Profile findProfileByUid(@Nonnull String uid);

    void deleteProfileByUid(@Nonnull String uid);
}
