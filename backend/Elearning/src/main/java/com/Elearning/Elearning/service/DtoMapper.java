package com.Elearning.Elearning.service;

import com.Elearning.Elearning.DTO.*;
import com.Elearning.Elearning.Models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DtoMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CourseDto toCourseDto(Course course, List<ModuleDto> modules) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setTeacherId(course.getInstructorId()); // map instructorId → teacherId in DTO
        dto.setModules(modules);
        return dto;
    }

    public ModuleDto toModuleDto(CourseModule module, List<LessonDto> lessons) {
        ModuleDto dto = new ModuleDto();
        dto.setId(module.getId());
        dto.setTitle(module.getTitle());
        dto.setOrder(module.getModuleOrder() != null ? module.getModuleOrder() : 0);
        dto.setLessons(lessons);
        return dto;
    }

    public LessonDto toLessonDto(Lesson lesson, QuizDto quizDto) {
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setTitle(lesson.getTitle());
        dto.setType(lesson.getType());
        dto.setContent(lesson.getContent());
        dto.setVideoUrl(lesson.getVideoUrl());
        dto.setQuiz(quizDto);
        return dto;
    }

    public QuizDto toQuizDto(Quiz quiz, List<QuestionDto> questions) {
        if (quiz == null) {
            return null;
        }
        QuizDto dto = new QuizDto();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setQuestions(questions);
        return dto;
    }

    public QuestionDto toQuestionDto(Question q) {
        QuestionDto dto = new QuestionDto();
        dto.setId(q.getId());
        dto.setType(q.getType());
        dto.setQuestionText(q.getQuestionText());

        if (q.getOptionsJson() == null || q.getOptionsJson().isBlank()) {
            dto.setOptions(Collections.emptyList());
        } else {
            try {
                List<String> options = objectMapper.readValue(
                        q.getOptionsJson(),
                        new TypeReference<List<String>>() {
                        }
                );
                dto.setOptions(options);
            } catch (Exception e) {
                dto.setOptions(Collections.emptyList());
            }
        }

        return dto;
    }
}
