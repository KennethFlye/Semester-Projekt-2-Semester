package model;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**	
 * 
 * 
 * */
public class Booking {
	private Customer customer;
	private ArrayList<BookingTime> bookingTime;
	private LocalDateTime creationDate;
	private CateringMenu cateringMenu;
	private Boolean isPaid;
	private int amountOfPeople, bookingID;
	
	public Booking() {
		System.out.println(this + " created");
		bookingTime = new ArrayList<>();
		creationDate = LocalDateTime.now();
		isPaid = false;
	}
	
	//SET
	
	public void addCustomer(Customer c) {
		customer=c;
		System.out.println("Customer " + c + " added");
	}
	
	public void addTime(BookingTime bt) {
		bookingTime.add(bt);
		System.out.println("Time " + bookingTime + " added");
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
		//return cateringMenu.getPrice();
		return 50; //TODO STUB TODO STUB TODO STUB TODO STUB
	}

	/*public Employee getEmployee() { Outside use case
		return null;
	}*/
		
}
