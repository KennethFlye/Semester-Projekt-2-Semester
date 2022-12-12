package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
	static EventType eg;
	static EventType ee;
	static BookingTime bt;
	

	
	
	@BeforeEach
	void setUp() throws Exception {

		con = DBConnection.getInstance();
		bc = new BookingCtrl();
		d = LocalDateTime.of(2022, 02, 01, 14, 30);	
		eg = new EventType(EnumType.LE_MANS_1_HOUR); //EG Event Gokart
		ee = new EventType(EnumType.EVENT_HALL_1_HOUR); //EE Event Eventhal
	
		
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
		bc = null;
		d = null;
		eg = null;
		ee = null;
		dt = null;
		bt = null;
	}
		
	/*
	 * 2.1
	 */
	@Test
	void testNoOverlapSameEventType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		bc.addAmountOfPeople(6);
		LocalDateTime dt = d.plusDays(1);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType()).getLength()),bc.getBooking().getTimeslots().get(0).getFinishTime());
	}
	
	/*
	 * 2.1.1
	 */
	@Test
	void testFullOverlapOtherEventType() throws DataAccessException {
		//arrange
		bc.createBooking();
		//act
		bc.addAmountOfPeople(6);
		BookingTime bt = new BookingTime(ee, d, 1); //eventtype eventhall
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//assert
		assertEquals(d, bt.getStartTime()); //test starttime
		assertEquals(d.plusHours(1), bt.getFinishTime()); //test finishtime
	}
	
	/*
	 * 2.2
	 */
	@Test
	void testFullOverlapSameEventType() throws DataAccessException {
		//arrange
		bc.createBooking();
		//act
		bc.addAmountOfPeople(6);
//		LocalDateTime dt = ;
		BookingTime bt = new BookingTime(eg, d, 1);
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//assert
//		assertThrows("Booking would overlap with other booking!", DataAccessException.class, () -> addtimeslot) TODO cleanup, maybe throws instead of equals?
		assertEquals(d, bt.getStartTime()); //test starttime
		assertEquals(d.plusHours(1), bt.getFinishTime()); //test finishtime
	}
	
	/*
	 * 2.2.1
	 */
	@Test
	void testFinishtimeOverlapSameEventType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		bc.addAmountOfPeople(6);
		LocalDateTime dt = d.minusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		//assertEquals(dt.plusMinutes((ee.getEnumType().getLength()*1)),bc.getBooking().getTimeslots().get(0).getFinishTime());	
		
		assertEquals(dt.plusMinutes((eg.getEnumType().getLength()*1)),bt.getFinishTime());	

	}
	
	/*
	 * 2.2.2
	 */
	@Test
	void testStarttimeOverlapSameEventType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		bc.addAmountOfPeople(6);
		LocalDateTime dt = d.plusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		
		assertEquals(dt.plusMinutes(eg.getEnumType().getLength()),bc.getBooking().getTimeslots().get(0).getFinishTime());
	}
	
	/*
	 * 2.2.3
	 */
	@Test
	void testInternalOverlapSameEventType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		bc.addAmountOfPeople(9);
		LocalDateTime dt = d.plusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 2); //eg eller ee??????
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType().getLength()*1)),bc.getBooking().getTimeslots().get(0).getFinishTime());
		
	}
	
	
	//TODO remove these?
	@Test
	void testStartOverlapDifferentType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		bc.addAmountOfPeople(6);
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
		bc.addAmountOfPeople(6);
		LocalDateTime dt = d.minusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType().getLength()*1)),bc.getBooking().getTimeslots().get(0).getFinishTime());
		
	}
	
}
