package observer;

import java.util.Observable;

public class TObservable extends Observable{
	
	private String name;
	private int id;
	
	public void setId(int id) {
		this.id = id;
		setChanged();
		notifyObservers(id);
	}
	public void setName(String name) {
		this.name = name;
		setChanged();
		notifyObservers(name);
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	
}
