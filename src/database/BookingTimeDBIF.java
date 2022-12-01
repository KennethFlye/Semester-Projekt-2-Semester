package database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.BookingTime;

public interface BookingTimeDBIF {
	
	public ArrayList<BookingTime> insertBookingTime(ArrayList<BookingTime> bookingTimes, int bookingid) throws DataAccessException;

	public List<BookingTime> getBookedTimeslots(int day, int month, int year) throws DataAccessException;
	
}
