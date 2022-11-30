package database;

import java.sql.SQLException;

import model.CateringMenu;

public interface CateringMenuDBIF {
	
	public CateringMenu findCatering(int menuId) throws DataAccessException;
	

}
