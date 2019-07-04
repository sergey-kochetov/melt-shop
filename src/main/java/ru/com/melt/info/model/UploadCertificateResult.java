package ru.com.melt.info.model;

import java.io.Serializable;

public class UploadCertificateResult extends UploadResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String certificateName;

    public UploadCertificateResult(String certificateName, String largeUrl, String smallUrl) {
        super(largeUrl, smallUrl);
        this.certificateName = certificateName;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }
}