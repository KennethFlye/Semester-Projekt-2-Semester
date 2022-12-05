package database;

import model.Booking;

public interface BookingDBIF {
	
	//void insertBookingTime(Booking newBooking);

	int insertBooking(Booking newBooking) throws DataAccessException;
}
