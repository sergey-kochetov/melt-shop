package ru.com.melt.info.component.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.com.melt.info.service.ImageStorageService;

import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UploadCertificateLinkTempStorage implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_CAPACITY = 6;
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadCertificateLinkTempStorage.class);

    @Autowired
    private transient ImageStorageService imageStorageService;

    private List<String> imageLinks;

    protected List<String> getImageLinks() {
        if (imageLinks == null) {
            imageLinks = new ArrayList<>(MAX_CAPACITY);
        }
        return imageLinks;
    }

    public final void addImageLinks(String largeImageLink, String smallImageLink) {
        getImageLinks().add(largeImageLink);
        getImageLinks().add(smallImageLink);
    }

    public final void clearImageLinks() {
        getImageLinks().clear();
    }

    @PreDestroy
    private void preDestroy() {
        if (!getImageLinks().isEmpty()) {
            for (String link : getImageLinks()) {
                imageStorageService.remove(link);
            }
            LOGGER.info("Removed {} temporary images", imageLinks);
        }
    }
}
