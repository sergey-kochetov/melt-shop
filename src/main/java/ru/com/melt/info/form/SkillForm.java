package ru.com.melt.info.form;

import ru.com.melt.info.entity.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SkillForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Skill> items = new ArrayList<>();

    public SkillForm() {
    }

    public SkillForm(List<Skill> items) {
        this.items = items;
    }

    public List<Skill> getItems() {
        return items;
    }

    public void setItems(List<Skill> items) {
        this.items = items;
    }
}
