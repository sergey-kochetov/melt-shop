package ru.com.melt.info.form;

import ru.com.melt.info.entity.Course;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class CourseForm {
    @Valid
    private List<Course> items = new ArrayList<>();

    public CourseForm() {
        super();
    }

    public CourseForm(List<Course> items) {
        super();
        this.items = items;
    }

    public List<Course> getItems() {
        return items;
    }

    public void setItems(List<Course> items) {
        this.items = items;
    }
}