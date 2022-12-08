package database;

import model.Customer;

public interface CustomerDBIF {
	public Customer findCustomer(String phoneNo) throws DataAccessException;
}
