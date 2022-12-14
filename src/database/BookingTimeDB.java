package database;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import model.Booking;
import model.BookingTime;
import model.EventType;
import model.EventType.EnumType;



public class BookingTimeDB implements BookingTimeDBIF {
	
	private static final String INSERTBOOKINGTIME_Q = "INSERT INTO BookingTime (eventType, bookingId, startTime, finishTime) VALUES (?, ?, ?, ?);";
	private static final String GETBOOKINGTIMESBYDATE_Q = "SELECT * FROM BookingTime WHERE BookingTime.startTime between ? and ?";
	private static final String GETBOOKINGTIMESBYDATEWITHOUTSPECIFICBOOKING_Q = GETBOOKINGTIMESBYDATE_Q + " and BookingTime.bookingId != ?";
	private static final String GET_EVENTTYPE_BOOKINGTIME_BY_BOOKINGID_JOIN_TABLE_Q = "Select * FROM EventType, BookingTime Where BookingTime.eventType = EventType.type and BookingTime.bookingId = ?";
	private static final String GETBOOKINGTIMEBOOKINGIDQ = "SELECT bookingId FROM BookingTime WHERE BookingTime.eventType = ? and BookingTime.startTime = ? and BookingTime.finishTime = ?";
	private static final String UPDATE_BOOKINGTIME_Q = "UPDATE BookingTime SET startTime = ?, finishTime = ? WHERE BookingTime.bookingId = ? AND BookingTime.eventType = ?" ;
	
	private PreparedStatement insertBookingTime,getBookingTimesByDate,getEventTypeBookingTimeByBookingId, getBookingTimeBookingId, updateBookingTime;
	
	private List<EnumType> usesEventHall = new ArrayList<>();
	private List<EnumType> usesGokartTrack = new ArrayList<>();
	
