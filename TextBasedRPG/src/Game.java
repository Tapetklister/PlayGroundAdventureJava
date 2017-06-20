import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import handlers.Handler;
import model.item.Item;
import model.item.ItemID;
import model.locations.*;

public class Game {

	private Handler handler = new Handler();
	private boolean gameOver = false;
	private Scanner scanner = new Scanner(System.in);
	private Location currentLocation;
	private List<String> directionList = new ArrayList<String>();
	
	public Game() {
		
		init();
		
		while (!gameOver) {																								// Main game loop
			
			String nextMove = scanner.nextLine();																		// Input command
			
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
	
	private void init() {																								// Initial setup
		
		directionList.add("east");
		directionList.add("west");
		directionList.add("north");
		directionList.add("south");
		
		currentLocation = handler.getLocation(LocationID.crossroads);
		System.out.println(currentLocation.getPresentation());
	}
	
	private void move(String direction) {																				// Loads a different location
		
		currentLocation = handler.goToLocation(currentLocation.getID(), direction);
		System.out.println(currentLocation.getPresentation());
		
		if (currentLocation.getID() == LocationID.pool)
			handler.player.restoreHP();
		
		if (currentLocation.getID() == LocationID.cave) {
			checkGameOver();
		}
	}
	
	private void checkStatus() {														// Displays information about the player; HP, Gold and Inventory
		
		String statusString = "HP: " + handler.player.getHP() + "/100, Gold: " + handler.player.getGold() + ", Items: ";
		List<Item> items = new ArrayList<Item>();
		
		if (handler.player.getItems().size() > 0) {
			items = handler.player.getItems();
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
		} else {
			statusString += "none";
		}
		System.out.println(statusString);
	}
	
	private void checkGameOver() {												// Loops through the Player's inventory. If the Talisman of Truth is found there, the player wins the game. If not, it's a loss.
		
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
