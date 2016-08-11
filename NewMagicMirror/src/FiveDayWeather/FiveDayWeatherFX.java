package FiveDayWeather;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;


/**
 * Created by Burhan N on 8/7/2016.
 */
public class FiveDayWeatherFX {
        VBox eventOrderBox;
        StackPane root;

    public StackPane getRoot(){
        FiveDayWeatherDriver weather = new FiveDayWeatherDriver();

        // View Style for bigger window
        root = new StackPane();
        // Holds all the events
        VBox paneHoldsEvents = new VBox();
        // Makes paneHoldsEvent scrollable
        ScrollPane scrollPane = new ScrollPane();

        for(int i = 0; i < weather.getData().getList().size(); i++) {
            // Puts event information in vertical order
            eventOrderBox = new VBox();
            eventOrderBox.setPadding(new Insets(10,10,10,10));
            eventOrderBox.setSpacing(5);
            //Adding title and other event information
            eventOrderBox.getChildren().add(new Label("Date: " + weather.getData().getList().get(i).getDate()));
            eventOrderBox.getChildren().add(new Label("Time: " + weather.getData().getList().get(i).getTime()));
            eventOrderBox.getChildren().add(new Label("Temperature: " + Long.toString(weather.getData().getList().get(i).getMain().getTemp())));
            eventOrderBox.getChildren().add(new Label("Low: " + Long.toString(weather.getData().getList().get(i).getMain().getTemp_min())));
            eventOrderBox.getChildren().add(new Label("High: " + Long.toString(weather.getData().getList().get(i).getMain().getTemp_max())));
            eventOrderBox.getChildren().add(new Label("Humidity: " + Long.toString(weather.getData().getList().get(i).getMain().getHumidity())));
            // Adding event in bigger paneEventsHolder
            paneHoldsEvents.getChildren().add(eventOrderBox);
        }
//        eventOrderBox.setStyle("-fx-padding: 10;" +
//                "-fx-border-style: solid inside;" +
//                "-fx-border-width: 2;" +
//                "-fx-border-insets: 5;" +
//                "-fx-border-radius: 5;" +
//                "-fx-border-color: blue;");

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setContent(paneHoldsEvents);
        root.getChildren().add(scrollPane);
        return root;
    }
}
