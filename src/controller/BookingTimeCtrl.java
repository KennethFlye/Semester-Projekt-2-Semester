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
	
<<<<<<< Updated upstream
	public void addBookingTimesToBookings(List<Booking> bookings) throws DataAccessException{
		bookingTimeDB.addBookingTimesToBooking(bookings);
=======
<<<<<<< HEAD
	public List<Booking> addBookingTimesToBookings(List<Booking> bookings) throws DataAccessException{
		return bookingTimeDB.findBookingTimeByBookingId(bookings);
=======
	public void addBookingTimesToBookings(List<Booking> bookings) throws DataAccessException{
		bookingTimeDB.addBookingTimesToBooking(bookings);
>>>>>>> 2897065492618a052d0d1bb8bce016e42734491b
>>>>>>> Stashed changes
	}
	
	public Boolean updateBookingTime(Booking booking) throws DataAccessException {
		return bookingTimeDB.updateBookingTime(booking);
		
	}

}
