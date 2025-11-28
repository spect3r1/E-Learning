package com.Elearning.Elearning.DTO;

public class QuizResultDto {
    private double score;
    private int correct;
    private int total;

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public int getCorrect() { return correct; }
    public void setCorrect(int correct) { this.correct = correct; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}
