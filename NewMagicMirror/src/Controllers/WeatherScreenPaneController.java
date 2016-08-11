package Controllers;

import Weather.WeatherDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Calendar;

/**
 * Created by Burhan N on 8/8/2016.
 */
public class WeatherScreenPaneController {
    private final String[] strDays = new String[] {"Saturday", "Sunday", "Monday", "Tuesday","Wednesday", "Thursday", "Friday"};
    private final String[] strMonths = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final String[] strAMPM = new String[] {"AM", "PM"};
    private final String[] ordinalIndicator = new String[] {"st", "nd", "rd", "th"};

    private WeatherDriver parsingWeather;
    private Calendar today;
    private Calendar sunrise;
    private Calendar sunset;
    private String indicator;

    // Instances for weather page
    @FXML
    private Label date;
    @FXML
    private Label degree;
    @FXML
    private Label sunriseTime;
    @FXML
    private Label sunsetTime;
    @FXML
    private Label highDegree;
    @FXML
    private Label lowDegree;

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
        date.setText(String.valueOf(strDays[today.get(Calendar.DAY_OF_WEEK)]) + ", " + strMonths[today.get(Calendar.MONTH)] + " " + today.get(Calendar.DAY_OF_MONTH) + indicator);
        degree.setText(Integer.toString((int) parsingWeather.getWeather().getMain().getTemp()) + "\u00b0");
        sunriseTime.setText(sunrise.get(Calendar.HOUR) + ":" + sunrise.get(Calendar.MINUTE) + " " + strAMPM[sunrise.get(Calendar.AM_PM)]);
        highDegree.setText(Integer.toString((int) parsingWeather.getWeather().getMain().getTemp_max()) + "\u00b0");
        lowDegree.setText(Integer.toString((int) parsingWeather.getWeather().getMain().getTemp_min()) + "\u00b0");
        sunsetTime.setText(sunset.get(Calendar.HOUR) + ":" + sunset.get(Calendar.MINUTE) + " " + strAMPM[sunset.get(Calendar.AM_PM)]);
    }
}
