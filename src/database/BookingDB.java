package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

import model.Booking;

public class BookingDB implements BookingDBIF {

	private static final String INSERTBOOKING_Q_FOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId, menuId) VALUES (?, ?, ?, ?, ?, ?); SELECT IDENT_CURRENT( 'Booking' );";
	private PreparedStatement insertBookingPSFood;
	
	private static final String INSERTBOOKING_Q_NOFOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId) VALUES (?, ?, ?, ?, ?); SELECT IDENT_CURRENT( 'Booking' );";
	private PreparedStatement insertBookingPSNoFood;
	
	private static final String GETBOOKINGBYIDQ = "SELECT * FROM Booking WHERE bookingId = ?";
	private PreparedStatement getBookingById;


	//Method mainly for making the transaction
	@Override
	public DBConnection getDBConnection() {
		return DBConnection.getInstance();
	}
	
	@Override
	public int insertBooking(Booking newBooking) throws DataAccessException {
		Connection connection;
		connection = getDBConnection().getConnection();
		try {
			insertBookingPSFood = connection.prepareStatement(INSERTBOOKING_Q_FOOD);
			insertBookingPSNoFood = connection.prepareStatement(INSERTBOOKING_Q_NOFOOD);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		PreparedStatement ps;
		
		if(newBooking.hasCateringMenu())
			ps = insertBookingPSFood;
				else 
			ps = insertBookingPSNoFood;
		
		try {
			
			//Total price
			ps.setFloat(1, (float) newBooking.getTotal());
			
			//Creation date
			ps.setDate(2, Date.valueOf(newBooking.getCreationDate().toLocalDate()));
			
			//Amount of people
			ps.setInt(3, newBooking.getAmountOfPeople());
			
			//Is paid?
			ps.setInt(4, newBooking.isPaid()?1:0);
			
			//Customer id
			ps.setInt(5, newBooking.getCustomer().getContactId());
			
			//Employee id
			//ps.setInt(6, newBooking.getEmployee().getId());// Outside use case
			//ps.setInt(6, 1);
			
			//Menu id
			if(newBooking.hasCateringMenu())
				ps.setInt(6, newBooking.getCatering().getId());
			
			ps.execute();
			ps.getMoreResults();
			ResultSet rs = ps.getResultSet();
			rs.next();
			int currentId = rs.getInt(1);
			ps.close();
			return currentId;
			
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not execute");
		}
				
	}
	
	public List<Booking> findBookingByDate(LocalDate date){
		Connection connection;
		connection = getDBConnection().getConnection();
		try {
			getBookingById = connection.prepareStatement(GETBOOKINGBYIDQ);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		List<Booking> bookings = new ArrayList<>();
		
		BookingTimeDBIF bkTDB = new BookingTimeDB();
		ResultSet rs = bkTDB.getBookingIdsByDate(date);
		
		while(rs.next()) {
			
			
		}
		
	}
	
}

