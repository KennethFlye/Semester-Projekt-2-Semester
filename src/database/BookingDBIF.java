package database;

import java.time.*;
import java.util.*;

import model.Booking;

public interface BookingDBIF {

	DBConnection getDBConnection();
	
	public int insertBooking(Booking newBooking) throws DataAccessException;
	
	public List<Booking> findBookingsByDate(LocalDate date) throws DataAccessException;
	
	public boolean updateBooking(Booking booking) throws DataAccessException;

}
