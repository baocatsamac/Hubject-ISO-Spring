package com.hubject.com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubject.com.helper.Utils;
import com.hubject.com.model.ChargingStation;
import com.hubject.com.repository.ChargingStationRepository;

@Service
public class ChargingStationService {
	
	@Autowired
	private ChargingStationRepository chargingStationRepository;

	/**
	 * Add a new charging station
	 * @param station
	 */
	public void addStation(ChargingStation station) {
		chargingStationRepository.save(station);
	}

	/**
	 * Remove one charging station based on user-defined id
	 * @param UID
	 */
	public void removeStation(String UID) {
		chargingStationRepository.deleteById(UID);
	}

	/**
	 * Update data for one existing charging station
	 * @param station
	 */
	public void updateStation(ChargingStation station) {
		chargingStationRepository.save(station);
	}

	/**
	 * Retrieve all current charging stations
	 * @return
	 */
	public List<ChargingStation> getAllStations() {
		List<ChargingStation> stations = new ArrayList<>();
		chargingStationRepository.findAll().forEach(stations::add);
		return stations;
	}
	
	/**
	 * Find a specific charging station based on user-defined id
	 * @param UID
	 * @return
	 */
	public ChargingStation findSpecificStation(String UID) {
		return chargingStationRepository.findById(UID).get();
	}

	/**
	 * Find all the possible charging stations based on a specific ZipCode
	 * @param zipCode
	 * @return
	 */
	public List<ChargingStation> findStationsByZipCode(String zipCode) {
		
		List<ChargingStation> stations = new ArrayList<>();
		chargingStationRepository.findAll().forEach(s -> {
			if (s.getZipCode().equals(zipCode)) {
				stations.add(s);
			}
		});
		return stations;
	}
	
	/**
	 * Find all the possible charging stations within a perimeter around a given GeoLocation
	 * @param perimeter the distance in kilometer unit
	 * @param lat
	 * @param lon
	 * @return
	 */
	public List<ChargingStation> findStationsWithinPerimeter(double perimeter, double lat, double lon) {
		List<ChargingStation> stations = new ArrayList<>();
		chargingStationRepository.findAll().forEach(s -> {
			if (Utils.calculateDistance(lat, lon, s.getLatitude(), s.getLongitude(), null) <= perimeter) {
				stations.add(s);
			}
		});
		return stations;
	}

}
