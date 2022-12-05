package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import database.DBConnection;
import model.Booking;
import model.BookingTime;

class calculateTotal {

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
	void test() {
		//Arrange
		boo
		
		
		//Act
		bc.addTimeslot();
		//Assert
	}

}
