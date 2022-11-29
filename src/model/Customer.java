package model;

public class Customer {

	String name, phoneNo, email;
	
	public Customer(String name, String phoneNo, String email) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		System.out.println(this + " created");
	}

}
