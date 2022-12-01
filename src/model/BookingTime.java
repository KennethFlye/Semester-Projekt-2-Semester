package model;


import java.time.LocalDateTime;

import model.EventType.EnumType;

public class BookingTime {

	
	EventType eventType; 
	LocalDateTime startTime, finishTime; 
	
	

	public static void main(String[] args) {
		
		BookingTime bt = new BookingTime("Formel 1", LocalDateTime.now(), LocalDateTime.now());
		System.out.println(bt.eventType); 
	}
	
	public BookingTime(String type, LocalDateTime startTime, LocalDateTime finishTime) {
		this.eventType = new EventType(EnumType.valueOfLabel(type));
		this.finishTime=finishTime;
		this.startTime=startTime;
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
