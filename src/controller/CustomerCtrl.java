package controller;

import database.CustomerDBIF;
import database.CustomerDB;
import model.Customer;

public class CustomerCtrl {
	private CustomerDBIF customerDBIF;

	public CustomerCtrl() /*throws SQLException**/ {
		//TODO remove cast to IF - let db implement IF instead
		customerDBIF = (CustomerDBIF) new CustomerDB();
	}
	
	public Customer findCustomer(String phoneNo) /*throws SQLException*/ {
		return customerDBIF.findCustomer(phoneNo);
	}

}
