package ru.com.melt.info.form;

import org.hibernate.validator.constraints.SafeHtml;
import ru.com.melt.info.annotation.constraints.EnglishLanguage;
import ru.com.melt.info.entity.Profile;

public class InfoForm {

    @EnglishLanguage
    @SafeHtml
    private String info;

    public InfoForm() {
        super();
    }

    public InfoForm(String info) {
        super();
        this.info = info;
    }

    public InfoForm(Profile profile) {
        super();
        this.info = profile.getInfo();
    }

    public String getInfo() {
        return info != null ? info.trim() : null;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
