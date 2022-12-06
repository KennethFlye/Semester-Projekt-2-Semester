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
	
	private EnumMenu enumMenu;
	private double price;
	
	public CateringMenu(EnumMenu enumMenu, double price) {
		this.price = price;
		this.enumMenu = enumMenu;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getId() {
		return enumMenu.getId();
	}
	
	public EnumMenu getEnumMenu() {
		return enumMenu;
	}

}
