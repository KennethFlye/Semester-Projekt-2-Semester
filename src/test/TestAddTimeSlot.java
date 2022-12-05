package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.BookingTime;
import model.EventType;
import model.EventType.EnumType;

class TestAddTimeSlot {

	static DBConnection con;
	static BookingCtrl bc;
	static LocalDateTime d;
	
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
		bc = new BookingCtrl();
		d = LocalDateTime.of(2022, 11, 30, 12, 0);
	
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
		d = null;
	
	}
		
	
	
	@Test
	void testT() throws DataAccessException {
		//Arrange
		bc.createBooking();
		EventType et = new EventType(EnumType.FORMULA_1);
		BookingTime bt = new BookingTime(et, d);
		
		
		//Act
		bc.addTimeslot(et.getEnumType().getLabel(), startTime);
		//Assert

		//assertEquals();

		assertEquals( d.plusMinutes(et.getEnumType().getLength()),bt.getFinishTime());

		assertEquals(bt.getFinishTime(), bt.getStartTime().plusMinutes(et.getEnumType().getLength()));

	}

}