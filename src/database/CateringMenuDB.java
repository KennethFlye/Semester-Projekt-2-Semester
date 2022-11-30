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

	
	public CateringMenu findCateringMenu(int cmId) throws DataAccessException {
		ResultSet rs;
		CateringMenu foundCateringMenu = null;
		try {
			String name = "";
			double price = 0;
			
			getCateringInfo.setInt(1, cmId);
			rs = getCateringInfo.executeQuery();
			name = rs.getString("foodName");
			price = rs.getDouble("price");
			foundCateringMenu = new CateringMenu(name, price);
		}
		catch(SQLException e) {
			throw new DataAccessException(e, "Could not retrieve cateringMenu");
		}
		return foundCateringMenu;
		
	}


}
