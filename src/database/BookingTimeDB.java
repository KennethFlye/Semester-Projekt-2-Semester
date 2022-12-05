package database;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import model.BookingTime;
import model.EventType;
import model.EventType.EnumType;



public class BookingTimeDB implements BookingTimeDBIF {
	
	private static final String INSERTBOOKINGTIME_Q = "INSERT INTO BookingTime (eventType, bookingId, startTime, finishTime) VALUES (?, ?, ?, ?);";
	private static final String GETBOOKINGTIMESBYDATE_Q = "SELECT * FROM BookingTime WHERE BookingTime.startTime LIKE '?%'";
	private static final String GETBOOKINGTIME_Q = "SELECT * FROM BookingTime WHERE BookingTime.startTime between ? and ?";
	private PreparedStatement insertBookingTime,getBookingTimesByDate,getBookingTime;
	
	public BookingTimeDB() throws DataAccessException{
		try {
			getBookingTimesByDate = DBConnection.getInstance().getConnection().prepareStatement(GETBOOKINGTIME_Q);
			insertBookingTime = DBConnection.getInstance().getConnection().prepareStatement(INSERTBOOKINGTIME_Q);
			getBookingTime = DBConnection.getInstance().getConnection().prepareStatement(GETBOOKINGTIMESBYDATE_Q); 
			
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	
	@Override
	public List<BookingTime> getBookedTimeslots(int year, int month, int day) throws DataAccessException {
		ResultSet rs;
		Date date = Date.valueOf(LocalDate.of(year, month, day));
		List<BookingTime> res = new ArrayList<BookingTime>();
			try {
				//Type
				getBookingTimesByDate.setDate(1, date);
				Timestamp ts = Timestamp.valueOf(date.toString()+" 23:59:59");
				getBookingTimesByDate.setTimestamp(2, ts);
				
				rs = getBookingTimesByDate.executeQuery();
				res = buildObjects(rs);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return res;	
	}
			
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

			
		@Override
		public boolean checkTimeslot(EnumType type, LocalDateTime startTime, LocalDateTime finishTime) {
			List<BookingTime> btList = null;
			try {
				btList = getBookedTimeslots(startTime.getYear(), startTime.getMonth().getValue(), startTime.getDayOfMonth());
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			System.out.println(btList);
			boolean returnBool = true;
			for (int i = 0; i < btList.size(); i++) {
				boolean inFront = false, inFront2 = false;
				ZoneOffset zo = ZoneOffset.systemDefault().getRules().getOffset(Instant.now());
				long timeA = btList.get(i).getStartTime().toEpochSecond(zo);
				long timeB = btList.get(i).getFinishTime().toEpochSecond(zo);
				long checkedTimeA = startTime.toEpochSecond(zo);
				long checkedTimeB = finishTime.toEpochSecond(zo);
				
				if(timeA<checkedTimeA||timeA<checkedTimeB)
					inFront = true;
				
				if(timeB<checkedTimeA)
					inFront2 = true;
				
				if(inFront!=inFront2) {
					returnBool = false;
					
				}
			}
			return returnBool;			
		}
		}
		
		
	
	
		


		
		
		
		
		
	

		