package ru.com.melt.info.component;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;

public interface ImageFormatConverter {
    void convertImage(@Nonnull Path sourceImageFile,
                      @Nonnull Path destImageFile) throws IOException;
}
