package model;

import java.time.LocalDate;

public class Customer extends Contact {
	
	private LocalDate dateOfBirth;
	
	public Customer(int contactId, String name, String phoneNo) {
		super(contactId, name, phoneNo);
		System.out.println(this + " created");
	}
	
	public Customer(int contactId, String name, String phoneNo, String email, String address, int zipCode, String city, String country, LocalDate dateOfBirth) {
		super(contactId, name, phoneNo, email, address, zipCode, city, country);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	

}
