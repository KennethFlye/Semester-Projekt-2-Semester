package database;

import model.CateringMenu;

public interface CateringMenuDBIF {
	
	public CateringMenu findCatering(int menuId) throws DataAccessException;
	

}
