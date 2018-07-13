import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
	
	// Testing Variables
	public static int serviceTime = 45;
	///////
	
	static List<Consultant> consultants = new ArrayList<Consultant>();
	static Date startTime = null;
	static Date doneTime = null;
	public static int hairs = 0;
	public static int makeups = 0;
	
	public static void main(String[] args) {
		
		// Gets number of consultants
		System.out.println("How many consultants");
		Scanner in = new Scanner(System.in);
		int numConsultants = in.nextInt();
		
		// Gets start time
		Scanner input = new Scanner(System.in);
		System.out.print("Enter start time (HH:mm): ");
		String tempTime = input.next();
		DateFormat format = new SimpleDateFormat("HH:mm");
		try {
			startTime = format.parse(tempTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// Gets done time
		Scanner input2 = new Scanner(System.in);
		System.out.print("Enter done time (HH:mm): ");
		String tempTime2 = input2.next();
		DateFormat format2 = new SimpleDateFormat("HH:mm");
		try {
			doneTime = format2.parse(tempTime2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// Gets hairs
		System.out.println("How many hairs");
		hairs = in.nextInt();
		
		// Gets makeups
		System.out.println("How many makeups");
		makeups = in.nextInt();
		
		int totalServices = ((makeups+hairs+2)/numConsultants);
		
		System.out.println(totalServices);
		
		// Adds consultants 
		for(int i = 0; i < numConsultants; i++) {
			consultants.add(new Consultant(startTime, doneTime));
		}
		
		boolean brideDone = false;
			
		for(Consultant c : consultants) {
			int tempTotalService = totalServices;
			while(tempTotalService > 0) {
				if(!brideDone) {
					c.add(new Client("bride"));
					brideDone = true;
					tempTotalService -= 2;
//					System.out.println("Added bride");
				}else {
					if(totalServices > 0) {
						c.add(new Client());
					}
					
					tempTotalService--;
//					System.out.println("Added service");
				}
			}
		}
		
		for(Consultant c : consultants) {
			c.autoName();
		}
		print();
		
	}
	
	/**
	 * Print the schedule
	 */
	public static void print() {
		System.out.println("Schedule: ");
		
		for(int i = 0; i < consultants.size(); i++) {
			int id = i+1;
			consultants.get(i).sort();
			System.out.println("Consultant " + id + "\n" + consultants.get(i).toString());
		}
	}
	
}
