package locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Item;
import model.ItemID;
import model.Player;

public class Shop extends Location {
	
	private ArrayList<Item> items;
	
	private Item charm;
	private Item talisman;
	
	public Shop(String name, String presentation, HashMap<String, Integer> exitPoints, LocationID id) {
		super(name, presentation, exitPoints, id);
		
		items = new ArrayList<Item>();
		items.add(new Item("Charm of Capitalism", "buy charm", 20, ItemID.charm));
		items.add(new Item("Talisman of Truth", "buy talisman", 100, ItemID.talisman));
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void checkShopInventory() {
		System.out.println("The shopkeeper has the following items in stock:");
		for (int i = 0; i < items.size(); i++) {
			System.out.println("- " + items.get(i).getName() + " (" + items.get(i).getPrice() + ")");
		}
	}
	
	public void buyItem(String itemName, Player player) {
		
		for (int i = items.size()-1; i >= 0; i--) {
			if (items.get(i).getBuyCommand().equals(itemName)) {
				Item chosenItem = items.get(i);
				
				if (player.getGold() >= chosenItem.getPrice()) {
					player.increaseGold(-chosenItem.getPrice());
					System.out.println("You have bought the " + chosenItem.getName() + " for " + chosenItem.getPrice() + ".");
					player.addItem(chosenItem);
					items.remove(i);
				} else
					System.out.println("Sorry, the " + chosenItem.getName() + " costs " + chosenItem.getPrice() + ". You only have " + player.getGold() + " gold.");
			}
		}
	}
}
