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
	public void checkGokarts(int amount, LocalDateTime start, LocalDateTime end) throws DataAccessException {
		
		try {
			gokartDB.checkGokarts(amount, start, end);
			
		}
		catch(Exception e) {
			throw new DataAccessException(e, "Could Not Check Gokarts");
			
		}
		
		
	}

}
