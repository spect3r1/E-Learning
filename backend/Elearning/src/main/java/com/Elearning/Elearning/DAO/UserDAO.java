package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByEmail(String email){

        try{
            String query = "select * from users where email = ?";
            return jdbcTemplate.queryForObject(
                    query,
                    new BeanPropertyRowMapper<>(User.class), email
            );
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }

    }
    public User findById(Long id){
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), id);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public int createUser(String name, String email, String password, String role){
        return jdbcTemplate.update(
                "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)",
                    name, email, password, role);
    }
    public List<User> getAllUsers(){
        String query = "SELECT * FROM users";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }
    public int updateRole(Long id, String role){
        String query = "UPDATE users SET role = ? WHERE id = ?";
        return jdbcTemplate.update(query, role, id);
    }

}
