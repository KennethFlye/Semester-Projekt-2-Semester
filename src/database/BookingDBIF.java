package database;

import model.Booking;

public interface BookingDBIF {
	
	//void insertBookingTime(Booking newBooking);

	void insertBooking(Booking newBooking) throws DataAccessException;

	int getCurrentId() throws DataAccessException;
}
