package model;

public class CateringMenu {
	
	public enum EnumMenu{
		
		CHICKEN("Kylling og Bacon Sandwich", 1),
		EGGS("Æg og Rejer Sandwich", 2),
		FRIKADEL("Frikadelle Sandwich", 3);
		
		private final String label;
		private final int id;
		
		
		private EnumMenu(String label, int id) {
			this.label = label;
			this.id = id;
		}
		
		public static EnumMenu valueOfLabel(String label) {
			
			for(EnumMenu element: values()) {
				if(element.label.equals(label)) {
					return element;
				}
			}
			
			return null;
		}
		
		public int getId() {
			return id;
		}
		
		public String getLabel() {
			return label;
		}
	}
	
	private String name;
	private double price;
	private int id;
	
	public CateringMenu(String name, double price, int id) {
		this.name = name;
		this.price = price;
		this.id = id;
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
