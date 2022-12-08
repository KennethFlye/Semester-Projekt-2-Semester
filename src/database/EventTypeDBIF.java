package database;

import model.EventType;
import model.EventType.EnumType;

public interface EventTypeDBIF {
	
	public EventType findEvent(EnumType eventType) throws DataAccessException;
}
