package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;

class TestFinishBooking {

	static DBConnection con;
	static BookingCtrl bc;
	static LocalDateTime d;
	static ArrayList<String> rec;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
		bc = new BookingCtrl();
		d = LocalDateTime.of(2022, 12, 24, 7, 0); 
//		d.plusHours(1); // make date increment before each test
		rec = new ArrayList<>(); //create an empty receipt alternately create a happy days receipt to compare with
	}
	
//	@BeforeAll
//	void makeTestReceipt() throws Exception {
//		rec = bc.finishBooking(); //the alt approach
//	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
		d = null;
	}
	

	/*
	 * Happy days 5.1
	 */
	@Test
	void testFinishBookingHappyDays() throws DataAccessException, SQLException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addCustomer("14354678");
		bc.addAmountOfPeople(8);
		LocalDateTime dt = d.plusWeeks(1);
		bc.addTimeslot("LeMans 1 Time", dt, dt.plusHours(2)); // 1 time * 2 grupper
		bc.addTimeslot("Eventhal 2 Timer", dt, dt.plusHours(2)); // 2 timer
		bc.addCateringMenu(3);
		//assert
		assertNotEquals(rec.toString(), bc.finishBooking().toString());
		//assertLinesMatch(null, null);
//		System.out.println("[" + rec + "] is not equal to [" + bc.finishBooking() + "]");
	}
	
	/*
	 * 5.2
	 */
	@Test
	void testFinishBookingAlternativeHappyDays() throws DataAccessException, SQLException {
		//arrange
		bc.createBooking();
		//act
		bc.addCustomer("14354678");
		bc.addAmountOfPeople(8);
		LocalDateTime dt = d.plusDays(5);
		bc.addTimeslot("LeMans 1 Time", dt, dt.plusHours(2)); 
		//assert
		assertNotEquals(rec.toString(), bc.finishBooking().toString());

	}
	
	/*
	 * 5.3
	 */
	@Test
	void testFinishBookingTimeSlotTrouble() throws DataAccessException, SQLException {
		//arrange
		bc.createBooking();
		//act
		bc.addCustomer("14354678");
		bc.addAmountOfPeople(8);
		//assert to make sure the test doesnt crash
//		assertThrows(NullPointerException.class, () -> bc.addTimeslot("wasd", d, d.plusHours(2)));
		assertEquals(rec.toString(), bc.finishBooking().toString()); //now we want it to match - //TODO maybe handle exception, change to assertlinesmatch
	}
	
	/*
	 * 5.3.1
	 */
	@Test
	void testFinishBookingTimeSlotOccupied() throws DataAccessException, SQLException {
		//arrange
		bc.createBooking();
		//act
		bc.addCustomer("14354678");
		bc.addAmountOfPeople(8);
		//assert to make sure test doesnt crash
		assertThrows("Booking would overlap with other booking!", DataAccessException.class, 
				() -> bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2)));
		assertEquals(rec.toString(), bc.finishBooking().toString()); //now we want it to match - //TODO maybe handle exception, change to assertlinesmatch
	}
	
	/*
	 * 5.4
	 */
	@Test
	void testFinishBookingTimeInvalidAttendees() throws DataAccessException, SQLException {
		//arrange
		bc.createBooking();
		//act
		bc.addCustomer("14354678");
		bc.addAmountOfPeople(-10);
		assertThrows("Amount of people must be set to a positive amount!", DataAccessException.class,
				() -> bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2)));
		assertEquals(rec.toString(), bc.finishBooking().toString());
	}
	
}
