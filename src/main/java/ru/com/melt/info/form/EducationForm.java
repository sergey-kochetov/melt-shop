package ru.com.melt.info.form;

import ru.com.melt.info.entity.Education;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class EducationForm {
    @Valid
    private List<Education> items = new ArrayList<>();

    public EducationForm() {
        super();
    }

    public EducationForm(List<Education> items) {
        super();
        this.items = items;
    }

    public List<Education> getItems() {
        return items;
    }

    public void setItems(List<Education> items) {
        this.items = items;
    }
}