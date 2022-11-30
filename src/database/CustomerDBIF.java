package database;

import model.Customer;

public interface CustomerDBIF {
	Customer findCustomer(String phoneNo) throws DataAccessException;
}
