package com.Elearning.Elearning.DTO;

public class LessonDto {
    private Long id;
    private String title;
    private String type;      // "TEXT" | "VIDEO" | "QUIZ"
    private String content;
    private String videoUrl;
    private QuizDto quiz;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public QuizDto getQuiz() { return quiz; }
    public void setQuiz(QuizDto quiz) { this.quiz = quiz; }
}
