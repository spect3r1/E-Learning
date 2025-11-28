package com.Elearning.Elearning.service;

import com.Elearning.Elearning.DAO.*;
import com.Elearning.Elearning.DTO.*;
import com.Elearning.Elearning.Models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    private final CourseDAO courseDAO;
    private final ModuleDAO moduleDAO;
    private final LessonDAO lessonDAO;
    private final QuizDAO quizDAO;
    private final QuestionDAO questionDAO;
    private final EnrollmentDAO enrollmentDAO;
    private final DtoMapper mapper;

    public CourseService(
            CourseDAO courseDAO,
            ModuleDAO moduleDAO,
            LessonDAO lessonDAO,
            QuizDAO quizDAO,
            QuestionDAO questionDAO,
            EnrollmentDAO enrollmentDAO,
            DtoMapper mapper
    ) {
        this.courseDAO = courseDAO;
        this.moduleDAO = moduleDAO;
        this.lessonDAO = lessonDAO;
        this.quizDAO = quizDAO;
        this.questionDAO = questionDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.mapper = mapper;
    }

    // GET /api/v1/courses?q=
    public List<CourseDto> listCourses(String search) {
        List<Course> courses = courseDAO.findAll(search);
        List<CourseDto> result = new ArrayList<>();

        for (Course course : courses) {
            // Load modules for each course
            List<CourseModule> modules = moduleDAO.findByCourse(course.getId());
            List<ModuleDto> moduleDtos = new ArrayList<>();

            for (CourseModule module : modules) {
                List<Lesson> lessons = lessonDAO.findByModule(module.getId());
                List<LessonDto> lessonDtos = new ArrayList<>();

                for (Lesson lesson : lessons) {
                    // For listing, we usually don't need quiz details; pass null
                    LessonDto lessonDto = mapper.toLessonDto(lesson, null);
                    lessonDtos.add(lessonDto);
                }

                ModuleDto moduleDto = mapper.toModuleDto(module, lessonDtos);
                moduleDtos.add(moduleDto);
            }

            CourseDto dto = mapper.toCourseDto(course, moduleDtos);
            result.add(dto);
        }

        return result;
    }

    // GET /api/v1/courses/{courseId}
    public CourseDto getCourse(Long courseId) {
        Course course = courseDAO.findById(courseId);
        if (course == null) {
            return null;
        }

        List<CourseModule> modules = moduleDAO.findByCourse(courseId);
        List<ModuleDto> moduleDtos = new ArrayList<>();

        for (CourseModule module : modules) {
            List<Lesson> lessons = lessonDAO.findByModule(module.getId());
            List<LessonDto> lessonDtos = new ArrayList<>();

            for (Lesson lesson : lessons) {
                Quiz quiz = quizDAO.findByLessonId(lesson.getId());
                QuizDto quizDto = null;
                if (quiz != null) {
                    List<Question> questions = questionDAO.findByQuiz(quiz.getId());
                    List<QuestionDto> questionDtos = new ArrayList<>();
                    for (Question q : questions) {
                        questionDtos.add(mapper.toQuestionDto(q));
                    }
                    quizDto = mapper.toQuizDto(quiz, questionDtos);
                }

                LessonDto lessonDto = mapper.toLessonDto(lesson, quizDto);
                lessonDtos.add(lessonDto);
            }

            ModuleDto moduleDto = mapper.toModuleDto(module, lessonDtos);
            moduleDtos.add(moduleDto);
        }

        return mapper.toCourseDto(course, moduleDtos);
    }

    // POST /api/v1/courses/{courseId}/enroll
    public EnrollmentDto enrollInCourse(Long userId, Long courseId) {
        Enrollment existing = enrollmentDAO.findEnrollment(userId, courseId);
        if (existing == null) {
            enrollmentDAO.createEnrollment(userId, courseId);
            existing = enrollmentDAO.findEnrollment(userId, courseId);
        }

        EnrollmentDto dto = new EnrollmentDto();
        dto.setCourseId(existing.getCourseId());
        dto.setUserId(existing.getUserId());
        dto.setProgress(existing.getProgress());
        return dto;
    }

    // GET /api/v1/courses/{courseId}/lessons/{lessonId}
    public LessonDto getLesson(Long courseId, Long lessonId) {
        Lesson lesson = lessonDAO.findById(lessonId);
        if (lesson == null) {
            return null;
        }

        // You might want to also verify that the lesson belongs to a module of this course
        // through a JOIN in SQL, but here we keep it simple.

        Quiz quiz = quizDAO.findByLessonId(lessonId);
        QuizDto quizDto = null;

        if (quiz != null) {
            List<Question> questions = questionDAO.findByQuiz(quiz.getId());
            List<QuestionDto> questionDtos = new ArrayList<>();
            for (Question q : questions) {
                questionDtos.add(mapper.toQuestionDto(q));
            }
            quizDto = mapper.toQuizDto(quiz, questionDtos);
        }

        return mapper.toLessonDto(lesson, quizDto);
    }

    // POST /api/v1/courses/{courseId}/lessons/{lessonId}/quiz/{quizId}/submit
    public QuizResultDto submitQuiz(Long userId, Long quizId, QuizSubmitRequest submitRequest) {
        Quiz quiz = quizDAO.findById(quizId);
        if (quiz == null) {
            return null;
        }

        List<Question> questions = questionDAO.findByQuiz(quizId);
        Map<Long, String> answers = submitRequest.getAnswers();

        int total = questions.size();
        int correct = 0;

        for (Question q : questions) {
            String expected = q.getCorrectAnswer();
            String given = answers != null ? answers.get(q.getId()) : null;

            boolean ok = expected != null && expected.equalsIgnoreCase(
                    given == null ? "" : given.trim()
            );
            if (ok) correct++;
        }

        double score = total == 0 ? 0.0 : (correct * 100.0 / total);
        QuizResultDto result = new QuizResultDto();
        result.setCorrect(correct);
        result.setTotal(total);
        result.setScore(Math.round(score));

        // Optional: store submission in DB using another DAO

        return result;
    }
}
