package com.Elearning.Elearning.DTO;

import java.util.List;

public class ModuleDto {
    private Long id;
    private String title;
    private int order;
    private List<LessonDto> lessons;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }

    public List<LessonDto> getLessons() { return lessons; }
    public void setLessons(List<LessonDto> lessons) { this.lessons = lessons; }
}
