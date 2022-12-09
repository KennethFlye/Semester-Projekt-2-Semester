package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	 * test cases 5.1
	 */
	@Test
	void testTotalPriceMoreTimeslotsOneCustomerWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(1);
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(285+180+55, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsMaxCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(8);
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addCateringMenu(1); //55
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(8*(285+180+55), bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsTooManyCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(9); //if nine or more drivers, two timeslots should be booked instead
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(9*(285+180+55), bc.getBooking().getTotal()); //The extra timeslot is not calculated because the attendence and therefore price is already more than necessary
	}
	
	/*
	 * test cases 5.2
	 */
	@Test
	void testTotalPriceMoreTimeslotsNoCustomersWithCatering() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d);
		bc.addCateringMenu(1);
		
		//arrange - calculate the total price so far
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal()); //attendees are set to 0 by default, which means price is multiplied by 0
	}
	
	@Test
	void testTotalPriceMoreTimeslotsNegativeCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		assertThrows(DataAccessException.class, () -> bc.addAmountOfPeople(-1)); //the method wont set non-positive numbers, therefore amount of people is still zero
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	/*
	 * test cases 5.3
	 */
	@Test
	void testTotalPriceOneTimeslotOneCustomer() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(1);
		bc.addTimeslot("Formel 1", d, d); //285
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(285, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotMaxCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(8);
		bc.addTimeslot("Formel 1", d, d); //285
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(8*285, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addAmountOfPeople(9);
		bc.addTimeslot("Formel 1", d, d); //285
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(9*285, bc.getBooking().getTotal());
	}
	
	/*
	 * test cases 5.4
	 */
	@Test 
	void testTotalPriceOneTimeslotNoCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		
		//arrange - calculate the total price so far
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceOneTimeslotNegativeCustomers() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		assertThrows(DataAccessException.class, () -> bc.addAmountOfPeople(-1));
		bc.addTimeslot("Formel 1", d, d); //285
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	/*
	 * test cases 5.5
	 * TODO case 5.5 - bookingctrl should not accept catering without timeslots
	 */
	@Test 
	void testTotalPriceNoTimeslotsOneCustomerWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(1);
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(55, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceNoTimeslotsMaxCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(8);
		bc.addCateringMenu(1); //55
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(8*55, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceNoTimeslotsTooManyCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addAmountOfPeople(9); 
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(9*55, bc.getBooking().getTotal());
	}
	
	/*
	 * test cases 5.6
	 * TODO as case 5.5
	 */
	@Test
	void testTotalPriceNoTimeslotsNoCustomerWithCatering() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addCateringMenu(1);
		
		//arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal()); 
	}
	
	@Test
	void testTotalPriceNoTimeslotsNegativeCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		assertThrows(DataAccessException.class, () -> bc.addAmountOfPeople(-1)); 
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal());
	}	

}
