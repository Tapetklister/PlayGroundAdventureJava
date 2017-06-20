package model.locations;

import java.util.HashMap;

public class Location {

	private String name;
	private String presentation;
	private HashMap<String, Integer> exitPoints;
	private LocationID id;
	
	public Location (String name, String presentation, HashMap<String, Integer> exitPoints, LocationID id) {
		this.name = name;
		this.presentation = presentation;
		this.exitPoints = exitPoints;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPresentation() {
		return this.presentation;
	}
	
	public LocationID getID() {
		return id;
	}
	
	public HashMap<String, Integer> getExitPoints() {
		return exitPoints;
	}
}
