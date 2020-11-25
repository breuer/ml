package com.exercise.mercadolibre.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {
	
	private double farthestDistance;
	private double closestDistance;
	private double averageDistance;
	
	public Statistics() {}
	
	public Statistics(
			double farthestDistance, 
			double closestDistance, 
			double averageDistance) {
		this.farthestDistance = farthestDistance;
		this.closestDistance = closestDistance;
		this.averageDistance = averageDistance;
		
	}
	
	@JsonProperty("farthest_distance_to_Buenos_Aires_in_kms")
	public double getFarthestDistance() {
		return farthestDistance;
	}
	public void setFarthestDistance(double farthestDistance) {
		this.farthestDistance = farthestDistance;
	}
	@JsonProperty("closest_distance_to_Buenos_Aires_in_kms")
	public double getClosestDistance() {
		return closestDistance;
	}
	public void setClosestDistance(double closestDistance) {
		this.closestDistance = closestDistance;
	}
	@JsonProperty("average_distance_to_Buenos_Aires_in_kms")
	public double getAverageDistance() {
		return averageDistance;
	}
	public void setAverageDistance(double averageDistance) {
		this.averageDistance = averageDistance;
	}

}
