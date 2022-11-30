package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Booking;

public class BookingDB implements BookingDBIF {

	private static final String INSERTBOOKING_Q_FOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId, menuId) VALUES (?, ?, ?, ?, ?, ?)";
	private PreparedStatement insertBookingPSFood;
	
	private static final String INSERTBOOKING_Q_NOFOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId) VALUES (?, ?, ?, ?, ?)";
	private PreparedStatement insertBookingPSNoFood;
	
	
	public BookingDB() {
		
		Connection connection;
		try {
			//Get connection
			connection = DBConnection.getInstance().getConnection();
			
			//Initialize prepared statement
			insertBookingPSFood = connection.prepareStatement(INSERTBOOKING_Q_FOOD);
			insertBookingPSNoFood = connection.prepareStatement(INSERTBOOKING_Q_NOFOOD);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void insertBooking(Booking newBooking) {
		PreparedStatement ps;
		
		if(newBooking.hasCateringMenu())
			ps = insertBookingPSFood;
				else 
			ps = insertBookingPSNoFood;
		
		try {
			
			//Total price
			ps.setInt(1, 0);
			//Creation date
			ps.setString(2, "");
			//Amount of people
			ps.setString(3, "");
			//Is paid?
			ps.setString(4, "");
			//Customer id
			
			//Menu id
			if(newBooking.hasCateringMenu())
				ps.setInt(5, 0);
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	
}
