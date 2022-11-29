package model;

//time, customer, catering


public class Booking {
	Customer customer;
	BookingTime bookingTime;
	
	
	public Booking() {
		System.out.println(this + " created");
	}
	
	public void addCustomer(Customer c) {
		customer=c;
		System.out.println("Customer " + c + " added");
	}
	
	public void addTime(BookingTime bt) {
		bookingTime = bt;
		System.out.println("Time " + bookingTime + " added");
	}
	
}
