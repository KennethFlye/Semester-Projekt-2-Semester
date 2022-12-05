package database;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface GokartDBIF {
	boolean hasEnoughAvailableGokarts(int amount, LocalDateTime time, LocalDateTime end) throws SQLException;

	/**
	 * Checks whether the required amount of gokarts are available for a booking. A gokart is available if it is not
	 * rented out at the time of the booking. The method works by finding each gokart's rentouts, and checking whether 
	 * any rentouts overlap the booking.
	 * @return true if enough gokarts are available, false otherwise
	 * @param amount gokarts required
	 * @param start start date and time for booking 
	 * @param end date and time for booking
	 */
	int getAvailableGokarts(LocalDateTime start, LocalDateTime end) throws SQLException;
}
