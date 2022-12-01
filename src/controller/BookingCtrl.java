package controller;

import java.sql.SQLException;
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
	
	public List<LocalDateTime> findBookedTimeslots(){
		return bookingTimeDatabase.getBookedTimeslots();
	}
	
	public void addTimeslot(String eventType, LocalDateTime startTime,LocalDateTime finishTime) {
		//TODO add param eventype to new bookingtime
		BookingTime bt = new BookingTime(startTime,finishTime);
		newBooking.addTimeslot(bt);
//		newBooking.addEvent(eventType); TODO
	}
	
	public void addCustomer(String phoneNo) throws DataAccessException {
		newBooking.addCustomer(customerCtrl.findCustomer(phoneNo));
	}
	
	public boolean addAmountOfPeople(int amount, LocalDateTime startTime,LocalDateTime finishTime) {
//		if(gokartCtrl.checkGokarts(amount, startTime, finishTime)==true) {
//			newBooking.setAmountOfPeople(amount);
//		}
//		return gokartCtrl.checkGokarts(amount, startTime, finishTime);
		return true; //temp
	}
	
	public void addCateringMenu(int cateringMenu) throws DataAccessException {
		newBooking.addCateringMenu(cateringCtrl.findCateringMenu(cateringMenu));
	}
	
	public void finishBooking() {
		bookingDatabase.insertBooking(newBooking);
	}
	
}
