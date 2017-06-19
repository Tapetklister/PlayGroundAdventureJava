package handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Player;
import locations.Shop;
import locations.Location;
import locations.LocationID;
import locations.Plains;

public class Handler {

	public Player player;
	private List<Location> locations;
	private HashMap<Integer, HashMap<String, Integer>> locDirections;
	
	public Handler() {
		
		player = new Player();
		setupDirections();
		this.locations = new ArrayList<Location>();
	}
	
	public Location getLocation (LocationID id) {
		return locations.get(id.ordinal());
	}
	
	public Location goToLocation (LocationID id, String direction) {
		
		if (locations.get(id.ordinal()).getExitPoints().containsKey(direction)) {
			int destinationID = locations.get(id.ordinal()).getExitPoints().get(direction);
			return locations.get(destinationID);
		} else {
			System.out.println("You could not go there. Try another direction.");
		}
		return locations.get(id.ordinal());
	}
	
	public void setupDirections() {
		
		locDirections = new HashMap<Integer, HashMap<String, Integer>>();
		
		HashMap<String, Integer> directionMap1 = new HashMap<String, Integer>();
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
