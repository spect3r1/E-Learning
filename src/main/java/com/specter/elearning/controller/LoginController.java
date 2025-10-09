package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
//import com.specter.elearning.service.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        loginButton.setOnAction(e -> onLogin());
        registerButton.setOnAction(e -> onRegister());
    }

    private void onLogin() {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText();
        // TODO: replace this sample logic with AuthService.login(...)
        if (user.equals("teacher")) {
            ViewManager.showTeacherDashboard();
        } else if (user.equals("student")) {
            ViewManager.showStudentDashboard();
        } else if (user.equals("admin")) {
            ViewManager.showCourseList();
        } else {
            messageLabel.setText("Unknown user. use 'student' or 'teacher' (demo)");
        }
//        AuthService.login(user, pass);
    }

    private void onRegister() {
        // TODO: show registration dialog or view
        messageLabel.setText("Registration not implemented in demo.");
    }
}
