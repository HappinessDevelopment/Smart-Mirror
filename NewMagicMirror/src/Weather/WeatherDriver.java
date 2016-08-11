package Weather;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Burhan N on 8/8/2016.
 */
public class WeatherDriver {
        // By city name
        //private final String hostUrl = "http://api.openweathermap.org/data/2.5/weather?q=Bothell&mode=json&appid=a6b9ffa2bef75a10e03fbe2ebb68d8f5";
        // By city id
        private final String hostUrl = "http://api.openweathermap.org/data/2.5/weather?id=5787829&mode=json&appid=a6b9ffa2bef75a10e03fbe2ebb68d8f5";
        private WeatherJsonObject weather;

        public WeatherDriver(){
            try{
                URL url = new URL(hostUrl);
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject rootObj = root.getAsJsonObject();
                weather = (new Gson().fromJson(rootObj, WeatherJsonObject.class));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public WeatherJsonObject getWeather() {
            return weather;
        }
}
