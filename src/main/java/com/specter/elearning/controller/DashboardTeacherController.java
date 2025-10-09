package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class DashboardTeacherController {
    @FXML private ListView<String> authoredCourses;
    @FXML private Button btnCreateCourse;
    @FXML private Button btnProfile;
    @FXML private Button btnLogout;

    @FXML
    public void initialize() {
        try {
            // demo data
            authoredCourses.getItems().addAll("Advanced Java", "Intro to Cryptography");

            btnCreateCourse.setOnAction(e -> onCreateCourse());
            btnProfile.setOnAction(e -> ViewManager.showProfile());
            btnLogout.setOnAction(e -> ViewManager.showLogin());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onCreateCourse() {
        // placeholder — later call ViewManager.showCourseEditor()
        System.out.println("Create course clicked");
    }
}
