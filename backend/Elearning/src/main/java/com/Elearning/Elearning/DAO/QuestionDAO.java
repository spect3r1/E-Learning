package com.Elearning.Elearning.DAO;

import com.Elearning.Elearning.Models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Question> findByQuiz(Long quizId) {
        String sql = "SELECT * FROM questions WHERE quiz_id = ?";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Question.class),
                quizId
        );
    }

    public int createQuestion(
            Long quizId,
            String type,
            String text,
            String optionsJson,
            String correctAnswer
    ) {
        String sql = """
                INSERT INTO questions (quiz_id, type, question_text, options_json, correct_answer)
                VALUES (?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql, quizId, type, text, optionsJson, correctAnswer);
    }
}
