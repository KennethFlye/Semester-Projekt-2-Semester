package database;

import model.Booking;

public interface BookingDBIF {
	DBConnection getDBConnection();
	int insertBooking(Booking newBooking) throws DataAccessException;

}
