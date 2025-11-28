package com.Elearning.Elearning.Controller;

import com.Elearning.Elearning.DTO.CourseDto;
import com.Elearning.Elearning.DTO.EnrollmentDto;
import com.Elearning.Elearning.DTO.LessonDto;
import com.Elearning.Elearning.DTO.QuizResultDto;
import com.Elearning.Elearning.DTO.QuizSubmitRequest;
import com.Elearning.Elearning.service.CourseService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // GET /api/v1/courses?q=...
    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses(@RequestParam(value = "q", required = false) String q) {
        return ResponseEntity.ok(courseService.listCourses(q));
    }

    // GET /api/v1/courses/{courseId}
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    // POST /api/v1/courses/{courseId}/enroll
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<EnrollmentDto> enrollCourse(
            @PathVariable Long courseId,
            HttpServletRequest request
    ) {
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.valueOf(claims.getSubject());

        return ResponseEntity.ok(courseService.enrollInCourse(userId, courseId));
    }

    // GET /api/v1/courses/{courseId}/lessons/{lessonId}
    @GetMapping("/{courseId}/lessons/{lessonId}")
    public ResponseEntity<LessonDto> getLesson(
            @PathVariable Long courseId,
            @PathVariable Long lessonId
    ) {
        return ResponseEntity.ok(courseService.getLesson(courseId, lessonId));
    }

    // POST /api/v1/courses/{courseId}/lessons/{lessonId}/quiz/{quizId}/submit
    @PostMapping("/{courseId}/lessons/{lessonId}/quiz/{quizId}/submit")
    public ResponseEntity<QuizResultDto> submitQuiz(
            @PathVariable Long quizId,
            HttpServletRequest request,
            @RequestBody QuizSubmitRequest submit
    ) {
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.valueOf(claims.getSubject());

        return ResponseEntity.ok(courseService.submitQuiz(userId, quizId, submit));
    }
}
