package com.exercise.mercadolibre.helper;

public class HaversineFormula {

	private static double BS_AS_LAT = -34.61315;
	private static double BS_AS_LON = -58.37723;
	
	public static int calculateDistanceByHaversineFormula(double lon1, double lat1,
			double lon2, double lat2) {

		double earthRadius = 6371; // km

		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);

		double dlon = (lon2 - lon1);
		double dlat = (lat2 - lat1);

		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);

		double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlon*sinlon);
			
		double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

		//double distanceInMeters = earthRadius * c * 1000;
		double distanceInKilometers = earthRadius * c;

		return (int)distanceInKilometers;
	}
	
	public static int distanceBetweenBuenosAiresInKilometers(double lon, double lat) {
		
		return calculateDistanceByHaversineFormula(BS_AS_LON, BS_AS_LAT, lon, lat);
	}
}
