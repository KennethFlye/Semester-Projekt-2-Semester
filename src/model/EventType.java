package model;

public class EventType {
	
	public enum EnumType{
		FORMULA_1("Formel 1"),
		LARGE_FORMULA_1("Stor Formel 1"),
		LE_MANS_1_HOUR("LeMans 1 Time"),
		EVENT_HALL_1_HOUR("EventHal 1 Time");
		
		public final String label;
		
		private EnumType(String label) {
			this.label = label;
		}
		
		public static EnumType valueOfLabel(String label) {
			
			for(EnumType element: values()) {
				if(element.label.equals(label)) {
					return element;
				}
			}
			
			return null;
		}
		
		public String getLabel() {
			return label;
		}
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
	public EnumType getEnumType() {
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
