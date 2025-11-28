package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getAllCategory() {
        String sql = "SELECT * FROM categories ORDER BY name ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }

    public Category findCategoryByName(String name) {
        try {
            String sql = "SELECT * FROM categories WHERE name = ?";
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Category.class),
                    name
            );
        } catch (Exception e) {
            return null;
        }
    }

    public int createCategory(Category category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        return jdbcTemplate.update(sql, category.getName());
    }
}