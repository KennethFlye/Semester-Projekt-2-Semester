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
	
	/**Returns true if there are no bookings in the given timespan*/
	public boolean checkTimeslot(EnumType type, LocalDateTime startTime,LocalDateTime finishTime) {
		return bookingTimeDatabase.checkTimeslot(type, startTime, finishTime);
	}
	
	public LocalDateTime addTimeslot(String eventType, LocalDateTime startTime,LocalDateTime finishTime) throws DataAccessException {
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
		double amount = newBooking.getAmountOfPeople();
		int amountOfGroups = 1;
		if( et.getEnumType().location == 1) {
				double available = gokartCtrl.getAvailableGokarts(startTime, finishTime);
				if(8 < available) {
					available = 8;
				}
				double additionalTimeMultiplication = Math.ceil((amount/available));
				amountOfGroups = (int) additionalTimeMultiplication;
				}
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
			newBooking.setAmountOfPeople(amount);
		    return true; //Skal nok �ndres til at returne void?
	}
	
	public void addCateringMenu(int cateringMenu) throws DataAccessException {
		//TODO make it possible to not set cateringmenu
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
	
}
