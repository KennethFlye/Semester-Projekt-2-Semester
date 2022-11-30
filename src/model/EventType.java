package model;

public class EventType {
	
	enum EnumType{
		FORMULA_1,
		LARGE_FORMULA_1,
		LE_MANS_1_HOUR,
		EVENT_HALL_1_HOUR
	}
	
	private EnumType eventType;
	private double price;
	
	public EventType(EnumType typeOfEvent, double price) {
		
		eventType = typeOfEvent;
		this.price = price;
		
	}

	/**
	 * @return the eventType
	 */
	public EnumType getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(EnumType eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
