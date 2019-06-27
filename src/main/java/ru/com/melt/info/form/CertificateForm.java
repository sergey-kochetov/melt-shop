package ru.com.melt.info.form;

import ru.com.melt.info.entity.Certificate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class CertificateForm {
    @Valid
    private List<Certificate> items = new ArrayList<>();

    public CertificateForm() {
        super();
    }

    public CertificateForm(List<Certificate> items) {
        super();
        this.items = items;
    }

    public List<Certificate> getItems() {
        return items;
    }

    public void setItems(List<Certificate> items) {
        this.items = items;
    }
}
