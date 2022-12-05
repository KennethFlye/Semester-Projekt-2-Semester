package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import controller.CateringCtrl;
import controller.EventTypeCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.CateringMenu;
import model.EventType;
import model.EventType.EnumType;

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
	 * Tests for checking if the event prices are as expected - TODO move these to a new test class
	 */
	@Test
	void testIfPriceIsRightEventType() throws DataAccessException {
		EventTypeCtrl ec = new EventTypeCtrl();
		
		EventType et1 = ec.findEvent(EnumType.FORMULA_1);
		EventType et2 = ec.findEvent(EnumType.LARGE_FORMULA_1);
		EventType et3 = ec.findEvent(EnumType.LE_MANS_1_HOUR);
		EventType et4 = ec.findEvent(EnumType.EVENT_HALL_1_HOUR);
		EventType et5 = ec.findEvent(EnumType.EVENT_HALL_1_AND_HALF_HOUR);
		EventType et6 = ec.findEvent(EnumType.EVENT_HALL_2_HOURS);
		
		assertEquals(285, et1.getPrice()); //formula 1
		assertEquals(360, et2.getPrice()); //large formula 1
		assertEquals(560, et3.getPrice()); //le mans
		assertEquals(180, et4.getPrice()); //event hall 1 hour
		assertEquals(240, et5.getPrice()); //event hall 1.5 hour = 1 hour * 0.75
		assertEquals(320, et6.getPrice()); //event hall 2 hour = 1.5 hour * 0.75
	}
	
	@Test
	void testIfPriceIsRightCateringMenu() throws DataAccessException {
		CateringCtrl cc = new CateringCtrl();
		
		CateringMenu cm1 = cc.findCateringMenu(1);
		CateringMenu cm2 = cc.findCateringMenu(2);
		CateringMenu cm3 = cc.findCateringMenu(3);
		
		assertEquals(55, cm1);
		assertEquals(55, cm2);
		assertEquals(55, cm3);
	}
		
	/*
	 * Testing of singular and multiple timeslots added
	 * Testing of multiple timeslots and catering added
	 * Testing of negative, null, minimum, maximum and above max customers added
	 */
	
	@Disabled //TODO fix
	@Test //Tests to see if Total is updated with the price of one timeslot, even with negative customers
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
	
	@Disabled //TODO fix
	@Test //Tests to see if Total is updated with the price of more timeslots
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
	
	@Disabled //TODO fix
	@Test //Tests to see if Total is updated with the price of more timeslots + price of catering 
	void testTotalPriceTimeslotNegativeCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(-1, d, d);
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal());
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
		bc.addAmountOfPeople(1, d, d);
		
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
		bc.addAmountOfPeople(1, d, d);
		
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
		bc.addAmountOfPeople(1, d, d);
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
		bc.addAmountOfPeople(8, d, d);
		
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
		
		bc.addAmountOfPeople(8, d, d);
		
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
		bc.addAmountOfPeople(8, d, d);
		bc.addCateringMenu(1); //55
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(8*(285+180+55), bc.getBooking().getTotal());
	}
	
	@Disabled //TODO fix
	@Test //Only 8 gokarts can be on the track at the same time
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
	
	@Disabled //TODO fix
	@Test
	void testTotalPriceMoreTimeslotsTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		
		bc.addAmountOfPeople(9, d, d); //if nine or more drivers, two timeslots should be booked instead - implement in another use case iteration
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Disabled //TODO fix
	@Test
	void testTotalPriceTimeslotTooManyCustomersWithCatering() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(9, d, d);
		bc.addCateringMenu(1);
		
		bc.getBooking().calculateTotalPrice();
		
		//assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test //Thy Gokart only has 10 gokarts in all
	void testTotalPriceOneTimeslotMuchTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addAmountOfPeople(11, d, d);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceMoreTimeslotsMuchTooManyCustomers() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d); //180
		bc.addAmountOfPeople(11, d, d);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}
	
	@Test
	void testTotalPriceTimeslotMuchTooManyCustomersWithCatering() throws DataAccessException {
		//Arrange
		bc.createBooking();
		
		//Act
		bc.addTimeslot("Formel 1", d, d); //285
		bc.addTimeslot("Eventhal 1 Time", d, d);
		bc.addAmountOfPeople(11, d, d);
		bc.addCateringMenu(1);
		
		//Arrange
		bc.getBooking().calculateTotalPrice();
		
		//Assert
		assertEquals(0, bc.getBooking().getTotal());
	}		
	
	

}
