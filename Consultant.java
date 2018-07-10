import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Consultant {

	List<client> people;
	Calendar calStart;
	Calendar calEnd;
	static int hairCount = 0;
	static int makeupCount = 0;
	public static int serviceTime = 0;
	int serviceCount = 0;
	
	public Consultant(Date startTimeIn, Date endTimeIn) {
		serviceTime = main.serviceTime;
		
		people = new ArrayList<client>();
		calStart = Calendar.getInstance();
		calEnd = Calendar.getInstance();

		calStart.set(2018, 6, 13);
		calStart.setTime(startTimeIn);
		
//		System.out.println(calStart.getTime());
		
		calEnd.set(2018, 6, 14);
		calEnd.setTime(endTimeIn);
	}
	
	public void add(client person) {
		people.add(person);
	}
	
	public client getClient(int index) {
		return people.get(index);
	}
	
	public client getLastClient() {
		return people.get(people.size()-1);
	}
	
	public void autoName() {
		for(client c : people) {
			if(c.getName().equals("")) {
				if(main.hairs > 0) {
					c.setName("hair");
					main.hairs--;
				}else if(main.makeups > 0) {
					c.setName("makeup");
					main.makeups--;
				}
			}
		}	
	}
	
	public void sort() {
		
		for(int i=0; i<people.size();i++) {
			if(people.get(i).getName().equalsIgnoreCase("Bride")) {
				people.add(people.size()-2, people.remove(i));
//				System.out.println("Switing bride");
				break;
			}
		}

		for(int i = 0; i<people.size();i++) {
			if(people.get(i).getName().equalsIgnoreCase("hair")) {
				hairCount++;
				people.get(i).setName("Hair #" + hairCount);
			}else if(people.get(i).getName().equalsIgnoreCase("makeup")) {
				makeupCount++;
				people.get(i).setName("Makeup #" + makeupCount);
			}else if(people.get(i).getName().equalsIgnoreCase("")){
				people.get(i).setName("Touch-Ups");
			}
			
			people.get(i).setStartTime(calStart.getTime());
			if(people.get(i).getName().equalsIgnoreCase("Bride")){
				calStart.add(Calendar.MINUTE, 120);	
			}else {
				calStart.add(Calendar.MINUTE, serviceTime);
			}
			people.get(i).setEndTime(calStart.getTime());
		}		
		
		if(calStart.getTime().before(calEnd.getTime())) {
			people.add(new client("Touch-Ups", -1));
			people.get(people.size()-1).setStartTime(calStart.getTime());
			people.get(people.size()-1).setEndTime(calEnd.getTime());
		}
		
	}
	
	public void addService(int num) {
		serviceCount += num;
	}
	
	public int getService() {
		return serviceCount;
	}	
	
	public String toString() {
		
		String temp = "";
		
		for(client c: people) {
			temp += c.toString();
		}
		
		return temp;
	}
}
