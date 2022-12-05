package model;


import java.time.LocalDateTime;

public class BookingTime {

	
	EventType eventType; 
	LocalDateTime startTime, finishTime; 
	
	

	
	
	public BookingTime(EventType et, LocalDateTime startTime) {
		this.eventType = et;
		this.startTime=startTime;
		this.finishTime=startTime.plusMinutes(et.getEnumType().getLenght());
	}
		
		
	
	public BookingTime(int i, Object object) {
		// TODO Auto-generated constructor stub
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