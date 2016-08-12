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
 * @author Magic Mirror Group
 * @version 2
 *
 * This class pulls in the bus information from the given link below. The information can now be used in the app to identify when a certain bus will appear.
 */
public class BusInfoDriver {
    // Link that returns a json format of the bus information.
    private final String HOST_URL = "http://api.pugetsound.onebusaway.org/api/where/schedule-for-stop/1_76305.json?key=65f351d5-6625-4a62-af86-88c05ae26b54";
    // Class used for parsing the json link when connected.
    private BusInfo busData;

    /**
     * This public constructor connects the link and saves the json format of the bus information as a Gson in a class called BusInfo.
     */
    public BusInfoDriver(){
        try{
            // Converting string representation of the link into a url.
            URL url = new URL(HOST_URL);
            // Requesting connection to link.
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            // Connecting to the link.
            request.connect();
            // Using a json parser to parse the information received from link.
            JsonParser jp = new JsonParser();
            // Taking in each parsed section as an element.
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            // Making the root element parsed an object.
            JsonObject rootObj = root.getAsJsonObject();
            // Filling our busData with the json object from above so we can use the different information provided from the link.
            busData = (new Gson().fromJson(rootObj, BusInfo.class));
        }catch(Exception e){
            // Any error found will be printed along witb order of statements executed to the error.
            e.printStackTrace();
        }
    }

    /**
     * Used for testing this driver to see if its actually parsing the link and returning the right information.
     * @param args
     */
    public static void main (String args[]){
//       BusInfoDriver test = new BusInfoDriver();
//       System.out.println(test.getData().getData().getReferences().getRoutes().get(0).getDescription());
    }

    /**
     * This method is used for returning the bus data so we can use the different information provided from the link.
     * @return Returns the BusInfo class because it holds all the information needed to display the bus information.
     */
    public BusInfo getData(){
        return busData;
    }
}