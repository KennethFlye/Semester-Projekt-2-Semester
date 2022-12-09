package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.Customer;
import model.PatternCheck;
import ui.CreateBookingMenu;

class TestAddCustomer {

	static DBConnection con;
	static CreateBookingMenu cbm;
	static BookingCtrl bc;
	static PatternCheck pc;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
		cbm = new CreateBookingMenu();
		bc = new BookingCtrl();
		pc = new PatternCheck();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		cbm = null;
		bc = null;
		pc = null;
	}
	
	/*
	 * 1.1
	 */
	@Test
	void testValidCustomerValidInput() throws DataAccessException {
		bc.createBooking();
		assertTrue(pc.checkPhoneNo("14354678")); //checks to see if phone number is valid
		bc.addCustomer("14354678"); //finds and adds a customer with that pattern
		assertEquals("14354678", bc.getBooking().getCustomer().getPhoneNo()); //checks to see if they're equal
	}
	
	/*
	 * 1.2
	 */
	@Test 
	void testNoCustomerValidInput() throws DataAccessException {
		bc.createBooking();
		assertTrue(pc.checkPhoneNo("+4590332222")); //checks if pattern is valid
		bc.addCustomer("+4590332222"); //finds and adds TODO addCustomer should maybe throw something
		assertThrows(NullPointerException.class, () -> bc.getBooking().getCustomer().getPhoneNo()); //we expect an exception to be thrown because customer with that number does not exist
		System.out.println("testNoCustomerValidInput() throws exception because Customer is " + bc.getBooking().getCustomer()); //shows us the customer
	}
	
	/*
	 * 1.3
	 */
	@Test
	void testAddCustomer() throws DataAccessException {
		bc.createBooking();
		assertNull(bc.addCustomer("wasd"));
		assertEquals("Bjarne Riis", bc.addCustomer("99999999").getName());
	}
	
	@Test
	void testValidPattern(String phoneInput) {
		assertTrue(pc.checkPhoneNo(phoneInput));
	}
	
	@Test //kan blive kaldt fra andre moteder
	void testInvalidPattern(String phoneInput) {
		assertTrue(pc.checkPhoneNo(phoneInput));
	}

}
