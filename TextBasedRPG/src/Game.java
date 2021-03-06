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
		
		init();		// Initial setup
		
		while (!gameOver) {																								// Main game loop
			
			String nextMove = scanner.nextLine();																		// Input command
			handleInput(nextMove);																						// Performs action based on input, like moving or checking status
			
			if (handler.player.getHP() <= 0) gameOver();																// If the player has been killed, the game is over
		}
	}
	
	private void init() {																								// Initial setup
		
		directionList.add("east");
		directionList.add("west");
		directionList.add("north");
		directionList.add("south");
		
		System.out.println("-----Welcome to Playground Adventure-----\n");
		System.out.println("Enter 'commands' to display the available commands\n");
		
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
	
	void handleInput(String nextMove) {																					//Performs action based on input
		
		if (directionList.contains(nextMove))
			move(nextMove);
		else if (nextMove.equals("status"))																				// Checks player status
			checkStatus();
		else if (nextMove.equals("commands"))
			displayCommands();
		else if (currentLocation.getID().equals(LocationID.shop)) {														// Enters shop, and enables commands specific to the shop.
			Shop shop = (Shop) handler.getLocation(LocationID.shop);
			if (nextMove.equals("look inventory")) {
				shop.checkShopInventory();
			} else
				shop.buyItem(nextMove, handler.player);
		}
		else if (currentLocation.getID() == LocationID.plains && nextMove.equals("kill monster")) {						// If the player is on the plains, he/she can kill a monster
			Plains plains = (Plains) handler.getLocation(LocationID.plains);
			plains.killMonster(handler.player);
		}
		else
			System.out.println("Unknown command");																		// Tells the player that the input is not valid
	}
	
	private void checkStatus() {																						// Displays information about the player; HP, Gold and Inventory
		
		String statusString = "HP: " + handler.player.getHP() + "/100, Gold: " + handler.player.getGold() + ", Items: ";
		List<Item> items = new ArrayList<Item>();
		
		if (handler.player.getItems().size() > 0) {																		// If the player has items in his/her inventory, then the items are added to the status information.
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
	
	private void displayCommands() {
		
		System.out.println("Anywhere:\n"
				+ "		Move east: 'east'\n"
				+ "		Move west: 'west'\n"
				+ "		Move north: 'north'\n"
				+ "		Move south: 'south'\n"
				+ "		Check status: 'status'\n");
		System.out.println("On the Plains of Grinding:\n"
				+ "		Kill monster: 'kill monster'\n");
		System.out.println("At the Hero's Shop:\n"
				+ "		Browse: 'look inventory'\n"
				+ "		Buy Charm of Capitalism: 'buy charm'\n"
				+ "		Buy Talisman of Truth: 'buy talisman'\n");
	}
	
	private void checkGameOver() {			// Loops through the Player's inventory. If the Talisman of Truth is found there, the player wins the game. If not, it's a loss.
		
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
	
	private void gameOver() {																								// Informs the player that the game is over and closes it.
		
		System.out.println("----- Game Over -----");
		scanner.nextLine();
		System.exit(-1);
	}
}
