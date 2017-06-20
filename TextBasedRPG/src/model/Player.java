package model;

import java.util.ArrayList;
import java.util.List;

import model.item.Item;

public class Player {

	private int hp = 100;
	private int gold = 0;
	private List<Item> items = new ArrayList<Item>();
	
	public int getHP() {
		return hp;
	}
	
	public void restoreHP() {
		hp = 100;
	}
	
	public void takeDamage(int damageTaken) {
		hp -= damageTaken;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void increaseGold(int amount) {
		gold += amount;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void addItem (Item item) {
		items.add(item);
	}
}
