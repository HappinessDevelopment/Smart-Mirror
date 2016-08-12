package Events;

import com.google.gson.Gson;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Class is used for connecting to a server that passes through a json format of
 * the events from the UW Bothell calendar to be parsed.
 * Created by Burhan N on 6/27/2016.
 */
public class EventsDriver {
    private final String hostURL = "https://api.fenixfox.net/events.json";
    private HttpURLConnection urlConnection;
    private Event[] gson;

    public EventsDriver() {
        try {
            gson = (new Gson().fromJson(readJSONFeed(hostURL), Event[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readJSONFeed(String urlString) throws Exception{
        /* Start of Fix */
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(X509Certificate[] certs, String authType) { }

        } };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        /* End of the fix*/
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result.toString();
    }

    public Event[] getGson() {
        ArrayList<Event> eventList = new ArrayList<Event>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        for(int i = 0; i < gson.length; i++){
            Calendar eventCalendar = Calendar.getInstance();
            eventCalendar.setTimeInMillis(gson[i].getStart());
            if((eventCalendar.get(Calendar.MONTH ) == (calendar.get(Calendar.MONTH)))&&(eventCalendar.get(Calendar.DAY_OF_MONTH) <= (calendar.get(Calendar.DAY_OF_MONTH) + 1)) && (eventCalendar.get(Calendar.DAY_OF_MONTH) >= (calendar.get(Calendar.DAY_OF_MONTH)))){
                eventList.add(gson[i]);
            }
        }
        Event[] returnedList = new Event[eventList.size()];
        for(int i = 0; i < eventList.size(); i++){
            returnedList[i] = eventList.get(i);
        }
        return returnedList;
    }
}
