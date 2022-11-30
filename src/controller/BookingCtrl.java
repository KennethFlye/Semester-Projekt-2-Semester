package controller;

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

public class BookingCtrl {
	
	//Fields -----------------------------------------------------------------------------------------------
	
	private CateringCtrl cateringCtrl;
	private CustomerCtrl customerCtrl = new CustomerCtrl();
	private EventTypeCtrl eventTypeCtrl;
	private GokartCtrl gokartCtrl = new GokartCtrl();
	
	private BookingDBIF bookingDatabase = new BookingDB();
	private List<LocalDateTime> listTs = new ArrayList<>();
	private BookingTimeDBIF bookingTimeDatabase = new BookingTimeDB();
	
	private Booking newBooking;
	
	//Constructor/init -----------------------------------------------------------------------------------------------
	public BookingCtrl() {
		
	}
	
	
	//Methods -----------------------------------------------------------------------------------------------
	
	public void createBooking() {
		newBooking = new Booking();
		
	}
	
	public List<LocalDateTime> findBookedTimeslots(){
		return bookingTimeDatabase.getBookedTimeslots();
	}
	
	public void addTime(LocalDateTime startTime,LocalDateTime finishTime,String eventType) {
		BookingTime bt = new BookingTime(startTime,finishTime);
		
		newBooking.addTime(bt);
	}
	
	public void addCustomer(String phoneNo) {
		Customer c = customerCtrl.findCustomer(phoneNo);
		newBooking.addCustomer(c);
	}
	
	public boolean addAmountOfPeople(int amount) {
		//Todo fix this its just a half stub rn
		gokartCtrl.checkGokarts(amount);
		return true;
	}
	
	public void addCatering(CateringMenu cateringMenu) {
		
	}
	
	public void finishBooking() {
		bookingDatabase.insertBooking(newBooking);
	}
	
}
