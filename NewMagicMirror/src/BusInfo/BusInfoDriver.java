package BusInfo;

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
public class BusInfoDriver {
    private final String hostUrl = "http://api.pugetsound.onebusaway.org/api/where/schedule-for-stop/1_76305.json?key=65f351d5-6625-4a62-af86-88c05ae26b54";
    private BusInfo busInfo;

    public BusInfoDriver(){
        try{
            URL url = new URL(hostUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            busInfo = (new Gson().fromJson(rootObj, BusInfo.class));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main (String args[]){
        BusInfoDriver test = new BusInfoDriver();
        System.out.println(test.getGson().getData().getReferences().getRoutes().get(0).getDescription());
    }

    public BusInfo getGson(){
        return busInfo;
    }
}
