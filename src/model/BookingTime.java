package model;

import java.time.LocalDateTime;

public class BookingTime {

	LocalDateTime startTime, finishTime;
	
	public BookingTime(LocalDateTime startTime, LocalDateTime finishTime) {
		this.finishTime=finishTime;
		this.startTime=startTime;
	}
	
}
