package model.item;

public class Item {

	private String name;
	private String buyCommand;
	private int price;
	private ItemID id;
	
	public Item(String name, String buyCommand, int price, ItemID id) {
		this.name = name;
		this.buyCommand = buyCommand;
		this.price = price;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBuyCommand() {
		return buyCommand;
	}
	
	public int getPrice() {
		return price;
	}
	
	public ItemID getID() {
		return id;
	}
}
