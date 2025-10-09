package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfileController {

    @FXML private Label usernameLabel;
    @FXML private Label roleLabel;
    @FXML private Button btnBack;

    @FXML
    public void initialize() {
        try {
            // Demo values while AuthService isn't implemented yet
            usernameLabel.setText("demo_user");
            roleLabel.setText("STUDENT");

            // safe wiring
            btnBack.setOnAction(e -> onBack());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onBack() {
        // go back to an appropriate view; student demo:
        ViewManager.showStudentDashboard();
    }
}
