package model;


import java.time.LocalDateTime;

public class BookingTime {

	
	EventType eventType; 
	LocalDateTime startTime, finishTime; 
	
	

	
	
	public BookingTime(EventType et, LocalDateTime startTime, LocalDateTime finishTime) {
		this.eventType = et;
		this.finishTime=finishTime;
		this.startTime=startTime;
		this.finishTime=startTime.plusMinutes(et.getEnumType().getLenght());
	}
		
		
	
	public EventType getEventType() {
		return eventType;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getFinishTime() {
		return finishTime;
		}
	
	
	
}