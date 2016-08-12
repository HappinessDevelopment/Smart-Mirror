package Controllers;

import Events.Event;
import Events.EventsDriver;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
        EventsDriver parsed = new EventsDriver();
        Event[] list = parsed.getGson();

        for(int i = 0; i < list.length; i++) {
            // Puts event information in vertical order
            events = new VBox();
            events.setPadding(new Insets(10,10,10,10));
            events.setSpacing(5);
            //Adding title and other event information
            Label title = new Label(list[i].getTitle());
            Label location = new Label(list[i].getLocation());
            Label time  = new Label(list[i].getFormattedStart());
            Label description = new Label(list[i].getDescription());
            // Setting font sizes
            title.setFont(Font.font(16.0));
            location.setFont(Font.font(13.0));
            time.setFont(Font.font(13.0));
            description.setFont(Font.font(13.0));
            // Adding to the vertical box to keep in order
            events.getChildren().add(title);
            events.getChildren().add(location);
            events.getChildren().add(time);
            events.getChildren().add(description);
            // Adding event in bigger paneEventsHolder
            eventsList.getChildren().add(events);
        }
        // Placing the list in a scroll pane to be able to scroll through list.
        scrollPane.setContent(eventsList);
    }
}
