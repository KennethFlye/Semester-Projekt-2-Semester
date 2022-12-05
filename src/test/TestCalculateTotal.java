package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
	 * Testing of singular and multiple timeslots added
	 * Testing of multiple timeslots and catering added
	 * Testing of negative, null, minimum, maximum and above max customers added
	 */
	
	@Test //Tests to see if Total is updated with the price of one timeslot, even with negative customers
	void testTotalPriceOneTimeslotNegativeCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(-1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal()); //TODO fix it
	}
	
	@Test //Tests to see if Total is updated with the price of more timeslots
	void testTotalPriceMoreTimeslotsNegativeCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(-1);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal()); //TODO fix it
	}
	
	@Test //Tests to see if Total is updated with the price of more timeslots + price of catering 
	void testTotalPriceTimeslotNegativeCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(-1);
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal()); //TODO fix it
	}
	
	@Test //Tests to see if Total is updated with the price of one timeslot, with no customers added
	void testTotalPriceOneTimeslotNoCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		
		//arrange - calculate the total price so far
		bc.getBooking().calculateTotalPrice(); //The price is multiplied with the amount of customers, 
													//which is 0 and therefore results in 0.
		
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
	void testTotalPriceTimeslotNoCustomersWithCatering() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d);
		bc.addCateringMenu(1);
		
		//arrange - calculate the total price so far
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotOneCustomer() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(285, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsOneCustomer() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(1);
		
		//Arrange
		bc.getBooking().calculateTotalPrice(); //simply multiplies price by 1
		
		//Assert
		assertEquals(285+180, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceTimeslotOneCustomerWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(1);
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(285+180+55, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotMaxCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(8);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(8*285, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsMaxCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(8);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(8*(285+180), bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceTimeslotMaxCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(8);
		bc.addCateringMenu(1); //55
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(8*(285+180+55), bc.getBooking().getTotal());
	}
	
	@Test //Only 8 gokarts can be on the track at the same time
	void testTotalPriceOneTimeslotTooManyCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(9); //if nine or more drivers, two timeslots should be booked instead
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(9*285, bc.getBooking().getTotal()); //The extra timeslot is not calculated because the attendence and therefore price is already more than necessary
	}
	
	@Test
	void testTotalPriceMoreTimeslotsTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(9);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(9*(285+180), bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceTimeslotTooManyCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(9);
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(9*(285+180+55), bc.getBooking().getTotal());
	}
	
	@Test //Thy Gokart only has 10 gokarts in all
	void testTotalPriceOneTimeslotMuchTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(11);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(11*285, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsMuchTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(11);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(11*(285+180), bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceTimeslotMuchTooManyCustomersWithCatering() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d);
		bc.addAmountOfPeople(11);
		bc.addCateringMenu(1);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(11*(285+180+55), bc.getBooking().getTotal());
	}		
	
	

}
