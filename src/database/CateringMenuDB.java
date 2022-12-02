package database;

import java.sql.*;

import model.CateringMenu;

public class CateringMenuDB implements CateringMenuDBIF {
	
	private static final String getCateringInfoQ = "SELECT * FROM CateringMenu WHERE menuId = ?";
	
	private PreparedStatement getCateringInfo;
	
	
	public CateringMenuDB() throws DataAccessException {
		try {
			getCateringInfo = DBConnection.getInstance().getConnection().prepareStatement(getCateringInfoQ);
		}
		catch(SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}

	/**
	 * Finds a catering menu using it's ID, if such a catering menu exists in the database.
	 * Creates a new catering menu object using the found catering menu's information, and returns this object.
	 * @return a new CateringMenu object, with the same values as the found catering menu
	 * @param cmId the id of the catering menu we are looking for
	 */
	
	public CateringMenu findCateringMenu(int cmId) throws DataAccessException {
		ResultSet rs;
		CateringMenu foundCateringMenu = null;
		try {
			String name = "";
			double price = 0;
			
			getCateringInfo.setInt(1, cmId);
			rs = getCateringInfo.executeQuery();
			
			while(rs.next()) {
				name = rs.getString("foodName");
				price = rs.getDouble("price");
			}
			
			
			foundCateringMenu = new CateringMenu(name, price, cmId);
		}
		catch(SQLException e) {
			throw new DataAccessException(e, "Could not retrieve cateringMenu");
		}
		return foundCateringMenu;
		
	}


}
