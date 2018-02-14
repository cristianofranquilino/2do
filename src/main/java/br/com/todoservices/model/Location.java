package br.com.todoservices.model;

public class Location {

	private String type;
	private double[] coordinates;
	
	public Location() {}
	
	public Location(String type, double latitude, double longitude) {
		this.type = type;
		coordinates[0] = latitude;
		coordinates[1] = longitude;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double[] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	
}
