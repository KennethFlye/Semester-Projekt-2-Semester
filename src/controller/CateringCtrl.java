package controller;

import database.CateringMenuDB;
import database.DataAccessException;
import model.CateringMenu;

public class CateringCtrl {
	
	private CateringMenuDB cateringMenuDB;
	
	public CateringCtrl() throws DataAccessException {
		cateringMenuDB = new CateringMenuDB();
	}
	
	
	public CateringMenu findCatering(int cateringId) throws DataAccessException {
		
		return cateringMenuDB.findCatering(cateringId);
		
	}
	
	

}
