package model;

public class Customer extends Contact {
	
	public Customer(String name, String phoneNo, String email) {
		super(name, phoneNo, email);
		System.out.println(this + " created");
	}

}
