package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CourseListController {
    @FXML private ListView<String> allCourses;
    @FXML private Button btnOpenCourse;
    @FXML private Button btnBack;

    @FXML
    public void initialize() {
        allCourses.getItems().addAll("Intro to Java", "Databases 101", "Networks");
        btnOpenCourse.setOnAction(e -> ViewManager.showCourseDetail());
        btnBack.setOnAction(e -> ViewManager.showLogin()); // or dashboard depending on session
    }
}
