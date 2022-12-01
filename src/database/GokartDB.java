package database;

import java.sql.*;
import java.time.LocalDateTime;

public class GokartDB implements GokartDBIF {
	
	private static final String getAllGokartsQ = "SELECT * FROM Gokart";
	private static final String getInfoForRentedGokartQ = "SELECT * FROM RentOutGokart WHERE gokartNumber = ?";
	private static final String getTimeForRentOutQ = "SELECT startTime, endTime FROM RentOut WHERE rentID = ?";
	
	private PreparedStatement getAllGokarts;
	public PreparedStatement getInfoForRentedGokart;
	public PreparedStatement getTimeForRentOut;
	
	public GokartDB() throws SQLException {
		getAllGokarts = DBConnection.getInstance().getConnection().prepareStatement(getAllGokartsQ);
		getInfoForRentedGokart = DBConnection.getInstance().getConnection().prepareStatement(getInfoForRentedGokartQ);
		getTimeForRentOut = DBConnection.getInstance().getConnection().prepareStatement(getTimeForRentOutQ);
	}

	
	/**
	 * Checks whether the required amount of gokarts are available for a booking. A gokart is available if it is not
	 * rented out at the time of the booking. The method works by finding each gokart's rentouts, and checking whether 
	 * any rentouts overlap the booking.
	 * @return true if enough gokarts are available, false otherwise
	 * @param amount gokarts required
	 * @param start start date and time for booking 
	 * @param end date and time for booking
	 */
	@Override
	public boolean checkGokarts(int amount, LocalDateTime start, LocalDateTime end) throws SQLException {
		int available = 0;
		
		ResultSet rsGetAll = getAllGokarts.executeQuery();
		
		while (rsGetAll.next() && available < amount) {
			int kartId = rsGetAll.getInt("gokartNumber");
			
			getInfoForRentedGokart.setInt(1, kartId);
			
			ResultSet rsRentedInfo = getInfoForRentedGokart.executeQuery();
			
			boolean rented = false;
			while (rsRentedInfo.next() && !rented) {
				int rentId = rsRentedInfo.getInt("rentId");
				
				getTimeForRentOut.setInt(1, rentId);
				ResultSet rsTimes = getTimeForRentOut.executeQuery();
				
				rsTimes.next();
				String rentOutStart = rsTimes.getString("startTime");
				String rentOutEnd = rsTimes.getString("endTime");
				String checkStart = start.toString();
				String checkEnd = end.toString();
				
				if (rentOutStart.compareTo(checkStart) >= 0 && rentOutStart.compareTo(checkEnd) <= 0) {
					rented = true;
				}
				else if(rentOutEnd.compareTo(checkStart) >= 0 &&  rentOutEnd.compareTo(checkEnd) <= 0) {
					rented = true;
				}
				else if(rentOutStart.compareTo(checkStart) <= 0 && rentOutEnd.compareTo(checkEnd) >= 0) {
					rented = true;
				}
				
			}
			if (!rented) {
				available++;
			}
			
			
		}
		return (available >= amount);
		
	}
	
	/**
	 * public static void main(String[] args) {
		try{ 
		getTimeForRentOut = DBConnection.getInstance().getConnection().prepareStatement(getTimeForRentOutQ);
		getTimeForRentOut.setInt(1, 1);
		ResultSet rsTimes = getTimeForRentOut.executeQuery();

		rsTimes.next();
		System.out.println(rsTimes.getString("startTime"));
		
	}
		catch(Exception e) {
			
		}
	}
	 */
	

}
