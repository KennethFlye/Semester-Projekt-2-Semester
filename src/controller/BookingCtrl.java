package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.BookingDB;
import database.BookingDBIF;
import database.BookingTimeDB;
import database.BookingTimeDBIF;
import database.DataAccessException;
import model.Booking;
import model.BookingTime;
import model.Customer;
import model.EventType;
import model.EventType.EnumType;

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
		bookingTimeCtrl = new BookingTimeCtrl();
	}
	
	
	//Methods -----------------------------------------------------------------------------------------------
	
	public void createBooking() {
		newBooking = new Booking();
		
	}
	
	public List<BookingTime> findBookedTimeslots(int year, int month, int day) throws DataAccessException {
		//TODO evt convert a string (the param of the method) to ints (the param of the return statement) for easier booking
		return bookingTimeDatabase.getBookedTimeslots(year, month, day);
	}
	
	/**Returns true if there are no bookings in the given timespan
	 * @throws SQLException */
	public boolean checkTimeslot(EnumType type, LocalDateTime startTime,LocalDateTime finishTime, int bookingId) throws SQLException {
		return bookingTimeDatabase.checkTimeslot(type, startTime, finishTime, bookingId);
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
		try {
			if (!checkTimeslot(et.getEnumType(), bt.getStartTime(), bt.getFinishTime(), newBooking.getBookingId())) {
				Exception e = new Exception();
				throw new DataAccessException(e, "Booking would overlap with other booking!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException(e, "Something went wrong");
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
		try {
			//starttransaction sets autocommit to false, which means nothing will be committed before we tell it to
			bookingDatabase.getDBConnection().startTransaction(); //since dbconnection is a singleton, it doesnt matter which db class we call it from
			
			newBooking.calculateTotalPrice();
			int currentId = bookingDatabase.insertBooking(newBooking);
			//int currentId = bookingDatabase.getCurrentId();
			bookingTimeDatabase.insertBookingTime(newBooking.getTimeslots(), currentId);
			
			ArrayList<BookingTime> timeSlots = newBooking.getTimeslots();
			boolean failed = false;
			
			for(int i = 0; i < timeSlots.size(); i++) {
				BookingTime currentTimeSlot = timeSlots.get(i);
				if(!bookingTimeDatabase.checkTimeslot(currentTimeSlot.getEventType().getEnumType(), currentTimeSlot.getStartTime(), currentTimeSlot.getFinishTime(), newBooking.getBookingId())) {
					bookingDatabase.getDBConnection().rollbackTransaction();
					failed = true;
				}
			}
			
			if(!failed) {
				//committransaction inserts everything in the database so far, unless there are problems - sets autocommit to true again
				bookingDatabase.getDBConnection().commitTransaction();
			}
			
			
			return getReceipt();
			
		} catch (SQLException e) {
			try {
				bookingDatabase.getDBConnection().rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DataAccessException(e1, "Could not rollback transaction");
			}
			e.printStackTrace();
			throw new DataAccessException(e, "Could not commit transaction");
		}
		
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
	
	//getBooking() only for testing purposes - returns current booking
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
        	newBooking = bookings.get(i);
        	return newBooking;
        }
        else {
        	return null;
        }
		
	}

	public Boolean updateBooking() throws DataAccessException {
		newBooking.calculateTotalPrice();
		bookingDatabase.updateBooking(newBooking); 
		updateBookingTime(newBooking);
		ArrayList<String> receipt = getReceipt();
		for (String element : receipt) {
			System.out.println(element);
		}
		return true;
	}
	
	public Boolean updateBookingTime(Booking booking) throws DataAccessException {
		return bookingTimeDatabase.updateBookingTime(booking);
	}
	
	public void clearLocationTimeSlot(int locationId) {
		ArrayList<BookingTime> timeSlots = newBooking.getTimeslots();
		for(int i = 0; i < timeSlots.size(); i++) {
			BookingTime currentTimeSlot = timeSlots.get(i);
			if(currentTimeSlot.getEventType().getEnumType().getLocation() == locationId) {
				timeSlots.remove(currentTimeSlot);
			}
		}
		newBooking.setTimeSlots(timeSlots);
	}
	


	
}
