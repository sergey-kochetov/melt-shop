package ru.com.melt.info.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.com.melt.info.Constants;
import ru.com.melt.info.annotation.EnableUploadImageTempStorage;
import ru.com.melt.info.component.DataBuilder;
import ru.com.melt.info.component.ImageFormatConverter;
import ru.com.melt.info.component.ImageOptimizator;
import ru.com.melt.info.component.ImageResizer;
import ru.com.melt.info.component.impl.UploadCertificateLinkTempStorage;
import ru.com.melt.info.component.impl.UploadImageTempStorage;
import ru.com.melt.info.exception.CantCompleteClientRequestException;
import ru.com.melt.info.model.UploadCertificateResult;
import ru.com.melt.info.model.UploadResult;
import ru.com.melt.info.model.UploadTempPath;
import ru.com.melt.info.service.ImageProcessorService;
import ru.com.melt.info.service.ImageStorageService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import static ru.com.melt.info.Constants.UIImageType.CERTIFICATES;

@Service
public class ImageProcessorServiceImpl implements ImageProcessorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageProcessorServiceImpl.class);

    @Autowired
    @Qualifier("pngToJpegImageFormatConverter")
    private ImageFormatConverter pngToJpegImageFormatConverter;

    @Autowired
    private ImageResizer imageResizer;

    @Autowired
    private ImageOptimizator imageOptimizator;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private UploadImageTempStorage uploadImageTempStorage;

    @Autowired
    private UploadCertificateLinkTempStorage uploadCertificateLinkManager;

    @Autowired
    protected DataBuilder dataBuilder;

    @Override
    @EnableUploadImageTempStorage
    public UploadResult processNewProfilePhoto(MultipartFile upload) {
        try {
            return processUpload(upload, Constants.UIImageType.AVATARS);
        } catch (IOException e) {
            throw new CantCompleteClientRequestException("Can't save profile photo upload: " + e.getMessage(), e);
        }
    }

    @Override
    @EnableUploadImageTempStorage
    public UploadCertificateResult processNewCertificateImage(MultipartFile upload) {
        try {
            UploadResult photoLinks = processUpload(upload, CERTIFICATES);
            uploadCertificateLinkManager.addImageLinks(photoLinks.getLargeUrl(), photoLinks.getSmallUrl());
            String certificateName = dataBuilder.buildCertificateName(upload.getOriginalFilename());
            return new UploadCertificateResult(certificateName, photoLinks.getLargeUrl(), photoLinks.getSmallUrl());
        } catch (IOException e) {
            throw new CantCompleteClientRequestException("Can't save certificate image upload: " + e.getMessage(), e);
        }
    }

    protected UploadResult processUpload(MultipartFile multipartFile, Constants.UIImageType imageType) throws IOException {
        String largePhoto = generateNewFileName();
        String smallPhoto = getSmallImageName(largePhoto);
        UploadTempPath uploadTempPath = getCurrentUploadTempPath();
        transferUploadToFile(multipartFile, uploadTempPath.getLargeImagePath());
        resizeAndOptimizeUpload(uploadTempPath, imageType);
        String largePhotoLink = imageStorageService.saveAndReturnImageLink(largePhoto, imageType, uploadTempPath.getLargeImagePath());
        String smallPhotoLink = imageStorageService.saveAndReturnImageLink(smallPhoto, imageType, uploadTempPath.getSmallImagePath());
        return new UploadResult(largePhotoLink, smallPhotoLink);
    }

    protected void resizeAndOptimizeUpload(UploadTempPath uploadTempPath, Constants.UIImageType imageType) throws IOException {
        imageResizer.resizeImage(uploadTempPath.getLargeImagePath(), uploadTempPath.getSmallImagePath(), imageType.getSmallWidth(), imageType.getSmallHeight());
        imageOptimizator.optimize(uploadTempPath.getSmallImagePath());
        imageResizer.resizeImage(uploadTempPath.getLargeImagePath(), uploadTempPath.getLargeImagePath(), imageType.getLargeWidth(), imageType.getLargeHeight());
        imageOptimizator.optimize(uploadTempPath.getLargeImagePath());
    }

    protected String generateNewFileName() {
        return UUID.randomUUID().toString() + ".jpg";
    }

    protected String getSmallImageName(String largePhoto) {
        return largePhoto.replace(".jpg", "-sm.jpg");
    }

    protected UploadTempPath getCurrentUploadTempPath() {
        return uploadImageTempStorage.getCurrentUploadTempPath();
    }

    protected void transferUploadToFile(MultipartFile uploadPhoto, Path destPath) throws IOException {
        String contentType = uploadPhoto.getContentType();
        LOGGER.debug("Content type for upload {}", contentType);
        uploadPhoto.transferTo(destPath.toFile());
        if (contentType.contains("png")) {
            pngToJpegImageFormatConverter.convertImage(destPath, destPath);
        } else if (!contentType.contains("jpg") && !contentType.contains("jpeg")) {
            throw new CantCompleteClientRequestException("Only png and jpg image formats are supported: Current content type=" + contentType);
        }
    }
}
