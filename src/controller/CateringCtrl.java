package controller;

import database.CateringMenuDB;
import database.CateringMenuDBIF;
import database.DataAccessException;
import model.CateringMenu;

public class CateringCtrl {
	
	private CateringMenuDBIF cateringMenuDBIF;
	
	public CateringCtrl() throws DataAccessException {
		cateringMenuDBIF = new CateringMenuDB();
	}
	
	public CateringMenu findCateringMenu(int cmId) throws DataAccessException {
		return cateringMenuDBIF.findCateringMenu(cmId);	
	}

}
