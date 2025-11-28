package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Course> findAll(String search) {
        String baseSql = "SELECT * FROM courses";
        if (search != null && !search.isBlank()) {
            String sql = baseSql + " WHERE LOWER(title) LIKE ? OR LOWER(description) LIKE ?";
            String pattern = "%" + search.toLowerCase() + "%";
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Course.class),
                    pattern,
                    pattern
            );
        } else {
            return jdbcTemplate.query(
                    baseSql,
                    new BeanPropertyRowMapper<>(Course.class)
            );
        }
    }

    public Course findById(Long id) {
        try {
            String sql = "SELECT * FROM courses WHERE id = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Course.class),
                    id
            );
        } catch (Exception e) {
            return null;
        }
    }

    public int createCourse(String title, String description, Long categoryId, Long instructorId) {
        String sql = """
                INSERT INTO courses (title, description, category_id, instructor_id)
                VALUES (?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql, title, description, categoryId, instructorId);
    }
}
