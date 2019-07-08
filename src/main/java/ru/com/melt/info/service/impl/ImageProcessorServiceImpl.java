package ru.com.melt.info.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.com.melt.info.model.UploadCertificateResult;
import ru.com.melt.info.model.UploadResult;
import ru.com.melt.info.service.ImageProcessorService;

import javax.annotation.Nonnull;

@Service
public class ImageProcessorServiceImpl implements ImageProcessorService {

    @Nonnull
    @Override
    public UploadResult processNewProfilePhoto(@Nonnull MultipartFile uploadPhoto) {
        return null;
    }

    @Nonnull
    @Override
    public UploadCertificateResult processNewCertificateImage(@Nonnull MultipartFile uploadCertificateImage) {
        return null;
    }
}