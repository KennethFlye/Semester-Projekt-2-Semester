package test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;

class TestFinishBooking {

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
	 * Happy days 5.1
	 */
	@Test
	void testFinishBookingHappyDays() throws DataAccessException {
		//arrange
		bc.createBooking();
		
		//act
		bc.addCustomer("14354678");
		bc.addAmountOfPeople(8);
		bc.addTimeslot("LeMans 1 Time", d, d);
		bc.addTimeslot("Eventhal 1.5 Time", d, d);
		bc.addCateringMenu(3);
		
//		assertDoesNotThrow(); //TODO either somehow get to throw or add a method in bc explicitly for calling the insert methods
	}

}
