package com.Elearning.Elearning.DTO;

public class EnrollmentDto {
    private Long userId;
    private Long courseId;
    private double progress;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public double getProgress() { return progress; }
    public void setProgress(double progress) { this.progress = progress; }
}
