package database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Booking;
import model.BookingTime;
import model.EventType.EnumType;

public interface BookingTimeDBIF {
	
	public ArrayList<BookingTime> insertBookingTime(ArrayList<BookingTime> bookingTimes, int bookingid) throws DataAccessException;

	public List<BookingTime> getBookedTimeslots(int day, int month, int year) throws DataAccessException;

	
	public boolean updateBookingTime(Booking booking) throws DataAccessException;

	void addBookingTimesToBooking(List<Booking> bookings) throws DataAccessException;

	/**Returns true if the spot is clear, returns false if the spot is taken
	 * @throws SQLException */
	boolean checkTimeslot(EnumType type, LocalDateTime startTime, LocalDateTime finishTime, int bookingId) throws SQLException;
	
}
