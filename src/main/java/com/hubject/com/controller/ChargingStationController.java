package com.hubject.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hubject.com.model.ChargingStation;
import com.hubject.com.service.ChargingStationService;

@RestController
public class ChargingStationController {
	
	@Autowired
	private ChargingStationService chargingService;
	
	@GetMapping("/stations")
	public List<ChargingStation> getAllChargingStations() {
		return chargingService.getAllStations();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/stations")
	public void addChargingStation(@RequestBody ChargingStation station) {
		chargingService.addStation(station);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/stations")
	public void updateChargingStation(@RequestBody ChargingStation station) {
		chargingService.updateStation(station);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/stations", params = {"uid"})
	public void updateChargingStation(@RequestParam String uid) {
		chargingService.removeStation(uid);
	}
	
	@GetMapping(value = "/stations", params = {"uid"})
	public ChargingStation findSpecificStation(@RequestParam(value= "uid") String UID) {
		return chargingService.findSpecificStation(UID);
	}
	
	@GetMapping(value = "/stations", params = {"zipcode"})
	public List<ChargingStation> findChargingStationsByZipCode(@RequestParam(value= "zipcode")  String zipCode) {
		return chargingService.findStationsByZipCode(zipCode);
	}
	
	@RequestMapping(value = "/stations", params = {"perimeter", "lat", "lon"})
	public List<ChargingStation> findChargingStationsWithinPerimeter(@RequestParam(value= "perimeter")  double perimeter,
			@RequestParam(value= "lat") double latitude, @RequestParam(value= "lon") double longitude) {
		return chargingService.findStationsWithinPerimeter(perimeter, latitude, longitude);
	}

}
