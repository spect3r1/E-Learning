package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class EnrollmentDAO {

    @Autowired
    private JdbcTemplate JdbcTemplate;

    public Enrollment findEnrollment(Long userId, Long courseId) {
        try {
            String sql = "SELECT * FROM enrollments WHERE user_id = ? AND course_id = ?";
            return JdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Enrollment.class), userId, courseId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int createEnrollment(Long userId, Long courseId) {
        String sql = "INSERT INTO enrollments (user_id, course_id, progress) VALUES (?, ?, 0)";
        return JdbcTemplate.update(sql, userId, courseId);
    }

    public int updateProgress(Long userId, Long courseId, double progress) {
        String sql = "UPDATE enrollments SET progress = ? WHERE user_id = ? AND course_id = ?";
        return JdbcTemplate.update(sql, progress, userId, courseId);
    }
}
