package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.PatternCheck;

class TestAddCustomer {

	static DBConnection con;
	static BookingCtrl bc;
	static PatternCheck pc;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance(); //calls database, used to find customers
		bc = new BookingCtrl(); //where the magic happens
		pc = new PatternCheck(); //checks the input patterns, used in gui
		
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
		pc = null;
	}
	
	/*
	 * 1.1
	 */
	@Test
	void testCustomerFoundWithValidPhoneNumberInput() throws DataAccessException {
		//arrange
		bc.createBooking();
		//act
		String phoneNo = "14354678"; //sets the phone number so we easily can change it
		//assert
		assertTrue(pc.checkPhoneNo(phoneNo)); //checks if the string pattern is valid - we expect it to be true or else stop the test
		//act
		bc.addCustomer(phoneNo); //finds and adds a customer with that phone number
		//assert
		assertEquals(phoneNo, bc.getBooking().getCustomer().getPhoneNo()); //checks to see if input number is equal to the phone number of the customer we have added
	}
	
	/*
	 * 1.2
	 */
	@Test 
	void testNoCustomerFoundWithValidPhoneNumberInput() throws DataAccessException {
		//arrange
		bc.createBooking();
		//act
		String phoneNo = "+4590332222";
		//assert
		assertTrue(pc.checkPhoneNo(phoneNo));
		//act
		bc.addCustomer(phoneNo); 
		//assert
		assertThrows(NullPointerException.class, () -> bc.getBooking().getCustomer().getPhoneNo()); //we expect an exception to be thrown because a customer with that number does not exist
		System.out.println("testNoCustomerFoundWithValidPhoneNumberInput() throws exception because Customer is " + bc.getBooking().getCustomer()); //shows us the customer - could also be assertNull
	}
	
	/*
	 * 1.3
	 */
	@Test
	void testInvalidPhoneNumberInput() throws DataAccessException {
		//arrange
		bc.createBooking();
		//act
		String phoneNo1 = "wasdwasd", phoneNo2 = "+45 12001200", phoneNo3 = "+99999999"; //some examples
		//assert
		assertFalse(pc.checkPhoneNo(phoneNo1)); //as before - this time we expect them to be false
		assertFalse(pc.checkPhoneNo(phoneNo2)); 
		assertFalse(pc.checkPhoneNo(phoneNo3));
		//act
		bc.addCustomer(phoneNo3); //we try to add one to the current booking - since all are false any of them would "work"
		//we know Bjarne Riis has the number 99999999 but since phoneNo3 isnt a valid sequence nothing is added
		assertEquals("Bjarne Riis", bc.getBooking().getCustomer().getName());
	}

}
