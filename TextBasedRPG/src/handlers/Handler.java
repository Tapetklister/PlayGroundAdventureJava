package handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Player;

import model.locations.*;

public class Handler {

	public Player player = new Player();
	private List<Location> locations = new ArrayList<Location>();
	private HashMap<Integer, HashMap<String, Integer>> locDirections = new HashMap<Integer, HashMap<String, Integer>>();
	
	public Handler() {
		setupDirections();
		setupLocations();
	}
	
	public Location getLocation (LocationID id) {												// Returns a location from the location list based on an ID
		
		for (int i = 0; i < locations.size(); i++) {
			if (locations.get(i).getID().equals(id)) {
				return locations.get(i);
			}
		}
		return null;
	}
	
	public Location goToLocation (LocationID id, String direction) {							// Takes in the current location's ID and a direction, and if possible, returns a new direction based on the arguments
		
		for (int i = 0; i < locations.size(); i++) {
			if (locations.get(i).getID().equals(id) && locations.get(i).getExitPoints().containsKey(direction)) { // If the player can move in the desired direction, the destionation's ID is returned
				int destinationID = locations.get(id.ordinal()).getExitPoints().get(direction);
				return locations.get(destinationID);
			}
		}
		return locations.get(id.ordinal());
	}
	
	public void setupDirections() {
		
		HashMap<String, Integer> directionMap1 = new HashMap<String, Integer>();				// These HashMaps represents direction. Each locations needs at least one of those
		directionMap1.put("west", 1);
		directionMap1.put("east", 2);
		directionMap1.put("south", 3);
		directionMap1.put("north", 4);
		
		HashMap<String, Integer> directionMap2 = new HashMap<String, Integer>();
		directionMap2.put("east", 0);
		
		HashMap<String, Integer> directionMap3 = new HashMap<String, Integer>();
		directionMap3.put("west", 0);
		
		HashMap<String, Integer> directionMap4 = new HashMap<String, Integer>();
		directionMap4.put("north", 0);
		
		locDirections.put(0, directionMap1);
		locDirections.put(1, directionMap2);
		locDirections.put(2, directionMap3);
		locDirections.put(3, directionMap4);
	}
	
	public void setupLocations() {
		
		locations.add(new Location("the Crossroads", "You are standing at the Crossroads. The sun is shining down throught the foilage", locDirections.get(0), LocationID.crossroads));
		locations.add(new Plains("the Plains", "You are standing on the Plains of Grinding. Monsters are lined up in a queue, staring at you.", locDirections.get(1), LocationID.plains));
		locations.add(new Shop("the Shop", "You enter the conventiently placed Hero's Shop. A shopkeeper is staring at you.", locDirections.get(2), LocationID.shop));
		locations.add(new Location("the Pool of Rejuvenation", "You enter the Pool of Rejuventaion. You feel power surge through you.", locDirections.get(3), LocationID.pool));
		locations.add(new Location("the Cave", "You run straight into the cave, shouting out your own name, swinging your sword above your head.", locDirections.get(4), LocationID.cave));
	}
}
