package ui;

import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.BookingCtrl;
import database.DBConnection;
import model.CateringMenu;

/**TUI - Testing class, not for standard use
 * <p>Methods <ul>
 * <li>createBooking()			TODO
 * <li>findBookedTimeslots()	TODO
 * <li>addTime()				TODO
 * <li>addCustomer				TODO
 * <li>addAmountOfPeople()		TODO
 * <li>addCatering()			TODO
 * <li>finishBooking()			TODO
 * */
public class TextualUserInterface {

			
	public static void main(String[] args) {
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		BookingCtrl bookingCtrl = new BookingCtrl();
		
		while(running) {
			//System.out.print();
			String input = getScan(scanner);
			
			switch(input) {
			case "exit":
				running=false;
				break;
				
			case "CreateBooking":
				//System.out.println("booking created");
				bookingCtrl.createBooking();
				break;
				
			case "FindBookedTimeslots":
				//System.out.println("Time found");
				bookingCtrl.findBookedTimeslots();
				break;
				
			case "AddTime":
				//System.out.println("added time");
				bookingCtrl.addTime(null, null, input);
				break;
				
			case "AddCustomer":
				//System.out.println("added customer");
				bookingCtrl.addCustomer("45702312");
				break;
				
			case "AddAmountOfPeople":
				System.out.println("added amount of people");
				bookingCtrl.addAmountOfPeople(5);
				break;
				
			case "AddCatering":
				System.out.println("catering was added");
				CateringMenu c = new CateringMenu();
				bookingCtrl.addCatering(c);
				break;
				
			case "FinishBooking":
				System.out.println("booking finished");
				bookingCtrl.finishBooking();
				break;
				
			case "FullTestCase": case"tst":
				CateringMenu catering = new CateringMenu();
				int count=0; int max=7;
				
				System.out.println("booking created");
				bookingCtrl.createBooking(); count++;
				
				System.out.println("\nfoundtimeslots");
				List<LocalDateTime> l = bookingCtrl.findBookedTimeslots(); count++;
				System.out.println(l);
				
				System.out.println("\nadded time");
				bookingCtrl.addTime(LocalDateTime.of(2022, 11, 30, 10, 55), LocalDateTime.of(2022, 12, 1, 14, 30), input); count++;
				
				System.out.println("\nadded customer"); count++;
				bookingCtrl.addCustomer("45702312");
				
				System.out.println("\nadded amount of people");
				System.out.println(bookingCtrl.addAmountOfPeople(5)); count++;
				
				System.out.println("\nadded catering");
				bookingCtrl.addCatering(catering); count++;
				
				System.out.println("\nfinished booking");
				bookingCtrl.finishBooking(); count++;
				
				System.out.println("\n full test completed " + count+"/"+max);
				
				break;
				
			//Outside tests
			case "PrintAllBookings":
					try {
						PreparedStatement psTst = DBConnection.getInstance().getConnection().prepareStatement("Select * from Booking");
						ResultSet rs = psTst.executeQuery();
						
						int width = rs.getMetaData().getColumnCount();
						
						int adjustmentSize=20;
						
						for (int i = 1; i < width+1; i++) {
							String s = rs.getMetaData().getColumnName(i);
						System.out.print(s +" ".repeat(adjustmentSize-s.length()));
						}
						System.out.print("\n");
						while (rs.next()) {
							for (int i = 1; i < width+1; i++) { //rs.getObject(i).toString().length()
								System.out.print(
										rs.getObject(i)+" ".repeat(
												adjustmentSize - (rs.getObject(i)!=null ? 
														rs.getObject(i).toString().length() : 4)));
							}
							System.out.print("\n");
						}
					} catch (SQLException e) {e.printStackTrace();}
				break;
				
				
				
			default:
				//throw new IllegalArgumentException("Unexpected value: " + input);
				System.out.println("No such command as "+ input);
			}
			
			
			
		}
		scanner.close();
	}
	
	private static String getScan(Scanner scanner) {
		return scanner.next();
	}
	

}
