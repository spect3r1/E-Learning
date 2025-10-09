package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

public class CourseDetailController {
    @FXML private TabPane tabs;
    @FXML private ListView<String> lecturesList;
    @FXML private ListView<String> quizzesList;
    @FXML private Button btnTakeQuiz;
    @FXML private Button btnEditCourse;
    @FXML private Button btnBack;

    @FXML
    public void initialize() {
        lecturesList.getItems().addAll("Lecture 1 - Intro", "Lecture 2 - Basics");
        quizzesList.getItems().addAll("Quiz 1", "Quiz 2");
        btnTakeQuiz.setOnAction(e -> ViewManager.showQuizTake());
        btnEditCourse.setOnAction(e -> ViewManager.showCourseEditor());
        btnBack.setOnAction(e -> ViewManager.showCourseList());
    }
}
