package com.specter.elearning.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FxmlUtils {

    /**
     * resourcePath should be an absolute resource path, e.g.
     * "/com/specter/elearning/fxml/login.fxml"
     */
    public static Parent load(String resourcePath) throws IOException {
        if (resourcePath == null) throw new IllegalArgumentException("resourcePath is null");
        URL url = FxmlUtils.class.getResource(resourcePath);
        System.out.println("[FxmlUtils] resolving resource: " + resourcePath + " -> " + url);
        if (url == null) {
            // try without leading slash as fallback
            url = FxmlUtils.class.getResource(resourcePath.startsWith("/") ? resourcePath.substring(1) : "/" + resourcePath);
            System.out.println("[FxmlUtils] second attempt: " + url);
        }
        if (url == null) {
            throw new IOException("FXML resource not found: " + resourcePath);
        }

        FXMLLoader loader = new FXMLLoader(url);
        try (InputStream is = url.openStream()) {
            Parent root = loader.load(is);
            System.out.println("[FxmlUtils] controller = " + loader.getController());
            return root;
        } catch (IOException e) {
            System.err.println("[FxmlUtils] error loading FXML from " + url + " : " + e.getMessage());
            throw e;
        }
    }
}
