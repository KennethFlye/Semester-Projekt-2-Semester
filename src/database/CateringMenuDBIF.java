package database;

import model.CateringMenu;

public interface CateringMenuDBIF {
	
	public CateringMenu findCateringMenu(int menuId) throws DataAccessException;
	

}
