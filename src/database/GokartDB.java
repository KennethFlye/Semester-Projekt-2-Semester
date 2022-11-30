package database;

import java.sql.*;
import java.time.LocalDateTime;

public class GokartDB implements GokartDBIF {
	
	private static final String getAllGokartsQ = "SELECT * FROM Gokart";
	private static final String getInfoForRentedGokartQ = "SELECT * FROM RentOutGokart WHERE gokartNumber = ?";
	private static final String getTimeForRentOutQ = "SELECT startTime, endTime FROM RentOut WHERE rentID = ?";
	
	private PreparedStatement getAllGokarts;
	public PreparedStatement getInfoForRentedGokart;
	public static PreparedStatement getTimeForRentOut;
	
	public GokartDB() throws SQLException {
		getAllGokarts = DBConnection.getInstance().getConnection().prepareStatement(getAllGokartsQ);
		getInfoForRentedGokart = DBConnection.getInstance().getConnection().prepareStatement(getInfoForRentedGokartQ);
		getTimeForRentOut = DBConnection.getInstance().getConnection().prepareStatement(getTimeForRentOutQ);
	}

	
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
				
				//String rentOutStart = rsTimes.getString
				
				
			}
			
			
		}
		
		return true;
	}
	
	public static void main(String[] args) {
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

}
