package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

import model.*;
import model.CateringMenu.EnumMenu;

public class BookingDB implements BookingDBIF {

	private static final String INSERTBOOKING_Q_FOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId, menuId) VALUES (?, ?, ?, ?, ?, ?); SELECT IDENT_CURRENT( 'Booking' );";
	private PreparedStatement insertBookingPSFood;
	
	private static final String INSERTBOOKING_Q_NOFOOD = "INSERT INTO Booking (totalPrice, creationDate, amountOfPeople, isPaid, customerId) VALUES (?, ?, ?, ?, ?); SELECT IDENT_CURRENT( 'Booking' );";
	private PreparedStatement insertBookingPSNoFood;
	
	private static final String GETBOOKINGIDSBYDATEQ = "SELECT bookingId FROM BookingTime WHERE BookingTime.startTime LIKE '?%'";
	private PreparedStatement getBookingIdsByDate;

	private static final String GETBOOKINGBYIDQ = "SELECT * FROM Booking WHERE bookingId = ?";
	private PreparedStatement getBookingById;
	
	private static final String GETALLCUSTOMERINFOQ = "SELECT * FROM Contact, Customer WHERE customerId = ? AND contactId = customerId";
	private PreparedStatement getAllCustomerInfo;
	
	private static final String GETZIPCODECITYQ = "SELECT zipcodeCity FROM ZipcodeCity WHERE zipcode = ?";
	private PreparedStatement getZipcodeCity;

	private static final String GETALLCATERINGINFOQ = "SELECT * FROM CateringMenu WHERE menuId = ?";
	private PreparedStatement getAllCateringInfo;
	
	private static final String UPDATEBOOKINGQ = "UPDATE Booking SET totalPrice = ?, creationDate = ?, amountOfPeople = ?, isPaid = ?, customerId = ?, employeeId = ?, menuId = ? WHERE Booking.bookingId = ?";
    private PreparedStatement updateBooking;
	

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
	
	@Override
	public List<Booking> findBookingByDate(LocalDate date) throws DataAccessException{
		Connection connection;
		connection = getDBConnection().getConnection();
		try {
			getBookingById = connection.prepareStatement(GETBOOKINGBYIDQ);
			getBookingIdsByDate = connection.prepareStatement(GETBOOKINGIDSBYDATEQ);
			getAllCustomerInfo = connection.prepareStatement(GETALLCUSTOMERINFOQ);
			getZipcodeCity = connection.prepareStatement(GETZIPCODECITYQ);
			getAllCateringInfo = connection.prepareStatement(GETALLCATERINGINFOQ);


		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			List<Booking> bookings = new ArrayList<>();
			
			getBookingIdsByDate.setDate(1, Date.valueOf(date));
			ResultSet rs = getBookingIdsByDate.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				
				getBookingById.setInt(1, id);
				ResultSet rs2 = getBookingById.executeQuery();
				rs2.next();
				
				int bookingId = rs2.getInt("bookingId");
				double totalPrice = rs2.getDouble("totalPrice");
				LocalDateTime creationDate = LocalDateTime.parse((rs2.getString("creationDate")));
				int amountOfPeople = rs2.getInt("amountOfPeople");
				int customerId = rs2.getInt("customerId");
				int menuId = rs2.getInt("menuId");
				
				getAllCustomerInfo.setInt(1, customerId);
				ResultSet rsC = getAllCustomerInfo.executeQuery();
				rsC.next();
				String name = rsC.getString("firstName") + " " + rsC.getString("lastName");
				
				int zipCode = rsC.getInt("zipCode");
				getZipcodeCity.setInt(1, zipCode);
				ResultSet rsZC = getZipcodeCity.executeQuery();
				rsZC.next();
				
				Customer customer = new Customer(customerId, name, rsC.getString("phone"), rsC.getString("email"), rsC.getString("address"), zipCode, rsZC.getString("zipcodeCity"), rsC.getString("country"), LocalDate.parse((rsC.getString("dateOFBirth"))));

				getAllCateringInfo.setInt(1, menuId);
				ResultSet rsCI = getAllCateringInfo.executeQuery();
				String label = rsCI.getString("foodName");
				EnumMenu enumMenu = EnumMenu.valueOfLabel(label);
				
				CateringMenu cateringMenu = new CateringMenu(enumMenu, rsCI.getDouble("price"));
				
				Booking booking = new Booking(customer, cateringMenu, amountOfPeople, bookingId, totalPrice, creationDate);
			    if(!bookings.contains(booking)) {
			    	bookings.add(booking);
			    	
			    }
				
		      }
			return bookings;
		}
		catch (SQLException e) {
			throw new DataAccessException(e, "Could not execute");
		}
					
	}

	@Override
	public boolean updateBooking(Booking booking) throws DataAccessException {
		Connection connection;
		connection = getDBConnection().getConnection();
		try {
			//UPDATE Booking SET totalPrice = ?, creationDate = ?, amountOfPeople = ?, isPaid = ?, customerId = ?, menuId = ? WHERE Booking.bookingId = ?
			updateBooking = connection.prepareStatement(UPDATEBOOKINGQ);
	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			updateBooking.setDouble(1, booking.getTotal());
			updateBooking.setDate(2, Date.valueOf(booking.getCreationDate().toLocalDate()));
			updateBooking.setInt(3, booking.getAmountOfPeople());
			updateBooking.setBoolean(4, booking.isPaid());
			updateBooking.setInt(5, booking.getCustomer().getContactId());
			updateBooking.setInt(6, booking.getCatering().getId());
			updateBooking.setInt(7, booking.getBookingId());

			updateBooking.executeUpdate();

		}
		catch (SQLException e) {
			throw new DataAccessException(e, "Error during update");
		}
		
		return true;
	}
	
}