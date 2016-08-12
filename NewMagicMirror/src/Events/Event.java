package Events;

import java.util.Calendar;

/**
 * Created by Burhan N on 6/27/2016.
 */
public class Event {
    private String category; // event category
    private long end; //event end time
    private String description; //description of event
    private String title; //title of event
    private long start; //event start time
    private String formattedStart; //string formatted start time
    private String location; //location of event (room)

    public Event(String title, String location, long time, String
            description, String category) {
        this.title = title;
        this.location = location;
        this.start = (time);
        this.description = description;
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getEnd() {
        return (end * 1000);
    }

    public long getStart() {
        return (start * 1000);
    }

    public String getCategory() {
        return category;
    }

    public String getFormattedStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getStart());
        return (calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) +
                "/" + calendar.get(Calendar.YEAR));
        //return formattedStart;
    }

    public void setFormattedStart(String formattedStart) {
        this.formattedStart = formattedStart;
    }

    public String toString(){
        String retVal = getTitle();
        return retVal;
    }

    public static void main(String args[]){
        EventsDriver parsed = new EventsDriver();
        Events.Event[] list = parsed.getGson();
        System.out.println(list[1].getDescription());
    }
}
