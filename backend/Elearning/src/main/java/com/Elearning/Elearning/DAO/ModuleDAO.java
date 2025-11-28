package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.CourseModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ModuleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CourseModule> findByCourse(Long courseId) {
        String sql = "SELECT * FROM modules WHERE course_id = ? ORDER BY module_order ASC";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(CourseModule.class),
                courseId
        );
    }

    public CourseModule findById(Long id) {
        try {
            String sql = "SELECT * FROM modules WHERE id = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(CourseModule.class),
                    id
            );
        } catch (Exception e) {
        }
        return null;
    }

    public int createModule(Long courseId, String title, int order) {
        String sql = "INSERT INTO modules (course_id, title, module_order) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, courseId, title, order);
    }
}
