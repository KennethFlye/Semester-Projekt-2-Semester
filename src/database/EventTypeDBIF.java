package database;

import model.EventType;
import model.EventType.EnumType;

public interface EventTypeDBIF {
	
	EventType findEvent(EnumType eventType) throws DataAccessException;

}
