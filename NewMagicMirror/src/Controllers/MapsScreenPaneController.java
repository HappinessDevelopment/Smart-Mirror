package Controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Created by Burhan N on 8/11/2016.
 */
public class MapsScreenPaneController {
    private WebEngine webEngine;

    @FXML
    private WebView webView;

    @FXML
    private void initialize() {
        webEngine = webView.getEngine();
        webView.getStyleClass().add("browser");
        String url = MapsScreenPaneController.class.getResource("..\\MapInformation\\maps.html").toExternalForm();
        // load the web page
        webEngine.load(url);
    }
}
