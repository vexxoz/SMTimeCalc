import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
	
	// Testing Variables
	public static boolean allowTime = true;
	public static boolean allowFeedBack = true;
	public static int serviceTime = 40;
	///////
	
	static List<Consultant> consultants = new ArrayList<Consultant>();
	static Date startTime = null;
	static Date doneTime = null;
	static int hairCount = 1;
	static int makeupCount = 1;
	
	public static void main(String[] args) {
		
		System.out.println("How many consultants");
		Scanner in = new Scanner(System.in);
		int numConsultants = in.nextInt();
		
		if(allowTime) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter start time (HH:mm): ");
			String tempTime = input.next();
			DateFormat format = new SimpleDateFormat("HH:mm");
			try {
				startTime = format.parse(tempTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Scanner input2 = new Scanner(System.in);
			System.out.print("Enter done time (HH:mm): ");
			String tempTime2 = input2.next();
			DateFormat format2 = new SimpleDateFormat("HH:mm");
			try {
				doneTime = format2.parse(tempTime2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		System.out.println("How many hairs");
		int hairs = in.nextInt();
		
		System.out.println("How many makeups");
		int makeups = in.nextInt();
		
		if(allowTime) {
			for(int i = 0; i < numConsultants; i++) {
				consultants.add(new Consultant(startTime, doneTime, serviceTime));
			}
		}
		
		int totalServicesTime = ((hairs+makeups)*serviceTime)+120;
		
		int brideDone = 0;
		double timePerCons = totalServicesTime/numConsultants;
		
		int differenceTime = 0;
		differenceTime = (doneTime.getHours()-startTime.getHours())*60;
		differenceTime += (doneTime.getMinutes()-startTime.getMinutes());
		
		if(allowFeedBack) {
			System.out.println("Time per consultant: " + timePerCons);
			System.out.println("Total Time: " + differenceTime);
		}
		
		double timeLimit = timePerCons-(timePerCons%serviceTime);
		
		
		if(timePerCons < differenceTime) {
			int index = 0;
			while(index<numConsultants) {
				int i;
				if(index==0) {
					consultants.get(0).add(new client("Bride", -1));
					i = 120;
				}else {
					i = 0;
				}
				if(allowFeedBack) {
					System.out.println();
					System.out.println("Consultant: " + index);
					System.out.println("I: " + i);
				}
				for(; i < timeLimit;) {
					if(hairs > 0) {
						consultants.get(index).add(new client("Hair", hairCount));	
						hairCount++;
						hairs--;
					}else if(makeups > 0) {
						consultants.get(index).add(new client("Makeup", makeupCount));
						makeupCount++;
						makeups--;
					}
					i+=serviceTime;
					if(allowFeedBack) {
						System.out.println("I in loop: " + i);
					}
				}
				
				
				if(allowFeedBack) {
					System.out.println("Final I: " + i);
				}
				// increments to next consultant
				index++;
				if(allowFeedBack) {
					System.out.println();
				}
				
				if((hairs>0||makeups>0) && index == numConsultants) {
					if((timeLimit+serviceTime) <= differenceTime){
						timeLimit = timePerCons+(timePerCons%serviceTime);
					}
					index=1;
				}
				
			}
		
			
			//check
			boolean timeOverflow = true;
			for(int i =0; i < consultants.size(); i++) {
				if(!(timeOverflow && consultants.get(i).withinTime(differenceTime))) {
					timeOverflow = false;
				}
			}
			
			checkOverlap();
			
			// checks to see if time overflows and if not prints the schedule
			if(timeOverflow) {
				print();
			}else {
				// there is an overflow of up to one service, ask if the user still wants to print
				System.out.println("Scheduled Time is not enough for services! The ending times will be over the wanted done time by up to just under the time of one service!");
				System.out.println("Would you like to print anyways? (y/n)");
				Scanner response = new Scanner(System.in);
				String answer = response.nextLine();
				if(answer.equalsIgnoreCase("y")) {
					print();
				}
				
			}
		}else {
			System.out.println("Scheduled Time is not enough for services!");
		}
		
		
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
	
	public static void checkOverlap() {
		
	}
	
}
