package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CourseEditorController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    @FXML
    public void initialize() {
        btnSave.setOnAction(e -> onSave());
        btnCancel.setOnAction(e -> ViewManager.showTeacherDashboard());
    }

    private void onSave() {
        String title = titleField.getText().trim();
        // TODO: validate + call CourseService.create(...)
        ViewManager.showTeacherDashboard();
    }
}
