import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestDate {
  public static void main(String[] args) {

	   DateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM dd yyyy h:mm a");
	   //get current date time with Date()
	   Date date = new Date();
	   System.out.println(dateFormat.format(date));
	  
	   //get current date time with Calendar()
	   Calendar cal = Calendar.getInstance();
	   System.out.println(dateFormat.format(cal.getTime()));
  }
}