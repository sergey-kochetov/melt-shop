package ru.com.melt.info.service;

import ru.com.melt.info.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;

public interface ImageStorageService {

    @Nonnull
    String saveAndReturnImageLink(@Nonnull String imageName, @Nonnull Constants.UIImageType imageType, @Nonnull Path tempImageFile);

    void remove(@Nullable String... imageLinks);
}
