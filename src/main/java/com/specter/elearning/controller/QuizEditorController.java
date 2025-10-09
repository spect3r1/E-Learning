package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class QuizEditorController {
    @FXML private TextField quizTitle;
    @FXML private TableView<?> questionsTable;
    @FXML private Button btnAddQuestion;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    @FXML
    public void initialize() {
        btnAddQuestion.setOnAction(e -> {
            // TODO: open question editor dialog
        });
        btnSave.setOnAction(e -> ViewManager.showCourseDetail());
        btnCancel.setOnAction(e -> ViewManager.showCourseDetail());
    }
}
