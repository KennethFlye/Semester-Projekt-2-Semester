package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
	static ArrayList<String> rec;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
		bc = new BookingCtrl();
		d = LocalDateTime.now();
		rec = new ArrayList<>(); //create an empty receipt;
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
		d = null;
		rec = null;
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
		bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2)); // 1 time * 2 grupper
		bc.addTimeslot("Eventhal 2 Timer", d, d.plusHours(2)); // 2 timer
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
		bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2)); //TODO bør måske lave en ny dato for ikke at få overlap?
		bc.addTimeslot("Eventhal 2 Timer", d, d.plusHours(2)); 
		//assert
		assertNotEquals(rec.toString(), bc.finishBooking().toString());
//		System.out.println("[" + rec + "] is not equal to [" + bc.finishBooking() + "]");
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
		bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2));
		//assert to make sure the test doesnt crash
		assertThrows(DataAccessException.class, () -> bc.addTimeslot("wasd", d, d.plusHours(2)));
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
		assertThrows(DataAccessException.class, () -> bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2))); //TODO find occupied timeslot
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
		bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2)); 
		bc.addTimeslot("Eventhal 2 Timer", d, d.plusHours(2)); 
		assertEquals(rec.toString(), bc.finishBooking().toString()); //now we want it to match - //TODO maybe handle exception, change to assertlinesmatch
	}
	
	/*
	 * 5.5
	 */
	@Test
	void testFinishBookingTimeInvalidCustomer() throws DataAccessException, SQLException {
		//arrange
		bc.createBooking();
		//act
		bc.addCustomer("+45 99999999");
		bc.addAmountOfPeople(8);
		bc.addTimeslot("LeMans 1 Time", d, d.plusHours(2)); 
		bc.addTimeslot("Eventhal 2 Timer", d, d.plusHours(2)); 
		assertEquals(rec.toString(), bc.finishBooking().toString()); //now we want it to match - //TODO maybe handle exception, change to assertlinesmatch
	}
	
}
