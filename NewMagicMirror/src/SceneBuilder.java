import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * @author Magic Mirror Group
 * @version 2
 *
 * Main program that runs the window displaying the events, traffic, main screen, bus information, and weather.
 */
public class SceneBuilder extends Application {
    // Holds the different panes.
    private Pagination pagination;
    // All the panes for the different information screens.
    private AnchorPane weatherPane;
    private AnchorPane mainScreenPane;
    private AnchorPane eventScreenPane;

    // Number of pages we are displaying
    private final int NUMBER_OF_PAGES = 3;

    public static void main (String args[]){
        // Running the program.
        Application.launch(SceneBuilder.class, (java.lang.String[])null);
    }

    /**
     * This is the method the begins the program.
     * @param primaryStage This is the stage that will be presented when the information has been gathered.
     * @throws Exception Incase there is an error reading the fxml or css files.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Getting the weather pane.
            weatherPane = FXMLLoader.load(SceneBuilder.class.getResource("\\Screens\\WeatherScreen.fxml"));
            // Getting the main screen pane.
            mainScreenPane = FXMLLoader.load(SceneBuilder.class.getResource("\\Screens\\MainScreen.fxml"));
            // Getting the event screen pane.
            eventScreenPane = FXMLLoader.load(SceneBuilder.class.getResource("\\Screens\\EventsScreen.fxml"));
            // Making page holder for the different pages.
            pagination = new Pagination(NUMBER_OF_PAGES, 1);
            // Setting the style to bullet point.
            pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
            // Adding more style to the page holder.
            pagination.getStylesheets().add(SceneBuilder.class.getResource("PaginationDesign.css").toExternalForm());

            // Allowing us to use left and right keys to swtich between screens
            pagination.setPageFactory(new Callback<Integer, Node>() {
                /**
                 *
                 * @param param This is the page number we want to show.
                 * @return Returning the node that is the page we are displaying.
                 */
                @Override
                public Node call(Integer param) {
                    return getPage(param);
                }
            });
            // Setting up slideshow for screens.
            Timeline slideShow = new Timeline(new KeyFrame(Duration.seconds(NUMBER_OF_PAGES), event -> {
                int pos = (pagination.getCurrentPageIndex() +1) % pagination.getPageCount();
                pagination.setCurrentPageIndex(pos);
            }));
            // Slide show will go forever.
            slideShow.setCycleCount(Timeline.INDEFINITE);
            // Starting the slideshow.
            slideShow.play();

            // Setting up the base anchor pane that holds the pagination.
            AnchorPane anchor = new AnchorPane();
            AnchorPane.setTopAnchor(pagination, 0.0);
            AnchorPane.setRightAnchor(pagination, 0.0);
            AnchorPane.setBottomAnchor(pagination, 0.0);
            AnchorPane.setLeftAnchor(pagination, 0.0);
            anchor.getChildren().addAll(pagination);
            // Placing the base anchor pane in the new scene.
            Scene scene = new Scene(anchor, 400, 645);
            // Finally placing the scene final stage.
            primaryStage.setScene(scene);
            // Setting the title of the window.
            primaryStage.setTitle("FXML");
            // Preventing multiple windows from opening up. Using same window each time.
            primaryStage.setOnCloseRequest(e -> Platform.exit());
            // Displaying the stage.
            primaryStage.show();
        } catch (Exception ex) {
            // Logging the errors if any is caught.
            Logger.getLogger(SceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method returns the pane we want to display.
     * @param param This is the page number and that correlates to the page we want to see.
     * @return Returning the page as a node.
     */
    public Node getPage(int param){
        if(param == 0){
            return weatherPane;
        }else if(param == 1){
            return mainScreenPane;
        }
        return eventScreenPane;
    }
}
