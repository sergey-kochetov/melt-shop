package ru.com.melt.info.component;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;

public interface ImageResizer {
    void resizeImage(@Nonnull Path sourceImageFile,
                     @Nonnull Path destImageFile,
                     int width, int height) throws IOException;
}
