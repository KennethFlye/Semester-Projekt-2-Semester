package controller;

import database.CateringMenuDB;
import database.DataAccessException;
import model.CateringMenu;

public class CateringCtrl {
	
	private CateringMenuDB cateringMenuDB;
	
	public CateringCtrl() throws DataAccessException {
		cateringMenuDB = new CateringMenuDB();
	}
	
	
	public CateringMenu findCateringMenu(int cmId) throws DataAccessException {
		return cateringMenuDB.findCateringMenu(cmId);
		
	}
	
	

}
