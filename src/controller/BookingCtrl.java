package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.CateringMenu;
import model.Customer;
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
	
	//Constructor/init -----------------------------------------------------------------------------------------------
	public BookingCtrl() throws DataAccessException {
		listTs = new ArrayList<>();
		bookingTimeDatabase = new BookingTimeDB();
		customerCtrl = new CustomerCtrl();
		gokartCtrl = new GokartCtrl();
		cateringCtrl = new CateringCtrl();
		bookingDatabase = new BookingDB();
	}
	
	
	//Methods -----------------------------------------------------------------------------------------------
	
	public void createBooking() {
		newBooking = new Booking();
		
	}
	
	public List<BookingTime> findBookedTimeslots(int year, int month, int day) throws DataAccessException {
		//TODO convert localdatetime/ints to date in db layer
		return null; //bookingTimeDatabase.getBookedTimeslots(LocalDateTime.of(year, month, day, 10, 20));
	}
	
	public void addTimeslot(String eventType, LocalDateTime startTime,LocalDateTime finishTime) {
		/*pseudo
		 * get bookingdb
		 * get call a method like checkTimeslot with event and localdatetime
		 * if timeslot is not occupied - add timeslot to newbooking, and add event to newbooking
		 * ---
		 * BookingTime bt = null;
		 * eventtype et = null;
		 * if(startTime != findBookedTimeslots() && finish != findbookedslots){
		 * 	bt = new bookingtime(start, finish);
		 *  TODO somehow convert string eventtype to enum -> new methodcall maybe
		 *  newBooking.addTimeslot(bt, et); ? or .addevent(et)
		 *  
		 * }
		 */
		
		
		//TODO add param eventype to new bookingtime
		BookingTime bt = new BookingTime(eventType, startTime,finishTime);
		newBooking.addTimeslot(bt);
//		newBooking.addEvent(eventType); TODO
	}
	
	public void addCustomer(String phoneNo) throws DataAccessException {
		newBooking.addCustomer(customerCtrl.findCustomer(phoneNo));
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
	
	public void finishBooking() {
		bookingDatabase.insertBooking(newBooking);
	}
	
}
