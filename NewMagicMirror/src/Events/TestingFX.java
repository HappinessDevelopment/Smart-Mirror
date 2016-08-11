package Events;
import FiveDayWeather.FiveDayWeatherFX;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Burhan N on 8/1/2016.
 */
public class TestingFX extends Application implements KeyListener {
    JFXButton leftBtn = new JFXButton("Left");
    JFXButton rightBtn = new JFXButton("Right");
    VBox eventOrderBox;
    TabPane tabPane;
    Tab tab;
    Tab tab2;
    private BorderPane borderPane;
    public static void main(String[] args){
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        leftBtn.getStyleClass().add("button-raised");
        rightBtn.getStyleClass().add("button-raised");
        FiveDayWeatherFX fiveDayWeatherFX = new FiveDayWeatherFX();

        // Parsing the list of events
        ParsingJson parsed = new ParsingJson();
        Events.Event[] list = parsed.getGson();

        // Naming the Window
        primaryStage.setTitle("Test");
        // View Style for bigger window
        StackPane root = new StackPane();
        // Holds all the events
        VBox paneHoldsEvents = new VBox();
        // Makes paneHoldsEvent scrollable
        ScrollPane scrollPane = new ScrollPane();

        for(int i = 0; i < list.length; i++) {
            // Puts event information in vertical order
            eventOrderBox = new VBox();
            eventOrderBox.setPadding(new Insets(10,10,10,10));
            eventOrderBox.setSpacing(5);
            //Adding title and other event information
            eventOrderBox.getChildren().add(new Label(list[i].getTitle()));
            eventOrderBox.getChildren().add(new Label(list[i].getLocation()));
            eventOrderBox.getChildren().add(new Label(list[i].getFormattedStart()));
            eventOrderBox.getChildren().add(new Label(list[i].getDescription()));
            // Adding event in bigger paneEventsHolder
            paneHoldsEvents.getChildren().add(eventOrderBox);
        }
//        eventOrderBox.setStyle("-fx-padding: 10;" +
//                "-fx-border-style: solid inside;" +
//                "-fx-border-width: 2;" +
//                "-fx-border-insets: 5;" +
//                "-fx-border-radius: 5;" +
//                "-fx-border-color: blue;");

        HBox centerSection = new HBox(leftBtn, rightBtn);
        HBox.setHgrow(centerSection, Priority.ALWAYS);
        centerSection.setAlignment(Pos.CENTER);
        centerSection.setSpacing(10);

        borderPane = new BorderPane();
        ToolBar toolbar = new ToolBar(centerSection);
        borderPane.setTop(null);
        borderPane.setRight(null);
        borderPane.setLeft(null);
        borderPane.setBottom(null);

        //Testing Tabs
        tabPane = new TabPane();
        tab = new Tab();
        tab.setText("Events");
        tab.setContent(root);
        tabPane.getTabs().add(tab);
        tab2 = new Tab();
        tab2.setText("FiveDayWeather");
        tab2.setContent(fiveDayWeatherFX.getRoot());
        tabPane.getTabs().add(tab2);
        System.out.println(tabPane.getSide());

        borderPane.setCenter(tabPane);
        tabPane.setTabMaxHeight(-10);

        scrollPane.setContent(paneHoldsEvents);
        root.getChildren().add(scrollPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene scene = new Scene(borderPane, 500, 450);
//        scene.getStylesheets().add(TestingFX.class.getResource("\\css\\buttons.css").toExternalForm());
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            tabPane.getSelectionModel().select(tab2);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            tabPane.getSelectionModel().select(tab);
        }
    }

    public BorderPane getBorderPane(){
        return borderPane;
    }
}
