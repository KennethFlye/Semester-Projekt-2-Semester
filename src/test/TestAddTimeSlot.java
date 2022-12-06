package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.Booking;
import model.BookingTime;
import model.Customer;
import model.EventType;
import model.EventType.EnumType;

class TestAddTimeSlot {

	static DBConnection con = null;
	static BookingCtrl bc;
	static LocalDateTime d;
	static LocalDateTime dt;
	static Customer c;
	static EventType eg;
	static EventType ee;
	static BookingTime btg;
	static BookingTime bte;
	static Booking b;
	static Exception e = new Exception();
	static	DataAccessException trown;
	

	
	
	@BeforeEach
	void setUp() throws Exception {

		con = DBConnection.getInstance();
		bc = new BookingCtrl();
		d = LocalDateTime.of(2022, 02, 01, 14, 30);	
		dt = d;
		eg = new EventType(EnumType.LE_MANS_1_HOUR);
		ee = new EventType(EnumType.EVENT_HALL_1_HOUR);
	
		
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
		d = null;
		eg = null;
		ee = null;
		dt = null;
	}
		
	@Test
	void testNoOverlap() throws DataAccessException {
		//Arrange
		bc.createBooking();
		LocalDateTime dt = d.plusDays(1);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType()).getLength()*1),bc.getBooking().getTimeslots().get(0).getFinishTime());
	}
	@Test
	void testStartOverlapSameType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		LocalDateTime dt = d.plusMinutes(30);
		BookingTime bt = new BookingTime(ee, dt, 1);
		//Act
		bc.addTimeslot(ee.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		
		assertEquals(dt.plusMinutes((ee.getEnumType().getLength()*1)),bc.getBooking().getTimeslots().get(0).getFinishTime());
		System.out.println(dt.plusMinutes((ee.getEnumType().getLength()*1)));
	}
	
	@Test
	void testFinishOverlapSameType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		LocalDateTime dt = d.minusMinutes(30);
		BookingTime bt = new BookingTime(ee, dt, 1);
		//Act
		bc.addTimeslot(ee.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((ee.getEnumType().getLength()*1)),bc.getBooking().getTimeslots().get(0).getFinishTime());	
	}
	@Test
	void testStartOverlapDifferentType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		LocalDateTime dt = d.plusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType().getLength())*1),bc.getBooking().getTimeslots().get(0).getFinishTime());
		
	}
	@Test
	void testFinishOverlapDifferentType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		LocalDateTime dt = d.minusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType().getLength()*1)),bc.getBooking().getTimeslots().get(0).getFinishTime());
		
	}

	@Test
	void testInBetweenOverlap() throws DataAccessException {
		//Arrange
		bc.createBooking();
		LocalDateTime dt = d.plusMinutes(30);
		BookingTime bt = new BookingTime(ee, dt, 2);
		//Act
		bc.addTimeslot(ee.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType().getLength()*1)),bc.getBooking().getTimeslots().get(0).getFinishTime());
		
		
	}
}
