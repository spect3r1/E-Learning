package com.specter.elearning.controller;

import com.specter.elearning.app.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class QuizTakeController {
    @FXML private Label questionLabel;
    @FXML private RadioButton optA, optB, optC, optD;
    @FXML private Button btnNext, btnFinish;

    @FXML
    public void initialize() {
        ToggleGroup g = new ToggleGroup();
        optA.setToggleGroup(g);
        optB.setToggleGroup(g);
        optC.setToggleGroup(g);
        optD.setToggleGroup(g);

        // sample question - replace with QuizService
        questionLabel.setText("What is Java?");
        optA.setText("Programming Language");
        optB.setText("Coffee");
        optC.setText("OS");
        optD.setText("Library");

        btnNext.setOnAction(e -> {
            // TODO: next question
        });
        btnFinish.setOnAction(e -> {
            // TODO: grade + save result
            ViewManager.showCourseDetail();
        });
    }
}
