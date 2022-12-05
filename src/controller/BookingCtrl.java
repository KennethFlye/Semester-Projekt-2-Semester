package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.CateringMenu;
import model.Customer;
import model.EventType;
import model.EventType.EnumType;
import model.Booking;
import model.BookingTime;
import database.BookingDB;
import database.BookingDBIF;
import database.BookingTimeDB;
import database.BookingTimeDBIF;
import database.DataAccessException;

public class BookingCtrl {
	
	//Fields -----------------------------------------------------------------------------------------------
	private Booking newBooking;
	private List<LocalDateTime> listTs;
	private BookingTimeDBIF bookingTimeDatabase;
	private CustomerCtrl customerCtrl;
	private GokartCtrl gokartCtrl;
	private CateringCtrl cateringCtrl;
	private EventTypeCtrl eventTypeCtrl;
	private BookingDBIF bookingDatabase;
	private BookingTime bt;
	
	//Constructor/init -----------------------------------------------------------------------------------------------
	public BookingCtrl() throws DataAccessException {
		listTs = new ArrayList<>();
		bookingTimeDatabase = new BookingTimeDB();
		customerCtrl = new CustomerCtrl();
		gokartCtrl = new GokartCtrl();
		cateringCtrl = new CateringCtrl();
		bookingDatabase = new BookingDB();
		eventTypeCtrl = new EventTypeCtrl();
		bt=null;
	}
	
	
	//Methods -----------------------------------------------------------------------------------------------
	
	public void createBooking() {
		newBooking = new Booking();
		
	}
	
	public List<BookingTime> findBookedTimeslots(int year, int month, int day) throws DataAccessException {
		//TODO evt convert a string (the param of the method) to ints (the param of the return statement) for easier booking
		return bookingTimeDatabase.getBookedTimeslots(year, month, day);
	}
	
	public void addTimeslot(String eventType, LocalDateTime startTime) throws DataAccessException {
		/*pseudo TODO
		 * get bookingdb
		 * get call a method like checkTimeslot with event and localdatetime
		 * if timeslot is not occupied - add timeslot to newbooking, and add event to newbooking
		 * ---
		 * BookingTime bt = null;
		 * if(startTime != findBookedTimeslots() && finish != findbookedslots){
		 * 	bt = new bookingtime(et, start, finish);
		 *  newBooking.addTimeslot(bt);
		 * }
		 */
		
		//TODO set mutex lock on chosen timeslot??
		EventType et = eventTypeCtrl.findEvent(EnumType.valueOfLabel(eventType));
<<<<<<< Updated upstream
		bt = new BookingTime(et, startTime,finishTime); //set as field value, can be used for checking if timeslot requirements are met
		newBooking.addTimeslot(bt);
=======
		bt = new BookingTime(et, startTime); //set as field value, can be used for checking if timeslot requirements are met
		newBooking.addTimeslot(bt); 
>>>>>>> Stashed changes
	}
	
	public Customer addCustomer(String phoneNo) throws DataAccessException {
		return newBooking.addCustomer(customerCtrl.findCustomer(phoneNo));
	}
	
	public boolean addAmountOfPeople(int amount, LocalDateTime startTime,LocalDateTime finishTime) throws DataAccessException {
		if(gokartCtrl.checkGokarts(amount, startTime, finishTime)==true) {
			newBooking.setAmountOfPeople(amount);
		}
		return gokartCtrl.checkGokarts(amount, startTime, finishTime); 
	}
	
	public void addCateringMenu(int cateringMenu) throws DataAccessException {
		//TODO make it possible to not set cateringmenu
		newBooking.addCateringMenu(cateringCtrl.findCateringMenu(cateringMenu));
	}
	
	public String finishBooking() throws DataAccessException {
		bookingDatabase.insertBooking(newBooking);
		return "Booking was saved. Total is:" + calculateTotalPrice() + " kr.";
	}
	
	private double calculateTotalPrice() {
		Double total = bt.getEventType().getPrice();
		total += newBooking.getCatering().getPrice();
		total *= newBooking.getAmountOfPeople();
		return total;
	}
	
}
