package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.Customer;

class TestAddCustomer {

	static DBConnection con;
	static BookingCtrl bc;
	static LocalDateTime d;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
		bc = new BookingCtrl();
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
	}
	
	/*
	 * 1.1
	 */
	@Test
	void testValidCustomerValidInput() throws DataAccessException {
		//arrange
		bc.createBooking();
		//act
		Customer c = bc.addCustomer("14354678");
		//assert
		assertEquals(c, bc.getBooking().getCustomer());
	}
	
	/*
	 * 1.2
	 */
	@Test 
	void testNoCustomerValidInput() throws DataAccessException {
		bc.createBooking();
		Customer c = bc.addCustomer("+4590332222");
		assertEquals(c, bc.getBooking().getCustomer()); //TODO should not work yet
	}
	
	/*
	 * 1.3
	 */
	@Test
	void testInvalidInputType() {
		bc.createBooking();
		//TODO get throw exception - maybe check for returned msg i gui layer
	}

}
