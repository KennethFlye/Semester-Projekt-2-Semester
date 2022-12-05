package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.Booking;
import model.BookingTime;

class testGetBookingTime {

	static DBConnection con = null;
	static BookingCtrl bc;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws DataAccessException {
		//Arrange
		BookingTime bt1 = new BookingTime(20, null)
		
		//Act
		List<BookingTime> list = bc.findBookedTimeslots(2022, 11, 28);
		
		//Assert 
		assertArrayEquals(null, list.size());
	}

}