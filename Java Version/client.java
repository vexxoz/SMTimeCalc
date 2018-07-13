import java.text.SimpleDateFormat;
import java.util.Date;

public class client {

	Date startTime;
	Date endTime;
	String name;
	int id;

	public client(int idIn) {
		startTime = null;
		endTime = null;
		name = "";
		id = idIn;
	}	
	
	public client(String nameIn, int idIn) {
		startTime = null;
		endTime = null;
		name = nameIn;
		id = idIn;
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
		if(id > 0) {
			return new SimpleDateFormat("hh:mm aa").format(startTime.getTime()) + " - " + new SimpleDateFormat("hh:mm aa").format(endTime.getTime()) + " " + name + " #" + id + "\n";
		}
		return new SimpleDateFormat("hh:mm aa").format(startTime.getTime()) + " - " + new SimpleDateFormat("hh:mm aa").format(endTime.getTime()) + " " + name + "\n";
//		return name + "\n";
	}
}
