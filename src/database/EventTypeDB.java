package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EventType;
import model.EventType.EnumType;

public class EventTypeDB implements EventTypeDBIF{

	private static final String findAllQ = "SELECT EventType.type, EventType.price FROM EventType";
	private static final String findByTypeQ = findAllQ + " WHERE EventType.type = '?'";
	private static final String findPriceByTypeQ = "SELECT EventType.price FROM EventType WHERE EventType.type = ?";
	
	private PreparedStatement findAll, findByType, findPriceByType;
	
	public EventTypeDB() throws DataAccessException {
		
		try {
			
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findByType = DBConnection.getInstance().getConnection().prepareStatement(findByTypeQ);
			findPriceByType = DBConnection.getInstance().getConnection().prepareStatement(findPriceByTypeQ);
			
		} catch (SQLException e) {
			
			throw new DataAccessException(e, "Could not prepare statement");
			
		}
		
	}
	
	@Override
	public EventType findEvent(EnumType type) throws DataAccessException {
		
		ResultSet rs;
		EventType actualEventType = null;
		
		try {
			
			findByType.setString(1, type.getLabel());
			rs = findByType.executeQuery();
			
			while(rs.next()) {
				actualEventType = new EventType(type, rs.getInt("price"));
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not retrieve EventType");
		}
		
		
		return actualEventType;
	}


}
