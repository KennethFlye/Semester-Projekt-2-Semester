package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.BookingTime;
import model.EventType;
import model.EventType.EnumType;



public class BookingTimeDB implements BookingTimeDBIF {

	private static final String INSERTBOOKINGTIME_Q = "INSERT INTO BookingTime (eventType, bookingId, startTime, finishTime) VALUES (?, ?, ?, ?);";
	private static final String GETBOOKINGTIMESBYDATE_Q = "SELECT * FROM BookingTime WHERE BookingTime.startTime LIKE '?%'";
	private static final String GETBOOKINGTIME_Q = "SELECT * FROM BookingTime WHERE BookingTime.startTime between ? and ? + 23:59";
	private PreparedStatement insertBookingTime,getBookingTimesByDate,getBookingTime;

	/**
	 * Constructor
	 * @throws DataAccessException
	 */

	public void BookingDB() throws DataAccessException{
		try {
			insertBookingTime = DBConnection.getInstance().getConnection().prepareStatement(INSERTBOOKINGTIME_Q);
			getBookingTime = DBConnection.getInstance().getConnection().prepareStatement(GETBOOKINGTIMESBYDATE_Q);
			getBookingTimesByDate = DBConnection.getInstance().getConnection().prepareStatement(GETBOOKINGTIME_Q);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}

	/**
	 *
	 */

	@Override
	public List<BookingTime> getBookedTimeslots(int day, int month, int year) throws DataAccessException {
		ResultSet rs;
		Date date = Date.valueOf(LocalDate.of(year, month, day));
		try {
			getBookingTimesByDate.setDate(1, date);
			rs = getBookingTimesByDate.executeQuery();
			List<BookingTime> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not retrieve all persons");
		}
	}

	/**
	 *
	 */

	@Override
	public ArrayList<BookingTime> insertBookingTime(ArrayList<BookingTime> bookingTimes, int bookingid) throws DataAccessException {
		try {
			for (BookingTime i: bookingTimes) {
			insertBookingTime.setString(1, i.getEventType().getEnumType().getLabel());
			insertBookingTime.setInt(2, bookingid);
			insertBookingTime.setTime(3, Time.valueOf(i.getStartTime().toLocalTime()));
			insertBookingTime.setTime(4, Time.valueOf(i.getFinishTime().toLocalTime()));
			insertBookingTime.executeQuery();
			}
			return bookingTimes;
		}
		catch (SQLException e) {
			throw new DataAccessException(e, "Eror :c");
			}
		}


	private BookingTime buildObject(ResultSet rs) throws SQLException {
		EventType tempEvent = new EventType(EnumType.valueOfLabel(rs.getString("eventType")));
		BookingTime bt = new BookingTime(
			tempEvent,
			rs.getTimestamp("startTime").toLocalDateTime(),
			rs.getTimestamp("finishTime").toLocalDateTime());
		return bt;
		}


	private List<BookingTime> buildObjects(ResultSet rs) throws SQLException {
		List<BookingTime> res = new ArrayList<>();
			while(rs.next()) {
					res.add(buildObject(rs));
			}
			return res;
		}


		}