	public BookingTimeDB() throws DataAccessException{
		
		usesEventHall.add(EnumType.EVENT_HALL_1_HOUR);
		usesEventHall.add(EnumType.EVENT_HALL_1_AND_HALF_HOUR);
		usesEventHall.add(EnumType.EVENT_HALL_2_HOURS);
		usesGokartTrack.add(EnumType.FORMULA_1);
		usesGokartTrack.add(EnumType.LARGE_FORMULA_1);
		usesGokartTrack.add(EnumType.LE_MANS_1_HOUR);
		
		try {
			getBookingTimesByDate = DBConnection.getInstance().getConnection().prepareStatement(GETBOOKINGTIMESBYDATE_Q);
			insertBookingTime = DBConnection.getInstance().getConnection().prepareStatement(INSERTBOOKINGTIME_Q);
			getEventTypeBookingTimeByBookingId = DBConnection.getInstance().getConnection().prepareStatement(GET_EVENTTYPE_BOOKINGTIME_BY_BOOKINGID_JOIN_TABLE_Q);
			updateBookingTime = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_BOOKINGTIME_Q);
			getBookingTimeBookingId = DBConnection.getInstance().getConnection().prepareStatement(GETBOOKINGTIMEBOOKINGIDQ);

			
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
			insertBookingTime = DBConnection.getInstance().getConnection().prepareStatement(INSERTBOOKINGTIME_Q);
			for (BookingTime i: bookingTimes) {
			insertBookingTime.setString(1, i.getEventType().getEnumType().getLabel());
			insertBookingTime.setInt(2, bookingid);
			insertBookingTime.setTimestamp(3, Timestamp.valueOf(i.getStartTime()));
			insertBookingTime.setTimestamp(4, Timestamp.valueOf(i.getFinishTime()));
			insertBookingTime.execute();

			} 
			return bookingTimes;
		} 
		catch (SQLException e) {
			throw new DataAccessException(e, "Eror :c");
			}
		} 
	
		
		private BookingTime buildObject(ResultSet rs) throws SQLException {
			EventType tempEvent = new EventType(EnumType.valueOfLabel(rs.getString("eventType")));
			BookingTime bt = new BookingTime(tempEvent, rs.getTimestamp("startTime").toLocalDateTime(), rs.getTimestamp("finishTime").toLocalDateTime());
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
		/**Returns true if the spot is clear, returns false if the spot is taken
		 * @throws SQLException */
		public boolean checkTimeslot(EnumType type, LocalDateTime startTime, LocalDateTime finishTime, int bookingId) throws SQLException {
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
					if(!checkForSameBooking(btList.get(i), bookingId)) {
						
						long timeA = btList.get(i).getStartTime().toEpochSecond(zo);
						long timeB = btList.get(i).getFinishTime().toEpochSecond(zo);
						long checkedTimeA = startTime.toEpochSecond(zo);
						long checkedTimeB = finishTime.toEpochSecond(zo);
						
						if(timeA<checkedTimeA||timeA<checkedTimeB)
							inFront = true;
						
						if(timeB<checkedTimeA)
							inFront2 = true;
						
						if(inFront!=inFront2) {
							EnumType checkedType = btList.get(i).getEventType().getEnumType();
							if(usesEventHall.contains(type) && usesEventHall.contains(checkedType)
									|| usesGokartTrack.contains(type) && usesGokartTrack.contains(checkedType))
							returnBool = false;
				}
				
					}
						
				
				
					
			}
			return returnBool;	
						
		}

		@Override
		public boolean updateBookingTime(Booking booking) throws DataAccessException {
			//UPDATE BookingTime SET startTime = ?, finishTime = ? WHERE BookingTime.bookingId = ? AND BookingTime.eventType = ?
			List<BookingTime> bookingTimes = booking.getTimeslots();
			
			for(int i = 0; i < bookingTimes.size(); i++) {
				BookingTime currentBookingTime = bookingTimes.get(i);
				try {
					updateBookingTime.setTimestamp(1, Timestamp.valueOf(currentBookingTime.getStartTime()));
					updateBookingTime.setTimestamp(2, Timestamp.valueOf(currentBookingTime.getFinishTime()));
					updateBookingTime.setInt(3, booking.getBookingId());
					updateBookingTime.setString(4, currentBookingTime.getEventType().getEnumType().getLabel());
					
					updateBookingTime.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DataAccessException(e, "Error during update");
				}
			}
			
			
			return true;
		}

		@Override
		public void addBookingTimesToBooking(List<Booking> bookings) throws DataAccessException {
			ResultSet rs;
			
			
			for(int i = 0; i < bookings.size(); i++) {
				Booking currentBooking = bookings.get(i);
				
				try {
					getEventTypeBookingTimeByBookingId.setInt(1, currentBooking.getBookingId());
					
					rs = getEventTypeBookingTimeByBookingId.executeQuery();
					
					while(rs.next()) {
						EnumType enumType = EnumType.valueOfLabel(rs.getString("type"));
						double price = rs.getDouble("price");
						
						EventType et = new EventType(enumType, price);
						
						LocalDateTime startTime = rs.getTimestamp("startTime").toLocalDateTime();
						LocalDateTime finishTime = rs.getTimestamp("finishTime").toLocalDateTime();
						
						BookingTime bt = new BookingTime(et, startTime, finishTime);
						
						currentBooking.addTimeslot(bt);
					}
				} catch (SQLException e) {
					throw new DataAccessException(e, "Could not get EventType and BookingTime from Database");
				}
			}

		}
		
		public boolean checkForSameBooking(BookingTime bt, int id) throws SQLException {
			if (id <= 0) {
				return false;
			}
			else{
				getBookingTimeBookingId.setString(1, bt.getEventType().getEnumType().getLabel());
				getBookingTimeBookingId.setTimestamp(2, Timestamp.valueOf(bt.getStartTime()));
				getBookingTimeBookingId.setTimestamp(3, Timestamp.valueOf(bt.getFinishTime()));
				ResultSet rs = getBookingTimeBookingId.executeQuery();
				rs.next();
				return (rs.getInt(1) == id);
				
		}


		}
		
}
		