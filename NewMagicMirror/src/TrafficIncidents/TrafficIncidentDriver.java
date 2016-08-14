package TrafficIncidents;

import com.google.common.base.CharMatcher;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Burhan N on 8/14/2016.
 */
public class TrafficIncidentDriver {
    private final String hostUrl = "http://dev.virtualearth.net/REST/v1/Traffic/Incidents/47.202301,%20-122.567453,48.018827,%20-122.001673?key=AmVeODi5FTR7_AaGvrIDqw8nkNObF1EFW_VtUe_uWuv5CyRsfvSZiRwn7OSahDIV";

    private TrafficIncidentJsonObject trafficIncidentJsonObject;

    public TrafficIncidentDriver(){
        try{
            URL url = new URL(hostUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            trafficIncidentJsonObject = (new Gson().fromJson(rootObj, TrafficIncidentJsonObject.class));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public TrafficIncidentJsonObject getTrafficIncidentJsonObject() {
        return trafficIncidentJsonObject;
    }
}
