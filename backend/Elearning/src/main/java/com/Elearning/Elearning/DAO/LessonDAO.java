package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Lesson> findByModule(Long moduleId) {
        String sql = "SELECT * FROM lessons WHERE module_id = ? ORDER BY id ASC";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Lesson.class),
                moduleId
        );
    }

    public Lesson findById(Long id) {
        try {
            String sql = "SELECT * FROM lessons WHERE id = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Lesson.class),
                    id
            );
        } catch (Exception e) {
            return null;
        }
    }

    public int createLesson(Long moduleId, String title, String type, String content, String videoUrl) {
        String sql = """
                INSERT INTO lessons (module_id, title, type, content, video_url)
                VALUES (?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql, moduleId, title, type, content, videoUrl);
    }
}
