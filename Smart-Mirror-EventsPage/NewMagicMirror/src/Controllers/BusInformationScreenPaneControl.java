package Controllers;

import BusInfo.BusInfoDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.concurrent.TimeUnit;

public class BusInformationScreenPaneControl {
    private BusInfoDriver busInfo;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;

    @FXML
    private void initialize(){
        busInfo = new BusInfoDriver();
        BusInfoDriver.Node root;

        int numOfBuses = busInfo.getData().getData().getEntry().getStopRouteSchedules().size();

        int[][] routeSizes = new int[3][numOfBuses];
        for (int i = 0, k=0; i < numOfBuses; i++, k++){
            if(k == 0){
                routeSizes[0][i] = k;
            }else{
                routeSizes[0][i] = k + 1;
            }

            for (int j = 0; j < 2; j++){
                routeSizes[j+1][i] = (busInfo.getData().getData().getEntry().getStopRouteSchedules().get(i).getStopRouteDirectionSchedules().get(j).getScheduleStopTimes().size());
            }
        }

        int numOfRoutes;
        int stoproutedir;

        for (int buses = 0; buses < numOfBuses; buses++){ //iterates through the bus #
            for (int index = 1; index <=2; index++){ //goes between 1 or 2, which specifies the column of the array of sizes

                //need these for the get(int) calls inside the gson
                numOfRoutes = routeSizes[index][buses]; //set the numofroutes to the value from the array
                stoproutedir = index-1; //stoproutedir goes between 0 or 1, and has to change the same time as numofroutes, so just -1
                //this iterates through the number of routes in the stop

                for (int routes = 0; routes < numOfRoutes; routes++) {
                    int routeNum = 0;
                    switch (busInfo.getData().getData().getEntry().getStopRouteSchedules().get(buses).getStopRouteDirectionSchedules().get(stoproutedir).getTripHeadsign()){
                        case "Bothell Totem Lake": routeNum = 238;
                            break;
                        case "Kirkland Totem Lake": routeNum = 238;
                            break;
                        case "Bothell": routeNum = 312;
                            break;
                        case "Downtown Seattle":
                            if(stoproutedir == 1){
                                routeNum = 312;
                            }else{
                                routeNum = 522;
                            }
                            break;
                        case "UW Bothell/Cascadia College Kenmore": routeNum = 372;
                            break;
                        case "University District Lake City": routeNum = 372;
                            break;
                        case "Woodinville": routeNum = 522;
                            break;
                        case "Redmond Woodinville": routeNum = 931;
                            break;
                        case "UW Bothell/Cascadia College Woodinville": routeNum = 931;
                            break;
                    }
                    if(TimeUnit.MILLISECONDS.toMinutes((busInfo.getData().getData().getEntry().getStopRouteSchedules().get(buses).getStopRouteDirectionSchedules().get(stoproutedir).getScheduleStopTimes().get(routes).getArrivalTime()) - busInfo.getData().getCurrentTime()) >= 0){
                        //insert everything into the BSTree (desc, time, bus)
                        busInfo.insert((busInfo.getData().getData().getEntry().getStopRouteSchedules().get(buses).getStopRouteDirectionSchedules().get(stoproutedir).getTripHeadsign()), (busInfo.getData().getData().getEntry().getStopRouteSchedules().get(buses).getStopRouteDirectionSchedules().get(stoproutedir).getScheduleStopTimes().get(routes).getArrivalTime()), routeNum);
                    }
                }
            }
        }
        root = busInfo.getRoot();
        addArrivals(root);
        scrollPane.setContent(vBox);
    }

    public void addArrivals(BusInfoDriver.Node root){
        if (root!=null){
            addArrivals(root.getLeft());
            HBox hBox = new HBox();

            Label description = new Label(root.getDesc());
            Label busNumber = new Label(String.valueOf(root.getBus()));
            Label arrivalTime = new Label(String.format("%d min", TimeUnit.MILLISECONDS.toMinutes(root.getTime() - busInfo.getData().getCurrentTime())));

            description.setFont(Font.font(16));
            busNumber.setFont(Font.font(16));
            arrivalTime.setFont(Font.font(16));
            hBox.setSpacing(20);
            hBox.prefWidth(360);
            description.setMinWidth(200);
            description.setPrefWidth(200);
            busNumber.setMinWidth(50);
            busNumber.setPrefWidth(50);
            arrivalTime.setMinWidth(80);
            arrivalTime.setPrefWidth(80);


            hBox.getChildren().addAll(description, busNumber, arrivalTime);
            vBox.getChildren().addAll(hBox);
            addArrivals(root.getRight());
        }
    }
}
