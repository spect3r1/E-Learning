package com.specter.elearning.app;

import com.specter.elearning.util.FxmlUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewManager {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 700;

    private static void show(String fxmlPath) {
        Stage stage = MainApp.getPrimaryStage();
        try {
            System.out.println("[ViewManager] Loading FXML -> " + fxmlPath);
            Parent root = FxmlUtils.load(fxmlPath);
            if (root == null) {
                System.out.println("[ViewManager] FxmlUtils.load returned null for: " + fxmlPath);
                showFallback(stage, "FXML returned null: " + fxmlPath);
                return;
            }
            System.out.println("[ViewManager] Loaded root: " + root.getClass().getName()
                    + "  children=" + root.getChildrenUnmodifiable().size());
            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(root, WIDTH, HEIGHT);
                // safe add stylesheet
                try {
                    String css = ViewManager.class.getResource("/com/specter/elearning/css/style.css").toExternalForm();
                    scene.getStylesheets().add(css);
                } catch (Exception ex) {
                    System.out.println("[ViewManager] stylesheet not found or failed to load: " + ex.getMessage());
                }
                stage.setScene(scene);
            } else {
                scene.setRoot(root);
            }
        } catch (Exception e) {
            System.err.println("[ViewManager] Exception while loading FXML: " + e.getMessage());
            e.printStackTrace();
            showFallback(stage, "Error loading: " + fxmlPath + "\n" + e.getMessage());
        }
    }

    private static void showFallback(Stage stage, String message) {
        VBox box = new VBox(10);
        box.setStyle("-fx-padding:20;");
        box.getChildren().addAll(new Label("Fallback UI"), new Label(message));
        if (stage.getScene() == null) {
            stage.setScene(new Scene(box, WIDTH, HEIGHT));
        } else {
            stage.getScene().setRoot(box);
        }
    }

    public static void showLogin() { show("/com/specter/elearning/fxml/login.fxml"); }
    public static void showStudentDashboard() { show("/com/specter/elearning/fxml/dashboard_student.fxml"); }
    public static void showTeacherDashboard() { show("/com/specter/elearning/fxml/dashboard_teacher.fxml"); }
    public static void showCourseList() { show("/com/specter/elearning/fxml/course_list.fxml"); }
    public static void showCourseDetail() { show("/com/specter/elearning/fxml/course_detail.fxml"); }
    public static void showCourseEditor() { show("/com/specter/elearning/fxml/course_editor.fxml"); }
    public static void showLectureEditor() { show("/com/specter/elearning/fxml/lecture_editor.fxml"); }
    public static void showQuizEditor() { show("/com/specter/elearning/fxml/quiz_editor.fxml"); }
    public static void showQuizTake() { show("/com/specter/elearning/fxml/quiz_take.fxml"); }
    public static void showProfile() { show("/com/specter/elearning/fxml/profile.fxml"); }
}
