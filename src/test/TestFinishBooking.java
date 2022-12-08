package test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.BookingDB;
import database.BookingDBIF;
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
	void testFinishBookingHappyDays() throws DataAccessException, SQLException {
		//arrange
		bc.createBooking();
		
		//act
//		bc.addCustomer("14354678");
		bc.addAmountOfPeople(8);
		bc.addTimeslot("LeMans 1 Time", d, d.plusDays(1));
		bc.addTimeslot("Eventhal 2 Timer", d, d.plusDays(1));
		bc.addCateringMenu(3);
		
		BookingDBIF db = new BookingDB();
		assertThrows(DataAccessException.class, () -> bc.finishBooking());
		
	}

}
