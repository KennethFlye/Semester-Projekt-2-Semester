package controller;

import java.util.List;

import database.BookingTimeDB;
import database.BookingTimeDBIF;
import database.DataAccessException;
import model.Booking;
import model.BookingTime;

public class BookingTimeCtrl {
	
	BookingTimeDBIF bookingTimeDB;
	
	public BookingTimeCtrl() throws DataAccessException {
		bookingTimeDB = new BookingTimeDB();
	}
	
	public List<Booking> findBookingTimeByBookingId(List<Booking> bookings) throws DataAccessException{
		return bookingTimeDB.findBookingTimeByBookingId(bookings);
	}
	
	public Boolean updateBookingTime(Booking booking) throws DataAccessException {
		return bookingTimeDB.updateBookingTime(booking);
		
	}

}
