package ru.com.melt.info.service;

import ru.com.melt.info.entity.Profile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface SocialService<T> {

    @Nullable
    Profile loginOrSignup(@Nonnull T model);
}
