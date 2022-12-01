package model;

public class CateringMenu {
	
	public enum EnumMenu{
		
		CHICKEN("Kylling og Bacon Sandwich"),
		EGGS("Æg og Rejer Sandwich"),
		FRIKADEL("Frikadelle Sandwich");
		
		private final String label;
		
		
		private EnumMenu(String label) {
			this.label = label;
		}
		
		public static EnumMenu valueOfLabel(String label) {
			
			for(EnumMenu element: values()) {
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
	
	private String name;
	private double price;
	private int id;
	
	public CateringMenu(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
