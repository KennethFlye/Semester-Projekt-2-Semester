package model;

public class EventType {
	
	public enum EnumType{
		FORMULA_1("Formel 1", 30),
		LARGE_FORMULA_1("Stor Formel 1", 60),
		LE_MANS_1_HOUR("LeMans 1 Time", 60),
		EVENT_HALL_1_HOUR("Eventhal 1 Time", 60),
		EVENT_HALL_1_AND_HALF_HOUR("Eventhal 1.5 Time", 90),
		EVENT_HALL_2_HOURS("Eventhal 2 Timer", 120);
		
		
		public final String label;
		public final long lenght;
		
		private EnumType(String label, long lenght) {
			this.label = label;
			this.lenght = lenght;
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
		
		public long getLenght() {
			return lenght;
		}
	}
	
	private EnumType eventType;
	private double price;
	
	public EventType(EnumType typeOfEvent, double price) {
		
		eventType = typeOfEvent;
		this.price = price;
		
	}

	public EventType(EnumType valueOfLabel) {
		eventType = valueOfLabel;
		price = 0;
	}

	/**
	 * @return the eventType
	 */
	public EnumType getEnumType() {
		return eventType; //returns an enumtype
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
