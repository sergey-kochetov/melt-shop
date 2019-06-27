package ru.com.melt.info.form;

import ru.com.melt.info.entity.Practic;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class PracticForm {
    @Valid
    private List<Practic> items = new ArrayList<>();

    public PracticForm() {
        super();
    }

    public PracticForm(List<Practic> items) {
        super();
        this.items = items;
    }

    public List<Practic> getItems() {
        return items;
    }

    public void setItems(List<Practic> items) {
        this.items = items;
    }
}