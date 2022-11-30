package database;

import model.EventType;

public interface EventTypeDBIF {
	
	EventType findEvent(String eventType);

}
