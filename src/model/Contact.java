package model;

public abstract class Contact {
	
	private String name, phoneNo, email, address, city, country;
	private int zipCode, contactId;
	
	public Contact(int contactId, String name, String phoneNo) {
		this.contactId = contactId;
		this.name = name;
		this.phoneNo = phoneNo;
	}
	
	public Contact(int contactId, String name, String phoneNo, String email, String address, int zipCode, String city, String country) {
		this.contactId = contactId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}
	
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getContactId() {
		return contactId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getZipCode() {
		return zipCode;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	
}
