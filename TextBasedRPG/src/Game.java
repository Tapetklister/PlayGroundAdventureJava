import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import handlers.Handler;
import locations.Location;
import locations.LocationID;
import locations.Shop;
import model.Item;
import model.ItemID;
import locations.Plains;

public class Game {

	private Handler handler;
	private boolean gameOver;
	private Scanner scanner;
	private Location currentLocation;
	private List<String> directionList = new ArrayList<String>();
	
	public Game() {
		
		init();
		
		while (!gameOver) {																								// Main game loop
			
			String nextMove = scanner.nextLine();
			
			if (directionList.contains(nextMove))
				move(nextMove);
			else if (nextMove.equals("status"))
				checkStatus();
			else if (currentLocation.getID().equals(LocationID.shop)) {
				Shop shop = (Shop) handler.getLocation(LocationID.shop);
				if (nextMove.equals("look inventory")) {
					shop.checkShopInventory();
				} else
					shop.buyItem(nextMove, handler.player);
			}
			else if (currentLocation.getID() == LocationID.plains && nextMove.equals("kill monster")) {
				Plains plains = (Plains) handler.getLocation(LocationID.plains);
				plains.killMonster(handler.player);
			}
			else
				System.out.println("Unknown command");
			
			if (handler.player.getHP() <= 0) gameOver();
		}
	}
	
	private void init() {																// Initial setup
		
		directionList.add("east");
		directionList.add("west");
		directionList.add("north");
		directionList.add("south");
		
		handler = new Handler();
		gameOver = false;
		scanner = new Scanner(System.in);
		
		handler.setupLocations();
		
		currentLocation = handler.getLocation(LocationID.crossroads);
		System.out.println(currentLocation.getPresentation());
	}
	
	private void move(String direction) {												// Loads a different location
		
		currentLocation = handler.goToLocation(currentLocation.getID(), direction);
		System.out.println(currentLocation.getPresentation());
		
		if (currentLocation.getID() == LocationID.pool)
			handler.player.restoreHP();
		
		if (currentLocation.getID() == LocationID.cave) {
			enterCave();
		}
	}
	
	private void checkStatus() {														// Displays information about the player; HP, Gold and Inventory
		
		List<Item> items = new ArrayList<Item>();
		if (handler.player.getItems().size() > 0) {
			items = handler.player.getItems();
		} else {
			items.add(new Item("none", "", 0, null));
		}
		
		String statusString = "HP: " + handler.player.getHP() + "/100, Gold: " + handler.player.getGold() + ", Items: ";
		String commaString = "";
		
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null) {
				statusString += commaString;
				statusString += items.get(i).getName();
			} else {
				System.out.println("Item == null");
			}
			commaString = ", ";
		}
		System.out.println(statusString);
	}
	
	private void enterCave() {
		
		for (int i = 0; i < handler.player.getItems().size(); i++) {
			if (handler.player.getItems().get(i).getID() == ItemID.talisman) {
				System.out.println("Youre'immediately greeted by the Twilight Dragon, but with a mighty snap of you mighty fingers, you bring it to the ground.");
				System.out.println("You are victorious!");
				gameOver();
			}
		}
		System.out.println("You stumble over a stray skateboard and fall to your death.");
		gameOver();
	}
	
	private void gameOver() {
		
		System.out.println("----- Game Over -----");
		scanner.nextLine();
		System.exit(-1);
	}
}
