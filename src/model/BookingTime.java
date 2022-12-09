package model;


import java.time.LocalDateTime;

public class BookingTime {

	
	private EventType eventType; 
	private LocalDateTime startTime, finishTime; 
	
	

	
	
	public BookingTime(EventType et, LocalDateTime startTime, int groups) {
		this.eventType = et;
		this.startTime=startTime;
		this.finishTime=startTime.plusMinutes((et.getEnumType().getLength()*groups));
	}
	
	public BookingTime(EventType et, LocalDateTime startTime, LocalDateTime finishTime) {
		this.eventType = et;
		this.startTime = startTime;
		this.finishTime = finishTime;
				
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