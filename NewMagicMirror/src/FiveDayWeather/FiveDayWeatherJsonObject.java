package FiveDayWeather;

import java.util.Calendar;
import java.util.List;

public class FiveDayWeatherJsonObject {
    private List<Entity> list;

    public FiveDayWeatherJsonObject(List<Entity> list){
        this.list = list;
    }

    public List<Entity> getList() {
        return list;
    }

    public class Entity{
        private long dt;
        private Main main;
        private List<Weather> weather;
        private Wind wind;

        public Entity(long dt, Main main, List<Weather> weather, Wind wind){
            this.dt = dt;
            this.main = main;
            this.weather = weather;
            this.wind = wind;
        }

        public long getDt() {
            return dt*1000;
        }

        public Main getMain() {
            return main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public Wind getWind() {
            return wind;
        }

        public String getDate() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(getDt());
            return (calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) +
                    "/" + calendar.get(Calendar.YEAR));
        }

        public String getTime(){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(getDt());
            String amOrPm = "";
            if(calendar.get(Calendar.AM_PM) == 0){
                amOrPm = "AM";
            }else{
                amOrPm = "PM";
            }
            return(calendar.get(Calendar.HOUR) + ":00 " + amOrPm);
        }

        public class Wind{
            private int speed;
            private int deg;

            public Wind(int speed, int deg){
                this.speed = speed;
                this.deg = deg;
            }

            public int getSpeed() {
                return speed;
            }

            public int getDeg() {
                return deg;
            }
        }

        public class Weather{
            private String main;
            private String decription;

            public Weather(String main, String decription){
                this.main = main;
                this.decription = decription;
            }


            public String getMain() {
                return main;
            }

            public String getDecription() {
                return decription;
            }
        }

        public class Main{
            private int temp;
            private int temp_min;
            private int temp_max;
            private int humidity;

            public Main (int temp, int temp_min, int temp_max, int humidity){
                this.temp = temp;
                this.temp_min = temp_min;
                this.temp_max = temp_max;
                this.humidity = humidity;
            }

            public int getHumidity() {
                return humidity;
            }

            public int getTemp_max() {
                return temp_max;
            }

            public int getTemp_min() {
                return temp_min;
            }

            public int getTemp() {
                return temp;
            }
        }
    }
}
