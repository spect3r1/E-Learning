package com.Elearning.Elearning.DTO;

import java.util.List;

public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private Long teacherId;
    private List<ModuleDto> modules;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }

    public List<ModuleDto> getModules() { return modules; }
    public void setModules(List<ModuleDto> modules) { this.modules = modules; }
}
