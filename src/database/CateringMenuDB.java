package database;

import java.sql.*;

import model.CateringMenu;

public class CateringMenuDB implements CateringMenuDBIF {
	
	private static final String getCateringInfoQ = "SELECT * FROM CateringMenu WHERE menuId = ?";

	
	private PreparedStatement getCateringInfo;
	
	public CateringMenuDB() throws SQLException {
		getCateringInfo = DBConnection.getInstance().getConnection().prepareStatement(getCateringInfoQ);
	}

	
	public CateringMenu findCatering(int menuId) throws SQLException {
		String name = "";
		double price = 0;
		
		getCateringInfo.setInt(1, menuId);
		ResultSet rs = getCateringInfo.executeQuery();
		name = rs.getString("foodName");
		price = rs.getDouble("price");
		
		return new CateringMenu(name, price);
		
		
	}


}
