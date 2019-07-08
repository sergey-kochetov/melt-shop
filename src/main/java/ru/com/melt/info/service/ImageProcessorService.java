package ru.com.melt.info.service;

import org.springframework.web.multipart.MultipartFile;
import ru.com.melt.info.model.UploadCertificateResult;
import ru.com.melt.info.model.UploadResult;

import javax.annotation.Nonnull;

public interface ImageProcessorService {

    @Nonnull
    UploadResult processNewProfilePhoto(@Nonnull MultipartFile uploadPhoto);

    @Nonnull
    UploadCertificateResult processNewCertificateImage(@Nonnull MultipartFile uploadCertificateImage);
}
