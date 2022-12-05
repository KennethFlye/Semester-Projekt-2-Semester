package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Booking;

public class BookingDB implements BookingDBIF {

	private static final String INSERTBOOKING_Q_FOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId, menuId) VALUES (?, ?, ?, ?, ?, ?); SELECT IDENT_CURRENT( 'Booking' );";
	private PreparedStatement insertBookingPSFood;
	
	private static final String INSERTBOOKING_Q_NOFOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId) VALUES (?, ?, ?, ?, ?); SELECT IDENT_CURRENT( 'Booking' );";
	private PreparedStatement insertBookingPSNoFood;

	
	@Override
	public int insertBooking(Booking newBooking) throws DataAccessException {
		Connection connection;
		connection = DBConnection.getInstance().getConnection();
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
			
			//Start the transaction, sets autocommit to false
			DBConnection.getInstance().startTransaction();
			
			//Total price
			ps.setFloat(1, (float) newBooking.getTotal());
			
			//Creation date
			ps.setDate(2, Date.valueOf(newBooking.getCreationDate().toLocalDate()));
			
			//Amount of people
			ps.setInt(3, newBooking.getAmountOfPeople());
			
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
			
			//Commits the transaction and sets autocommit to true
			DBConnection.getInstance().commitTransaction();
			
			ps.execute();
			ps.getMoreResults();
			ResultSet rs = ps.getResultSet();
			rs.next();
			int currentId = rs.getInt(1);
			ps.close();
			return currentId;
			
		} catch (SQLException e) {
			try {
				//Undoes the changes that were tried to make and sets autocommit true
				DBConnection.getInstance().rollbackTransaction();
			}
			catch(SQLException ex) {
				throw new DataAccessException(ex, "Transaction cant be rolled back");
			}
			throw new DataAccessException(e, "Transaction couldnt be committed");
		}
				
	}
	
}

