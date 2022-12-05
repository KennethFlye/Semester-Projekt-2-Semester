package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.Booking;
import model.EventType;
import model.EventType.EnumType;

class TestAddAmountOfPeople {

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
	
		EventType et = new EventType(EnumType.FORMULA_1);
		LocalDateTime startTime = LocalDateTime.of(2022, 11, 30, 12, 0);
		bc.addTimeslot(et.getEnumType().getLabel(), startTime);
		
		//Act
		bc.addAmountOfPeople(2,bc.getNewBooking().g);
		
		//Assert
		
	}

}
