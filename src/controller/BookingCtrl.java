package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
	private BookingTimeCtrl bookingTimeCtrl;
	private BookingDBIF bookingDatabase;
	private BookingTime bt;
	private List<Booking> bookings;
	
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
	
	/**Returns true if there are no bookings in the given timespan*/
	public boolean checkTimeslot(EnumType type, LocalDateTime startTime,LocalDateTime finishTime) {
		return bookingTimeDatabase.checkTimeslot(type, startTime, finishTime);
	}
	
	public int calculateGroups(EventType et, LocalDateTime startTime, LocalDateTime finishTime , double amount) throws DataAccessException {
		if( et.getEnumType().location == 1) {
			double available = gokartCtrl.getAvailableGokarts(startTime, finishTime);
			if(8 < available) {
				available = 8;
			}
			double additionalTimeMultiplication = Math.ceil((amount/available));
			int amountOfGroups =  (int) additionalTimeMultiplication;
			return amountOfGroups;
			}
		else {
			return 1;
		}
		
		
	}
	
	public LocalDateTime addTimeslot(String eventType, LocalDateTime startTime,LocalDateTime finishTime) throws DataAccessException {
		
		//TODO set mutex lock on chosen timeslot??
		EventType et = eventTypeCtrl.findEvent(EnumType.valueOfLabel(eventType));
		double amount = newBooking.getAmountOfPeople();
    if (amount <= 0) {
			Exception e = new Exception();
			throw new DataAccessException(e, "Amount of people must be set to a positive amount!");
		}
    
		int amountOfGroups = calculateGroups(et, startTime, finishTime, amount) ;
    
		bt = new BookingTime(et, startTime, amountOfGroups); //set as field value, can be used for checking if timeslot requirements are met
		if (!checkTimeslot(et.getEnumType(), bt.getStartTime(), bt.getFinishTime())) {
			Exception e = new Exception();
			throw new DataAccessException(e, "Booking would overlap with other booking!");
		}
		newBooking.addTimeslot(bt); 
		return bt.getFinishTime();
	}
	
	public Customer addCustomer(String phoneNo) throws DataAccessException {
		return newBooking.addCustomer(customerCtrl.findCustomer(phoneNo));
	}
	
	public boolean addAmountOfPeople(int amount) throws DataAccessException {
		if(amount > 0) {
			newBooking.setAmountOfPeople(amount);
			return true;
		}
		else {
			return false; //TODO Skal nok �ndres til at returne void?
		}
	}
	
	public void addCateringMenu(int cateringMenu) throws DataAccessException {
		newBooking.addCateringMenu(cateringCtrl.findCateringMenu(cateringMenu));
	}
	
	public ArrayList<String> finishBooking() throws DataAccessException {
		newBooking.calculateTotalPrice();
		int currentId = bookingDatabase.insertBooking(newBooking);
		//int currentId = bookingDatabase.getCurrentId();
		bookingTimeDatabase.insertBookingTime(newBooking.getTimeslots(), currentId);
		return getReceipt();
		
	}
	
	public ArrayList<String> getReceipt(){
		ArrayList<String> receipt = new ArrayList<>();
		receipt.add("---RECEIPT---");
		receipt.add("Booking created on " + newBooking.getCreationDate());
		receipt.add("Customer name: " + newBooking.getCustomer().getName());
		receipt.add("Customer phone number: " + newBooking.getCustomer().getPhoneNo());
		receipt.add("Amount of people: " + newBooking.getAmountOfPeople());
		receipt.add("Event(s):");

		for (int i = 0; i < newBooking.getTimeslots().size();i++) {
			receipt.add("Event type: " + newBooking.getTimeslots().get(i).getEventType().getEnumType().getLabel());
			receipt.add("Event start time: " + newBooking.getTimeslots().get(i).getStartTime());
			receipt.add("Event finish time: " + newBooking.getTimeslots().get(i).getFinishTime());
			receipt.add("Event type base price: " + newBooking.getTimeslots().get(i).getEventType().getPrice() + " DKK");

			receipt.add(" ");
		}
		if(newBooking.hasCateringMenu()) {
			receipt.add("Catering menu: " + newBooking.getCatering().getEnumMenu().getLabel());
			receipt.add("Catering menu price per person: " + newBooking.getCatering().getPrice() + " DKK");

		}
		String paid = "";
		if(newBooking.isPaid()) {
			paid = "Yes";
		}
		else {
			paid = "No";
		}
		receipt.add("Booking paid?: " + paid);
		receipt.add("Booking total: " + newBooking.getTotal() + " DKK");

		return receipt;
	}
	
	//Only for testing purposes - returns current booking
		public Booking getBooking() {
			return newBooking;
		}
	
	
	public List<Booking> findBookingsByDate(LocalDate date) throws DataAccessException {
		
		bookings = bookingDatabase.findBookingsByDate(date);
		addBookingTimesToBooking(bookings);
		return bookings;
	}
	
	public void addBookingTimesToBooking(List<Booking> list) throws DataAccessException {
		bookingTimeCtrl.addBookingTimesToBookings(list);
		
	}
	
	public Booking selectBooking(int bookingId) {
        boolean found = false;
        int i = 0;
        while(!found && i<bookings.size()) {
        	if(bookings.get(i).getBookingId() == bookingId) {
        		found = true;
        	}
        	else {
        		i++;
        	}
        }
        if (found) {
        	return bookings.get(i);
        }
        else {
        	return null;
        }
		
	}

	public Boolean updateBooking(Booking booking) throws DataAccessException {
		bookingDatabase.updateBooking(booking); 
		updateBookingTime(booking);
		return true;
	}
	
	public Boolean updateBookingTime(Booking booking) throws DataAccessException {
		return bookingTimeDatabase.updateBookingTime(booking);
	}


	
}
