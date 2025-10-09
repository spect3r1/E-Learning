package com.specter.elearning.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class MainApp extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        stage.setTitle("E-Learning (Prototype)");
        ViewManager.showLogin();
        InputStream iconStream = getClass().getResourceAsStream("/com/specter/elearning/icons/app-icon.png");
        if (iconStream != null) {
            try (InputStream is = iconStream) {
                stage.getIcons().add(new Image(is));
            } catch (Exception ex) {
                // log if you have logger, otherwise ignore to keep app running
                ex.printStackTrace();
            }
        } else {
            // resource missing — optional: log or ignore
            System.out.println("App icon not found: /com/specter/elearning/icons/app-icon.png");
        }
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
