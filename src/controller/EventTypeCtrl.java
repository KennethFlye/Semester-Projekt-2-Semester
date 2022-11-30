package controller;

import database.DataAccessException;
import database.EventTypeDB;
import database.EventTypeDBIF;
import model.EventType;
import model.EventType.EnumType;

public class EventTypeCtrl {
	
	private EventTypeDBIF eventTypeDB;
	
	public EventTypeCtrl() throws DataAccessException {
		
		eventTypeDB = new EventTypeDB();
		
	}
	
	public EventType findEvent(EnumType type) throws DataAccessException {
		return eventTypeDB.findEvent(type);
	}

}
