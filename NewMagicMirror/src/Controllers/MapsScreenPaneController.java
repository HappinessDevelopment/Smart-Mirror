package Controllers;

import TrafficIncidents.TrafficIncidentDriver;
import com.google.common.base.CharMatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by Burhan N on 8/11/2016.
 */
public class MapsScreenPaneController {
    private WebEngine webEngine;
    private TrafficIncidentDriver trafficIncidentDriver;
    private String[] reasons = new String[] {"Accident", "Congestion", "DisabledVehicle", "MassTransit", "Miscellaneous", "OtherNews", "PlannedEvent", "RoadHazard", "Construction", "Alert", "Weather"};

    @FXML
    private WebView webView;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;

    @FXML
    private void initialize() {
        webEngine = webView.getEngine();
        trafficIncidentDriver = new TrafficIncidentDriver();

        webView.getStyleClass().add("browser");
        String url = MapsScreenPaneController.class.getResource("..\\TrafficIncidents\\maps.html").toExternalForm();
        // load the web page
        webEngine.load(url);

        for(int i = 0; i < trafficIncidentDriver.getTrafficIncidentJsonObject().getResourceSets().get(0).getEstimatedTotal(); i++){
            HBox hBox = new HBox();
            Calendar endDate = Calendar.getInstance();
            Calendar startDate = Calendar.getInstance();

            String removeFromTime ="/Date()/";
            long endTime = Long.parseLong(CharMatcher.anyOf(removeFromTime).removeFrom(trafficIncidentDriver.getTrafficIncidentJsonObject().getResourceSets().get(0).getResources().get(i).getEnd()));
            long startTime = Long.parseLong(CharMatcher.anyOf(removeFromTime).removeFrom(trafficIncidentDriver.getTrafficIncidentJsonObject().getResourceSets().get(0).getResources().get(i).getStart()));
            endDate.setTimeInMillis(endTime);
            startDate.setTimeInMillis(startTime);

            Label description = new Label(trafficIncidentDriver.getTrafficIncidentJsonObject().getResourceSets().get(0).getResources().get(i).getDescription());
            Label duration = new Label(String.format("%d Days", TimeUnit.MILLISECONDS.toDays(endTime - startTime)));
            Label reason = new Label(reasons[trafficIncidentDriver.getTrafficIncidentJsonObject().getResourceSets().get(0).getResources().get(i).getType() - 1]);

            hBox.getStylesheets().add(MapsScreenPaneController.class.getResource("..\\DarkTheme.css").toExternalForm());
            description.setFont(Font.font(16));
            duration.setFont(Font.font(16));
            reason.setFont(Font.font(16));
            hBox.setSpacing(10);
            hBox.prefWidth(360);
            description.setMinWidth(200);
            description.setPrefWidth(200);
            duration.setMinWidth(50);
            duration.setPrefWidth(50);
            reason.setMinWidth(80);
            reason.setPrefWidth(80);


            hBox.getChildren().addAll(description, duration, reason);
            vBox.getChildren().addAll(hBox);
        }
        scrollPane.setContent(vBox);
    }
}
