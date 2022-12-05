package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.BookingCtrl;
import controller.CateringCtrl;
import controller.EventTypeCtrl;
import database.DBConnection;
import database.DataAccessException;
import model.CateringMenu;
import model.EventType;
import model.EventType.EnumType;

public class TestIfPriceIsRight {
	
	static DBConnection con;
	
	@BeforeEach
	void setUp() throws Exception {
		con = DBConnection.getInstance();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		con = null;
	}
	
	/*
	 * Tests for checking if the event prices are as expected
	 */
	@Test
	void testIfPriceIsRightEventType() throws DataAccessException {
		EventTypeCtrl ec = new EventTypeCtrl();
		
		EventType et1 = ec.findEvent(EnumType.FORMULA_1);
		EventType et2 = ec.findEvent(EnumType.LARGE_FORMULA_1);
		EventType et3 = ec.findEvent(EnumType.LE_MANS_1_HOUR);
		EventType et4 = ec.findEvent(EnumType.EVENT_HALL_1_HOUR);
		EventType et5 = ec.findEvent(EnumType.EVENT_HALL_1_AND_HALF_HOUR);
		EventType et6 = ec.findEvent(EnumType.EVENT_HALL_2_HOURS);
		
		assertEquals(285, et1.getPrice()); //formula 1
		assertEquals(360, et2.getPrice()); //large formula 1
		assertEquals(560, et3.getPrice()); //le mans
		assertEquals(180, et4.getPrice()); //event hall 1 hour
		assertEquals(240, et5.getPrice()); //event hall 1.5 hour = 1 hour * 0.75
		assertEquals(320, et6.getPrice()); //event hall 2 hour = 1.5 hour * 0.75
	}
	
	@Test
	void testIfPriceIsRightCateringMenu() throws DataAccessException {
		CateringCtrl cc = new CateringCtrl();
		
		CateringMenu cm1 = cc.findCateringMenu(1);
		CateringMenu cm2 = cc.findCateringMenu(2);
		CateringMenu cm3 = cc.findCateringMenu(3);
		
		assertEquals(55, cm1.getPrice());
		assertEquals(55, cm2.getPrice());
		assertEquals(55, cm3.getPrice());
	}
}
