package Controllers;

import Events.Event;
import Events.ParsingJson;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Created by Burhan N on 8/10/2016.
 */
public class EventsScreenPaneController {
    private VBox events;

    @FXML
    private VBox eventsList;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        // Parsing the list of events
        ParsingJson parsed = new ParsingJson();
        Event[] list = parsed.getGson();

        for(int i = 0; i < list.length; i++) {
            // Puts event information in vertical order
            events = new VBox();
            events.setPadding(new Insets(10,10,10,10));
            events.setSpacing(5);
            //Adding title and other event information
            events.getChildren().add(new Label(list[i].getTitle()));
            events.getChildren().add(new Label(list[i].getLocation()));
            events.getChildren().add(new Label(list[i].getFormattedStart()));
            events.getChildren().add(new Label(list[i].getDescription()));
            // Adding event in bigger paneEventsHolder
            eventsList.getChildren().add(events);
        }
        scrollPane.setContent(eventsList);
    }
}
