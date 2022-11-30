package controller;

import database.CustomerDBIF;
import database.CustomerDB;
import model.Customer;

public class CustomerCtrl {
	private CustomerDBIF customerDBIF;

	public CustomerCtrl() /*throws DataAccessException*/ {
		//TODO remove cast to IF - let db implement IF instead
		customerDBIF = (CustomerDBIF) new CustomerDB();
	}
	
	public Customer findCustomer(String phoneNo) /*throws DataAccessException*/ {
		return customerDBIF.findCustomer(phoneNo);
	}

}
