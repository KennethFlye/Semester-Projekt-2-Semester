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
	
	//SET
	
	public void addTimeslot(BookingTime bt) {
		bookingTimeslots.add(bt);
		totalPrice += bt.getEventType().getPrice();
		System.out.println("Time " + bt + " added");
	}
	
	public void addCustomer(Customer c) {
		customer=c;
		System.out.println("Customer " + c + " set");
	}
	
	public void addCateringMenu(CateringMenu cm) {
		cateringMenu = cm;
		totalPrice += cateringMenu.getPrice();
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

	public int amountOfPeople() {
		return amountOfPeople;
	}

	public Customer getCustomer() {
		return customer;
	}

	public float getPrice() { //TODO remove stub when getPrice is made
		//return cateringMenu.getPrice() + eventprice;
		return (float) cateringMenu.getPrice(); //TODO STUB TODO STUB TODO STUB TODO STUB
	}

	/*public Employee getEmployee() { Outside use case
		return null;
	}*/
		
}
