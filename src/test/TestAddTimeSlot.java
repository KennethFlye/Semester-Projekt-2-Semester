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
		bc.createBooking();
		EventType et = new EventType(EnumType.FORMULA_1);
		BookingTime bt = new BookingTime(et, LocalDateTime.of(2022, 11, 30, 12, 0));
		
		
		//Act
		bc.addTimeslot(et.getEnumType().getLabel(), bt.getStartTime(), bt.getFinishTime());
		//Assert
		assertEquals(bt.getFinishTime(), bt.getStartTime().plusMinutes(et.getEnumType().getLength()));
	}

}
