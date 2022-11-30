package model;

public abstract class Contact {
	
	private String name, phoneNo, email, address, city, country;
	private int zipCode;
	
	public Contact(String name, String phoneNo, String email) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	void setEmail(String email) {
		this.email = email;
	}
	
	void setAddress(String address) {
		this.address = address;
	}
	
	void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	void setCity(String city) {
		this.city = city;
	}
	
	void setCountry(String country) {
		this.country = country;
	}
	
	String getName() {
		return name;
	}
	
	String getPhoneNo() {
		return phoneNo;
	}
	
	String getEmail() {
		return email;
	}
	
	String getAddress() {
		return address;
	}
	
	int getZipCode() {
		return zipCode;
	}
	
	String getCity() {
		return city;
	}
	
	String getCountry() {
		return country;
	}
	
}
