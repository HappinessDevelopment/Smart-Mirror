package FiveDayWeather;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Burhan N on 8/7/2016.
 */
public class FiveDayWeatherDriver {
    private final String hostUrl = "http://api.openweathermap.org/data/2.5/forecast?q=Bothell&mode=json&appid=a6b9ffa2bef75a10e03fbe2ebb68d8f5";
    private FiveDayWeatherJsonObject data;

    public FiveDayWeatherDriver(){
        try{
            URL url = new URL(hostUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            data = (new Gson().fromJson(rootObj, FiveDayWeatherJsonObject.class));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        //FiveDayWeatherDriver weatherTest = new FiveDayWeatherDriver();
        //System.out.println(weatherTest.getData().getList().get(0).getWind().getSpeed());
        //System.out.println(weatherTest.getData().getList().get(0).getWeather().get(0).getMain());
    }

    public FiveDayWeatherJsonObject getData() {
        return data;
    }
}
