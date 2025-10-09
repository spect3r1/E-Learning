package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class DashboardStudentController {
    @FXML private ListView<String> coursesList;
    @FXML private Button btnBrowseCourses;
    @FXML private Button btnProfile;
    @FXML private Button btnLogout;

    @FXML
    public void initialize() {
        // sample data — replace with CourseService.getEnrolledCourses(...)
        coursesList.getItems().addAll("Intro to Java", "Network Security 101");
        btnBrowseCourses.setOnAction(e -> ViewManager.showCourseList());
        btnProfile.setOnAction(e -> ViewManager.showProfile());
        btnLogout.setOnAction(e -> ViewManager.showLogin());
    }
}
