package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
		d = LocalDateTime.of(2022, 11, 28, 9, 30);	
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
		LocalDateTime dt = d.plusDays(1); //gokart kolliderer ikke med noget kl 9:30 dagen efter
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Act
		bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(dt.plusMinutes((eg.getEnumType()).getLength()),bc.getBooking().getTimeslots().get(0).getFinishTime());
		//asserts that the booking has been added
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
		BookingTime bt = new BookingTime(eg, d, 1);
		bc.addTimeslot(ee.getEnumType().label, bt.getStartTime(), bt.getFinishTime());
		//tries to add a timeslot with another eventtype in the same time period
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
		BookingTime bt = new BookingTime(eg, d, 1);
		//assert
		assertThrows(DataAccessException.class, () -> bc.addTimeslot(
				eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime()));
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
		assertThrows("Booking would overlap with other booking!", DataAccessException.class,
				() -> bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime()));
	}
	
	/*
	 * 2.2.2
	 */
	@Test
	void testStarttimeOverlapSameEventType() throws DataAccessException {
		//Arrange
		bc.createBooking();
		//Act
		bc.addAmountOfPeople(6);
		LocalDateTime dt = d.plusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 1);
		//Assert
		assertThrows("Booking would overlap with other booking!", DataAccessException.class,
				() -> bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime()));
	}
	
	/*
	 * 2.2.3
	 */
	@Test
	void testInternalOverlapSameEventType() throws DataAccessException {
		bc.createBooking();
		bc.addAmountOfPeople(9);
		LocalDateTime dt = d.plusMinutes(30);
		BookingTime bt = new BookingTime(eg, dt, 3);
		assertThrows("Booking would overlap with other booking!", DataAccessException.class, 
				() -> bc.addTimeslot(eg.getEnumType().label, bt.getStartTime(), bt.getFinishTime()));
	}
}
