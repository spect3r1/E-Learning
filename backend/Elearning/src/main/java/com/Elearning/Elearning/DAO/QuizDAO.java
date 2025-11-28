package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuizDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Quiz findByLessonId(Long lessonId) {
        try {
            String sql = "SELECT * FROM quizzes WHERE lesson_id = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Quiz.class),
                    lessonId
            );
        } catch (Exception e) {
            return null;
        }
    }

    public Quiz findById(Long id) {
        try {
            String sql = "SELECT * FROM quizzes WHERE id = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Quiz.class),
                    id
            );
        } catch (Exception e) {
            return null;
        }
    }
}
