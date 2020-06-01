package com.hubject.com.helper;

import java.util.concurrent.atomic.AtomicInteger;

public class Utils {
	private static AtomicInteger counter;
	
	public static AtomicInteger getIdCounterInstance() {
		if (counter == null) {
			counter = new AtomicInteger();
		}
		return counter;
	}
	
	/**
	 * Calculate the distance between two GeoLocation based on their latitude and longitude
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @param unit Miles by default, "K" -> Kilometers, "N" -> Nautical Miles 
	 * @return
	 */
	public static double calculateDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit != null) {
				if (unit.equals("K")) {
					dist = dist * 1.609344;
				} else if (unit.equals("N")) {
					dist = dist * 0.8684;
				}
			}
			
			return (dist);
		}
	}

}
