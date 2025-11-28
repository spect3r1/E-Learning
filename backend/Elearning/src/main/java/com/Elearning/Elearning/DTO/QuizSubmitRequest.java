package com.Elearning.Elearning.DTO;

import java.util.Map;

public class QuizSubmitRequest {

    private Map<Long, String> answers;

    public Map<Long, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }
}
