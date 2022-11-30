package database;

import java.sql.PreparedStatement;

import model.EventType;

public class EventTypeDB implements EventTypeDBIF{

	private static final String findEventQ = "SELECT EventType.type, EventType.price FROM EventType";
	private static final String findByTypeQ = findEventQ + " WHERE EventType.type = '?'";
	
	private PreparedStatement findEvent, findByType;
	
	public EventTypeDB() throws DataAccessException {
		
	}
	
	@Override
	public EventType findEvent(String eventType) {
		
		
		
		return null;
	}
	
	

}
