package database;

import model.Booking;

public interface BookingDBIF {

	DBConnection getDBConnection();
	public int insertBooking(Booking newBooking) throws DataAccessException;

}
