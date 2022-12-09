package model;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**	
 * 
 * 
 * */
public class Booking {
	private Customer customer;
	private ArrayList<BookingTime> bookingTimeslots;
	private LocalDateTime creationDate;
	private CateringMenu cateringMenu;
	private Boolean isPaid;
	private int amountOfPeople, bookingID;
	private double totalPrice;
	
	public Booking() {
		System.out.println(this + " created");
		bookingTimeslots = new ArrayList<>();
		creationDate = LocalDateTime.now();
		isPaid = false;
		totalPrice = 0;
	}
	
	public Booking(Customer customer, CateringMenu cateringMenu, int amountOfPeople, int bookingID, double totalPrice, LocalDateTime creationDate) {
		this.customer = customer;
		this.cateringMenu = cateringMenu;
		this.amountOfPeople = amountOfPeople;
		this.bookingID = bookingID;
		this.totalPrice = totalPrice;
		this.creationDate = creationDate;
		
		bookingTimeslots = new ArrayList<>();
		isPaid = false;
	}
	
	//SET
	
	public void addTimeslot(BookingTime bt) {
		bookingTimeslots.add(bt);
//		totalPrice += bt.getEventType().getPrice();
		System.out.println("Time " + bt + " added");
	}
	
	public Customer addCustomer(Customer c) {
		customer=c;
		System.out.println("Customer " + c + " set");

		return customer;
	}
	
	public void addCateringMenu(CateringMenu cm) {
		cateringMenu = cm;
//		totalPrice += cateringMenu.getPrice();
		System.out.println("Cateringmenu " + cm + " set");
	}
	
	public void setAmountOfPeople(int amount) {
		amountOfPeople=amount;
		System.out.println("Amount of attendees set to " + amountOfPeople);
	}

	
	
	//GET
	
	public boolean hasCateringMenu() {
		return cateringMenu!=null;
	}

	public CateringMenu getCatering() {
		return cateringMenu;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public Boolean isPaid() {
		return isPaid;
	}

	public int getAmountOfPeople() {
		return amountOfPeople;
	}

	public Customer getCustomer() {
		return customer;
	}

	public double getTotal() {
		return totalPrice;
	}
	
	public void setTotal(double total) {
		totalPrice = total;
	}
	
	public ArrayList<BookingTime> getTimeslots(){
		return bookingTimeslots; //Returns a list of timeslot(s)
	}
	
	public int getBookingId() {
		return bookingID;
	}

	/*public Employee getEmployee() { Outside use case
		return null;
	}*/
	
	public void calculateTotalPrice() {
		double total = 0;
		for (int i = 0; i < bookingTimeslots.size(); i++) {
			total += bookingTimeslots.get(i).getEventType().getPrice();
		}
		
		if(hasCateringMenu()) {
			total += getCatering().getPrice();
		}
		
		total *= getAmountOfPeople();
		
		setTotal(total);
	}
		
}
