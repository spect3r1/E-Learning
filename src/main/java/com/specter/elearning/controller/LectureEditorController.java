package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LectureEditorController {
    @FXML private TextField titleField;
    @FXML private TextArea contentArea;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    @FXML
    public void initialize() {
        btnSave.setOnAction(e -> {
            // TODO: save lecture via LectureDao/Service
            ViewManager.showCourseDetail();
        });
        btnCancel.setOnAction(e -> ViewManager.showCourseDetail());
    }
}
