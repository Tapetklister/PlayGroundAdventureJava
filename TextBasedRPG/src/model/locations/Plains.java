package model.locations;

import java.util.HashMap;
import java.util.Random;

import model.Player;
import model.item.ItemID;

public class Plains extends Location {

	public Plains(String name, String presentation, HashMap<String, Integer> exitPoints, LocationID id) {
		super(name, presentation, exitPoints, id);
	}
	
	public void killMonster(Player player) {
		Random random = new Random();
		int damageTaken = random.nextInt(20)+1;
		player.takeDamage(damageTaken);
		
		if (player.getHP() > 0) {
			
			int goldRate = 1;
			for (int i = 0; i < player.getItems().size(); i++) {						// Checks if the Player's inventory contains the Charm of Capitalism. If so, the goldRate is multiplied by ten.
				if (player.getItems().get(i).getID() == ItemID.charm) goldRate = 10;
			}
			int awardedGold = (random.nextInt(10)+1) * goldRate;
			player.increaseGold(awardedGold);
			System.out.println("You kill the monster without mercy. Mercilessly.");
			System.out.println("You take " + damageTaken + "damage! You recieve " + awardedGold + " gold.");
		} else {
			System.out.println("The monster slams a big, pink squid across your skull.");
			System.out.println("Your adventure is over. You got beaten by a squid.");
		}
	}

}
