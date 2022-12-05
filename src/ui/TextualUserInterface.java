package ui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.BookingTime;
import model.CateringMenu;
import model.CateringMenu.EnumMenu;
import model.EventType.EnumType;

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

			
	public static void main(String[] args) throws DataAccessException {
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		BookingCtrl bookingCtrl = new BookingCtrl();
		
		while(running) {
			//System.out.print();
			String input = getScan(scanner);
			
			switch(input) {
			
			case "FullTestCase": case"tst":
					
				int count=0; int max=7; //Amount of tests accepted/all
				
				//Test parameters
				CateringMenu catering = new CateringMenu(EnumMenu.CHICKEN, 210);
				//LocalDateTime from = LocalDateTime.now();
				
				int year = 2022, month=11, day=28;
				int time = 18, minute = 55;
				LocalDateTime from= LocalDateTime.of(year,month,day,8, 30);
				LocalDateTime to = LocalDateTime.of(year, month, day, time, minute);
				String type = "Formel 1";
				int menu = 1;
				String tlf="40404040";
				
				if(bookingCtrl.checkTimeslot(EnumType.FORMULA_1, from,to)) {
				System.out.println("booking created");
				bookingCtrl.createBooking(); count++;
				
				System.out.println("\nfoundtimeslots");
				List<BookingTime> l = bookingCtrl.findBookedTimeslots(year, month, day); count++;
				System.out.println(l);
				
				System.out.println("\nadded time");
				bookingCtrl.addTimeslot(type, from, to); count++;
				
				System.out.println("\nadded customer"); count++;
				bookingCtrl.addCustomer(tlf);
				
				System.out.println("\nadded amount of people");
				System.out.println(bookingCtrl.addAmountOfPeople(5, from, to)); count++;
				
				System.out.println("\nadded employee id");
				//bookingCtrl.add
				
				System.out.println("\nadded catering");
				bookingCtrl.addCateringMenu(menu); count++;
				
				System.out.println("\nfinished booking");
				bookingCtrl.finishBooking(); count++;
				
				System.out.println("\n full test completed " + count+"/"+max);
				} else System.out.println("Timeslot already taken: [" +from + " - "+to.toString()+"]" );
				break;
				
			//Outside tests
			//	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	PRINTS ALL BOOKINGS
			case "PrintAllBookings":
				selectAllFrom("Booking");
				break;
//				-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	PRINTS ALL CONTACTS
			case "PrintAllContacts":
				selectAllFrom("Contact");
                break;
//            	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	-	PRINTS ALL CUSTOMERS
            case "PrintAllCustomers":
            	selectAllFrom("Customer");
                break;
				
            case "PrintAllRentOuts":
            	selectAllFrom("RentOut");
                break;
                
            case "All":
            case "PRINT":
            	
            	selectAllFrom("Booking");
            	
            	selectAllFrom("BookingTime");
            	
            	selectAllFrom("RentOut");
            	
            	selectAllFrom("Customer");
            	
            	selectAllFrom("Contact");
            	
            	selectAllFrom("TimeSheet");
            	selectAllFrom("Employee");
            	selectAllFrom("RentOutGokart");
            	selectAllFrom("EventType");
            	selectAllFrom("Gokart");
            	selectAllFrom("ZipcodeCity");
            	selectAllFrom("CateringMenu");
            	break;
            case "Overlap":
            	for (int i = 1; i < 30; i++) {
            		LocalDateTime start = LocalDateTime.of(2022, 11, i, 9, 30);
                	LocalDateTime end = LocalDateTime.of(2022, 12, i, 18, 30);
                	System.out.println(bookingCtrl.checkTimeslot(EnumType.LE_MANS_1_HOUR, start, end));
				}
            	
            	break;
			default:
				//throw new IllegalArgumentException("Unexpected value: " + input);
				System.out.println("No such command as "+ input);
			}
			
			
			
		}
		scanner.close();
	}
	
	private static void selectAllFrom(String string) {
		try {
			System.out.println("\n"+string);
            PreparedStatement psTst = DBConnection.getInstance().getConnection().prepareStatement("Select * from "+string);
            ResultSet rs = psTst.executeQuery();
            printResultSet(rs);
        } catch (SQLException e) {e.printStackTrace();}
		
	}

	private static String getScan(Scanner scanner) {
		return scanner.next();
	}
	
	private static void printResultSet(ResultSet rs) throws SQLException {
        int width = rs.getMetaData().getColumnCount();
        
        int adjustmentSize=20;
        
        for (int i = 1; i < width+1; i++) {
            String s = rs.getMetaData().getColumnName(i);
            System.out.print(s +" ".repeat(adjustmentSize-s.length()));
        }
        
        System.out.print("\n");
        while (rs.next()) {
            for (int i = 1; i < width+1; i++) {
            	int spacing = adjustmentSize - (rs.getObject(i)!=null ? rs.getObject(i).toString().length() : 4);
            	if(spacing<1) spacing=1;
            	
                System.out.print(
                        rs.getObject(i)+" ".repeat(spacing));
            }
            System.out.print("\n");
        }    
    }
	
	
}
