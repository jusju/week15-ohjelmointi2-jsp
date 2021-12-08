package model;

public class ShoppingListItem {
	private int id;
	private String ostos;

	public ShoppingListItem() {
		super();
	}

	public ShoppingListItem(int id, String ostos) {
		super();
		this.id = id;
		this.ostos = ostos;
	}

	public ShoppingListItem(String ostos) {
		super();
		this.ostos = ostos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOstos() {
		return ostos;
	}

	public void setOstos(String ostos) {
		this.ostos = ostos;
	}

	@Override
	public String toString() {
		return "ShoppingListItem [id=" + id + ", ostos=" + ostos + "]";
	}
}
