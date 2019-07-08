package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface SocialService<T> {

    Profile loginViaSocialNetwork(T model);

    @Nullable
    Profile login(@Nonnull T model);

    @Nullable Profile createNewProfile(@Nonnull T model);
}
