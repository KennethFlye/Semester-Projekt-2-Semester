package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import database.GokartDB;
import database.GokartDBIF;

public class GokartCtrl {

	private GokartDBIF gokartDB;
	
	public GokartCtrl() throws SQLException {
		gokartDB = new GokartDB();
	}
	public void checkGokarts(int amount, LocalDateTime start, LocalDateTime end) throws SQLException {
		
		gokartDB.checkGokarts(amount, start, end);
		
	}

}
