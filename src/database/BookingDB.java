package database;

import java.sql.Connection;
import java.sql.Date;
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
			ps.setFloat(1, (float) newBooking.getTotal());
			
			//Creation date
			ps.setDate(2, Date.valueOf(newBooking.getCreationDate().toLocalDate()));
			
			//Amount of people
			ps.setInt(3, newBooking.amountOfPeople());
			
			//Is paid?
			ps.setInt(4, newBooking.isPaid()?1:0);
			
			//Customer id
			//ps.setInt(5, newBooking.getCustomer().getId());// ToDo
			ps.setInt(5, 1);
			
			//Employee id
			//ps.setInt(6, newBooking.getEmployee().getId());// Outside use case
			//ps.setInt(6, 1);
			
			//Menu id
			if(newBooking.hasCateringMenu())
				ps.setInt(6, newBooking.getCatering().getId());
			
			
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	
}
