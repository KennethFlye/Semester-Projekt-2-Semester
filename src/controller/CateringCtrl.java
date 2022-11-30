package controller;

import java.sql.SQLException;

import database.CateringMenuDB;
import model.CateringMenu;

public class CateringCtrl {
	
	private CateringMenuDB cateringMenuDB;
	
	public CateringCtrl() throws SQLException {
		cateringMenuDB = new CateringMenuDB();
	}
	
	
	public CateringMenu findCatering(int cateringId) throws SQLException {
		
		return cateringMenuDB.findCatering(cateringId);
		
	}
	
	

}
