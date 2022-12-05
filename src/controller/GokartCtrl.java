package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;

import database.DataAccessException;
import database.GokartDB;
import database.GokartDBIF;

public class GokartCtrl {

	private GokartDBIF gokartDB;
	
	public GokartCtrl() throws DataAccessException{
		try {
			gokartDB = new GokartDB();
		}
		catch(SQLException e) {
			throw new DataAccessException(e, "Could Not Prepare Statements");
		}
		
	}
	public boolean hasEnoughAvailableGokarts(int amount, LocalDateTime start, LocalDateTime end) throws DataAccessException {
		
		try {
			return gokartDB.hasEnoughAvailableGokarts(amount, start, end);
			
		}
		catch(Exception e) {
			throw new DataAccessException(e, "Could Not Check Gokarts");
			
		}		
		
	}
	public int getAvailableGokarts(LocalDateTime start, LocalDateTime end) throws DataAccessException {
		try {
			return gokartDB.getAvailableGokarts(start, end);
			
		}
		catch(Exception e) {
			throw new DataAccessException(e, "Could Not Get Gokarts");
			
		}	
	}

}
