package model.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Player;
import model.item.Item;
import model.item.ItemID;

public class Shop extends Location {
	
	private ArrayList<Item> items;
	
	public Shop(String name, String presentation, HashMap<String, Integer> exitPoints, LocationID id) {
		super(name, presentation, exitPoints, id);
		
		items = new ArrayList<Item>();
		items.add(new Item("Charm of Capitalism", "buy charm", 20, ItemID.charm));
		items.add(new Item("Talisman of Truth", "buy talisman", 100, ItemID.talisman));
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void checkShopInventory() {																			// Lists the shop's available wares
		System.out.println("The shopkeeper has the following items in stock:");
		for (int i = 0; i < items.size(); i++) {
			System.out.println("- " + items.get(i).getName() + " (" + items.get(i).getPrice() + ")");
		}
	}
	
	public void buyItem(String itemName, Player player) {														// Lets the player try to buy an item
		
		for (int i = items.size()-1; i >= 0; i--) {
			if (items.get(i).getBuyCommand().equals(itemName)) {
				Item chosenItem = items.get(i);
				
				if (player.getGold() >= chosenItem.getPrice()) {												// If the player has enough gold, the item is removed from the shop's inventory and added to the player's inventory.
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
