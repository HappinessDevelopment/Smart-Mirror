package Controllers;

import Weather.WeatherDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Calendar;

/**
 * Created by Burhan N on 8/8/2016.
 */
public class MainScreenPaneController {
    private final String[] strDays = new String[] {"Sunday", "Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday"};
    private final String[] strMonths = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final String[] strAMPM = new String[] {"AM", "PM"};
    private final String[] ordinalIndicator = new String[] {"st", "nd", "rd", "th"};

    private WeatherDriver parsingWeather;
    private Calendar today;
    private Calendar sunrise;
    private Calendar sunset;
    private String indicator;

    // Instances for main screen page
    @FXML
    private Label mainDate;
    @FXML
    private Label mainTime;
    @FXML
    private Label mainDegree;

    @FXML
    private void initialize(){
        parsingWeather = new WeatherDriver();
        today = Calendar.getInstance();
        sunrise = Calendar.getInstance();
        sunset = Calendar.getInstance();

        today.setTimeInMillis(parsingWeather.getWeather().getDt());
        sunrise.setTimeInMillis(parsingWeather.getWeather().getSys().getSunrise());
        sunset.setTimeInMillis(parsingWeather.getWeather().getSys().getSunset());

        switch(today.get(Calendar.DAY_OF_MONTH)){
            case 1: indicator = ordinalIndicator[0];
                break;
            case 2: indicator = ordinalIndicator[1];
                break;
            case 3: indicator = ordinalIndicator[2];
                break;
            default: indicator = ordinalIndicator[3];
                break;
        }
        mainDate.setText(String.valueOf(strDays[today.get(Calendar.DAY_OF_WEEK) - 1]) + ", " + strMonths[today.get(Calendar.MONTH)] + " " + today.get(Calendar.DAY_OF_MONTH) + indicator);
        mainTime.setText(getTime());
        mainDegree.setText(Integer.toString((int) parsingWeather.getWeather().getMain().getTemp()) + "\u00b0");
    }

    public String getTime(){
        if(today.get(Calendar.HOUR) == 0){
            return ("12:" + today.get(Calendar.MINUTE) + " " + strAMPM[today.get(Calendar.AM_PM)]);
        }
        return (today.get(Calendar.HOUR) + ":" + today.get(Calendar.MINUTE) + " " + strAMPM[today.get(Calendar.AM_PM)]);
    }
}
