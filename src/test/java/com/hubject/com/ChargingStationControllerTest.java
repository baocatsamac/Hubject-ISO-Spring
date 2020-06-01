package com.hubject.com;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.hubject.com.controller.ChargingStationController;
import com.hubject.com.model.ChargingStation;
import com.hubject.com.service.ChargingStationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ChargingStationController.class)
public class ChargingStationControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ChargingStationService service;
	
	@Test
	public void getAllChargingStationsTest() throws Exception{
		ChargingStation guckelsbergStation = new ChargingStation("Guckelsberg", 49.261387, 7.777777,"66125");
		
		List<ChargingStation> stations = Arrays.asList(guckelsbergStation);
		
		given(service.getAllStations()).willReturn(stations);
		
		mvc.perform(get("/stations")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].uid", is(guckelsbergStation.getUID())));
	}
	
	@Test
	public void findStationByZipCodeTest() throws Exception{
		ChargingStation guckelsbergStation = new ChargingStation("Guckelsberg", 49.261387, 7.777777,"66125");
		ChargingStation sulzbachStation = new ChargingStation("Sulzbach", 49.361387, 7.92367,"66111");
		
		List<ChargingStation> stations = Arrays.asList(guckelsbergStation, sulzbachStation);
		
		given(service.getAllStations()).willReturn(stations);
		given(service.findStationsByZipCode("66125")).willReturn(Arrays.asList(guckelsbergStation));
		
		mvc.perform(get("/stations?zipcode=66125")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].uid", is(guckelsbergStation.getUID())));
	}
}
