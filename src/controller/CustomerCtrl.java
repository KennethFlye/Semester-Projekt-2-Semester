package controller;

import database.CustomerDB;
import database.CustomerDBIF;
import database.DataAccessException;
import model.Customer;

public class CustomerCtrl {
	private CustomerDBIF customerDBIF;

	public CustomerCtrl() throws DataAccessException {
		customerDBIF = new CustomerDB();
	}
	
	public Customer findCustomer(String phoneNo) throws DataAccessException {
		return customerDBIF.findCustomer(phoneNo);
	}

}
