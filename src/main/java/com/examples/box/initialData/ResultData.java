package com.examples.box.initialData;

import java.util.List;

import com.example.easynotes.model.Location;

public class ResultData {
	
	List<String> actions;
	List<String> boxState;
	Location currentLocation;
	public List<String> getActions() {
		return actions;
	}
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
	public List<String> getBoxState() {
		return boxState;
	}
	public void setBoxState(List<String> boxState) {
		this.boxState = boxState;
	}
	public Location getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	

}
