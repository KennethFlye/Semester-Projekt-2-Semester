package database;

import model.Booking;

public interface BookingDBIF {
	
	public int insertBooking(Booking newBooking) throws DataAccessException;

}
