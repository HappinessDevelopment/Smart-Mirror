package Weather;

import java.util.List;

/**
 * Created by Burhan N on 8/8/2016.
 */
public class WeatherJsonObject {
    private List<Weather> weather;
    private Main main;
    private long dt;
    private Sys sys;

    public WeatherJsonObject(List<Weather> weather, Main main, long dt, Sys sys){
        this.weather = weather;
        this.main = main;
        this.dt = dt;
        this.sys = sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public long getDt() {
        return (dt * 1000);
    }

    public Sys getSys() {
        return sys;
    }

    public class Main {
        private long temp;
        private long temp_min;
        private long temp_max;

        public Main(long temp, long temp_min, long temp_max){
            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
        }

        public long getTemp() {
            return (long) (1.8 *(temp - 273) + 32);
        }

        public long getTemp_min() {
            return (long)(1.8 *(temp_min - 273) + 32);
        }

        public long getTemp_max() {
            return (long) (1.8 * (temp_max - 273) + 32);
        }
    }

    public class Sys {
        private long sunrise;
        private long sunset;

        public Sys(long sunrise, long sunset){
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public long getSunset() {
            return (sunset * 1000);
        }

        public long getSunrise() {
            return (sunrise * 1000);
        }
    }

    public class Weather {
        private String main;
        private String description;

        public Weather(String main, String description){
            this.main = main;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String getMain() {
            return main;
        }
    }
}
