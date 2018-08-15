import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TimeCalc {
	
	// Testing Variables
	public static int serviceTime = 40;
	///////
	
	private static List<Consultant> consultants = new ArrayList<Consultant>();
	private static Date startTime = null;
	private static Date doneTime = null;
	public static int hairs = 0;
	public static int makeups = 0;
	
	public String calculate(int serviceTimeIn, int numConsultants, int makeupsIn, int hairsIn, Date startTimeIn, Date doneTimeIn) {

		serviceTime = serviceTimeIn;
		hairs = hairsIn;
		makeups = makeupsIn;
		startTime = startTimeIn;
		doneTime = doneTimeIn;
		
		// Gets number of consultants
//		System.out.println("How long per service");
//		Scanner timing = new Scanner(System.in);
//		serviceTime = timing.nextInt();
		
		// Gets number of consultants
//		System.out.println("How many consultants");
//		Scanner in = new Scanner(System.in);
//		int numConsultants = in.nextInt();
		
		// Gets start time
//		Scanner input = new Scanner(System.in);
//		System.out.print("Enter start time (HH:mm): ");
//		String tempTime = input.next();
//		DateFormat format = new SimpleDateFormat("HH:mm");
//		try {
//			startTime = format.parse(tempTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		// Gets done time
//		Scanner input2 = new Scanner(System.in);
//		System.out.print("Enter done time (HH:mm): ");
//		String tempTime2 = input2.next();
//		DateFormat format2 = new SimpleDateFormat("HH:mm");
//		try {
//			doneTime = format2.parse(tempTime2);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		// Gets hairs
//		System.out.println("How many hairs");
//		hairs = in.nextInt();
		
		// Gets makeups
//		System.out.println("How many makeups");
//		makeups = in.nextInt();
		
		int totalServices = (makeups+hairs+2)/numConsultants;
		
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
					if(tempTotalService > 0) {
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
		
		return print();
	}
	
	/**
	 * Print the schedule
	 */
	public static String print() {
		String output = "";
		output += "Schedule: \n";
		
		for(int i = 0; i < consultants.size(); i++) {
			int id = i+1;
			consultants.get(i).sort();
			output += "Consultant " + id + "\n" + consultants.get(i).toString();
		}
		
		return output;
	}
	
}
