package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;

class TestCalculateTotal {

	static DBConnection con;
	static BookingCtrl bc;
	static LocalDateTime d;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
		bc = new BookingCtrl();
		d = LocalDateTime.now();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
		d = null;
	}
		
	/*
	 * Tests for testing singular timeslot added
	 */
	@Disabled //TODO fix
	@Test
	void testTotalPriceOneTimeslotNegativeCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(-1, d, d);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotNoCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		//arrange - calculate the total price so far
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotOneCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(1, d, d);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(285, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotMaxCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(8, d, d);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(8*285, bc.getBooking().getTotal());
	}
	
	@Disabled //TODO fix
	@Test
	void testTotalPriceOneTimeslotTooManyCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(9, d, d);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	
	/*
	 * Tests for testing multiple timeslots added
	 */
	@Disabled //TODO fix
	@Test
	void testTotalPriceMoreTimeslotsNegativeCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(-1, d, d);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsNoCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsOneCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(1, d, d);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(285+180, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsMaxCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(8, d, d);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(8*(285+180), bc.getBooking().getTotal());
	}
	
	@Disabled //TODO fix
	@Test
	void testTotalPriceMoreTimeslotsTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(9, d, d);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	

}
