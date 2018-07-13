import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	Date startTime;
	Date endTime;
	String name;

	public Client() {
		startTime = null;
		endTime = null;
		name = "";
	}	
	
	public Client(String nameIn) {
		startTime = null;
		endTime = null;
		name = nameIn;
	}
	
	public void setStartTime(Date timeIn) {
		startTime = timeIn;
	}
	
	public void setEndTime(Date timeIn) {
		endTime = timeIn;
	}
	
	public void setName(String nameIn) {
		name = nameIn;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return new SimpleDateFormat("hh:mm aa").format(startTime.getTime()) + " - " + new SimpleDateFormat("hh:mm aa").format(endTime.getTime()) + " " + name + "\n";
	}
}
